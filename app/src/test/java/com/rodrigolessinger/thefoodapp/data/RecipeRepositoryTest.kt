package com.rodrigolessinger.thefoodapp.data

import com.rodrigolessinger.thefoodapp.helper.MockRecipe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeRepositoryTest {

    private val provider = mockk<RecipeProvider>()
    private val repository = RecipeRepository(provider)

    @Test(expected = IllegalStateException::class)
    fun `when provider returns error, getRecipeList throws exception`() = runBlockingTest {
        coEvery { provider.getRecipeList() } throws IllegalStateException()
        repository.getRecipeList()
    }

    @Test
    fun `when provider returns model, getRecipeList returns model`() = runBlockingTest {
        val recipeList = MockRecipe.defaultRecipeList
        coEvery { provider.getRecipeList() } returns recipeList
        assertEquals(repository.getRecipeList(), recipeList)
    }

    @Test
    fun `when local cache is empty, getRecipe fetches list and returns model`() = runBlockingTest {
        val recipeList = MockRecipe.defaultRecipeList
        coEvery { provider.getRecipeList() } returns recipeList

        assertEquals(repository.getRecipe(recipeList[0].id), recipeList[0])
    }

    @Test(expected = IllegalArgumentException::class)
    fun `when local cache is empty and provider returns error, getRecipe throws exception`() =
        runBlockingTest {
            coEvery { provider.getRecipeList() } throws IllegalArgumentException()
            repository.getRecipeList()
        }

    @Test
    fun `when getRecipeList is called first, getRecipe returns model immediately`() =
        runBlockingTest {
            val recipeList = MockRecipe.defaultRecipeList
            coEvery { provider.getRecipeList() } returns recipeList

            assertEquals(repository.getRecipeList(), recipeList)
            assertEquals(repository.getRecipe(recipeList[0].id), recipeList[0])

            coVerify(exactly = 1) { provider.getRecipeList() }
        }

    @Test
    fun `with multiple recipes, getRecipe returns correct model`() = runBlockingTest {
        val recipeList = listOf(
            MockRecipe.defaultRecipe.copy(id = "0", name = "Other Recipe"),
            MockRecipe.defaultRecipe.copy(id = "1", description = "Random Text"),
            MockRecipe.defaultRecipe.copy(id = "2", name = "Third Recipe"),
            MockRecipe.defaultRecipe.copy(id = "3", thumbnail = "http://other.image"),
            MockRecipe.defaultRecipe.copy(id = "4", ingredients = listOf()),
        )
        coEvery { provider.getRecipeList() } returns recipeList

        assertEquals(repository.getRecipeList(), recipeList)
        assertEquals(repository.getRecipe("3"), recipeList[3])
        assertEquals(repository.getRecipe("1"), recipeList[1])
        assertEquals(repository.getRecipe("0"), recipeList[0])
        assertEquals(repository.getRecipe("2"), recipeList[2])
        assertEquals(repository.getRecipe("4"), recipeList[4])
        assertEquals(repository.getRecipe("1"), recipeList[1])

        coVerify(exactly = 1) { provider.getRecipeList() }
    }

    @Test
    fun `when recipe is not available at first, getRecipe fetches again and returns model`() =
        runBlockingTest {
            val recipe = MockRecipe.defaultRecipe.copy(id = "7")

            val firstList = MockRecipe.defaultRecipeList
            val secondList = listOf(recipe)

            coEvery { provider.getRecipeList() } returns firstList
            assertEquals(repository.getRecipeList(), firstList)

            coEvery { provider.getRecipeList() } returns secondList
            assertEquals(repository.getRecipe("7"), recipe)

            coVerify(exactly = 2) { provider.getRecipeList() }
        }

    @Test
    fun `when recipe is not available, getRecipe fetches again and returns null`() =
        runBlockingTest {
            val firstList = MockRecipe.defaultRecipeList
            val secondList = listOf(MockRecipe.defaultRecipe.copy(id = "7"))

            coEvery { provider.getRecipeList() } returns firstList
            assertNull(repository.getRecipe("10"))

            coEvery { provider.getRecipeList() } returns secondList
            assertNull(repository.getRecipe("10"))

            coVerify(exactly = 2) { provider.getRecipeList() }
        }
}
