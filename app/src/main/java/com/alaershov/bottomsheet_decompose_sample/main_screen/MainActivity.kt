package com.alaershov.bottomsheet_decompose_sample.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alaershov.bottomsheet_decompose_sample.ui.theme.DecomposeSampleTheme
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenComponent: MainScreenComponent = MainScreenComponentImpl(
            componentContext = defaultComponentContext(),
        )

        setContent {
            DecomposeSampleTheme {
                MainScreen(screenComponent)
            }
        }
    }
}
