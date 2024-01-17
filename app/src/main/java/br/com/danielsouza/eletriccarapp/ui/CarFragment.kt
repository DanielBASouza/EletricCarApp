package br.com.danielsouza.eletriccarapp.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.danielsouza.eletriccarapp.R
import br.com.danielsouza.eletriccarapp.data.CarFactory
import br.com.danielsouza.eletriccarapp.domain.Carro
import br.com.danielsouza.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.ArrayList

class CarFragment : Fragment() {
    lateinit var fabCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView

    var carrosArray : ArrayList<Carro> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListeners()
        setupList()
    }

    fun setupView(view: View) {
        view?.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }

    }

    fun setupList() {
        val adapter = CarAdapter(carrosArray)

        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        fabCalcular.setOnClickListener {
            MyTask().execute("https://igorbag.github.io/cars-api/cars.json")
//            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    inner class MyTask : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "Iniciando...")
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000

                var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                publishProgress(response)

            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar processamento...")
            } finally {
                if (urlConnection != null) {
                    urlConnection?.disconnect()
                }
            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")


                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto
                    )
                    carrosArray.add(model)
                    Log.d("Model ->", model.toString())
                }
                setupList()

            } catch (ex: Exception) {
                Log.e("Erro ->", ex.message.toString())
            }
        }

    }
}