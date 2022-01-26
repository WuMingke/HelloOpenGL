package com.example.helloopengl

import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyRender : GLSurfaceView.Renderer {
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) { // 只要onResume就会被调用
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) { // Surface 尺寸发生变化
        glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) { // 绘制一帧
        glClear(GL_COLOR_BUFFER_BIT)
    }

}