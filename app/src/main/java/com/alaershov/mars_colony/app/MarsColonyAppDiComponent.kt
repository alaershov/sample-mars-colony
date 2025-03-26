package com.alaershov.mars_colony.app

import com.alaershov.mars_colony.dashboard.di.DashboardComponentDiModule
import com.alaershov.mars_colony.habitat.di.HabitatComponentDiModule
import com.alaershov.mars_colony.habitat.di.HabitatRepositoryDiModule
import com.alaershov.mars_colony.message_dialog.di.MessageDialogComponentDiModule
import com.alaershov.mars_colony.power.PowerPlantRepositoryDiModule
import com.alaershov.mars_colony.power.di.PowerComponentDiModule
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.di.RootComponentDiModule
import com.alaershov.mars_colony.shared.weather.di.WeatherDiModule
import dagger.Component
import javax.inject.Singleton

/**
 * Единственный Dagger-компонент приложения.
 *
 * В него подключаются все Dagger-модули, которые предоставляют
 * feature-модули приложения.
 *
 * В этих Dagger-модулях могут быть описаны как фабрики создания
 * Decompose-компонентов, так и любые другие зависимости -
 * репозитории, интеракторы, и прочее.
 *
 * В feature-модулях нет нужны объявлять собственные Dagger-компоненты.
 */
@Singleton
@Component(
    modules = [
        RootComponentDiModule::class,
        DashboardComponentDiModule::class,
        HabitatComponentDiModule::class,
        PowerComponentDiModule::class,
        MessageDialogComponentDiModule::class,
        HabitatRepositoryDiModule::class,
        PowerPlantRepositoryDiModule::class,
        WeatherDiModule::class,
    ]
)
interface MarsColonyAppDiComponent {

    val rootComponentFactory: RootComponent.Factory
}
