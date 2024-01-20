package br.com.danielsouza.eletriccarapp.data

import br.com.danielsouza.eletriccarapp.domain.Carro

object CarFactory {
    val list = listOf(
        Carro(
            id = 1,
            preco = "100.000",
            bateria = "100",
            potencia = "100cv",
            recarga = "10",
            urlPhoto = "www.google.com.br",
            isFavorite = true
        ),
        Carro(
            id = 2,
            preco = "200.000",
            bateria = "200",
            potencia = "200cv",
            recarga = "20",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
    )

}