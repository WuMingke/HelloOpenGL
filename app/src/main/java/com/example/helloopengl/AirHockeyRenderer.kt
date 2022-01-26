package com.example.helloopengl

import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class AirHockeyRenderer : GLSurfaceView.Renderer {

    companion object {
        val POSITION_COMPONENT_COUNT = 2
        val BYTES_PER_FLOAT = 4
    }

    private var vertexData: FloatBuffer? = null

    init {
        // 数据
        val tableVertices = floatArrayOf(0f, 0f, 0f, 14f, 9f, 14f, 9f, 0f) // 顶点属性
        val tableVerticesWithTriangles = floatArrayOf(
            0f, 0f, 9f, 14f, 0f, 14f,
            0f, 0f, 9f, 0f, 9f, 14f,
            0f, 7f, 9f, 7f,
            4.5f, 2f, 4.5f, 12f
        )

        vertexData = ByteBuffer
            .allocateDirect(tableVerticesWithTriangles.size * BYTES_PER_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        vertexData?.put(tableVerticesWithTriangles)

        // 着色器
        // 顶点着色器、片段着色器 ---->写到帧缓冲区  ===》管道

    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    }

    override fun onDrawFrame(gl: GL10?) {
    }
}