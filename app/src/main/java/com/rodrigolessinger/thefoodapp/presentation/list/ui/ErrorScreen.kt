package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Column(
        Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("An error has occured")
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen {}
}
