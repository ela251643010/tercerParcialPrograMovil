package com.ucb.usecases

import com.ucb.data.repository.PlanRepository
import com.ucb.domain.model.Plan

class GetPlanUseCase (private val repository: PlanRepository){
    operator fun invoke(): List<Plan> = repository.getPlans()
}