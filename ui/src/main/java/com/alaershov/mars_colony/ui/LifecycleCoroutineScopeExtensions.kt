package com.alaershov.mars_colony.ui

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * CoroutineScope, который отменится, когда в lifecycle произойдёт onDestroy.
 */
fun CoroutineScope(context: CoroutineContext, lifecycle: Lifecycle): CoroutineScope {
    val scope = CoroutineScope(context)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}

/**
 * CoroutineScope, который отменится, когда в lifecycle этого LifecycleOwner произойдёт onDestroy.
 */
fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope {
    return CoroutineScope(context, lifecycle)
}
