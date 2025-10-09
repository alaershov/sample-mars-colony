package com.alaershov.mars_colony.dashboard.component

import com.alaershov.mars_colony.dashboard.DashboardScreenState
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.totalCapacity
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.totalPower
import com.alaershov.mars_colony.shared.weather.WeatherRepository
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

// Обратите внимание на internal constructor
// этот компонент можно создать через фабрику,
// но нельзя из другого модуля напрямую вызвать конструктор.
class DefaultDashboardScreenComponent internal constructor(
    // сначала объявлены "аргументы"
    // они передаются в фабрику компонента
    // и подставляются сюда через Assisted Inject
    componentContext: ComponentContext,
    private val navigateToHabitatList: () -> Unit,
    private val navigateToPowerPlantList: () -> Unit,

    // ниже идут зависимости
    // их не нужно передавать в фабрику компонента
    // за зависимости отвечает DI
    // в этом его основная польза - не подставлять зависимости вручную.
    private val habitatRepository: HabitatRepository,
    private val powerPlantRepository: PowerPlantRepository,
    private val weatherRepository: WeatherRepository,
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

    fun onRefreshClick() {
        scope.launch {
            habitatRepository.buildHabitat(1)
            // TODO обновить
        }
    }

    /**
     * Интерфейс фабрики для AssistedInject через Dagger.
     *
     * Он реализует интерфейс фабрики из интерфейса компонента,
     * и связывается с ним в DashboardComponentModule.
     */
    interface Factory : DashboardScreenComponent.Factory
}
