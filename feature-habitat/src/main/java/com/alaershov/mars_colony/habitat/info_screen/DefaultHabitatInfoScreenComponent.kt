package com.alaershov.mars_colony.habitat.info_screen

import com.arkivanov.decompose.ComponentContext
 

class DefaultHabitatInfoScreenComponent internal constructor(
    componentContext: ComponentContext,
    habitatId: String
) : HabitatInfoScreenComponent, ComponentContext by componentContext
