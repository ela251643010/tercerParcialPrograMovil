package com.ucb.tercerparcial.ui.theme


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ucb.usecases.GetPlanUseCase
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.tercerparcial.R
import com.ucb.tercerparcial.viewModel.HomeViewModel
import com.ucb.tercerparcial.viewModel.HomeViewModelFactory
@Composable
fun HomeScreen(
    getPlansUseCase: GetPlanUseCase,
    onContinue: () -> Unit
) {
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(getPlansUseCase))
    val plan = viewModel.currentPlan.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Nuestros planes móviles",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "La mejor cobertura, increíbles beneficios y un compromiso con el planeta.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        Card(
            modifier = Modifier.padding(vertical = 24.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(plan.name, style = MaterialTheme.typography.titleLarge)
                Text(
                    "Antes \$${plan.oldPrice}",
                    style = TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
                Text(
                    "Ahora \$${plan.price}",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Text("${plan.gb}GB", style = MaterialTheme.typography.titleMedium)

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 24.dp)
                        .align(Alignment.Start)
                ) {
                    Text("✓ Llamadas y SMS ilimitados")
                    Text("✓ Hotspot. Comparte tus datos")
                    Text("✓ Redes sociales ilimitadas incluidas")
                    Text("✓ Arma tu plan con más apps ilimitadas")
                    Text("✓ CO2 Negativo")
                }
            }
        }

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { viewModel.goPrevious() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Anterior")
            }
            IconButton(onClick = { viewModel.goNext() }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente")
            }
        }

        Button(
            onClick = onContinue,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Quiero este plan", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.whatsapp_ic),
                contentDescription = "WhatsApp",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        val message = "Hola, UCB mobile, preciso su ayuda"
                        val encodedMessage = java.net.URLEncoder.encode(message, "UTF-8")
                        val whatsappUrl = "https://api.whatsapp.com/send?phone=+59175904352&text=$encodedMessage"

                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(whatsappUrl)
                        }

                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
