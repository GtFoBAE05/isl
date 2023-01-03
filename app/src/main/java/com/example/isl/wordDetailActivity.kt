package com.example.isl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import com.example.isl.databinding.ActivityWordDetailBinding

class wordDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityWordDetailBinding

    lateinit var backBtn: ImageButton
    lateinit var searchView: SearchView

    lateinit var word: TextView
    lateinit var pronounce: TextView

    lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtn = binding.backBtn
        searchView = binding.searchView
        word = binding.wordTv
        pronounce = binding.pronounTv
        description = binding.desciptionTv


    }
}