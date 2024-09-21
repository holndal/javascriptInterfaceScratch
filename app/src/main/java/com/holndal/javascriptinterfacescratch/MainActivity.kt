package com.holndal.javascriptinterfacescratch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.holndal.javascriptinterfacescratch.databinding.ActivityMainBinding

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
            loadUrl("https://www.google.co.jp/")
        }
    }
}