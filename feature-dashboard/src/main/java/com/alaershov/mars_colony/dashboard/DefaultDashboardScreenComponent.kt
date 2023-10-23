package com.alaershov.mars_colony.dashboard

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.totalCapacity
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.totalPower
import com.alaershov.mars_colony.shared.weather.WeatherRepository
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// Обратите внимание на internal constructor
// этот компонент можно создать через фабрику,
// но нельзя из другого модуля напрямую вызвать конструктор.
class DefaultDashboardScreenComponent @AssistedInject internal constructor(
    // сначала объявлены "аргументы"
    // они передаются в фабрику компонента
    // и подставляются сюда через Assisted Inject
    @Assisted
    componentContext: ComponentContext,
    @Assisted("navigateToHabitatList")
    private val navigateToHabitatList: () -> Unit,
    @Assisted("navigateToPowerPlantList")
    private val navigateToPowerPlantList: () -> Unit,

    // ниже идут зависимости
    // их не нужно передавать в фабрику компонента
    // за зависимости отвечает DI
    // в этом его основная польза - не подставлять зависимости вручную.
    habitatRepository: HabitatRepository,
    powerPlantRepository: PowerPlantRepository,
    weatherRepository: WeatherRepository,
) : DashboardScreenComponent,
    ComponentContext by componentContext {

    private val _state: MutableStateFlow<DashboardScreenState> = MutableStateFlow(
        DashboardScreenState(
            totalCapacity = habitatRepository.state.value.totalCapacity,
            totalPower = powerPlantRepository.state.value.totalPower,
            weatherState = weatherRepository.state.value
        )
    )

    override val state: StateFlow<DashboardScreenState> = _state

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        combine(
            habitatRepository.state,
            powerPlantRepository.state,
            weatherRepository.state,
        ) { habitatState, powerPlantState, weatherState ->
            DashboardScreenState(
                totalCapacity = habitatState.totalCapacity,
                totalPower = powerPlantState.totalPower,
                weatherState = weatherState,
            )
        }
            .onEach { dashboardScreenState ->
                _state.value = dashboardScreenState
            }
            .launchIn(scope)
    }

    override fun onHabitatClick() {
        navigateToHabitatList()
    }

    override fun onPowerClick() {
        navigateToPowerPlantList()
    }

    /**
     * Интерфейс фабрики для AssistedInject через Dagger.
     *
     * Он реализует интерфейс фабрики из интерфейса компонента,
     * и связывается с ним в DashboardComponentModule.
     */
    @AssistedFactory
    interface Factory : DashboardScreenComponent.Factory {
        override fun create(
            componentContext: ComponentContext,
            // Аннотации @Assisted("name") нужны, чтобы разрулить
            // зависимости с одинаковым типом, но разными именами.
            // Аналогично работает @Named("name") в обычном графе зависимостей.
            @Assisted("navigateToHabitatList")
            navigateToHabitatList: () -> Unit,
            @Assisted("navigateToPowerPlantList")
            navigateToPowerPlantList: () -> Unit,
        ): DefaultDashboardScreenComponent
    }
}
