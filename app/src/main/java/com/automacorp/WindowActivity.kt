package com.automacorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WindowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val param = intent.getStringExtra(MainActivity.WINDOW_NAME_PARAM)
        val windowName = findViewById<TextView>(R.id.txt_window_name)
        windowName.text = param
    }
}