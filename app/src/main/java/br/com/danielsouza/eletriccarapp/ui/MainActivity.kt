package br.com.danielsouza.eletriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.danielsouza.eletriccarapp.R
import br.com.danielsouza.eletriccarapp.data.CarFactory
import br.com.danielsouza.eletriccarapp.ui.adapter.CarAdapter

class MainActivity : AppCompatActivity() {
    lateinit var btnCalcular: Button
//    lateinit var ivCarRedirect: ImageView
    lateinit var listaCarros: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
        setupList()
    }

    fun setupViews() {
        btnCalcular = findViewById(R.id.btn_calcular)
//        ivCarRedirect = findViewById(R.id.iv_image)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }

    fun setupList() {
        var dados = CarFactory.list
        val adapter = CarAdapter(dados)

        listaCarros.adapter = adapter
    }


    fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
//        ivCarRedirect.setOnClickListener {
//            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
//        }
    }
}