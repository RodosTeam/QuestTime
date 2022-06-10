package dev.rodosteam.questtime.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import org.json.JSONArray
import org.json.JSONTokener
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.net.HttpURLConnection
import java.net.URL

fun hasInternet(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting && netInfo.isAvailable
}

object Cat {

    val catErrorUrl = "https://im0-tub-ru.yandex.net/i?id=51c92e4e9b4e2f09e23e6aa1cab293ad&n=13"

    private fun getJson(): String {
        var connection: HttpURLConnection? = null
        try {
            connection =
                URL("https://api.thecatapi.com/v1/images/search").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 2500
            connection.readTimeout = 2500
            connection.connect()
            if (connection.responseCode == 200) {
                val input = BufferedInputStream(connection.inputStream)
                val reader = BufferedReader(InputStreamReader(input))
                return reader.readText()
            }
        } catch (e: IOException) {
            Log.e("TAG", "Bad request")
        } finally {
            connection?.disconnect()
        }
        return "error"
    }


    fun parseCatImageURL(json: String): String {
        var catImageUrl: String? = null
        catImageUrl = try {
            (JSONTokener(json).nextValue() as JSONArray)
                .getJSONObject(0)
                .getString("url")
        } catch (e: Exception) {
            catErrorUrl
        }
        return catImageUrl!!
    }
    fun getUrl(): String {

        fun String.parsed(): String {
            val list = this.split("\"")
            return try {
                list[list.size - 6]
            } catch (e: IndexOutOfBoundsException) {
                Log.d("Json parse error", this)
                "error"
            }
        }

        val content = getJson().parsed()
        Log.d("TAG", content)

        return if (content == "error") catErrorUrl
        else content
    }
}