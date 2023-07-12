package com.alaershov.mars_colony.habitat.dismantle_dialog

import com.alaershov.mars_colony.habitat.Habitat

data class HabitatDismantleDialogState(
    val habitat: Habitat?,
    val capacityCurrent: Int,
    val capacityAfterDismantle: Int,
)
