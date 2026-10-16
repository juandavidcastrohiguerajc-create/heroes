package com.ucaldas.aplicacion1

/**
 * Paso 2: rutas de navegacion centralizadas.
 *
 * Evita escribir strings sueltos por todo el proyecto y hace que la
 * navegacion sea mas segura y mas facil de mantener.
 */
object Utils {

    object Rutas {

        /** Pantalla principal con la lista de heroes. */
        const val LISTA = "lista"

        /** Ruta del detalle de un heroe con un parametro de tipo entero: {id}. */
        const val DETALLE = "detalle/{id}"

        /** Construye la ruta real, por ejemplo: detalle/644. */
        fun detalle(id: Int) = "detalle/$id"

        /** Seccion de villanos (destino de la barra inferior). */
        const val VILLANOS = "villanos"

        /** Ruta del detalle de un villano: {id} separado del de heroes. */
        const val VILLANO_DETALLE = "villano/{id}"

        /** Construye la ruta real, por ejemplo: villano/370. */
        fun villanoDetalle(id: Int) = "villano/$id"
    }
}
