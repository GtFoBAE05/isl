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
import com.example.isl.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

     val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var searchView: SearchView
    private lateinit var randomBtn: ImageButton

    lateinit var randomWordTv : TextView
    lateinit var randomImg : ImageView
    lateinit var randomDescTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()

        searchView = binding.searchView
        randomBtn = binding.randomBtn

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                getSign(p0.toString())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        randomBtn.setOnClickListener {
            val modalSheetView = layoutInflater.inflate(R.layout.fragment_bottom_sheet,null)
            val dialog = BottomSheetDialog(this)

            randomWordTv = modalSheetView.findViewById<TextView>(R.id.randomWordTv)
            randomImg = modalSheetView.findViewById<ImageView>(R.id.randomImageView)
            randomDescTv = modalSheetView.findViewById<TextView>(R.id.randomDescTv)

            getRandomSign()

            dialog.setContentView(modalSheetView)
            dialog.show()
        }



    }

    fun getRandomSign(){
        instance.instances.getRandomSign().enqueue(object : Callback<Sign> {
            override fun onResponse(call: Call<Sign>, response: Response<Sign>) {
                randomWordTv.setText(response.body()!!.name)
                println(response.body()!!.name)
                randomDescTv.setText(response.body()!!.description)
                val imgUrl = response.body()!!.imageUrl
                Glide.with(this@MainActivity).load(imgUrl).into(randomImg)

            }

            override fun onFailure(call: Call<Sign>, t: Throwable) {
                println(t)
            }
        })
    }

    fun getSign(cari:String){
        instance.instances.getSign(cari).enqueue(object :Callback<Sign> {
            override fun onResponse(call: Call<Sign>, response: Response<Sign>) {
                val intent = Intent(this@MainActivity, wordDetailActivity::class.java)
                intent.putExtra("cari", cari)

                startActivity(intent)

            }

            override fun onFailure(call: Call<Sign>, t: Throwable) {
                println(t)
            }
        })
    }


}