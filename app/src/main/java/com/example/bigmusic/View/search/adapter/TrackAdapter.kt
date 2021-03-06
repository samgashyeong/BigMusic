package com.example.bigmusic.View.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigmusic.Data.Search.SearchArtist.Artist
import com.example.bigmusic.Data.Search.SearchTrack.SearchTrackData
import com.example.bigmusic.Data.Search.SearchTrack.Track
import com.example.bigmusic.R

class  TrackAdapter(val DataList:ArrayList<Track>, val itemClick: (Track) -> Unit): RecyclerView.Adapter<TrackAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val itemText = itemView.findViewById<TextView>(R.id.searchItemTv)
        init {
            itemView.setOnClickListener {
                itemClick(DataList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_search_list, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //ex)holder.(홀더클래스변수).text = DataList[position].name
        holder.itemText.text = DataList[position].name
    }
    override fun getItemCount() = DataList.size
}