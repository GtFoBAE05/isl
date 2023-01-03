package com.example.isl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import com.example.isl.api.Sign
import com.example.isl.api.instance
import com.example.isl.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

     val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private lateinit var searchView: SearchView
    private lateinit var randomBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()

        searchView = binding.searchView
        randomBtn = binding.randomBtn




    }

    override fun onStart() {
        super.onStart()
        getResponse()
    }

    fun getResponse(){
        instance.instances.getRandomSign().enqueue(object : Callback<Sign> {
            override fun onResponse(call: Call<Sign>, response: Response<Sign>) {
            }

            override fun onFailure(call: Call<Sign>, t: Throwable) {
                println(t)
            }
        })
    }


}