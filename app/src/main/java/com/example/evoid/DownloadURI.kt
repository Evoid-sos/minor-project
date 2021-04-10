package com.example.evoid

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadUrl {
    @Throws(IOException::class)
    fun readUrl(strUrl: String?): String {
        var data = ""
        var iStream: InputStream? = null
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(strUrl)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connect()
            iStream = urlConnection.getInputStream()
            val br = BufferedReader(InputStreamReader(iStream))
            val sb = StringBuffer()
            var line: String? = ""
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }
            data = sb.toString()
            Log.d("downloadUrl", data)
            br.close()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        } finally {
            if (iStream != null) {
                iStream.close()
            }
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
        }
        return data
    }
}