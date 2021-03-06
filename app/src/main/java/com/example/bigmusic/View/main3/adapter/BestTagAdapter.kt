package com.example.bigmusic.View.main3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigmusic.Data.Best.BestTag.Tag
import com.example.bigmusic.R

class  BestTagAdapter(val DataList:ArrayList<Tag>, private val OnClick : (data : Tag)->Unit): RecyclerView.Adapter<BestTagAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val tagText = itemView.findViewById<TextView>(R.id.tagText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_tag_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        view.setOnClickListener {
            OnClick(DataList[myViewHolder.adapterPosition])
        }
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //ex)holder.(홀더클래스변수).text = DataList[position].name
        holder.tagText.text = "#${DataList[position].name}"
    }
    override fun getItemCount() = DataList.size

}