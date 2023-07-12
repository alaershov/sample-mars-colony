package com.alaershov.mars_colony.main_screen

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class MainScreenBottomSheetConfig : Parcelable {

    @Parcelize
    object Info : MainScreenBottomSheetConfig()

    @Parcelize
    object HabitatBuild : MainScreenBottomSheetConfig()

    @Parcelize
    data class Confirm(
        val question: String,
    ) : MainScreenBottomSheetConfig()
}
