package com.example.royrine.jokemon.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.royrine.jokemon.R
import com.example.royrine.jokemon.model.Score
import kotlinx.android.synthetic.main.rank_item.view.*

class ScoreAdapter(private val scores: List<Score>,
                   private val context: Context,
                   val listener: (Score) -> Unit): Adapter<ScoreAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position:Int){
     val note = scores[position]
        holder?.let{
            it.bindView(note,listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rank_item, parent, false)
        return ViewHolder(view) }

    override fun getItemCount(): Int {
        return scores.size }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(scores: Score,
                     listener: (Score) -> Unit) = with(itemView) {
            val txtnome = txt_playername
            val txtscore = txt_playerscore

            txtnome.text = scores.name
            txtscore.text = scores.score

            setOnClickListener { listener(scores) } }
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }
}