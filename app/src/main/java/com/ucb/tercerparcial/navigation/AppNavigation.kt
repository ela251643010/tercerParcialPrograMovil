package com.ucb.tercerparcial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost // ✅ CORRECTO
import androidx.navigation.compose.rememberNavController
import com.ucb.data.repository.PlanRepository
import com.ucb.tercerparcial.ui.theme.HomeScreen
import com.ucb.tercerparcial.ui.theme.SendSimScreen
import com.ucb.usecases.GetPlanUseCase
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val useCase = GetPlanUseCase(PlanRepository())

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(getPlansUseCase = useCase, onContinue = {
                navController.navigate("sendSim")
            })
        }
        composable("sendSim") {
            SendSimScreen()
        }
    }
}