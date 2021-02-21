package com.rodrigolessinger.thefoodapp.presentation.common

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen() {
    CircularProgressIndicator()
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}
