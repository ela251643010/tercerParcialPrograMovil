package com.ucb.tercerparcial.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.ucb.usecases.GetPlanUseCase

class HomeViewModel(getPlansUseCase: GetPlanUseCase) : ViewModel() {

    private val plans = getPlansUseCase()
    private val _currentIndex = mutableStateOf(0)

    val currentPlan = mutableStateOf(plans[_currentIndex.value])

    fun goNext() {
        _currentIndex.value = (_currentIndex.value + 1) % plans.size
        currentPlan.value = plans[_currentIndex.value]
    }

    fun goPrevious() {
        _currentIndex.value = (_currentIndex.value - 1 + plans.size) % plans.size
        currentPlan.value = plans[_currentIndex.value]
    }
}
