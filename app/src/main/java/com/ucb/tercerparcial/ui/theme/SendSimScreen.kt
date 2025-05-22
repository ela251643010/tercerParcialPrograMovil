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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


@Composable
fun SendSimScreen() {
    val context = LocalContext.current

    var phone by remember { mutableStateOf("") }
    var lat by remember { mutableStateOf<Double?>(null) }
    var lng by remember { mutableStateOf<Double?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text= "Dónde enviaremos tu SIM",
            style = MaterialTheme.typography.headlineSmall,
           modifier= Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { newValue -> phone = newValue },
            label = { Text("Teléfono de referencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(   text = "Toca el mapa para seleccionar tu ubicación:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                onMapClick = { latLng ->
                    lat = latLng.latitude
                    lng = latLng.longitude
                }
            ) {
                if (lat != null && lng != null) {
                    Marker(
                        state = MarkerState(position = LatLng(lat!!, lng!!)),
                        title = "Ubicación seleccionada"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Latitud: ${lat ?: "---"}")
        Text("Longitud: ${lng ?: "---"}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Toast
                    .makeText(context, "Enviando SIM a lat=$lat lng=$lng tel=$phone", Toast.LENGTH_LONG)
                    .show()
            },
            enabled = phone.isNotEmpty() && lat != null && lng != null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(48.dp)
        ) {
            Text("Continuar")
        }
    }
}

