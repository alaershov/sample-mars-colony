package com.alaershov.mars_colony.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.RootScreen
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.defaultComponentContext

/**
 * Единственное Activity, которое является точкой входа в приложение.
 */
class MarsColonyAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Единственное место, где мы создаём и напрямую используем Dagger компонент
        // для того, чтобы создать корневой Decompose-компонент.
        val component: RootComponent = DaggerMarsColonyAppDiComponent.create()
            .rootComponentFactory
            .create(
                componentContext = defaultComponentContext(),
            )

        setContent {
            MarsColonyTheme {
                RootScreen(component)
            }
        }
    }
}
