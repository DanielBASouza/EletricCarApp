package br.com.danielsouza.eletriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.danielsouza.eletriccarapp.R
import br.com.danielsouza.eletriccarapp.domain.Carro

class CarAdapter(private val carros: List<Carro>, private val isFavoriteScreen: Boolean) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemListener: (Carro) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }

    //Pega o conteudo da view e troca pela info de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            preco.text = carros[position].preco
            bateria.text = carros[position].bateria
            potencia.text = carros[position].potencia
            recarga.text = carros[position].recarga
            if(isFavoriteScreen){
                holder.favorito.setImageResource(R.drawable.ic_star_selected)
            }
            favorito.setOnClickListener{
                val carro = carros[position]
                carItemListener(carro)
                setupFavorite(carro, holder)
            }
        }

    }

    private fun setupFavorite(
        carro: Carro,
        holder: ViewHolder
    ) {
        carro.isFavorite = !carro.isFavorite
        if (carro.isFavorite) {
            holder.favorito.setImageResource(R.drawable.ic_star_selected)
        } else {
            holder.favorito.setImageResource(R.drawable.ic_star)
        }
    }

    override fun getItemCount(): Int = carros.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        val favorito: ImageView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                bateria = findViewById(R.id.tv_bateria_value)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_value)
                favorito = findViewById(R.id.iv_favorite)
            }
        }
    }
}

