package com.example.recomposememoryleak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class LastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                ViewTreeLifecycleOwner.set(this, this.findViewTreeLifecycleOwner())
                setViewTreeSavedStateRegistryOwner(this.findViewTreeSavedStateRegistryOwner())
                setContent { ScreenContent() }
            }
        )
    }

    @Composable
    fun ScreenContent() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "LastActivity",
                    fontSize = 24.sp
                )
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { this@LastActivity.finish() }) { Text(text = "Go Back") }
            }
        }
    }
}