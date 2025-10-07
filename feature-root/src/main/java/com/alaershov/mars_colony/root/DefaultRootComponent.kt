package com.alaershov.mars_colony.root

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.bottomSheetPages
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.pop
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.pushNew
import com.alaershov.mars_colony.dashboard.component.DashboardScreenComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.HabitatListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import com.alaershov.mars_colony.root.RootComponent.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    @Assisted
    private val componentContext: ComponentContext,

    // Кроме контекста, корневому компоненту нужно уметь создавать свои дочерние компоненты
    // Для этого в конструктор компонента передаются фабрики, с помощью которых можно
    // создать инстансы дочерних компонентов в нужный момент.
    //
    // Нужны только фабрики непосредственно вложенных компонентов
    // Если в каком-то из них будет своя внутренняя навигация, им точно так же нужно
    // будет передать фабрики компонентов, участвующих в этой навигации.
    private val dashboardComponentFactory: DashboardScreenComponent.Factory,
    private val habitatInfoScreenComponentFactory: HabitatInfoScreenComponent.Factory,
    private val habitatListScreenComponentFactory: HabitatListScreenComponent.Factory,
    private val powerPlantListScreenComponentFactory: PowerPlantListScreenComponent.Factory,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _childStack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Dashboard,
            handleBackButton = true, // Pop the back stack on back button press
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, Child>> = _childStack

    private val bottomSheetPagesNavigation = PagesNavigation<RootBottomSheetConfig>()

    override val bottomSheetPages: Value<ChildPages<RootBottomSheetConfig, BottomSheetContentComponent>> =
        bottomSheetPages(
            source = bottomSheetPagesNavigation,
            serializer = RootBottomSheetConfig.serializer(),
            childFactory = ::createBottomSheet,
        )

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Dashboard -> Child.Dashboard(
                dashboardComponentFactory.create(
                    componentContext = componentContext,
                    navigateToHabitatList = {
                        navigation.pushNew(Config.HabitatList)
                    },
                    navigateToPowerPlantList = {
                        navigation.pushNew(Config.PowerPlantList)
                    }
                )
            )

            Config.HabitatList -> Child.HabitatList(
                habitatListScreenComponentFactory.create(
                    componentContext = componentContext,
                    onBackClick = {
                        navigation.pop()
                    },
                )
            )

            is Config.HabitatInfo -> Child.HabitatInfo(
                habitatInfoScreenComponentFactory.create(
                    componentContext = componentContext,
                    habitatId = config.habitatId
                )
            )

            Config.PowerPlantList -> Child.PowerPlantList(
                powerPlantListScreenComponentFactory.create(
                    componentContext = componentContext
                )
            )
        }
    }

    private fun createBottomSheet(
        config: RootBottomSheetConfig,
        componentContext: ComponentContext
    ): BottomSheetContentComponent {
        TODO()
    }

    private fun showBottomSheet(config: RootBottomSheetConfig) {
        bottomSheetPagesNavigation.pushNew(config)
    }

    private fun dismissBottomSheet() {
        bottomSheetPagesNavigation.pop()
    }

    override fun onBottomSheetPagesDismiss() {
        bottomSheetPagesNavigation.pop()
    }

    @Serializable
    private sealed class Config {

        @Serializable
        data object Dashboard : Config()

        @Serializable
        data object HabitatList : Config()

        @Serializable
        data class HabitatInfo(
            val habitatId: String
        ) : Config()

        @Serializable
        data object PowerPlantList : Config()
    }

    @AssistedFactory
    interface Factory : RootComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): DefaultRootComponent
    }
}
