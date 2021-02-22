package com.rodrigolessinger.thefoodapp.logic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.IllegalArgumentException

class GridExtTest {

    @Test(expected = IllegalArgumentException::class)
    fun `test no columns, throws error`() {
        listOf(0).buildGrid(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test negative number of columns, throws error`() {
        listOf(0).buildGrid(-1)
    }

    @Test
    fun `test more columns than items, returns single list`() {
        val singleItemList = listOf(1)
        assertEquals(singleItemList.buildGrid(2), listOf(singleItemList))
        assertEquals(singleItemList.buildGrid(7), listOf(singleItemList))

        val itemsList = listOf(1, 2, 3)
        assertEquals(itemsList.buildGrid(4), listOf(itemsList))
        assertEquals(itemsList.buildGrid(100), listOf(itemsList))
    }

    @Test
    fun `test same number of columns and items, returns single list`() {
        val singleItemList = listOf(1)
        assertEquals(singleItemList.buildGrid(1), listOf(singleItemList))

        val itemList = listOf(10, 12, 14, 16)
        assertEquals(itemList.buildGrid(4), listOf(itemList))
    }

    @Test
    fun `test less columns than items, returns correct grid`() {
        val itemList = listOf(101, 102, 103, 104, 105, 106, 107, 108, 109, 110)

        assertEquals(
            itemList.buildGrid(1),
            listOf(*itemList.map { listOf(it) }.toTypedArray())
        )
        assertEquals(
            itemList.buildGrid(2),
            listOf(
                listOf(itemList[0], itemList[1]),
                listOf(itemList[2], itemList[3]),
                listOf(itemList[4], itemList[5]),
                listOf(itemList[6], itemList[7]),
                listOf(itemList[8], itemList[9]),
            )
        )
        assertEquals(
            itemList.buildGrid(3),
            listOf(
                listOf(itemList[0], itemList[1], itemList[2]),
                listOf(itemList[3], itemList[4], itemList[5]),
                listOf(itemList[6], itemList[7], itemList[8]),
                listOf(itemList[9]),
            )
        )
        assertEquals(
            itemList.buildGrid(4),
            listOf(
                listOf(
                    itemList[0],
                    itemList[1],
                    itemList[2],
                    itemList[3]
                ),
                listOf(
                    itemList[4],
                    itemList[5],
                    itemList[6],
                    itemList[7]
                ),
                listOf(itemList[8], itemList[9]),
            )
        )
        assertEquals(
            itemList.buildGrid(5),
            listOf(
                listOf(
                    itemList[0],
                    itemList[1],
                    itemList[2],
                    itemList[3],
                    itemList[4]
                ),
                listOf(
                    itemList[5],
                    itemList[6],
                    itemList[7],
                    itemList[8],
                    itemList[9]
                ),
            )
        )
        assertEquals(
            itemList.buildGrid(6),
            listOf(
                listOf(
                    itemList[0],
                    itemList[1],
                    itemList[2],
                    itemList[3],
                    itemList[4],
                    itemList[5]
                ),
                listOf(
                    itemList[6],
                    itemList[7],
                    itemList[8],
                    itemList[9]
                ),
            )
        )
    }
}
