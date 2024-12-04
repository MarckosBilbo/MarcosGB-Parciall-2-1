package com.example.marcosgb_parciall_2_1.vista

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.marcosgb_parciall_2_1.modelo.ClaseHorario
import com.example.marcosgb_parciall_2_1.modelo.VistaModeloHorario
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController


@Composable
fun PantallaVerHorario(vistaModelo: VistaModeloHorario, navController: NavController) {
    var dia by remember { mutableStateOf("") }
    val horarios by vistaModelo.obtenerHorarios().observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        TextField(
            value = dia,
            onValueChange = { dia = it },
            label = { Text("Día (Lunes, Martes, etc.)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            val horariosDelDia = horarios.filter { it.dia.equals(dia, ignoreCase = true) }
            items(horariosDelDia) { clase ->
                ClaseItem(clase)
            }
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


@Composable
fun ClaseItem(clase: ClaseHorario) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Asignatura: ${clase.asignatura}", style = MaterialTheme.typography.titleLarge)
            Text("Día: ${clase.dia}", style = MaterialTheme.typography.bodyMedium)
            Text("Hora: ${clase.hora}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

