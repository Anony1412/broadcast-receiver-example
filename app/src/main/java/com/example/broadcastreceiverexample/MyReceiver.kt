package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log

class MyReceiver (private val communicator: Communicator): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != null) {
            val imageUrl = intent.getStringExtra(MainActivity.IMAGE_URL)
            ImageUtils(::solveBitmap).execute(imageUrl)
        } else {
            // do something
        }
    }

    private fun solveBitmap(bitmap: Bitmap) {
        communicator.respondFromReceiver(bitmap)
    }
}

