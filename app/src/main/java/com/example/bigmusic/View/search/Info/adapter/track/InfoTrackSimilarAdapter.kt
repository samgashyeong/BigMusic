package com.example.bigmusic.View.search.Info.adapter.track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Info.TrackData.Similare.Track
import com.example.bigmusic.R
import kotlin.math.roundToInt

class  InfoTrackSimilarAdapter(val DataList:ArrayList<Track>): RecyclerView.Adapter<InfoTrackSimilarAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val similarText = itemView.findViewById<TextView>(R.id.similarNameText)
        val image = itemView.findViewById<ImageView>(R.id.singerImage)
        val similarPersent = itemView.findViewById<TextView>(R.id.similarPercentText)
        val aritstNameText = itemView.findViewById<TextView>(R.id.artistNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_similar, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView).load(DataList[position].image[2].text).into(holder.image)
        holder.similarText.text = DataList[position].name
        val persent = (DataList[position].match*100).roundToInt()
        holder.similarPersent.text = "$persent% 일치함"
        holder.aritstNameText.text = DataList[position].artist.name

    }
    override fun getItemCount() = DataList.size
}