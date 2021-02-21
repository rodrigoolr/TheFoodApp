package com.rodrigolessinger.thefoodapp.presentation.list

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listActivityModule = module {
    viewModel { ListViewModel(get()) }
}
