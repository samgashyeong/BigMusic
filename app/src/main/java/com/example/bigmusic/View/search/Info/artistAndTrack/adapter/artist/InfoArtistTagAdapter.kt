package com.example.bigmusic.View.search.Info.artistAndTrack.adapter.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigmusic.Data.Info.ArtistData.Tag.Tag
import com.example.bigmusic.R

class  InfoArtistTagAdapter(val DataList:ArrayList<Tag>): RecyclerView.Adapter<InfoArtistTagAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val tagText = itemView.findViewById<TextView>(R.id.tagText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_tag, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //ex)holder.(홀더클래스변수).text = DataList[position].name
        holder.tagText.text = "#${DataList[position].name}"
    }
    override fun getItemCount() = DataList.size

}