package com.alaershov.mars_colony.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.RootContent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.defaultComponentContext

class MarsColonyAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component: RootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            MarsColonyTheme {
                RootContent(component)
            }
        }
    }
}
