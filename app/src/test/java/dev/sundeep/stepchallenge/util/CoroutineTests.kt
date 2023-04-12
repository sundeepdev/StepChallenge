package dev.sundeep.stepchallenge.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseCoroutineTests {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
}