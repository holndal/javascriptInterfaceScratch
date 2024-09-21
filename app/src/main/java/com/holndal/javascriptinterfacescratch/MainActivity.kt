package com.holndal.javascriptinterfacescratch

import android.os.Bundle
import android.webkit.JavascriptInterface
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.holndal.javascriptinterfacescratch.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get(): ActivityMainBinding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWebView()
    }

    private fun initWebView(){
        binding.webView.apply {
            settings.javaScriptEnabled = true
            addJavascriptInterface(MyJavascriptInterfaces(), "MyJavascriptInterface")
            addJavascriptInterface(HeavyJavascriptInterfaces(), "HeavyJavascriptInterface")
            loadUrl("https://www.google.co.jp/")
        }
    }

    private inner class HeavyJavascriptInterfaces{
        @JavascriptInterface
        fun heavyTask1(): String {
            Thread.sleep(10000)
            return "DONE!"
        }

        @JavascriptInterface
        fun heavyTask2(callback: String) {
            CoroutineScope(Dispatchers.IO).launch {
                Thread.sleep(10000)
                val returnValue = "DONE!"
                withContext(Dispatchers.Main){
                    binding.webView.evaluateJavascript("javascript:$callback('$returnValue')", null)
                }
            }
        }
    }
}