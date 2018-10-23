package com.example.royrine.jokemon.api



import com.example.royrine.jokemon.model.ScoreResponse
import retrofit2.Call
import retrofit2.http.GET

interface scoreAPI{
    @GET("pontuacao")
    fun listaScore(): Call<List<ScoreResponse>>
}