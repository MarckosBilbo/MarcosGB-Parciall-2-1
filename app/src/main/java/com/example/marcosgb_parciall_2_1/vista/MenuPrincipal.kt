package com.example.marcosgb_parciall_2_1.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MenuPrincipal(navController: NavController, auth: FirebaseAuth) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Button(
                onClick = { navController.navigate("agregar_clase") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir Clase")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("ver_horario") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Horario")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("clase_actual") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("¿Qué toca ahora?")
            }
        }

        Button(
            onClick = {
                auth.signOut()
                navController.navigate("login") {
                    popUpTo("menu_principal") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}
