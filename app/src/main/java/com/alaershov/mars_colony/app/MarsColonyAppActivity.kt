package com.alaershov.mars_colony.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.RootContent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.defaultComponentContext

class MarsColonyAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO сделать Dagger DI, и вынести логику в отдельные модули
        //  - добавить общий bottomsheet в отдельном модуле
        //  - добавить навигацию с табами, чтобы было посложнее, и был пример Page навигации Decompose
        val component: RootComponent = DaggerMarsColonyAppDiComponent.create()
            .rootComponentFactory
            .create(
                componentContext = defaultComponentContext()
            )

        setContent {
            MarsColonyTheme {
                RootContent(component)
            }
        }
    }
}
