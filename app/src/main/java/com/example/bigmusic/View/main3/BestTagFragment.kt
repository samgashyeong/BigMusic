package com.example.bigmusic.View.main3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bigmusic.R
import com.example.bigmusic.View.MainViewModel
import com.example.bigmusic.View.main3.adapter.BestTagAdapter
import com.example.bigmusic.databinding.FragmentBestTagBinding

class BestTagFragment : Fragment() {
    private lateinit var binding: FragmentBestTagBinding
    private lateinit var vM : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_best_tag, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vM = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        vM.bestTag.observe(requireActivity(), {
            binding.tagRecycler.adapter = BestTagAdapter(it)
        })
    }
}