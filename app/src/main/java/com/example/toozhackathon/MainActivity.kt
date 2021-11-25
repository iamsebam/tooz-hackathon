package com.example.toozhackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.LinearLayout
import tooz.bto.common.Constants
import tooz.bto.toozifier.ToozifierFactory

class MainActivity : AppCompatActivity() {
    val toozifier = ToozifierFactory.getInstance()
    lateinit var toozPromptView: LinearLayout
    lateinit var toozFocusView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toozifier.register(this, "appName", EmptyRegistrationListener())
    }

    override fun onResume() {
        super.onResume()
        // straight on resume yealds no result
        Handler(Looper.getMainLooper()).postDelayed({ setToozView() }, 100)
    }

    override fun onDestroy() {
        toozifier.deregister()
        super.onDestroy()
    }

    private fun setToozView() {
        toozPromptView = LayoutInflater.from(this).inflate(R.layout.tooz_prompt_layout, null) as LinearLayout
        toozFocusView = LayoutInflater.from(this).inflate(R.layout.tooz_focus_layout, null) as LinearLayout

        toozifier.updateCard(toozPromptView, toozFocusView, Constants.FRAME_TIME_TO_LIVE_FOREVER)
    }
}