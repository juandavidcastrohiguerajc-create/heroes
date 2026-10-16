package com.ucaldas.aplicacion1

/**
 * Contrato comun entre superheroes y villanos.
 *
 * Al definir esta interfaz, los componentes de UI (HeroImage, HeroInfo,
 * HeroInfoDetail y HeroCard) pueden dibujar cualquier personaje sin
 * duplicar codigo ni crear una segunda tarjeta.
 *
 * @param id            identificador unico DENTRO de su propia lista.
 * @param nombre        texto principal que vera el usuario.
 * @param imagenUrl     direccion de la imagen remota (cdn.jsdelivr.net).
 * @param descripcion   informacion corta del personaje.
 * @param superPoderes  lista de habilidades.
 * @param universo      DC o Marvel.
 * @param esVillano     decide los acentes visuales (colores, rotulo) y ayuda
 *                      a que una seccion se distinga de la otra.
 */
interface Personaje {
    val id: Int
    val nombre: String
    val imagenUrl: String
    val descripcion: String
    val superPoderes: List<String>
    val universo: String
    val esVillano: Boolean
}
