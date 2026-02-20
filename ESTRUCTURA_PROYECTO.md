# Estructura del Proyecto - SudokuJ v2.0.0

## 📁 Organización del Repositorio

```
SuDoKuJ/
│
├── 📂 original/                    # Código original v1.0.1
│   ├── binaries/                   # Archivos binarios originales
│   │   ├── sudokuj.jar            # JAR original
│   │   ├── libcore.so             # Biblioteca nativa Linux
│   │   └── options.ser            # Configuración serializada
│   ├── resources/                  # Recursos originales
│   │   ├── background/            # Imágenes de fondo
│   │   └── images/                # Iconos e imágenes
│   ├── src-decompiled/            # Código descompilado (referencia)
│   ├── cfr.jar                     # Herramienta de descompilación
│   ├── LICENCE.txt                # Licencia GPL v2 original
│   ├── LISEZMOI.txt               # README original (francés)
│   └── README.md                  # Documentación del código original
│
├── 📂 docs/                        # Documentación del proyecto
│   ├── compilacion/                # Guías de compilación
│   │   ├── GUIA_COMPILACION_COMPLETA.md    # ⭐ Guía principal
│   │   ├── README_ACTUALIZACION.md         # Detalles técnicos
│   │   ├── RESUMEN_ACTUALIZACION.md        # Resumen ejecutivo
│   │   └── INDICE_DOCUMENTACION.md          # Índice completo
│   ├── SudokuJ - Google Code Project Hosting.pdf  # PDF original
│   └── README.md                   # Índice de documentación
│
├── 📂 src/                         # Código fuente actualizado
│   ├── sudoku/                     # Paquete principal
│   │   ├── core/                   # Lógica del juego
│   │   │   ├── Core.java           # ⭐ Implementación Java pura
│   │   │   ├── Grid.java           # Corregido
│   │   │   └── ...
│   │   ├── gui/                    # Interfaz gráfica
│   │   ├── commands/               # Patrón Command
│   │   └── util/                   # Utilidades
│   │       └── ResourceLoader.java # ⭐ Nuevo
│   └── jguic/                      # Framework GUI interno
│
├── 📂 resources/                   # Recursos para compilación
│   ├── images/                     # Iconos e imágenes
│   └── background/                 # Imágenes de fondo
│
├── 📂 release/                     # Archivos para distribución
│   ├── sudokuj-2.0.0.jar          # JAR ejecutable (generado)
│   ├── SudokuJ.exe                 # Ejecutable Windows (generado)
│   ├── LICENCE.txt                 # Licencia GPL v2
│   └── README.md                   # README para usuarios
│
├── 📂 target/                      # Archivos compilados (generado, en .gitignore)
│
├── 📄 README.md                    # ⭐ Documentación principal
├── 📄 pom.xml                      # Configuración Maven
├── 📄 compile_windows.bat          # Script compilación Windows
├── 📄 compile_linux.sh             # Script compilación Linux/macOS
├── 📄 crear_exe.bat                # Script para crear .exe
├── 📄 ESTRUCTURA_PROYECTO.md       # Este archivo
└── 📄 .gitignore                   # Archivos a ignorar en Git
```

## 🎯 Propósito de cada Directorio

### `original/`
**Propósito:** Preservar el código original como referencia histórica
- Útil para comparar con la versión actualizada
- Permite entender cómo funcionaba el código original
- Referencia para futuras actualizaciones

### `docs/`
**Propósito:** Toda la documentación del proyecto
- Guías de compilación detalladas
- Documentación técnica
- Material de referencia

### `src/`
**Propósito:** Código fuente actualizado y funcional
- Código modificado y mejorado
- Compatible con Java 11+
- Multiplataforma

### `resources/`
**Propósito:** Recursos necesarios para compilar
- Imágenes e iconos
- Archivos de configuración
- Recursos embebidos en el JAR

### `release/`
**Propósito:** Archivos listos para distribución
- JAR ejecutable final
- Ejecutables nativos (.exe, .app, etc.)
- Documentación para usuarios finales
- Licencia

## 🔄 Flujo de Trabajo

1. **Desarrollo:** Trabajar en `src/`
2. **Compilación:** Generar archivos en `target/`
3. **Distribución:** Copiar a `release/`
4. **Documentación:** Mantener actualizada en `docs/`

## 📦 Para GitHub

### Archivos a Incluir:
- ✅ Todo `src/`
- ✅ Todo `docs/`
- ✅ Todo `original/` (como referencia)
- ✅ `resources/`
- ✅ Scripts de compilación
- ✅ `pom.xml`
- ✅ `README.md` y documentación
- ✅ `.gitignore`

### Archivos a Excluir (en .gitignore):
- ❌ `target/` (generado al compilar)
- ❌ `release/*.jar` y `release/*.exe` (generados)
- ❌ Archivos temporales
- ❌ Configuraciones del IDE

## 🚀 Para Crear un Release

1. Compilar el proyecto
2. Copiar JAR a `release/`
3. Crear ejecutable (opcional)
4. Copiar ejecutable a `release/`
5. Verificar que `release/README.md` y `release/LICENCE.txt` estén presentes
6. Crear ZIP con contenido de `release/`
7. Subir a GitHub Releases

---

**Última actualización:** 2024
