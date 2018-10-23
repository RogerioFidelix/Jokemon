package com.example.royrine.jokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.royrine.jokemon.adapter.ScoreAdapter
import com.example.royrine.jokemon.api.getScoreAPI
import com.example.royrine.jokemon.model.ScoreResponse
import kotlinx.android.synthetic.main.activity_rank.*
import retrofit2.Call
import retrofit2.Response

class RankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)
        supportActionBar!!.title = "Go Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getScore()
    }

    fun exibeMensagemErro(t: Throwable?){
        Toast.makeText(this,
                t?.message,
                Toast.LENGTH_LONG).show()
    }

    fun getScore(){
        getScoreAPI().listaScore()
                .enqueue(object:retrofit2.Callback<List<ScoreResponse>>{
                    override fun onFailure(call: Call<List<ScoreResponse>>?, t: Throwable?) {
                        exibeMensagemErro(t)
                    }

                    override fun onResponse(call: Call<List<ScoreResponse>>?, response: Response<List<ScoreResponse>>?) {
                        if(response!!.isSuccessful){
                            response?.body()?.let {
                                val score: List<ScoreResponse> = it
                                exibeNaLista(score)
                            }
                        }
                    }
                })
    }

    fun exibeNaLista(score: List<ScoreResponse>){
        rvScoreData.adapter = ScoreAdapter(score,
                this, {
            Toast.makeText(this, "O jogador: " + it.name +
                    " tem um total de " + it.score + " pontos.",Toast.LENGTH_LONG).show()
        })

        rvScoreData.layoutManager = LinearLayoutManager(this)
    }
}
