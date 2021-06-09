package com.aditprayogo.core.utils

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}