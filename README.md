# Superheroes y Villanos — Lista y detalle con Jetpack Compose

**Universidad de Caldas — Programación para Dispositivos Móviles**

App Android construida con Jetpack Compose con **dos secciones** (superheroes y
villanos), cada una con su lista y su pantalla de detalle, navegando entre ambas
con `navigation-compose`.

## Qué hace la app

- **Lista** (`LazyColumn` con `items(key = { it.id })`) de 8 superheroes y **10
  villanos**, cada uno dentro de una `Card` reutilizable con retrato remoto, ID,
  universo, nombre, descripción y superpoderes.
- **Barra inferior** con dos pestañas: *Superheroes* y *Villanos*. Solo aparece
  en las listas; se oculta en el detalle.
- **Navegación** lista → detalle con argumento: `detalle/{id}` para heroes y
  `villano/{id}` para villanos, ambas construidas con `Utils.Rutas` (sin strings
  sueltos). Al tener rutas separadas, **un villano y un héroe pueden compartir id**
  (Venom es 687 en ambas listas) sin abrir el detalle equivocado.
- **Detalle distinto a la card**: encabezado con inicial circular, universo y
  rótulo, descripción amplia, fila de **métricas derivadas**, **superpoderes en
  chips** (`FlowRow` + `AssistChip`) y un **resumen táctico** calculado a partir
  de los datos.
- **Volver a la lista** con `popBackStack()`.
- Imágenes cargadas con **Coil** (`AsyncImage`).

## Estructura (tres capas)

| Capa | Qué contiene | Archivo |
|------|--------------|---------|
| Datos | Modelo común, lista de heroes y lista de villanos | `Personaje.kt`, `Nombres.kt`, `Villanos.kt` |
| Componentes UI | Imagen, card, info simple e info de detalle | `ui/theme/UtilsUI.kt` |
| Navegación y pantallas | Lista, detalle y `NavHost` | `MainActivity.kt` |

```
app/src/main/java/com/ucaldas/aplicacion1/
├── MainActivity.kt      # Pantallas, barra inferior y NavHost
├── Personaje.kt         # Contrato común heroes/villanos
├── Nombres.kt           # data class Nombres + listaNombres
├── Villanos.kt          # data class Villano + listaVillanos
├── Utils.kt             # Rutas centralizadas
└── ui/theme/
    ├── Color.kt         # Paleta de la app
    ├── Theme.kt         # Aplicacion1Theme
    ├── Type.kt          # Tipografía
    └── UtilsUI.kt       # Componentes reutilizables
```

## Cómo se reutiliza todo sin duplicar código

`Personaje` es la interfaz que cumplen `Nombres` y `Villano`. Como los
componentes reciben `Personaje`, una sola tarjeta sirve para ambas secciones:

```kotlin
HeroCard(personaje = heroe,  onClick = { onVerDetalle(heroe.id) })    // lista héroes
HeroCard(personaje = villano, onClick = { onVerDetalle(villano.id) }) // lista villanos
HeroCard(personaje = personaje, option = 1)                           // detalle
```

- `onClick`: si es `null`, la card no es presionable.
- `option == 0` → `HeroInfo` (compacta, para decidir rápido).
- `option == 1` → `HeroInfoDetail` (aprovecha el espacio, cuenta una historia completa).

La diferencia visual entre secciones nace del dato `Personaje.esVillano`: los
héroes usan los colores `primary`/`primaryContainer` (azules) y los villanos
`secondary`/`secondaryContainer` (rojos), sin crear componentes paralelos.

## Métricas derivadas

La pantalla de detalle no solo pinta datos crudos: `heroProfile()` evalúa la lista
de superpoderes con `when` + `any { }` y clasifica al personaje:

| Regla | Perfil |
|-------|--------|
| Tiene "Inteligencia" **y** "Combate" | Estratega |
| Tiene "Energia" | Cósmico |
| Tiene "velocidad" | Velocista |
| Ninguna anterior | Titán |

El mismo perfil alimenta el resumen táctico, que se arma con la cantidad de
poderes, el poder principal y los de apoyo.

## Imágenes

Los retratos provienen de [superhero-api](https://github.com/akabab/superhero-api)
vía `cdn.jsdelivr.net` (licencia libre). Los IDs usados son 644, 346, 720, 659,
149, 620, 687 y 423.

## Cómo ejecutarla

1. Abrir la carpeta `Heroes` en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar en un emulador o dispositivo (`minSdk 28`).
4. Tocar cualquier card → se abre el detalle de ese heroe → "Volver a la lista".

## Mini laboratorio de comprobación

- [ ] La app abre en la lista.
- [ ] Cada heroe aparece dentro de una card.
- [ ] Al tocar una card se abre el detalle correcto.
- [ ] El detalle tiene una presentación distinta a la lista.
- [ ] Se muestran los superpoderes del heroe.
- [ ] El botón de volver funciona.
