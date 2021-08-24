package com.example.bigmusic.View.search.Info.tag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Info.Tag.Track.Track
import com.example.bigmusic.R

class  TagTrackAdapter(val DataList:ArrayList<Track>): RecyclerView.Adapter<TagTrackAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val item_tag_track = itemView.findViewById<TextView>(R.id.item_tag_track)
        val item_tag_artist = itemView.findViewById<TextView>(R.id.item_tag_artist)
        val trackImage = itemView.findViewById<ImageView>(R.id.trackImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_item_track_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //ex)holder.(홀더클래스변수).text = DataList[position].name
        holder.item_tag_artist.text = DataList[position].artist.name
        holder.item_tag_track.text = DataList[position].name
        Glide.with(holder.itemView)
            .load(DataList[position].image[2].text)
            .error(R.drawable.icon)
            .placeholder(R.drawable.icon)
            .into(holder.trackImage)
    }
    override fun getItemCount() = DataList.size

}