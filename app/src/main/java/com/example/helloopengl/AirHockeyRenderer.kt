package com.example.helloopengl

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class AirHockeyRenderer(val context: Context) : GLSurfaceView.Renderer {

    companion object {
        val POSITION_COMPONENT_COUNT = 2
        val BYTES_PER_FLOAT = 4
        val U_COLOR = "u_Color"
        val A_POSITION = "a_Position"
    }

    private var vertexData: FloatBuffer? = null
    private var program = 0
    private var uColorLocation = 0
    private var aPositionLocation = 0


    init {
        // 数据
        val tableVertices = floatArrayOf(0f, 0f, 0f, 14f, 9f, 14f, 9f, 0f) // 顶点属性
//        val tableVerticesWithTriangles = floatArrayOf(
//            0f, 0f, 9f, 14f, 0f, 14f,
//            0f, 0f, 9f, 0f, 9f, 14f,
//            0f, 7f, 9f, 7f,
//            4.5f, 2f, 4.5f, 12f
//        )

//        val tableVerticesWithTriangles = floatArrayOf(
//            -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f,
//            -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
//            -0.5f, 0f, 0.5f, 0f,
//            0f, -0.25f, 0f, 0.25f
//        )

        val tableVerticesWithTriangles = floatArrayOf(
            0f, 0f,
            -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f, -0.5f,
//            0.5f, 0.5f,
//            -0.5f, 0f, 0.5f, 0f,
//            0f, -0.25f, 0f, 0.25f
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
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        val vertexShaderSource = Utils.readTextFileFromResource(context, R.raw.simple_vertex_shader)
        val fragmentShaderSource = Utils.readTextFileFromResource(context, R.raw.simple_fragment_shader)

        val vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource)
        val fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource)

        // 一个OpenGL程序就是把一个顶点着色器和一个片段着色器链接在一起变成单个对象
        program = ShaderHelper.linkProgram(vertexShader, fragmentShader)

        if (ShaderHelper.validateProgram(program)) {
            glUseProgram(program) // 运行程序
        }

        uColorLocation = glGetUniformLocation(program, U_COLOR) // 获取uniform位置
        aPositionLocation = glGetAttribLocation(program, A_POSITION) // 获取某属性

        // 告诉OpenGL到哪里找到属性a_Position对应的数据
        vertexData?.position(0) // 移动内部指针
        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData)
        glEnableVertexAttribArray(aPositionLocation)


    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    }

    override fun onDrawFrame(gl: GL10?) { // 绘制一帧

        glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f) // 更新着色器的 u_Color 值
//        glDrawArrays(GL_TRIANGLES, 0, 6) // 画三角形
//
//        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f) // 更新着色器的 u_Color 值
//        glDrawArrays(GL_LINES, 6, 2) // 画直线
//
//        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f) // 更新着色器的 u_Color 值
//        glDrawArrays(GL_POINTS, 8, 1) // 画点
//
//        glUniform4f(uColorLocation, 1.0f, 0.0f, 1.0f, 1.0f) // 更新着色器的 u_Color 值
//        glDrawArrays(GL_POINTS, 9, 1) // 画点

        glDrawArrays(GL_TRIANGLE_FAN, 0, 6)


    }
}