package com.donut.插件 id

import android.app.Activity
import android.speech.tts.TextToSpeech
import android.util.Log
import com.tencent.luggage.wxa.SaaA.plugin.AsyncJsApi
import com.tencent.luggage.wxa.SaaA.plugin.NativePluginBase
import com.tencent.luggage.wxa.SaaA.plugin.NativePluginInterface
import com.tencent.luggage.wxa.SaaA.plugin.SyncJsApi
import org.json.JSONObject

class TestNativePlugin : NativePluginBase(), NativePluginInterface {
    private val TAG = "TestNativePlugin"
    private var broadcastReceiverManager: BroadcastReceiverManager? = null
    private var tts: TextToSpeech? = null

    override fun getPluginID(): String {
        Log.e(TAG, "getPluginID")
        return BuildConfig.PLUGIN_ID
    }

    @AsyncJsApi(methodName = "pdaScan")
    fun pdaScan(data: JSONObject, callback: (data: Any) -> Unit) {
        broadcastReceiverManager!!.changeCallback(callback)
    }

    @SyncJsApi(methodName = "init")
    fun init(data: JSONObject, activity: Activity) {
        val context = activity.applicationContext

        broadcastReceiverManager = BroadcastReceiverManager(context)

        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                Log.e(TAG, "TTS初始化成功");
                tts!!.setSpeechRate(1.5f)
                tts!!.speak("扫描服务已就绪", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        })
    }

    @SyncJsApi(methodName = "readText")
    fun readText(data: JSONObject, activity: Activity) {
        tts!!.speak(data.getString("text"), TextToSpeech.QUEUE_FLUSH, null, null)
    }


    @SyncJsApi(methodName = "unload")
    fun unload(data: JSONObject, activity: Activity) {
        broadcastReceiverManager!!.unregisterReceiver()

        tts!!.stop()
        tts!!.shutdown()
    }
}

