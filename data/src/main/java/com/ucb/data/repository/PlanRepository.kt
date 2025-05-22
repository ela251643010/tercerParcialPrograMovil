package com.ucb.data.repository

import com.ucb.domain.model.Plan

class PlanRepository {
    fun getPlans(): List<Plan> = listOf(
        Plan("Plan FLEX 5", 199, 270, 5),
        Plan("Plan FLEX 8", 299,370, 8),
        Plan("Plan FLEX 10", 399, 470, 10)
    )
}