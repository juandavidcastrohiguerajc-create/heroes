package com.ucaldas.aplicacion1

/**
 * Capa de datos de la seccion SUPERHEROES.
 *
 * Una data class modela la informacion de un personaje de forma clara:
 * la UI recibe objetos bien definidos y los dibuja, sin preocuparse por
 * como se almacenaron.
 *
 * Cumple el contrato [Personaje] para que los mismos componentes de UI
 * puedan pintar heroes y villanos.
 *
 * @param id            identificador unico del heroe (se usa como key de la
 *                      lista y como argumento de la ruta de detalle).
 * @param nombre        texto principal que vera el usuario.
 * @param imagenUrl     direccion de la imagen remota (cdn.jsdelivr.net).
 * @param descripcion   informacion corta del heroe.
 * @param superPoderes  lista de habilidades del heroe.
 * @param universo      DC o Marvel, tomado del publisher real de superhero-api.
 */
data class Nombres(
    override val id: Int,
    override val nombre: String,
    override val imagenUrl: String,
    override val descripcion: String,
    override val superPoderes: List<String>,
    override val universo: String = "DC"
) : Personaje {

    override val esVillano: Boolean
        get() = false
}

/** Lista de prueba que alimenta la seccion de superheroes. */
val listaNombres = listOf(
    Nombres(
        644,
        "Superman",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/644-superman.jpg",
        "Superman es un heroe de Superman Prime One-Million reconocido por ACTION COMICS #1.",
        listOf("Inteligencia superior", "Super fuerza", "Super velocidad", "Alta resistencia"),
        "DC"
    ),
    Nombres(
        346,
        "Iron Man",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/346-iron-man.jpg",
        "Tony Stark combina una mente brillante con una armadura alimentada por el reactor arc para proteger al mundo.",
        listOf("Inteligencia de nivel 7", "Combate cuerpo a cuerpo", "Traje de armadura", "Energia arc"),
        "Marvel"
    ),
    Nombres(
        720,
        "Wonder Woman",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/720-wonder-woman.jpg",
        "Princesa amazona, embajadora de paz y una de las guerreras mas letales del universo DC.",
        listOf("Inteligencia de Amazona", "Combate ancestral", "Super fuerza", "Vuelo"),
        "DC"
    ),
    Nombres(
        659,
        "Thor",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/659-thor.jpg",
        "Dios del trueno de Asgard, hijo de Odín y miembro fundador de los Vengadores.",
        listOf("Energia del trueno", "Super fuerza", "Vuelo", "Inmortalidad"),
        "Marvel"
    ),
    Nombres(
        149,
        "Captain America",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/149-captain-america.jpg",
        "Steve Rogers, soldado perfecto de la Segunda Guerra Mundial y lider moral de los Vengadores.",
        listOf("Inteligencia tactica", "Combate cuerpo a cuerpo", "Super fuerza", "Escudo indestructible"),
        "Marvel"
    ),
    Nombres(
        620,
        "Spider-Man",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/620-spider-man.jpg",
        "Peter Parker, adolescente mordido por una araña radiactiva que aprendio que un gran poder conlleva una gran responsabilidad.",
        listOf("Agilidad sobrehumana", "Sentido aracnido", "Super velocidad", "Trepar paredes"),
        "Marvel"
    ),
    Nombres(
        687,
        "Venom",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/687-venom.jpg",
        "Simbionte alienigena que se fusiona con un huesped para dar vida a uno de los antivillanos mas temidos de Marvel.",
        listOf("Regeneracion acelerada", "Super fuerza", "Cambiar de forma", "Garras venenosas"),
        "Marvel"
    ),
    Nombres(
        423,
        "Magneto",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/423-magneto.jpg",
        "Maestro del magnetismo y fundador de la Hermandad de Mutantes, siempre a un paso de reescribir el orden mundial.",
        listOf("Energia electromagnetica", "Control de metales", "Vuelo", "Inteligencia estrategica"),
        "Marvel"
    )
)
