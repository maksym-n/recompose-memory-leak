package com.example.recomposememoryleak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : AppCompatActivity() {

    private var textMutableState = MutableStateFlow("Default mutable message")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                ViewTreeLifecycleOwner.set(this, this.findViewTreeLifecycleOwner())
                setViewTreeSavedStateRegistryOwner(this.findViewTreeSavedStateRegistryOwner())
                setContent { ScreenContent(textMutableState.collectAsState(initial = "").value) }
            }
        )
    }

    override fun onStop() {
        super.onStop()
        textMutableState.value = "Updated mutable message"
        println("-------------------- MainActivity.onStop() --------------------")
    }

    @Composable
    fun ScreenContent(text: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = "MainActivity",
                    fontSize = 24.sp
                )
                Button(modifier = Modifier.align(CenterHorizontally), onClick = {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            IntermediateActivity::class.java
                        )
                    )
                }) { Text(text = text) }
            }
        }
    }
}