package com.alaershov.mars_colony.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * Запуск корутины из компонента с обязательной обработкой исключения.
 *
 * Если в вашем случае будет нужна более сложная обработка ошибок,
 * например их игнорирование - используйте обычный [launch].
 */
inline fun CoroutineFeature.launchSafe(
    coroutineScope: CoroutineScope = scope,
    crossinline errorHandler: suspend (exception: Exception) -> Unit,
    crossinline block: suspend CoroutineScope.() -> Unit,
): Job {
    return coroutineScope.launch {
        try {
            block()
        } catch (exception: Exception) {
            errorHandler(exception)
        }
    }
}
