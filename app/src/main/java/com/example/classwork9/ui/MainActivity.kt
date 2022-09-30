package com.example.classwork9.ui

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.classwork9.common.Consts
import com.example.classwork9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var currentPosition = 0
    private var text: String = ""
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        Adapter().apply {
            listener = {
                if (currentPosition != -1) {
                    text += it.toString()
                    dotsList[currentPosition].backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#00FF00"))
                    if (currentPosition == 3) {
                        if (text == Consts.PIN) {
                            Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                            currentPosition = -1
                        } else {
                            text = ""
                            currentPosition = 0
                            dotsList.forEach { view ->
                                view.backgroundTintList =
                                    ColorStateList.valueOf(Color.parseColor("#808080"))
                            }
                        }
                    } else {
                        currentPosition++
                    }
                }
            }
            backSpaceListener = {
                if (currentPosition != 0 && currentPosition != -1) {
                    currentPosition--
                    text = text.subSequence(0, text.length - 1).toString()
                    dotsList[currentPosition].backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#808080"))
                }
            }
        }
    }
    private lateinit var dotsList: List<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            dotsList = listOf(dot1, dot2, dot3, dot4)
        }
        initRecycler()
    }

    private fun initRecycler() {
        binding.rec.adapter = adapter
        binding.rec.layoutManager = GridLayoutManager(applicationContext, 3)
    }

}