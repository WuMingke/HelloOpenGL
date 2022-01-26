package com.example.helloopengl

import android.opengl.GLES20.*
import android.util.Log

class ShaderHelper {

    companion object {
        fun compileVertexShader(shaderCode: String): Int {
            return compileShader(GL_VERTEX_SHADER, shaderCode)
        }

        fun compileFragmentShader(shaderCode: String): Int {
            return compileShader(GL_FRAGMENT_SHADER, shaderCode)
        }

        private fun compileShader(type: Int, shaderCode: String): Int {
            val shaderObjectId = glCreateShader(type) // 创建着色器对象
            if (shaderObjectId == 0) {
                return 0
            }
            glShaderSource(shaderObjectId, shaderCode) // 着色器代码传到着色器上
            glCompileShader(shaderObjectId) // 编译着色器

            val compileStatus = IntArray(1) // TODO: 感觉这里需要优化，不能一只创建
            glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0) // 取出编译状态

            Log.i("wmkwmk", "compileShader: compileStatus:${compileStatus[0]},log:${glGetShaderInfoLog(shaderObjectId)}") // 日志

            if (compileStatus[0] == 0) { // 失败
                glDeleteShader(shaderObjectId)
                Log.i("wmkwmk", "compileShader: 失败")
                return 0
            }
            return shaderObjectId
        }

        // 顶点着色器和片段着色器总是要一起工作，但是并不意味着他们必须是一一对应的，可以在多个程序中使用同一个着色器
        fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
            val programObjectId = glCreateProgram()  // 创建程序对象
            if (programObjectId == 0) {
                Log.i("wmkwmk", "linkProgram: 失败")
                return 0
            }
            glAttachShader(programObjectId, vertexShaderId)  // 将着色器附着到程序对象上
            glAttachShader(programObjectId, fragmentShaderId)

            glLinkProgram(programObjectId) // 链接程序

            val linkStatus = IntArray(1) // TODO: 感觉这里需要优化 ，不能一只创建
            glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0) // 状态

            Log.i("wmkwmk", "linkProgram: linkStatus:${linkStatus[0]},log:${glGetProgramInfoLog(programObjectId)}")

            if (linkStatus[0] == 0) {
                glDeleteProgram(programObjectId) //  删除
                Log.i("wmkwmk", "linkProgram: 失败")
                return 0
            }

            return programObjectId
        }

        /**
         * 检查程序状态
         */
        fun validateProgram(programObjectId: Int): Boolean {
            glValidateProgram(programObjectId)

            val validateStatus = IntArray(1)
            glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0)

            Log.i("wmkwmk", "validateProgram: validateStatus:${validateStatus[0]},log:${glGetProgramInfoLog(programObjectId)}")
            return validateStatus[0] != 0
        }
    }
}