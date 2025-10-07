package com.alaershov.mars_colony.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alaershov.mars_colony.dashboard.component.DefaultDashboardScreenComponent
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.build_dialog.DefaultHabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.DefaultHabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.info_screen.DefaultHabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.DefaultHabitatListScreenComponent
import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.alaershov.mars_colony.message_dialog.component.DefaultMessageDialogComponent
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.list_screen.component.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.RootScreen
import com.alaershov.mars_colony.shared.weather.WeatherRepository
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext

/**
 * Единственное Activity, которое является точкой входа в приложение.
 */
class MarsColonyAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val habitatRepository = HabitatRepository
        val powerPlantRepository = PowerPlantRepository
        val weatherRepository = WeatherRepository()

        // Единственное место, где мы создаём и напрямую используем Dagger компонент
        // для того, чтобы создать корневой Decompose-компонент.
        val component: RootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            dashboardComponentFactory = object : DefaultDashboardScreenComponent.Factory {
                override fun create(
                    componentContext: ComponentContext,
                    navigateToHabitatList: () -> Unit,
                    navigateToPowerPlantList: () -> Unit
                ): DefaultDashboardScreenComponent {
                    return DefaultDashboardScreenComponent(
                        componentContext = componentContext,
                        navigateToHabitatList = navigateToHabitatList,
                        navigateToPowerPlantList = navigateToPowerPlantList,
                        habitatRepository = habitatRepository,
                        powerPlantRepository = powerPlantRepository,
                        weatherRepository = weatherRepository
                    )
                }
            },
            habitatInfoScreenComponentFactory = object : DefaultHabitatInfoScreenComponent.Factory {
                override fun create(
                    componentContext: ComponentContext,
                    habitatId: String
                ): DefaultHabitatInfoScreenComponent {
                    return DefaultHabitatInfoScreenComponent(
                        componentContext = componentContext,
                        habitatId = habitatId
                    )
                }
            },
            habitatListScreenComponentFactory = object : DefaultHabitatListScreenComponent.Factory {
                override fun create(
                    componentContext: ComponentContext,
                    onBackClick: () -> Unit
                ): DefaultHabitatListScreenComponent {
                    return DefaultHabitatListScreenComponent(
                        componentContext = componentContext,
                        onBackClick = onBackClick,
                        habitatRepository = habitatRepository,
                        habitatBuildDialogComponentFactory = object : DefaultHabitatBuildDialogComponent.Factory {
                            override fun create(
                                componentContext: ComponentContext,
                                onDismiss: () -> Unit
                            ): DefaultHabitatBuildDialogComponent {
                                return DefaultHabitatBuildDialogComponent(
                                    componentContext = componentContext,
                                    onDismiss = onDismiss,
                                    habitatRepository = habitatRepository
                                )
                            }
                        },
                        habitatDismantleDialogComponentFactory = object :
                            DefaultHabitatDismantleDialogComponent.Factory {
                            override fun create(
                                componentContext: ComponentContext,
                                habitatId: String,
                                onConfirmationNeeded: () -> Unit,
                                onDismiss: () -> Unit
                            ): DefaultHabitatDismantleDialogComponent {
                                return DefaultHabitatDismantleDialogComponent(
                                    componentContext = componentContext,
                                    habitatId = habitatId,
                                    onConfirmationNeeded = onConfirmationNeeded,
                                    onDismiss = onDismiss,
                                    habitatRepository = habitatRepository
                                )
                            }
                        },
                        messageDialogComponentFactory = object : DefaultMessageDialogComponent.Factory {
                            override fun create(
                                componentContext: ComponentContext,
                                dialogState: MessageDialogState,
                                onButtonClick: () -> Unit
                            ): DefaultMessageDialogComponent {
                                return DefaultMessageDialogComponent(
                                    componentContext = componentContext,
                                    dialogState = dialogState,
                                    onButtonClick = onButtonClick
                                )
                            }
                        }
                    )
                }
            },
            powerPlantListScreenComponentFactory = object :
                DefaultPowerPlantListScreenComponent.Factory {
                override fun create(componentContext: ComponentContext): DefaultPowerPlantListScreenComponent {
                    return DefaultPowerPlantListScreenComponent(
                        componentContext = componentContext,
                    )
                }
            },
        )

        setContent {
            MarsColonyTheme {
                RootScreen(component)
            }
        }
    }
}
