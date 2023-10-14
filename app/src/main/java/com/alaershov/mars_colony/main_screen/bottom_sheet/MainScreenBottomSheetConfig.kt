package com.alaershov.mars_colony.main_screen.bottom_sheet

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class MainScreenBottomSheetConfig : Parcelable {

    @Parcelize
    data object HabitatBuild : MainScreenBottomSheetConfig()

    @Parcelize
    data class HabitatDismantle(
        val habitatId: String
    ) : MainScreenBottomSheetConfig()
}
