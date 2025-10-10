package com.alaershov.mars_colony.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alaershov.mars_colony.root.di.RootComponentDiModule
import com.alaershov.mars_colony.dashboard.di.DashboardComponentDiModule
import com.alaershov.mars_colony.habitat.di.HabitatComponentDiModule
import com.alaershov.mars_colony.power.di.PowerComponentDiModule
import com.alaershov.mars_colony.message_dialog.di.MessageDialogComponentDiModule
import com.alaershov.mars_colony.shared.weather.di.WeatherDiModule
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.RootScreen
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.ksp.generated.module

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
                RootComponentDiModule().module,
                DashboardComponentDiModule().module,
                HabitatComponentDiModule().module,
                PowerComponentDiModule().module,
                MessageDialogComponentDiModule().module,
                WeatherDiModule().module,
            )
        }

        val rootFactory: RootComponent.Factory = getKoin().get()

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
