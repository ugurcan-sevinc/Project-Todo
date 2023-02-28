package com.ugrcaan.todoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ugrcaan.todoproject.databinding.ActivityMainBinding
import com.ugrcaan.todoproject.vm.MainVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}