package com.ucaldas.aplicacion1

/**
 * Capa de datos de la seccion VILLANOS.
 *
 * Se mantiene separada de los superheroes (Nombres.kt) pero reutiliza la
 * misma estructura: los dos tipos cumplen el contrato [Personaje], por eso
 * la app puede pintarlos con los mismos componentes.
 *
 * Nota: el id identifica al villano dentro de su propia lista. Puede coincidir
 * con el id de un heroe (por ejemplo Venom es 687 en ambas), porque las rutas
 * de navegacion son distintas: "detalle/{id}" para heroes y "villano/{id}" para
 * villanos.
 *
 * @param universo DC o Marvel, tomado del publisher real de superhero-api.
 */
data class Villano(
    override val id: Int,
    override val nombre: String,
    override val imagenUrl: String,
    override val descripcion: String,
    override val superPoderes: List<String>,
    override val universo: String
) : Personaje {

    override val esVillano: Boolean
        get() = true
}

/** Lista inicial de villanos. Imagenes verificadas contra superhero-api 0.3.0. */
val listaVillanos = listOf(
    Villano(
        370,
        "Joker",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/370-joker.jpg",
        "El principe payaso del crimen: sin poderes sobrehumanos, solo una mente criminal que convierte Gotham en su broma personal.",
        listOf("Inteligencia criminal", "Maestro del caos", "Combate impredecible", "Resistencia al dolor"),
        "DC"
    ),
    Villano(
        655,
        "Thanos",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/655-thanos.jpg",
        "El Titano Locura, obsesionado con equilibrar el universo eliminando a la mitad de todos los seres vivos.",
        listOf("Energia del infinito", "Super fuerza", "Regeneracion", "Inmortalidad"),
        "Marvel"
    ),
    Villano(
        414,
        "Loki",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/414-loki.jpg",
        "El dios de las mentiras, principe de Asgard y hermano adoptivo de Thor, maestro del engaño y la magia.",
        listOf("Inteligencia engañosa", "Magia asgardiana", "Combate cuerpo a cuerpo", "Cambio de forma"),
        "Marvel"
    ),
    Villano(
        687,
        "Venom",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/687-venom.jpg",
        "Simbionte alienigena que se fusiona con un huesped para dar vida a uno de los antivillanos mas temidos de Marvel.",
        listOf("Super fuerza", "Regeneracion acelerada", "Garras venenosas", "Cambiar de forma"),
        "Marvel"
    ),
    Villano(
        299,
        "Green Goblin",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/299-green-goblin.jpg",
        "Norman Osborn, industrial genio que tras experimentar el suero del duende se convirtio en el archienemigo de Spider-Man.",
        listOf("Inteligencia quimica", "Planeador de duende", "Bomba de calabaza", "Combate callejero"),
        "Marvel"
    ),
    Villano(
        405,
        "Lex Luthor",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/405-lex-luthor.jpg",
        "Industrial, filantropo y presidente: el hombre mas poderoso de la Tierra sin necesitar un solo superpoder.",
        listOf("Inteligencia estrategica", "Traje de poder", "Combate tactico", "Fortuna ilimitada"),
        "DC"
    ),
    Villano(
        204,
        "Darkseid",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/204-darkseid.jpg",
        "Tirano absoluto de Apokolips, en busca del Anti-Vida para esclavizar a todo el universo conocido.",
        listOf("Energia Omega", "Super fuerza", "Rayos Omega", "Inmortalidad"),
        "DC"
    ),
    Villano(
        528,
        "Reverse-Flash",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/528-professor-zoom.jpg",
        "Eobard Thawne, tambien llamado Professor Zoom: un viajero del tiempo obsesionado con arruinar la vida de Flash.",
        listOf("Super velocidad", "Viaje en el tiempo", "Inteligencia tactica", "Manipulacion de la fuerza de velocidad"),
        "DC"
    ),
    Villano(
        225,
        "Doctor Octopus",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/225-doctor-octopus.jpg",
        "Otto Octavius, fisico de nivel 8 cuyos brazos mecanicos lo convirtieron en uno de los genios mas peligrosos de Marvel.",
        listOf("Inteligencia de genio", "Brazos mecanicos", "Combate a distancia", "Ciencia de nivel 8"),
        "Marvel"
    ),
    Villano(
        423,
        "Magneto",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/423-magneto.jpg",
        "Maestro del magnetismo y fundador de la Hermandad de Mutantes, siempre a un paso de reescribir el orden mundial.",
        listOf("Energia electromagnetica", "Control de metales", "Vuelo", "Inteligencia estrategica"),
        "Marvel"
    )
)
