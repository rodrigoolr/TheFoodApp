package com.rodrigolessinger.thefoodapp.presentation.detail.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeInstructions(instructions: String) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Text(
            text = "Instructions",
            style = typography.h5
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = instructions,
            style = typography.body1
        )
    }
}

@Preview
@Composable
fun RecipeInstructionsPreview() {
    RecipeInstructions("Mix everything\nAdd salt\nDone")
}
