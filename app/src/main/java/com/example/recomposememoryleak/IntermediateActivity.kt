package com.example.recomposememoryleak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class IntermediateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermediate)

//        findViewById<Button>(R.id.intermidiate_go_next_btn).setOnClickListener {
//            startActivity(Intent(this, ComposeActivity::class.java))
//        }

        setContentView(
            ComposeView(this).apply {
                setViewTreeLifecycleOwner(this.findViewTreeLifecycleOwner())
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
                    text = "IntermediateActivity",
                    fontSize = 24.sp
                )
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        startActivity(
                            Intent(
                                this@IntermediateActivity,
                                LastActivity::class.java
                            )
                        )
                    }
                ) { Text(text = "Go Next") }
            }
        }
    }
}