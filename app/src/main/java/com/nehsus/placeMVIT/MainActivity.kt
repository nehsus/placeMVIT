package com.nehsus.placeMVIT

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Handler
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

import com.nehsus.placeMVIT.R

class MainActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val actionBar = supportActionBar
        actionBar?.hide()

        Handler().postDelayed({
            val startActivityIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(startActivityIntent)
            this@MainActivity.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
