package com.example.marcosgb_parciall_2_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.*
import androidx.work.*
import com.example.marcosgb_parciall_2_1.modelo.VistaModeloHorario
import com.example.marcosgb_parciall_2_1.service.ConnectivityWorker
import com.example.marcosgb_parciall_2_1.ui.theme.MarcosGBParcial2_1Theme
import com.example.marcosgb_parciall_2_1.vista.*
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            MarcosGBParcial2_1Theme {
                val navController = rememberNavController()

                // Verifica si hay un usuario autenticado
                val startDestination = if (auth.currentUser != null) {
                    scheduleConnectivityWorker() // Sincronizar datos si hay sesión activa
                    "menu_principal"
                } else {
                    "login"
                }

                NavHost(navController, startDestination = startDestination) {
                    composable("login") { PantallaLogin(navController, auth) }
                    composable("registro") { PantallaRegistro(navController, auth) }
                    composable("menu_principal") {
                        scheduleConnectivityWorker() // Sincronizar al acceder al menú principal
                        MenuPrincipal(navController, auth)
                    }
                    composable("agregar_clase") {
                        PantallaAgregarClase(VistaModeloHorario(application), navController)
                    }
                    composable("ver_horario") {
                        PantallaVerHorario(VistaModeloHorario(application), navController)
                    }
                    composable("clase_actual") {
                        PantallaClaseActual(VistaModeloHorario(application), navController)
                    }
                }
            }
        }
    }

    // Configuración del ConnectivityWorker como tarea única
    private fun scheduleConnectivityWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // Solo ejecutar si hay conexión
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ConnectivityWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    // Configuración del ConnectivityWorker como tarea periódica (opcional)
    private fun schedulePeriodicConnectivityWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<ConnectivityWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SyncWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }
}
