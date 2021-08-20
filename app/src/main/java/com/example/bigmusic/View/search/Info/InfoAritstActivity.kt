package com.example.bigmusic.View.search.Info

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigmusic.R

class InfoAritstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_aritst)
        
        val a = intent.getSerializableExtra("artist")

        Log.d(TAG, "onCreate: a : $a")
    }
}