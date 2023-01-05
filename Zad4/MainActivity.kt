package com.example.listazadn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = Fragment1()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout, firstFragment)
            commit()
        }
    }

}