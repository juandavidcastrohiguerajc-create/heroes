package com.ucaldas.aplicacion1.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ucaldas.aplicacion1.Personaje

/**
 * Capa de componentes UI.
 *
 * Aqui viven las piezas reutilizables: la imagen, la informacion simple de la
 * card, la informacion de detalle y la tarjeta que las contiene. Todas reciben
 * un [Personaje], por eso sirven igual para superheroes y para villanos.
 * Ninguna de estas funciones conoce la navegacion ni la lista completa.
 */

/**
 * Componente de imagen.
 *
 * Encapsula la carga de imagen en internet para no repetir ese codigo dentro
 * de cada card.
 *
 * @param imageUrl            direccion remota de la imagen.
 * @param contentDescription  descripcion accesible (la lee TalkBack).
 * @param modifier            permite reutilizar el componente con distintos
 *                            tamanos o estilos.
 */
@Composable
fun HeroImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        contentScale = ContentScale.Crop
    )
}

/**
 * Informacion simple que vive dentro de la lista.
 *
 * Es deliberadamente compacta: en una lista lo que importa es decidir rapido
 * a quien le doy tap.
 */
@Composable
fun HeroInfo(
    personaje: Personaje,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "ID: ${personaje.id} · ${personaje.universo}",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = personaje.nombre,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = personaje.descripcion,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Superpoderes: ${personaje.superPoderes.joinToString()}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/**
 * Metricas derivadas del personaje.
 *
 * No son datos crudos: la UI muestra resultados que nacen de una pequena
 * logica de negocio aplicada a la lista de superpoderes.
 */
private fun heroProfile(superPoderes: List<String>): String = when {
    superPoderes.any { it.contains("Inteligencia", ignoreCase = true) } &&
        superPoderes.any { it.contains("Combate", ignoreCase = true) } -> "Estratega"

    superPoderes.any { it.contains("Energia", ignoreCase = true) } -> "Cosmico"

    superPoderes.any { it.contains("velocidad", ignoreCase = true) } -> "Velocista"

    else -> "Titan"
}

/** Resumen tactico: parrafo construido a partir de los datos del personaje. */
private fun heroResumen(personaje: Personaje): String {
    val perfil = heroProfile(personaje.superPoderes)
    val principal = personaje.superPoderes.firstOrNull() ?: "ninguna habilidad registrada"
    val apoyo = personaje.superPoderes.drop(1).joinToString().ifEmpty { "sin habilidades de apoyo" }
    return "Perfil $perfil. ${personaje.nombre} entra al combate con " +
        "${personaje.superPoderes.size} superpoderes y usa $principal como recurso principal; " +
        "$apoyo completan el plan cuando el frente se complica."
}

/**
 * Bloque de metrica usado en la fila del detalle.
 *
 * Cambia de color segun el tipo de personaje: asi la seccion de villanos se
 * distingue sin duplicar el componente.
 */
@Composable
private fun Metrica(
    valor: String,
    etiqueta: String,
    esVillano: Boolean = false,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val colorFondo = if (esVillano) colorScheme.secondaryContainer else colorScheme.primaryContainer
    val colorTexto = if (esVillano) {
        colorScheme.onSecondaryContainer
    } else {
        colorScheme.onPrimaryContainer
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = colorFondo,
        contentColor = colorTexto
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
            Text(
                text = valor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = etiqueta,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

/**
 * Vista de detalle.
 *
 * No es una copia de la card: aprovecha mejor el espacio y cuenta una historia
 * mas completa del personaje. Se construye con Surface, Row, Column, Box y
 * FlowRow. Los colores cambian segun [Personaje.esVillano].
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroInfoDetail(
    personaje: Personaje,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val esVillano = personaje.esVillano
    val colorContenedor = if (esVillano) colorScheme.secondaryContainer else colorScheme.primaryContainer
    val colorTextoContenedor = if (esVillano) {
        colorScheme.onSecondaryContainer
    } else {
        colorScheme.onPrimaryContainer
    }
    val perfil = heroProfile(personaje.superPoderes)

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Bloque principal destacado: inicial circular + rotulo + nombre.
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = colorContenedor,
            contentColor = colorTextoContenedor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(if (esVillano) colorScheme.secondary else colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = personaje.nombre.first().uppercaseChar().toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        color = colorScheme.onPrimary
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = if (esVillano) {
                            "VILLANO #${personaje.id} · ${personaje.universo}"
                        } else {
                            "HEROE #${personaje.id} · ${personaje.universo}"
                        },
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = personaje.nombre,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Perfil: $perfil",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        // Descripcion amplia.
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Descripcion",
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.primary
            )
            Text(
                text = personaje.descripcion,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Metricas derivadas del personaje.
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Metricas",
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.primary
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Metrica(
                    valor = personaje.id.toString(),
                    etiqueta = "Identificador",
                    esVillano = esVillano,
                    modifier = Modifier.weight(1f)
                )
                Metrica(
                    valor = personaje.superPoderes.size.toString(),
                    etiqueta = "Superpoderes",
                    esVillano = esVillano,
                    modifier = Modifier.weight(1f)
                )
            }
            // El perfil ocupa el ancho completo: evita partir palabras como
            // "Velocista" o "Estratega" dentro de una columna angosta.
            Metrica(
                valor = perfil,
                etiqueta = "Perfil de combate",
                esVillano = esVillano
            )
        }

        // Superpoderes como chips visuales.
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Superpoderes",
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.primary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                personaje.superPoderes.forEach { poder ->
                    AssistChip(
                        onClick = { },
                        label = { Text(poder) }
                    )
                }
            }
        }

        // Resumen tactico.
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = colorScheme.surfaceVariant,
            contentColor = colorScheme.onSurfaceVariant
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Resumen tactico",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = heroResumen(personaje),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/**
 * Card reutilizable.
 *
 * Un solo componente con comportamientos distintos segun sus parametros:
 * siempre muestra la imagen, cambia de vista con [option] y se vuelve
 * interactivo si recibe [onClick]. Como recibe un [Personaje], sirve para
 * heroes y para villanos sin duplicar la tarjeta.
 *
 * @param personaje  heroe o villano a renderizar.
 * @param onClick    callback opcional; si es null la card no es presionable.
 * @param option     0 = vista simple de lista, 1 = vista de detalle.
 */
@Composable
fun HeroCard(
    personaje: Personaje,
    onClick: (() -> Unit)? = null,
    option: Int = 0,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            )
    ) {
        HeroImage(
            imageUrl = personaje.imagenUrl,
            contentDescription = personaje.nombre
        )

        if (option == 0) {
            HeroInfo(personaje = personaje)
        } else {
            HeroInfoDetail(personaje = personaje)
        }
    }
}
