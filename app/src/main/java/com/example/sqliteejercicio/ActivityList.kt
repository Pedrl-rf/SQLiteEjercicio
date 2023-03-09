package com.example.sqliteejercicio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteejercicio.databinding.ActivityMainBinding


class ActivityList : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    lateinit var contactosHelper: miOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactosHelper = miOpenHelper(this)

    }
}