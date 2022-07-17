package com.bruno.pokedex.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> safeRequest(
    dispatcher: CoroutineDispatcher,
    block: suspend CoroutineScope.() -> (T)
) = withContext(dispatcher) {
    try {
        Result.success(block())
    } catch (e: Exception) {
        Result.failure(e)
    }
}