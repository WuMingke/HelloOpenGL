package com.example.helloopengl

import android.content.Context
import android.util.Log
import java.io.BufferedReader

class Utils {

    companion object {

        fun readTextFileFromResource(context: Context, resourceId: Int): String {
            val body = StringBuilder()
            try {
                val inputStream = context.resources.openRawResource(resourceId)
                body.append(inputStream.bufferedReader().use(BufferedReader::readText))
            } catch (e: Exception) {
                Log.i("wmkwmk", "readTextFileFromResource: ${e.toString()}")
            }
            return body.toString()
        }
    }
}