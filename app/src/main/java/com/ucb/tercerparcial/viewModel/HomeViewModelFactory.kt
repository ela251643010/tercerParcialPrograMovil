package com.ucb.tercerparcial.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ucb.usecases.GetPlanUseCase

class HomeViewModelFactory(private val getPlansUseCase: GetPlanUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getPlansUseCase) as T
    }
}
