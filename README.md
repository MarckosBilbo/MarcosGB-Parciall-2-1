# MarcosGB-Parcial-2-1


REPOSITORIO--> https://github.com/Parcial-Eventos-2/MarcosGB-Parciall-2-1
REPOSITORIO--> https://github.com/Parcial-Eventos-2/MarcosGB-Parcial-2-2
REPOSITORIO-->https://github.com/Parcial-Eventos-2/MarcosGB-Parcial-2-3


## Descripción
Esta aplicación permite a los usuarios autenticados gestionar un horario académico personalizado, incluyendo funcionalidades para:
1. **Añadir asignaturas con día y hora.**
2. **Consultar el horario completo de un día.**
3. **Ver qué clase está en curso actualmente.**

La aplicación está integrada con **Firebase Authentication** y **Firebase Realtime Database** para gestionar usuarios y sincronizar datos.

---

## Tecnologías Utilizadas
- **Kotlin**
- **Jetpack Compose** para el diseño de la interfaz.
- **Firebase Authentication** para el manejo de usuarios.
- **Firebase Realtime Database** para la persistencia de datos.
- **WorkManager** para la sincronización en segundo plano.

---

## Funcionalidades Principales
### Gestión de Usuarios
- Registro de usuarios nuevos.
- Inicio de sesión con correo y contraseña.
- Cierre de sesión.

### Gestión de Horarios
- **Añadir Clase**: Permite registrar asignaturas con día y hora.
- **Ver Horario**: Muestra el horario completo para un día seleccionado.
- **Clase Actual**: Indica qué asignatura está en curso según el día y hora actuales.

### Sincronización de Datos
- La aplicación sincroniza los horarios con Firebase cada vez que se accede al menú principal o al iniciar sesión.
- Opción para implementar sincronización periódica usando `WorkManager`.

---

## Estructura del Proyecto

com.example.marcosgb_parcial_2_1
 ├── modelo
 │     ├── ClaseHorario
 │     ├── RepositorioHorario
 │     └── VistaModeloHorario
 ├── vista
 │     ├── PantallaLogin
 │     ├── PantallaRegistro
 │     ├── MenuPrincipal
 │     ├── PantallaAgregarClase
 │     ├── PantallaVerHorario
 │     └── PantallaClaseActual
 ├── service
 │     └── ConnectivityWorker
 └── ui.theme
       ├── Color.kt
       ├── Theme.kt
       └── Type.kt


## Flujo de Navegación

1. **Pantalla de Login**
   - Validación de credenciales.

2. **Pantalla de Registro**
   - Creación de nuevos usuarios.

3. **Pantalla Principal**
   - Navegar a:
     - **Añadir Clase**
     - **Ver Horario**
     - **Clase Actual**
   - Cerrar sesión y regresar al login.

---

## Configuración

### Firebase Authentication
- Habilitar autenticación por correo y contraseña.

### Firebase Realtime Database
- Configurar la base de datos en modo **Realtime**.
- Añadir la URL de la base de datos en `RepositorioHorario`.

---

## Pruebas Sugeridas

1. **Registro de nuevos usuarios**
   - Verificar que los usuarios se crean correctamente en Firebase Console.

2. **Inicio de sesión**
   - Asegurar que los datos se sincronizan con Firebase.

3. **Pruebas en modo offline/online**
   - Validar la funcionalidad del `ConnectivityWorker` en ambos estados.

