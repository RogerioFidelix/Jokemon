package com.example.royrine.jokemon.api



import com.example.royrine.jokemon.model.Score
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface scoreAPI{
    @GET("pontuacao")
    fun listaScore(): Call<List<Score>>

    @POST("pontuacao")
    fun postScore(@Body score: Score): Call<Void>
}