package com.ucb.tercerparcial.ui.theme

import ads_mobile_sdk.h5
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.ucb.usecases.GetPlanUseCase
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ucb.tercerparcial.R


@Composable
fun HomeScreen(getPlansUseCase: GetPlanUseCase, onContinue: ()->Unit) {
    val plans = remember { getPlansUseCase() }
    var currentIndex by remember { mutableStateOf(0) }

    val plan = plans[currentIndex]

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal
        Text(
            "Nuestros planes móviles",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        // Descripción del plan
        Text(
            text = "La mejor cobertura, increíbles beneficios y un compromiso con el planeta.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        // Tarjeta del plan
        Card(
            modifier = Modifier
                .padding(vertical = 24.dp),
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

         // Beneficios del plan
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 24.dp)
                        .align(Alignment.Start)
                ) {
                    Text("✓ Llamadas y SMS ilimitados", style = MaterialTheme.typography.bodyMedium)
                    Text("✓ Hotspot. Comparte tus datos", style = MaterialTheme.typography.bodyMedium)
                    Text("✓ Redes sociales ilimitadas incluidas", style = MaterialTheme.typography.bodyMedium)
                    Text("✓ Arma tu plan con más apps ilimitadas", style = MaterialTheme.typography.bodyMedium)
                    Text("✓ CO2 Negativo", style = MaterialTheme.typography.bodyMedium)
                }

            }

        }


        // Navegación entre planes
        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                currentIndex = (currentIndex - 1 + plans.size) % plans.size
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Anterior")
            }

            IconButton(onClick = {
                currentIndex = (currentIndex + 1) % plans.size
            }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente")
            }
        }

        // Botón para seleccionar plan
        Button(
            onClick = onContinue ,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Quiero este plan", color = MaterialTheme.colorScheme.onPrimary)
        }

        //boton para whatsapp
        val context = LocalContext.current

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // WhatsApp Icon Clickable
            Image(
                painter = painterResource(id = R.drawable.whatsapp_ic), // usa tu ícono aquí
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
