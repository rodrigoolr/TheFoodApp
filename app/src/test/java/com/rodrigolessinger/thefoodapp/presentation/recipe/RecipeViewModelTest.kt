package com.rodrigolessinger.thefoodapp.presentation.recipe

import app.cash.turbine.FlowTurbine
import app.cash.turbine.test
import com.rodrigolessinger.thefoodapp.data.RecipeRepository
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import com.rodrigolessinger.thefoodapp.helper.MockRecipe
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableUiState
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableUiState.*
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
class RecipeViewModelTest {

    private val repository = mockk<RecipeRepository>()
    private val dispatcher = TestCoroutineDispatcher()

    private suspend fun testUiState(
        id: String = "1",
        validate: suspend FlowTurbine<LoadableUiState<Recipe>>.(RecipeViewModel) -> Unit
    ) {
        val viewModel = RecipeViewModel(id, repository, dispatcher, loadOnInit = false)
        viewModel.uiState.test { validate(viewModel) }
    }

    @Test
    fun `before load, ui state starts as loading`() =
        dispatcher.runBlockingTest {
            testUiState {
                assertEquals(expectItem(), Loading)
            }

            coVerify(exactly = 0) { repository.getRecipe("1") }
        }

    @Test
    fun `when repository throws error, ui state goes from loading to error`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipe("1") } throws Exception()

            testUiState { viewModel ->
                assertEquals(expectItem(), Loading)
                viewModel.load()
                assertEquals(expectItem(), Error)
            }
        }

    @Test
    fun `when repository returns null, ui state goes from loading to error`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipe("1") } returns null

            testUiState { viewModel ->
                assertEquals(expectItem(), Loading)
                viewModel.load()
                assertEquals(expectItem(), Error)
            }
        }

    @Test
    fun `when repository returns successfully, ui state goes from loading to success`() =
        dispatcher.runBlockingTest {
            val recipe = MockRecipe.defaultRecipe
            coEvery { repository.getRecipe("1") } returns recipe

            testUiState { viewModel ->
                assertEquals(expectItem(), Loading)
                viewModel.load()
                assertEquals(expectItem(), Success(recipe))
            }
        }

    @Test
    fun `after failed attempt, retry fails again`() =
        dispatcher.runBlockingTest {
            coEvery { repository.getRecipe("1") } throws Exception()

            testUiState { viewModel ->
                assertEquals(expectItem(), Loading)

                viewModel.load()
                assertEquals(expectItem(), Error)

                viewModel.retry()
                assertEquals(expectItem(), Loading)
                assertEquals(expectItem(), Error)
            }
        }

    @Test
    fun `after failed attempt, retry works`() =
        dispatcher.runBlockingTest {
            val recipe = MockRecipe.defaultRecipe

            testUiState { viewModel ->
                assertEquals(expectItem(), Loading)

                coEvery { repository.getRecipe("1") } throws Exception()
                viewModel.load()
                assertEquals(expectItem(), Error)

                coEvery { repository.getRecipe("1") } returns recipe
                viewModel.retry()
                assertEquals(expectItem(), Loading)
                assertEquals(expectItem(), Success(recipe))
            }
        }
}
