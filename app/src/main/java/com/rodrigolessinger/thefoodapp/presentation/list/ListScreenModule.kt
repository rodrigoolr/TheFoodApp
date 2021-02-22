package com.rodrigolessinger.thefoodapp.presentation.list

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listScreenModule = module {
    viewModel { ListViewModel(get()) }
}
