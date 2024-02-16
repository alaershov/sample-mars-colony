package com.alaershov.mars_colony.ui

import kotlinx.coroutines.CoroutineScope

/**
 * Фича-владелец CoroutineScope.
 * Позволяет запускать корутины, в том числе безопасно через launchSafe.
 */
interface CoroutineFeature {

    val scope: CoroutineScope
}
