package com.alaershov.mars_colony.power.list_screen.component

import com.arkivanov.decompose.ComponentContext

class DefaultPowerPlantListScreenComponent internal constructor(
    componentContext: ComponentContext,
) : PowerPlantListScreenComponent, ComponentContext by componentContext
