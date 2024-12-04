package com.example.marcosgb_parciall_2_1.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marcosgb_parciall_2_1.modelo.VistaModeloHorario
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*



@Composable
fun PantallaClaseActual(vistaModelo: VistaModeloHorario, navController: NavController) {
    val horarios by vistaModelo.obtenerHorarios().observeAsState(emptyList())
    val ahora = LocalTime.now()
    val diaActual = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())

    val claseActual = horarios.firstOrNull {
        it.dia.equals(diaActual, ignoreCase = true) && it.hora == ahora.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (claseActual != null) {
            Text(
                text = "Ahora estás en clase de ${claseActual.asignatura}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = "No hay clases en este momento.",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )
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


