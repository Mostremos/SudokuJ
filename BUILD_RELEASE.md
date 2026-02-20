# Crear release para distribución

Pasos para generar los ZIP listos para subir al repositorio.

## Orden de ejecución

1. **Compilar JAR**
   ```
   compile_windows.bat
   ```
   Genera: `target/sudokuj-2.0.0.jar`

2. **Crear ejecutable EXE** (opcional)
   ```
   crear_exe.bat
   ```
   Requiere **jpackage** (Java 14+) o **Launch4j** instalado.  
   Genera: `dist/SudokuJ.exe`

3. **Preparar directorios para ZIP**
   ```
   build_release.bat
   ```
   Crea:
   - `SudokuJ 2.0.0 Java/` — JAR, LICENCE, README (para SudokuJ-2.0.0-Java.zip)
   - `SudokuJ 2.0.0 Windows/` — EXE, LICENCE, README (para SudokuJ-2.0.0-Windows.zip)

## Resultado

- **SudokuJ-2.0.0-Java.zip** — Plataforma independiente (Java). Incluye JAR + archivos legales.
- **SudokuJ-2.0.0-Windows.zip** — Para Windows. Incluye EXE + archivos legales.

Sube ambos ZIP a GitHub Releases.
