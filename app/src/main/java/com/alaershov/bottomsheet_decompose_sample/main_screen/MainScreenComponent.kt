package com.alaershov.bottomsheet_decompose_sample.main_screen

import com.alaershov.bottomsheet_decompose_sample.bottomsheet.BottomSheetContentComponent
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface MainScreenComponent {

    val state: StateFlow<MainScreenState>

    val bottomSheet: Value<ChildSlot<*, BottomSheetContentComponent>>

    fun onRandomizeClicked()

    fun onInfoBottomSheetClicked()

    fun onBuyConfirmBottomSheetClicked()

    fun onLogoutConfirmBottomSheetClicked()

    fun onBottomSheetDismiss()
}
