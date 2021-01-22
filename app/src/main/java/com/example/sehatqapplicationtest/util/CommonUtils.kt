package com.example.sehatqapplicationtest.util

import android.content.ContentValues
import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by M Hafidh Abdul Aziz on 22/01/21.
 */

object CommonUtils {

    fun <T> convertClassToJson(convertJson: T?): String? {
        var reqq: String? = null
        if (convertJson != null) {
            reqq = Gson().toJson(convertJson)
        }
        return reqq
    }

    @Throws(JsonSyntaxException::class)
    fun <T> convertJsonToClass(convertString: String, convertClass: Class<T>): T? {
        if (!TextUtils.isEmpty(convertString)) {
            return try {
                Gson().fromJson(convertString, convertClass)
            } catch (e: Exception) {
                null
            }

        }
        return null
    }

    fun <T> convertStreamToJsonClass(
        aContext: Context,
        aFileName: String,
        convertClass: Class<T>
    ): T? {
        val inputStream: InputStream?
        try {
            inputStream = aContext.assets.open(aFileName)
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            return Gson().fromJson(reader, convertClass)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error: ", e)
        }

        return null
    }
}