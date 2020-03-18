package com.example.broadcastreceiverexample

import android.graphics.Bitmap

interface Communicator {
    fun respondFromReceiver(bitmap: Bitmap)
}
