# The Movie DB App 

Una aplicación móvil para explorar una galería de películas populares utilizando la API de The Movie DB. La aplicación está desarrollada con **Kotlin** y **Jetpack Compose** siguiendo la arquitectura **MVVM**, y ofrece una interfaz moderna basada en **Material Design 3**.

## Tecnologías Utilizadas

### Lenguaje y Herramientas
- **Kotlin**: Lenguaje principal para el desarrollo de la app.
- **Jetpack Compose**: Framework declarativo para el diseño de UI.
- **Android Studio (LadyBug 2024.2.1 Patch 3)**: IDE para el desarrollo de aplicaciones Android.

### Arquitectura
- **MVVM (Model-View-ViewModel)**: Separación de responsabilidades para un código limpio y mantenible.

### Dependencias
- **Retrofit**: Cliente HTTP para la comunicación con la API de The Movie DB.
- **Coil**: Librería para la carga eficiente de imágenes.
- **Material Design 3**: Diseño moderno y responsivo.

## Documentación de Tecnologías Utilizadas

- **[Jetpack Compose](https://developer.android.com/develop/ui/compose/documentation?hl=es-419)**

- **[Retrofit](https://square.github.io/retrofit/)**

- **[Coil](https://coil-kt.github.io/coil/)**

- **[Material Design 3](https://m3.material.io/)**

### Configuración
- Colocar la vista **Project** y crear un archivo nuevo llamado `gradle.properties` desde la raiz.
- Agregar lo siguiente al archivo:

- `org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8`
- `android.useAndroidX=true`
- `kotlin.code.style=official`
- `android.nonTransitiveRClass=true`

- `TMDB_BEARER_TOKEN=API-KEY` **Asegurarse que la API-KEY este en una sola línea**

- Sync los archivos gradles
- Limpiar el build y volverlo a contruir para terminar de sincronizar la variable `TMDB_BEARER_TOKEN` con su **API-KEY**




