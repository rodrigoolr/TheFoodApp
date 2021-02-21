package com.rodrigolessinger.thefoodapp.presentation.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.rodrigolessinger.thefoodapp.presentation.list.ui.ListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by inject()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListScreen(viewModel)
        }
    }
}

