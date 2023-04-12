package dev.sundeep.stepchallenge.data

import dev.sundeep.stepchallenge.data.repository.StepsDataRepositoryImpl
import dev.sundeep.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import dev.sundeep.stepchallenge.data.source.network.mapper.SheetResponseToStepDataMapper
import dev.sundeep.stepchallenge.data.source.network.mapper.StepDataEntityToRequestMapper
import dev.sundeep.stepchallenge.util.BaseCoroutineTests
import dev.sundeep.stepchallenge.util.getSheetResponseForStepData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class StepsDataRepositoryImplTest: BaseCoroutineTests() {
    private lateinit var subjectRepositoryImpl: StepsDataRepositoryImpl
    private val apiService: GoogleSheetsApiService = mockk()

    @Before
    fun setUp() {
        subjectRepositoryImpl = StepsDataRepositoryImpl(apiService,
                                    StepDataEntityToRequestMapper(),
                                    SheetResponseToStepDataMapper(),
                                    coroutinesTestRule.dispatcher
                                )
    }

    @Test
    fun `GIVEN apiService is working WHEN getStepsData() is called THEN the list of StepData is returned in case of success`()
        = runTest(coroutinesTestRule.dispatcher) {

            coEvery { apiService.getSheetData(any(),any(),any()) } returns
                                                                        getSheetResponseForStepData()

            val flowResult = subjectRepositoryImpl.getStepsData()

            flowResult.collect { result ->
                result.fold(
                    onSuccess = { list ->
                        assertEquals(list.size,4)
                    },
                    onFailure = { error ->
                        fail("The response of repository was failure instead of success ${error.message}")
                    }
                )
            }

            coVerify { apiService.getSheetData(any(),any(),any()) }

            confirmVerified( apiService )
        }
}