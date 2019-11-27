package com.example.myapplication.forJson

import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HttpHandler {
    fun makeServiceCall(reqUrl: String?): String? {
        var response: String? = null
        try {
            val url = URL(reqUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val inputStream: InputStream = BufferedInputStream(connection.inputStream)
            response = convertStreamToString(inputStream)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return response
    }

    private fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`) as Reader)
        val sb = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append('\n')
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }
}