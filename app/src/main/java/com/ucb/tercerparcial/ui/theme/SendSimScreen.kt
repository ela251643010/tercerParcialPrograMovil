package com.ucb.tercerparcial.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState


import androidx.compose.ui.Alignment
import com.ucb.tercerparcial.viewModel.SendSimViewModel

@Composable
fun SendSimScreen(viewModel: SendSimViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dónde enviaremos tu SIM",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = viewModel.phone.value,
            onValueChange = { viewModel.updatePhone(it) },
            label = { Text("Teléfono de referencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Toca el mapa para seleccionar tu ubicación:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                onMapClick = { latLng ->
                    viewModel.updateLocation(latLng.latitude, latLng.longitude)
                }
            ) {
                val lat = viewModel.lat.value
                val lng = viewModel.lng.value

                if (lat != null && lng != null) {
                    Marker(
                        state = MarkerState(position = LatLng(lat, lng)),
                        title = "Ubicación seleccionada"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Latitud: ${viewModel.lat.value ?: "---"}")
        Text("Longitud: ${viewModel.lng.value ?: "---"}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Enviando SIM a\nlat=${viewModel.lat.value} lng=${viewModel.lng.value} tel=${viewModel.phone.value}",
                    Toast.LENGTH_LONG
                ).show()
            },
            enabled = viewModel.isValid(),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(48.dp)
        ) {
            Text("Continuar")
        }
    }
}
