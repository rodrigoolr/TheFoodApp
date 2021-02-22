package com.rodrigolessinger.thefoodapp.presentation.list.ui

import com.rodrigolessinger.thefoodapp.presentation.list.logic.GridBuilder
import org.junit.Test

class GridBuilderTest {

    private val gridBuilder = GridBuilder()

    @Test(expected = AssertionError::class)
    fun `test no columns, throws error`() {
        gridBuilder.build(MockRecipe.defaultRecipeList, 0)
    }

    @Test(expected = AssertionError::class)
    fun `test negative number of columns, throws error`() {
        gridBuilder.build(MockRecipe.defaultRecipeList, -1)
    }

    @Test
    fun `test more columns than recipes, returns single list`() {
        assertEquals(
            gridBuilder.build(MockRecipe.defaultRecipeList, 2),
            listOf(MockRecipe.defaultRecipeList)
        )
        assertEquals(
            gridBuilder.build(MockRecipe.defaultRecipeList, 7),
            listOf(MockRecipe.defaultRecipeList)
        )

        val recipeList = listOf(
            MockRecipe.defaultRecipe.copy(id = "1"),
            MockRecipe.defaultRecipe.copy(id = "2"),
            MockRecipe.defaultRecipe.copy(id = "3"),
        )

        assertEquals(
            gridBuilder.build(recipeList, 4),
            listOf(recipeList)
        )
        assertEquals(
            gridBuilder.build(recipeList, 100),
            listOf(recipeList)
        )
    }

    @Test
    fun `test same number of columns and recipes, returns single list`() {
        assertEquals(
            gridBuilder.build(MockRecipe.defaultRecipeList, 1),
            listOf(MockRecipe.defaultRecipeList)
        )

        val recipeList = listOf(
            MockRecipe.defaultRecipe.copy(id = "10"),
            MockRecipe.defaultRecipe.copy(id = "12"),
            MockRecipe.defaultRecipe.copy(id = "14"),
            MockRecipe.defaultRecipe.copy(id = "16"),
        )
        assertEquals(
            gridBuilder.build(recipeList, 4),
            listOf(recipeList)
        )
    }

    @Test
    fun `test less columns than recipes, returns correct grid`() {
        val recipeList = listOf(
            MockRecipe.defaultRecipe.copy(id = "101"),
            MockRecipe.defaultRecipe.copy(id = "102"),
            MockRecipe.defaultRecipe.copy(id = "103"),
            MockRecipe.defaultRecipe.copy(id = "104"),
            MockRecipe.defaultRecipe.copy(id = "105"),
            MockRecipe.defaultRecipe.copy(id = "106"),
            MockRecipe.defaultRecipe.copy(id = "107"),
            MockRecipe.defaultRecipe.copy(id = "108"),
            MockRecipe.defaultRecipe.copy(id = "109"),
            MockRecipe.defaultRecipe.copy(id = "110"),
        )

        assertEquals(
            gridBuilder.build(recipeList, 1),
            listOf(*recipeList.map { listOf(it) }.toTypedArray())
        )
        assertEquals(
            gridBuilder.build(recipeList, 2),
            listOf(
                listOf(recipeList[0], recipeList[1]),
                listOf(recipeList[2], recipeList[3]),
                listOf(recipeList[4], recipeList[5]),
                listOf(recipeList[6], recipeList[7]),
                listOf(recipeList[8], recipeList[9]),
            )
        )
        assertEquals(
            gridBuilder.build(recipeList, 3),
            listOf(
                listOf(recipeList[0], recipeList[1], recipeList[2]),
                listOf(recipeList[3], recipeList[4], recipeList[5]),
                listOf(recipeList[6], recipeList[7], recipeList[8]),
                listOf(recipeList[9]),
            )
        )
        assertEquals(
            gridBuilder.build(recipeList, 4),
            listOf(
                listOf(recipeList[0], recipeList[1], recipeList[2], recipeList[3]),
                listOf(recipeList[4], recipeList[5], recipeList[6], recipeList[7]),
                listOf(recipeList[8], recipeList[9]),
            )
        )
        assertEquals(
            gridBuilder.build(recipeList, 5),
            listOf(
                listOf(recipeList[0], recipeList[1], recipeList[2], recipeList[3], recipeList[4]),
                listOf(recipeList[5], recipeList[6], recipeList[7], recipeList[8], recipeList[9]),
            )
        )
        assertEquals(
            gridBuilder.build(recipeList, 6),
            listOf(
                listOf(
                    recipeList[0],
                    recipeList[1],
                    recipeList[2],
                    recipeList[3],
                    recipeList[4],
                    recipeList[5]
                ),
                listOf(
                    recipeList[6],
                    recipeList[7],
                    recipeList[8],
                    recipeList[9]
                ),
            )
        )
    }
}
