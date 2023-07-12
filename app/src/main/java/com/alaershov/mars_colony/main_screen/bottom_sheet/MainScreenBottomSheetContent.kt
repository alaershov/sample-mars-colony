package com.alaershov.mars_colony.main_screen.bottom_sheet

import androidx.compose.runtime.Composable
import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialog
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialog
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogComponent

@Composable
fun MainScreenBottomSheetContent(component: BottomSheetContentComponent) {
    when (component) {
        is HabitatBuildDialogComponent -> {
            HabitatBuildDialog(component)
        }

        is HabitatDismantleDialogComponent -> {
            HabitatDismantleDialog(component)
        }
    }
}
