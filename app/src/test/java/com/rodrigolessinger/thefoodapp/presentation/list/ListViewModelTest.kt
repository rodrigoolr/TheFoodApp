package com.rodrigolessinger.thefoodapp.presentation.list

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
class ListViewModelTest {

    private val repository = mockk<RecipeRepository>()
    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun `before load, ui state starts as loading`() =
        dispatcher.runBlockingTest {
            val viewModel = ListViewModel(repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), ListUiState.Loading)
            }

            coVerify(exactly = 0) { repository.getRecipeList() }
        }

    @Test
    fun `when repository throws error, ui state goes from loading to error`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipeList() } throws Exception()

            val viewModel = ListViewModel(repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), ListUiState.Loading)
                viewModel.load()
                assertEquals(expectItem(), ListUiState.Error)
            }
        }

    @Test
    fun `when repository returns successfully, ui state goes from loading to success`() =
        dispatcher.runBlockingTest {
            val recipeList = MockRecipe.defaultRecipeList
            coEvery { repository.getRecipeList() } returns recipeList

            val viewModel = ListViewModel(repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), ListUiState.Loading)
                viewModel.load()
                assertEquals(expectItem(), ListUiState.Success(recipeList))
            }
        }

    @Test
    fun `after failed attempt, retry fails again`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipeList() } throws Exception()

            val viewModel = ListViewModel(repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), ListUiState.Loading)

                viewModel.load()
                assertEquals(expectItem(), ListUiState.Error)

                viewModel.retry()
                assertEquals(expectItem(), ListUiState.Loading)
                assertEquals(expectItem(), ListUiState.Error)
            }
        }

    @Test
    fun `after failed attempt, retry works`() =
        dispatcher.runBlockingTest {
            val recipeList = MockRecipe.defaultRecipeList

            val viewModel = ListViewModel(repository, dispatcher)
            viewModel.uiState.test {
                assertEquals(expectItem(), ListUiState.Loading)

                coEvery { repository.getRecipeList() } throws Exception()
                viewModel.load()
                assertEquals(expectItem(), ListUiState.Error)

                coEvery { repository.getRecipeList() } returns recipeList
                viewModel.retry()
                assertEquals(expectItem(), ListUiState.Loading)
                assertEquals(expectItem(), ListUiState.Success(recipeList))
            }
        }
}
