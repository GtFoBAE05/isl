package com.example.isl

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.isl.api.Sign
import com.example.isl.api.instance
import com.example.isl.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModalBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetBinding

    lateinit var word: TextView
    lateinit var img: ImageView
    lateinit var desc: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        word = binding.randomWordTv
        img = binding.randomImageView
        desc = binding.randomDescTv

        getRandomSign()

    }





    fun getRandomSign(){
        instance.instances.getRandomSign().enqueue(object : Callback<Sign> {
            override fun onResponse(call: Call<Sign>, response: Response<Sign>) {
                word.setText("aaa"+response.body()!!.name)
                println(response.body()!!.name)
                desc.setText(response.body()!!.description)
                val imgUrl = response.body()!!.imageUrl
                Glide.with(this@ModalBottomSheet).load(imgUrl).into(img)

            }

            override fun onFailure(call: Call<Sign>, t: Throwable) {
                println("aaaa")
                println(t)
            }
        })
    }

}