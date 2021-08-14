package com.example.bigmusic.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bigmusic.View.main1.BestTrackFragment
import com.example.bigmusic.View.main2.BestArtistFragment
import com.example.bigmusic.View.main3.BestTagFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->
                BestTrackFragment()
            1->
                BestArtistFragment()
            2->
                BestTagFragment()

            else -> BestTrackFragment()
        }
    }

}