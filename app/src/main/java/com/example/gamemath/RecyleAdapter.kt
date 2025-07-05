package com.example.gamemath

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyleAdapter(
    var scores: ArrayList<Int>,
    var corrects: ArrayList<Int>,
    var wrongs: ArrayList<Int>,
    var context : Context
) : RecyclerView.Adapter<RecyleAdapter.holder>() {
    class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var score : TextView = itemView.findViewById(R.id.score)
        var correct : TextView = itemView.findViewById(R.id.Correct)
        var wrong : TextView = itemView.findViewById(R.id.wrong)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.hekko,parent,false)
        return holder(view)
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.score.text = scores[position].toString()
        holder.correct.text = corrects[position].toString()
        holder.wrong.text = wrongs[position].toString()
    }

}