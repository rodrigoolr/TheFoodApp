package com.rodrigolessinger.thefoodapp.presentation.detail

import app.cash.turbine.test
import com.rodrigolessinger.thefoodapp.data.RecipeRepository
import com.rodrigolessinger.thefoodapp.helper.MockRecipe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class DetailViewModelTest {

    private val repository = mockk<RecipeRepository>()
    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun `before load, ui state starts as loading`() =
        dispatcher.runBlockingTest {
            val viewModel = DetailViewModel("1", repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), DetailUiState.Loading)
            }

            coVerify(exactly = 0) { repository.getRecipe("1") }
        }

    @Test
    fun `when repository throws error, ui state goes from loading to error`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipe("1") } throws Exception()

            val viewModel = DetailViewModel("1", repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), DetailUiState.Loading)
                viewModel.load()
                assertEquals(expectItem(), DetailUiState.Error)
            }
        }

    @Test
    fun `when repository returns null, ui state goes from loading to error`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipe("1") } returns null

            val viewModel = DetailViewModel("1", repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), DetailUiState.Loading)
                viewModel.load()
                assertEquals(expectItem(), DetailUiState.Error)
            }
        }

    @Test
    fun `when repository returns successfully, ui state goes from loading to success`() =
        dispatcher.runBlockingTest {
            val recipe = MockRecipe.defaultRecipe
            coEvery { repository.getRecipe("1") } returns recipe

            val viewModel = DetailViewModel("1", repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), DetailUiState.Loading)
                viewModel.load()
                assertEquals(expectItem(), DetailUiState.Success(recipe))
            }
        }

    @Test
    fun `after failed attempt, retry fails again`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipe("1") } throws Exception()

            val viewModel = DetailViewModel("1", repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), DetailUiState.Loading)

                viewModel.load()
                assertEquals(expectItem(), DetailUiState.Error)

                viewModel.retry()
                assertEquals(expectItem(), DetailUiState.Loading)
                assertEquals(expectItem(), DetailUiState.Error)
            }
        }

    @Test
    fun `after failed attempt, retry works`() =
        dispatcher.runBlockingTest {
            val recipe = MockRecipe.defaultRecipe

            val viewModel = DetailViewModel("1", repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), DetailUiState.Loading)

                coEvery { repository.getRecipe("1") } throws Exception()
                viewModel.load()
                assertEquals(expectItem(), DetailUiState.Error)

                coEvery { repository.getRecipe("1") } returns recipe
                viewModel.retry()
                assertEquals(expectItem(), DetailUiState.Loading)
                assertEquals(expectItem(), DetailUiState.Success(recipe))
            }
        }
}
