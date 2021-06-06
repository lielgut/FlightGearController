package com.example.flightgearcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    private lateinit var throttleBar : SeekBar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        throttleBar = findViewById<SeekBar>(R.id.throttleBar);
    }
}