package com.example.isl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.isl.api.Sign
import com.example.isl.api.instance
import com.example.isl.databinding.ActivityWordDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class wordDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityWordDetailBinding

    lateinit var backBtn: ImageButton
    lateinit var searchView: SearchView

    lateinit var word: TextView
    lateinit var pronounce: TextView
    lateinit var img: ImageView
    lateinit var description: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()

        backBtn = binding.backBtn
        searchView = binding.searchView
        word = binding.wordTv
        pronounce = binding.pronounTv
        img = binding.signImageView
        description = binding.desciptionTv

        backBtn.setOnClickListener {
            startActivity(Intent(this@wordDetailActivity, MainActivity::class.java))
        }

        val intent = getIntent()
        val cari = intent.getStringExtra("cari")
        searchView.setQuery(cari,true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                getSign(p0.toString())
                return false
            }
        })

    }

    override fun onStart() {
        super.onStart()
        val intent = getIntent()
        val cari = intent.getStringExtra("cari")
        getSign(cari!!)
    }

    fun getSign(cari:String){
        instance.instances.getSign(cari).enqueue(object : Callback<Sign> {
            override fun onResponse(call: Call<Sign>, response: Response<Sign>) {
                word.setText(response.body()!!.name)
                description.setText(response.body()!!.description)
                val imgUrl = response.body()!!.imageUrl
                Glide.with(this@wordDetailActivity).load(imgUrl).into(img)

            }

            override fun onFailure(call: Call<Sign>, t: Throwable) {
                println(t)
            }
        })
    }
}