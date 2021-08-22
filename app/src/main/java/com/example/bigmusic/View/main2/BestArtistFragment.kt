package com.example.bigmusic.View.main2

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bigmusic.R
import com.example.bigmusic.View.MainViewModel
import com.example.bigmusic.View.main2.adater.BestArtistAdapter
import com.example.bigmusic.View.main2.adater.BestArtistKrAdapter
import com.example.bigmusic.View.search.Info.InfoAritstActivity
import com.example.bigmusic.databinding.FragmentBestArtistBinding

class BestArtistFragment : Fragment() {
    private lateinit var binding : FragmentBestArtistBinding
    private lateinit var vM : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_best_artist, container, false)

        binding.sortBtn.setOnClickListener {
            when(binding.sortBtn.text){
                "한국 기준" ->{
                    binding.recycler.adapter = vM.bestArtistKr.value?.let { it1 ->
                        BestArtistKrAdapter(
                            it1
                        ){
                            startActivity(Intent(requireContext(), InfoAritstActivity::class.java)
                                .putExtra("artist", it))
                        }
                    }
                    binding.sortBtn.text="세계 기준"
                }
                "세계 기준"->{
                    binding.recycler.adapter = vM.bestArtist.value?.let { it1 ->
                        BestArtistAdapter(
                            it1
                        ){
                            startActivity(Intent(requireContext(), InfoAritstActivity::class.java)
                                .putExtra("artist", it))
                        }
                    }
                    binding.sortBtn.text="한국 기준"
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vM = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        vM.bestArtist.observe(requireActivity(), {
            Log.d(TAG, "onViewCreated: vM.bestArtist 실행됨")
            binding.recycler.adapter = BestArtistAdapter(it){
                startActivity(Intent(requireContext(), InfoAritstActivity::class.java)
                    .putExtra("artist", it))
            }
        })
    }
}