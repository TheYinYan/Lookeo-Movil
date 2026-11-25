package com.alanturing.lookeo_movil.features.home.ui

import androidx.lifecycle.ViewModel
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userLocalDataSource: UserLocalDataSource
) : ViewModel() {

    val valueUser = userLocalDataSource.observeUser()
}
