package com.example.royrine.jokemon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*


class GameActivity : AppCompatActivity() {

    private val delayTela = 1000L
    private var numeroAleatorio: Random?=null
    var score:Int = 0
    var plays:Int = 0
    private var FOGO = 1
    private var GRAMA = 2
    private var AGUA = 3

    private fun win(){
        score+=2
        plays++
        tvResult!!.text = "Winner"
        //tvResult!!.setTextColor(ContextCompat.getColor(this,R.color.loss))
        tvPoints!!.text = score.toString()
        tvTries!!.text = plays.toString()
    }

    private fun loss(){
        plays++
        tvResult!!.text = "Loser"
        tvTries!!.text = plays.toString()
        Handler().postDelayed({

            val gameOverScreenie = Intent(this,GameOverActivity::class.java)
            gameOverScreenie.putExtra("SCORE", score)
            startActivity(gameOverScreenie)
            finish()
        },delayTela)

    }

    private fun draw(){
        score++
        plays++
        tvResult!!.text = "Almost"
        tvPoints!!.text = score.toString()
        tvTries!!.text = plays.toString()
    }

    private fun makePlay(playerPlay:Int){

        var PC = numeroAleatorio!!.nextInt(3)+1

        when (PC){
            FOGO ->{
                ivResultPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.fire))
                when (playerPlay){
                    GRAMA -> loss()
                    FOGO -> draw()
                    AGUA -> win()
                }
            }

            GRAMA -> {
                ivResultPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.grass))
                when(playerPlay){
                    GRAMA -> draw()
                    FOGO -> win()
                    AGUA -> loss()
                }
            }

            AGUA -> {
                ivResultPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.water))
                when(playerPlay){
                    GRAMA -> win()
                    FOGO -> loss()
                    AGUA -> draw()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar!!.title = "Go Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        tvPoints!!.text = score.toString()
        tvTries!!.text = plays.toString()

        numeroAleatorio=Random()

        ivFire.setOnClickListener {
            ivPlayerResult!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.grass))
            makePlay(FOGO)
        }

        ivGrass.setOnClickListener {
            ivPlayerResult!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.grass))
            makePlay(GRAMA)
        }

        ivWater.setOnClickListener {
            ivPlayerResult!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.water))
            makePlay(AGUA)
        }

    }
}
