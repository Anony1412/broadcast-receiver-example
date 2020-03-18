package com.example.broadcastreceiverexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ImageUtils (private val solveBitmap: (Bitmap) -> Unit):
    AsyncTask<String, Void, Bitmap>(){

    override fun doInBackground(vararg params: String?): Bitmap? {
        var bitmap: Bitmap?= null
        try {
            val url = URL(params[0])
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.connect()
            val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            // do something
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        result?.let { solveBitmap(it) }
    }
}
