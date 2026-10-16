package com.ucaldas.aplicacion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucaldas.aplicacion1.ui.theme.Aplicacion1Theme
import com.ucaldas.aplicacion1.ui.theme.HeroCard

/**
 * Capa de navegacion y pantallas.
 *
 * La barra inferior alterna entre las dos secciones (Superheroes y Villanos).
 * Cada seccion tiene sus propias rutas, por eso un heroe y un villano pueden
 * compartir id sin abrirse el detalle del otro.
 */

/**
 * Lista vertical reutilizable de personajes.
 *
 * Se usa LazyColumn en lugar de Column porque solo compone lo que esta en
 * pantalla, lo que la hace ideal para listas largas.
 *
 * @param personajes   heroes o villanos a mostrar.
 * @param titulo       encabezado de la seccion.
 * @param esVillanos   cambia el color del encabezado para diferenciar secciones.
 * @param onVerDetalle callback que entrega el id del personaje pulsado.
 */
@Composable
fun ListaPersonajes(
    personajes: List<Personaje>,
    titulo: String,
    esVillanos: Boolean,
    onVerDetalle: (Int) -> Unit
) {
    val colorSeccion = if (esVillanos) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.primary
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            Column(
                modifier = Modifier.padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = colorSeccion
                )
                Text(
                    text = "${personajes.size} personajes · toca una card para ver el detalle",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // key = { it.id } le da una identidad estable a cada item.
        items(personajes, key = { it.id }) { personaje ->
            HeroCard(
                personaje = personaje,
                onClick = { onVerDetalle(personaje.id) }
            )
        }
    }
}

/**
 * Pantalla de detalle.
 *
 * Reutiliza HeroCard, pero al enviar option = 1 la informacion cambia a la
 * vista de detalle. El boton de volver no reescribe logica: delega en el
 * NavController con popBackStack().
 */
@Composable
fun DetallePersonaje(
    personaje: Personaje,
    onVolver: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            HeroCard(personaje = personaje, option = 1)
        }

        item {
            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver a la lista")
            }
        }
    }
}

/**
 * Colores de una pestana de la barra inferior.
 *
 * Al pintar indicador, icono y texto con el color de la seccion, el usuario
 * sabe en todo momento donde esta: azul en Superheroes, rojo en Villanos.
 */
@Composable
private fun coloresPestana(colorSeccion: Color) =
    NavigationBarItemDefaults.colors(
        indicatorColor = colorSeccion,
        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
        selectedTextColor = colorSeccion,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

/**
 * Conecta las dos secciones y sus detalles.
 *
 * En Compose la navegacion no mueve pantallas fisicas: se decide que
 * composable se dibuja segun la ruta activa.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    // La barra inferior solo aparece en las listas; en el detalle se ocupa
    // todo el espacio y se regresa con el boton correspondiente.
    val esPantallaDeLista = rutaActual == Utils.Rutas.LISTA || rutaActual == Utils.Rutas.VILLANOS

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (esPantallaDeLista) {
                NavigationBar {
                    NavigationBarItem(
                        selected = rutaActual == Utils.Rutas.LISTA,
                        onClick = {
                            navController.navigate(Utils.Rutas.LISTA) {
                                // Evita apilar la misma lista varias veces si el
                                // usuario pulsa la pestana repetidamente.
                                launchSingleTop = true
                                popUpTo(Utils.Rutas.LISTA) { saveState = true }
                            }
                        },
                        icon = { Icon(Icons.Default.Star, contentDescription = null) },
                        label = { Text("Superheroes") },
                        colors = coloresPestana(MaterialTheme.colorScheme.primary)
                    )
                    NavigationBarItem(
                        selected = rutaActual == Utils.Rutas.VILLANOS,
                        onClick = {
                            navController.navigate(Utils.Rutas.VILLANOS) {
                                launchSingleTop = true
                                popUpTo(Utils.Rutas.LISTA) { saveState = true }
                            }
                        },
                        icon = { Icon(Icons.Default.Warning, contentDescription = null) },
                        label = { Text("Villanos") },
                        colors = coloresPestana(MaterialTheme.colorScheme.secondary)
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Utils.Rutas.LISTA,
            // Scaffold ya entrega el padding de barras de sistema y de la
            // barra inferior (innerPadding), asi que no hace falta otro ajuste.
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            composable(Utils.Rutas.LISTA) {
                ListaPersonajes(
                    personajes = listaNombres,
                    titulo = "Superheroes",
                    esVillanos = false,
                    onVerDetalle = { id ->
                        navController.navigate(Utils.Rutas.detalle(id))
                    }
                )
            }

            composable(
                route = Utils.Rutas.DETALLE,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStack ->
                val id = backStack.arguments?.getInt("id") ?: return@composable
                val hero = listaNombres.firstOrNull { it.id == id } ?: return@composable

                DetallePersonaje(
                    personaje = hero,
                    onVolver = { navController.popBackStack() }
                )
            }

            composable(Utils.Rutas.VILLANOS) {
                ListaPersonajes(
                    personajes = listaVillanos,
                    titulo = "Villanos",
                    esVillanos = true,
                    onVerDetalle = { id ->
                        navController.navigate(Utils.Rutas.villanoDetalle(id))
                    }
                )
            }

            composable(
                route = Utils.Rutas.VILLANO_DETALLE,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStack ->
                val id = backStack.arguments?.getInt("id") ?: return@composable
                val villano = listaVillanos.firstOrNull { it.id == id } ?: return@composable

                DetallePersonaje(
                    personaje = villano,
                    onVolver = { navController.popBackStack() }
                )
            }
        }
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aplicacion1Theme {
                AppNavigation()
            }
        }
    }
}
