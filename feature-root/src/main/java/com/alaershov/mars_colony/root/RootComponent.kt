package com.alaershov.mars_colony.root

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.dashboard.DashboardScreenComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenComponent
import com.alaershov.mars_colony.power.list_screen.PowerPlantListScreenComponent
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    val bottomSheet: Value<ChildSlot<*, BottomSheetContentComponent>>

    fun onBottomSheetDismiss()

    sealed class Child {

        class Dashboard(val component: DashboardScreenComponent) : Child()

        class HabitatList(val component: HabitatListScreenComponent) : Child()

        class HabitatInfo(val component: HabitatInfoScreenComponent) : Child()

        class PowerPlantList(val component: PowerPlantListScreenComponent) : Child()
    }
}
