package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.ApplicationNavHost
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Box {
                    val navController = rememberNavController()
                    ApplicationNavHost(
                        navController = navController,
                    )
                }
            }
        }
    }
}