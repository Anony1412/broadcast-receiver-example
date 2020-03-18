package com.example.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {

    private var myReceiver: MyReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDownload.setOnClickListener { sendBroadCastReceiver() }
    }

    override fun onStart() {
        super.onStart()
        initReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myReceiver)
    }

    /**
     * initialization broadcast receiver
     * function register called on onStart()
     * function unregister called on onStop()
     * @funciton registerReceiver(): register a broadcast receiver with a custom broadcast receiver
     * and a intent filter
     * @function unregisterReceiver(): unregister broadcast receiver to avoid crash app
     */
    private fun initReceiver() {
        myReceiver = MyReceiver(this)
        val intentFilter = IntentFilter(MY_ACTION)
        registerReceiver(myReceiver, intentFilter)
    }

    /**
     * send a broadcast receiver
     */
    private fun sendBroadCastReceiver() {
        val intent = Intent()
        intent.apply {
            action = MY_ACTION
            putExtra(IMAGE_URL, IMAGE_URL)
        }
        sendBroadcast(intent)
    }

    /**
     * listen bitmap responded from receiver to set for image view
     */
    override fun respondFromReceiver(bitmap: Bitmap) {
        imageDownload.setImageBitmap(bitmap)
    }

    companion object {
        val MY_ACTION = "my_action"
        val IMAGE_URL = "https://gateway.ipfs.io/ipfs/QmcfzdiDMCy6yd74evDLwk2p43ca5a6ssqrwihdbkF1ExJ"
    }
}

