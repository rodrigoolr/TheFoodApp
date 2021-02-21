package com.rodrigolessinger.thefoodapp.presentation.detail

import org.koin.dsl.module

val detailActivityModule = module {
    single { DetailViewModelFactory(get()) }
}
