package com.donut.插件 id

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter


typealias CallType = (data: Any) -> Unit

class BroadcastReceiverManager(private val context: Context) {
    private var myBroadcastReceiver: MyBroadcastReceiver
    private var callback: CallType = {}

    init {
        myBroadcastReceiver = MyBroadcastReceiver()
        context.registerReceiver(myBroadcastReceiver, IntentFilter("com.service.scanner.data"))
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val code = intent.getStringExtra("ScanCode") as Any
            callback(code)
        }
    }

    fun changeCallback(callback: CallType) {
        this.callback = callback
    }

    fun unregisterReceiver() {
        context.unregisterReceiver(myBroadcastReceiver)
    }
}