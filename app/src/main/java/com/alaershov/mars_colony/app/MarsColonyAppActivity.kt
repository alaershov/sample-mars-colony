package com.alaershov.mars_colony.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.RootScreen
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.alaershov.mars_colony.root.di.rootKoinModule
import com.alaershov.mars_colony.dashboard.di.dashboardKoinModule
import com.alaershov.mars_colony.habitat.di.habitatKoinModule
import com.alaershov.mars_colony.power.di.powerKoinModule
import com.alaershov.mars_colony.message_dialog.di.messageDialogKoinModule
import com.alaershov.mars_colony.shared.weather.di.weatherKoinModule
import org.koin.java.KoinJavaComponent.getKoin

/**
 * Единственное Activity, которое является точкой входа в приложение.
 */
class MarsColonyAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        startKoin {
            androidContext(this@MarsColonyAppActivity)
            modules(
                rootKoinModule,
                dashboardKoinModule,
                habitatKoinModule,
                powerKoinModule,
                messageDialogKoinModule,
                weatherKoinModule,
            )
        }

        val rootFactory: com.alaershov.mars_colony.root.RootComponent.Factory =
            getKoin().get()

        val component: RootComponent = rootFactory.create(
            componentContext = defaultComponentContext(),
        )

        setContent {
            MarsColonyTheme {
                RootScreen(component)
            }
        }
    }
}
