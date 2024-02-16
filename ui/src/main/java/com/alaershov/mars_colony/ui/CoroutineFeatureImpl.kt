package com.alaershov.mars_colony.ui

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Стандартная реализация CoroutineFeature для Decompose Component.
 */
class CoroutineFeatureImpl(
    lifecycleOwner: LifecycleOwner,
) : CoroutineFeature {

    // Этот scope автоматически отменяется, когда компонент уничтожается
    override val scope = lifecycleOwner.coroutineScope(Dispatchers.Main.immediate + SupervisorJob())
}
