package com.ucb.tercerparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.data.repository.PlanRepository
import com.ucb.tercerparcial.ui.theme.HomeScreen
import com.ucb.tercerparcial.ui.theme.TercerParcialTheme
import com.ucb.usecases.GetPlanUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val getPlanUseCase= GetPlanUseCase(PlanRepository())
        setContent(){
            MaterialTheme{
                HomeScreen(getPlanUseCase)
            }
        }
    }
}
