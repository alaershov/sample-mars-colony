package com.alaershov.mars_colony.root.bottom_sheet

import androidx.compose.runtime.Composable
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialog
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialog
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogComponent

@Composable
fun RootBottomSheetContent(component: BottomSheetContentComponent) {
    when (component) {
        is HabitatBuildDialogComponent -> {
            HabitatBuildDialog(component)
        }

        is HabitatDismantleDialogComponent -> {
            HabitatDismantleDialog(component)
        }
    }
}
