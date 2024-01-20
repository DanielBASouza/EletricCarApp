package br.com.danielsouza.eletriccarapp.data

import br.com.danielsouza.eletriccarapp.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {
    @GET("cars.json")
    fun getAllCars(): Call<List<Carro>>
}