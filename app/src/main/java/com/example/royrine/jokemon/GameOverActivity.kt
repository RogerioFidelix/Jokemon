package com.example.royrine.jokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.royrine.jokemon.api.getScoreAPI
import com.example.royrine.jokemon.model.Score
import kotlinx.android.synthetic.main.activity_game_over.*
import retrofit2.Call
import retrofit2.Response

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val scores = intent.extras.getInt("SCORE")

        btn_playagain.setOnClickListener {
            getScoreAPI().postScore(Score(et_playername.text.toString(), scores.toString()))

                    .enqueue(object:retrofit2.Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Toast.makeText(this@GameOverActivity,
                                    "Fail",
                                    Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            Toast.makeText(this@GameOverActivity,
                                    "Sucess",
                                    Toast.LENGTH_SHORT).show()
                        }
                    })
            Handler().postDelayed({
                val Game = Intent(this, GameActivity::class.java)
                startActivity(Game)
                finish()
            }, 2000)
        }

        btn_mainmenu.setOnClickListener {
            val Menu = Intent(this, MenuActivity::class.java)
            startActivity(Menu)
            finish()
        }
    }
}