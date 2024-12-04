package com.example.marcosgb_parciall_2_1.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marcosgb_parciall_2_1.modelo.ClaseHorario
import com.example.marcosgb_parciall_2_1.modelo.VistaModeloHorario
import java.time.LocalTime


@Composable
fun PantallaAgregarClase(vistaModelo: VistaModeloHorario, navController: NavController) {
    var asignatura by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = asignatura,
            onValueChange = { asignatura = it },
            label = { Text("Nombre de la asignatura") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = dia,
            onValueChange = { dia = it },
            label = { Text("Día (Lunes, Martes, etc.)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Hora (HH:mm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (asignatura.isNotEmpty() && dia.isNotEmpty() && hora.isNotEmpty()) {
                    vistaModelo.agregarClase(ClaseHorario(asignatura, dia, hora))
                    asignatura = ""
                    dia = ""
                    hora = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Clase")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("menu_principal") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a la pantalla principal")
        }
    }
}

