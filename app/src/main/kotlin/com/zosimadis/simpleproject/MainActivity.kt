package com.zosimadis.simpleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.zosimadis.designsystem.theme.SimpleProjectTheme
import com.zosimadis.simpleproject.ui.ViesureApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val appState = rememberNavController()
            SimpleProjectTheme {
                ViesureApp(appState)
            }
        }
    }
}
