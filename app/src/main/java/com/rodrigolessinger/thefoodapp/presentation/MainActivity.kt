package com.rodrigolessinger.thefoodapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.rodrigolessinger.thefoodapp.presentation.detail.DetailViewModelFactory
import com.rodrigolessinger.thefoodapp.presentation.list.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by inject()
    private val detailViewModelFactory: DetailViewModelFactory by inject()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(listViewModel, detailViewModelFactory)
        }
    }
}

