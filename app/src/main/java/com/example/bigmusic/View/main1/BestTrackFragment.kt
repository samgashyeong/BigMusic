package com.example.bigmusic.View.main1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bigmusic.R
import com.example.bigmusic.View.MainViewModel
import com.example.bigmusic.View.main1.adapter.TrackAdapter
import com.example.bigmusic.databinding.FragmentBestTrackBinding

class BestTrackFragment : Fragment() {

    private lateinit var binding : FragmentBestTrackBinding
    private lateinit var vM : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_best_track, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vM = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        vM.bestTrack.observe(requireActivity(), Observer {
            binding.recycler.adapter = vM.bestTrack.value?.let { it1 -> TrackAdapter(it1) }
        })
    }
}