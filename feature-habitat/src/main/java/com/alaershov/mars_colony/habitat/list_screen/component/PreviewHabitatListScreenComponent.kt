package com.alaershov.mars_colony.habitat.list_screen.component

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.habitat.Habitat
import com.alaershov.mars_colony.habitat.bottom_sheet.HabitatBottomSheetConfig
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenState
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.MutableValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewHabitatListScreenComponent : HabitatListScreenComponent {

    override val state: StateFlow<HabitatListScreenState> = MutableStateFlow(
        HabitatListScreenState(
            listOf(
                Habitat(
                    id = "1111-1111-1111-1111",
                    capacity = 40
                ),
                Habitat(
                    id = "2222-2222-2222-2222",
                    capacity = 20
                )
            ),
            60
        )
    )

    override val bottomSheetPages =
        MutableValue<ChildPages<HabitatBottomSheetConfig, BottomSheetContentComponent>>(
            ChildPages(listOf(), 0)
        )

    override fun onBackClick() {}

    override fun onBuildClick() {}

    override fun onHabitatClick(id: String) {}

    override fun onBottomSheetPagesDismiss() {}
}
