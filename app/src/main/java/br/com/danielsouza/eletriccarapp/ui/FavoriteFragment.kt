package br.com.danielsouza.eletriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.danielsouza.eletriccarapp.R
import br.com.danielsouza.eletriccarapp.data.local.CarRepository
import br.com.danielsouza.eletriccarapp.domain.Carro
import br.com.danielsouza.eletriccarapp.ui.adapter.CarAdapter

class FavoriteFragment : Fragment() {
    lateinit var listaCarrosFavoritos: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    override fun onResume() {
        super.onResume()
        setupList()
    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }

    fun setupList(){
        val cars = getCarsOnLocalDb()
        val carroAdapter = CarAdapter(cars, true)

        listaCarrosFavoritos.apply {
            visibility = View.VISIBLE
            adapter = carroAdapter
        }

        carroAdapter.carItemListener = {carro ->
            val isDeleted = CarRepository(requireContext()).deleteCarById(carro.id)
            setupList()
        }
    }

    fun setupView(view: View){
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)
        }
    }
}