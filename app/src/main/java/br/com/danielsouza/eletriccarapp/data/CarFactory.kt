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
        ),
        Carro(
            id = 2,
            preco = "200.000",
            bateria = "200",
            potencia = "200cv",
            recarga = "20",
            urlPhoto = "www.google.com.br",
        ),
        Carro(
            id = 3,
            preco = "300.000",
            bateria = "300",
            potencia = "300cv",
            recarga = "30",
            urlPhoto = "www.google.com.br",
        ),
        Carro(
            id = 4,
            preco = "400.000",
            bateria = "400",
            potencia = "400cv",
            recarga = "40",
            urlPhoto = "www.google.com.br",
        )
    )

}