package dev.sundeep.stepchallenge.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
internal suspend fun <T> Flow<T>.test(block: suspend (List<T>) -> Unit) = runTest {
    val result = mutableListOf<T>()
    val job = launch { this@test.toList(result) }
    block(result)
    job.cancel()
}