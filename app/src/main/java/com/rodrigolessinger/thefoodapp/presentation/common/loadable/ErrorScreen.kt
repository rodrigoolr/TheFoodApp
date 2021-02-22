package com.rodrigolessinger.thefoodapp.presentation.common.loadable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigolessinger.thefoodapp.R

@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Column(
        Modifier.padding(all = 16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MaterialTheme {
            val typography = MaterialTheme.typography

            Text(
                text = stringResource(R.string.error_screen_title),
                style = typography.h4
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.error_screen_message),
                style = typography.body1
            )

            Spacer(modifier = Modifier.height(32.dp))

            IconButton(onClick = onRetry) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_retry),
                    contentDescription = stringResource(R.string.error_screen_retry_content_description),
                )
            }
        }

    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen {}
}
