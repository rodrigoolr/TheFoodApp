package com.rodrigolessinger.thefoodapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesList()
        }
    }
}

@Composable
fun RecipesList() {
    Column {
        RecipeCard(name = "Mustard Champ", area = "Irish", category = "Side", image = "https://www.themealdb.com/images/media/meals/o7p9581608589317.jpg")
        RecipeCard(name = "Boxty Breakfast", area = "Irish", category = "Pork", image = "https://www.themealdb.com/images/media/meals/naqyel1608588563.jpg")
    }
}

@Composable
fun RecipeCard(name: String, area: String, category: String, image: String) {
    Card {
        Column {
            CoilImage(data = image, contentDescription = "")
            Text(text = "$area - $category")
            Text(text = name)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RecipesList()
}
