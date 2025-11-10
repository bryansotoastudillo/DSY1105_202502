# AppDuocDSY1105 (Compose + MVVM + Retrofit)

Institución: **Duoc UC**  
Curso: **DSY1105**

Proyecto Android listo para importar en Android Studio que consume `https://jsonplaceholder.typicode.com/posts` y muestra una lista de publicaciones con Jetpack Compose.

## Cómo importar
1. Android Studio → **File > Open** → abre la carpeta `AppDuocDSY1105`.
2. Espera a que Gradle sincronice. Si propone actualizar versiones, acepta.
3. Ejecuta en emulador o dispositivo.

## Paquete
- `cl.duoc.dsy1105.app`

## Estructura
- `data/` (model, remote)
- `repository/`
- `viewmodel/`
- `ui/screens/`

## Tests
- Unit: `app/src/test/java`
- UI: `app/src/androidTest/java`

> Tip: Si aparecen advertencias de versiones, usa el asistente de Android Studio para actualizar Compose/AGP.
