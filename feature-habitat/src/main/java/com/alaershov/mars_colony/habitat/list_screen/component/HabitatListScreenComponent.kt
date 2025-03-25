package com.alaershov.mars_colony.habitat.list_screen.component

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface HabitatListScreenComponent {

    val state: StateFlow<HabitatListScreenState>

    val bottomSheetPages: Value<ChildPages<*, BottomSheetContentComponent>>

    fun onBackClick()

    fun onBuildClick()

    fun onHabitatClick(id: String)

    fun onBottomSheetPagesDismiss()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onBackClick: () -> Unit,
        ): HabitatListScreenComponent
    }
}
