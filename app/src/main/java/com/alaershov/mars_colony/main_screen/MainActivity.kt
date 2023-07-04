package com.alaershov.mars_colony.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenComponent: MainScreenComponent = MainScreenComponentImpl(
            componentContext = defaultComponentContext(),
        )

        setContent {
            MarsColonyTheme {
                MainScreen(screenComponent)
            }
        }
    }
}
