package com.example.bigmusic.View.search.Info.artistAndTrack.adapter.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Info.ArtistData.Similar.Artist
import com.example.bigmusic.R
import kotlin.math.roundToInt

class  InfoArtistSimilarAdapter(val DataList:ArrayList<Artist>): RecyclerView.Adapter<InfoArtistSimilarAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val similarText = itemView.findViewById<TextView>(R.id.similarNameText)
        val image = itemView.findViewById<ImageView>(R.id.singerImage)
        val similarPersent = itemView.findViewById<TextView>(R.id.similarPercentText)
        val aritstNameText = itemView.findViewById<TextView>(R.id.artistNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_similar, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //ex)holder.(홀더클래스변수).text = DataList[position].name
        Glide.with(holder.itemView).load(DataList[position].image[2].text).into(holder.image)
        holder.similarText.text = DataList[position].name
        holder.similarPersent.text = "${(DataList[position].match.toDouble() * 100).roundToInt()}% 일치함"
        holder.aritstNameText.visibility = View.INVISIBLE
    }
    override fun getItemCount() = DataList.size

}