package com.holndal.javascriptinterfacescratch

import android.util.Log
import android.webkit.JavascriptInterface
import org.json.JSONObject

class MyJavascriptInterfaces {
    @JavascriptInterface
    fun hello(){
        Log.i("webViewApp", "Hello, World")
    }

    @JavascriptInterface
    fun getInt(v: Int): Int {
        return v+2
    }

    @JavascriptInterface
    fun getBoolean(v: Boolean): Boolean {
        return !v
    }

    @JavascriptInterface
    fun getDouble(v: Double): Double {
        return v+1
    }

    @JavascriptInterface
    fun getString(v: String): String {
        return "hello~ $v"
    }

    @JavascriptInterface
    fun getOptionalString(v: String?): String? {
        return v?.let{"hello~ $it"}
    }

    @JavascriptInterface
    fun getStringArray(v: Array<String>) {
        v.forEach {
            element -> Log.i("WebViewApp", element)
        }
    }

    @JavascriptInterface
    fun getJsonString(v: String): String {
        val json: JSONObject = JSONObject(v)
        val name: String = json.get("name") as String
        val age: Int = json.get("age") as Int
        json.put("status", "alive")
        return json.toString()
    }
}