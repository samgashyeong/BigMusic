package com.example.bigmusic.View.main2.adater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Best.BestArtist.Artist
import com.example.bigmusic.Data.Best.BestTrackKr.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.ReturnK

class  BestArtistAdapter(val DataList:ArrayList<Artist>, private val onClick : (data : Artist)->Unit): RecyclerView.Adapter<BestArtistAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val trackText = itemView.findViewById<TextView>(R.id.trackText)
        val rankText = itemView.findViewById<TextView>(R.id.rankText)
        val trackImage = itemView.findViewById<ImageView>(R.id.imageView)
        val playCountText = itemView.findViewById<TextView>(R.id.playCountText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_best_artist_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        view.setOnClickListener {
            onClick.invoke(DataList[myViewHolder.adapterPosition])
        }
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.trackText.text = DataList[position].name
        holder.rankText.text = position.plus(1).toString()
        Glide.with(holder.itemView)
            .load(DataList[position].image[2].text)
            .into(holder.trackImage)
        val playCount = ReturnK(DataList[position].playcount.toLong()).formatNumber(DataList[position].playcount.toLong())
        holder.playCountText.text = playCount
    }
    override fun getItemCount() = DataList.size

}