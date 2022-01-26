package com.example.helloopengl

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var rendererSet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val version = (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)?.deviceConfigurationInfo?.reqGlEsVersion ?: 0

        val support = version >= 0x2000

        if (support) {
            gl_view?.setEGLContextClientVersion(2)
//            gl_view?.setRenderer(MyRender())
            gl_view?.setRenderer(AirHockeyRenderer(this))
            rendererSet = true
        } else {
            rendererSet = false
            Toast.makeText(this, "not support", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (rendererSet) {
            gl_view?.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (rendererSet) {
            gl_view?.onPause()
        }
    }
}