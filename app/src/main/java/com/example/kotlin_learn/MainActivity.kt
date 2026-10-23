package com.example.kotlin_learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var result by remember { mutableStateOf("等待请求") }
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("网络测试")
                    Button(onClick = {
                        result = "请求中"
                        Thread {
                            val text = try {
                                val conn = URL("https://httpbin.org/get").openConnection() as HttpURLConnection
                                conn.connectTimeout = 8000
                                conn.readTimeout = 8000
                                val body = conn.inputStream.bufferedReader().readText()
                                "HTTP ${conn.responseCode}\n$body"
                            } catch (e: Exception) {
                                "请求失败：${e.message}"
                            }
                            runOnUiThread { result = text }
                        }.start()
                    }) {
                        Text("发送请求")
                    }
                    Text(result)
                }
            }
        }
    }
}
