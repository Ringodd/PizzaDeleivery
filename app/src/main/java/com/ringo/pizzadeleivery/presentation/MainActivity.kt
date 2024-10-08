package com.ringo.pizzadeleivery.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.ringo.pizzadeleivery.presentation.ui.PizzaList
import com.ringo.pizzadeleivery.presentation.ui.theme.bg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = bg
                ) { innerPadding ->
                    Spacer(modifier = Modifier.height(100.dp))
                    PizzaList(innerPadding)
                }

        }
    }

    @OptIn(ExperimentalCoilApi::class)
    override fun onDestroy() {
        super.onDestroy()
        imageLoader.diskCache?.clear()
        imageLoader.memoryCache?.clear()
    }
}


