package com.bearya.intelliscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bearya.intelliscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = ActivityMainBinding.inflate(layoutInflater)
    }

}