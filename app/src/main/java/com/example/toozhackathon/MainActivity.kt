package com.example.toozhackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.ImageView
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
        toozPromptView = LayoutInflater.from(this).inflate(R.layout.tooz_prompt_layout, null) as LinearLayout
        toozFocusView = LayoutInflater.from(this).inflate(R.layout.tooz_focus_layout, null) as LinearLayout

        toozifier.register(this, "appName", EmptyRegistrationListener())
    }

    override fun onResume() {
        super.onResume()
        // straight on resume yields no result
        Handler(Looper.getMainLooper()).postDelayed({ setToozView() }, 100)
        Handler(Looper.getMainLooper()).postDelayed({  rotateAndUpdate() }, 200)
    }

    override fun onDestroy() {
        toozifier.deregister()
        super.onDestroy()
    }

    private fun setToozView() {
        toozifier.updateCard(toozPromptView, toozFocusView, Constants.FRAME_TIME_TO_LIVE_FOREVER)
    }

    private fun rotateAndUpdate() {
        toozFocusView.findViewById<ImageView>(R.id.star).apply {
            rotation += 10
        }
        toozifier.updateCard(toozPromptView, toozFocusView, Constants.FRAME_TIME_TO_LIVE_FOREVER)
        Handler(Looper.getMainLooper()).postDelayed({  rotateAndUpdate() }, 100)
    }

}