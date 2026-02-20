# SudokuJ v2.0.0 - Actualización y Modernización

![Java](https://img.shields.io/badge/Java-11%2B-orange)
![License](https://img.shields.io/badge/License-GPL%20v2-blue)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20macOS-lightgrey)

## 🎮 Descripción

SudokuJ es un juego de Sudoku completo y funcional, actualizado desde la versión original 1.0.1 (discontinuada) para funcionar con versiones modernas de Java y ser completamente multiplataforma.

**Proyecto Original:** SudokuJ 1.0.1 por Romain Huet y Nicolas Raynaud — https://code.google.com/archive/p/sudokuj07/ (discontinuado desde 2007)

**Actualización 2026:** https://github.com/Mostremos/SudokuJ

## ✨ Características

- ✅ **Multiplataforma**: Windows, Linux, macOS
- ✅ **Java Moderno**: Compatible con Java 11+
- ✅ **Sin Dependencias Nativas**: Todo en Java puro
- ✅ **Interfaz Gráfica Completa**: Swing UI moderna
- ✅ **Múltiples Dificultades**: Fácil, Medio, Difícil
- ✅ **Funciones Completas**: Generar, resolver, validar sudokus
- ✅ **Guardar/Cargar**: Persistencia de partidas

## 🚀 Inicio Rápido

### Requisitos
- **Java JDK 11 o superior** (no solo JRE)
- Sistema operativo: Windows 7+, Linux, macOS 10.12+

### Compilación Rápida

#### Windows:
```cmd
compile_windows.bat
```

#### Linux/macOS:
```bash
chmod +x compile_linux.sh
./compile_linux.sh
```

#### Con Maven (todas las plataformas):
```bash
mvn clean package
```

### Ejecución
```bash
java -jar target/sudokuj-2.0.0.jar
```

## 📚 Documentación Completa

- **[docs/compilacion/GUIA_COMPILACION_COMPLETA.md](docs/compilacion/GUIA_COMPILACION_COMPLETA.md)** ⭐ - Guía detallada paso a paso
  - Instalación de Java en todas las plataformas
  - Compilación en Windows, Linux y macOS
  - Creación de ejecutables (.exe, .app, AppImage)
  - Solución de problemas comunes

- **[docs/compilacion/README_ACTUALIZACION.md](docs/compilacion/README_ACTUALIZACION.md)** - Detalles técnicos de la actualización
- **[docs/compilacion/RESUMEN_ACTUALIZACION.md](docs/compilacion/RESUMEN_ACTUALIZACION.md)** - Resumen ejecutivo
- **[ESTRUCTURA_PROYECTO.md](ESTRUCTURA_PROYECTO.md)** - Estructura del repositorio
- **[PREPARAR_GITHUB.md](PREPARAR_GITHUB.md)** - Guía para subir a GitHub

## 🔧 Cambios Principales desde v1.0.1

### Problemas Resueltos

1. **Biblioteca Nativa Eliminada**
   - ❌ Original: Dependía de `libcore.so` (solo Linux 32-bit)
   - ✅ Actualizado: Implementación Java pura, multiplataforma

2. **Errores de Swing Corregidos**
   - ❌ Original: Uso incorrecto de `getGraphics()` en `paint()`
   - ✅ Actualizado: Uso correcto del parámetro `Graphics`

3. **Carga de Recursos Mejorada**
   - ❌ Original: Rutas relativas que fallaban en JAR
   - ✅ Actualizado: Carga desde classpath con fallback

4. **Bugs Corregidos**
   - ❌ Original: Bug en método `solve()` de Grid
   - ✅ Actualizado: Lógica corregida

5. **Look and Feel Multiplataforma**
   - ❌ Original: Solo Windows Look and Feel
   - ✅ Actualizado: Detecta y usa el Look and Feel apropiado

## 📁 Estructura del Proyecto

```
SuDoKuJ/
├── original/                      # Código original v1.0.1 (referencia)
│   ├── binaries/                  # JAR y bibliotecas originales
│   ├── resources/                 # Recursos originales
│   └── src-decompiled/           # Código descompilado
├── docs/                          # 📖 Documentación completa
│   ├── compilacion/               # Guías de compilación
│   └── *.pdf                     # Documentos originales
├── src/                           # Código fuente actualizado
│   ├── sudoku/                    # Paquete principal
│   │   ├── core/                  # Lógica del juego
│   │   │   ├── Core.java          # ⭐ Implementación Java pura
│   │   │   └── Grid.java          # Corregido
│   │   ├── gui/                   # Interfaz gráfica
│   │   └── util/                  # Utilidades
│   └── jguic/                     # Framework GUI interno
├── resources/                     # Recursos para compilación
│   ├── images/
│   └── background/
├── release/                       # Archivos para distribución
│   ├── sudokuj-2.0.0.jar         # JAR ejecutable (generado)
│   ├── SudokuJ.exe                # Ejecutable Windows (generado)
│   └── README.md                  # README para usuarios
├── pom.xml                        # Configuración Maven
├── compile_windows.bat            # Script compilación Windows
├── compile_linux.sh               # Script compilación Linux/macOS
├── crear_exe.bat                  # Script para crear .exe
└── README.md                      # Este archivo
```

Ver **[ESTRUCTURA_PROYECTO.md](ESTRUCTURA_PROYECTO.md)** para detalles completos.

## 🛠️ Compilación Detallada

### Opción 1: Scripts Automáticos (Recomendado)

**Windows:**
```cmd
compile_windows.bat
```

**Linux/macOS:**
```bash
./compile_linux.sh
```

### Opción 2: Maven

```bash
# Compilar
mvn clean compile

# Crear JAR
mvn clean package

# El JAR estará en: target/sudokuj-2.0.0.jar
```

### Opción 3: Manual

Ver [GUIA_COMPILACION_COMPLETA.md](GUIA_COMPILACION_COMPLETA.md) para instrucciones detalladas.

## 💻 Crear Ejecutable .exe (Windows)

### Método 1: jpackage (Java 14+)
```cmd
crear_exe.bat
```

O manualmente:
```cmd
jpackage --input target --name SudokuJ --main-jar sudokuj-2.0.0.jar --main-class sudoku.Main --type exe --dest dist
```

### Método 2: Launch4j
1. Descarga Launch4j: http://launch4j.sourceforge.net/
2. Configura:
   - Output file: `SudokuJ.exe`
   - Jar: `target\sudokuj-2.0.0.jar`
   - Min JRE version: `11.0`
3. Build wrapper

Ver [GUIA_COMPILACION_COMPLETA.md](GUIA_COMPILACION_COMPLETA.md) para más detalles.

## 🐛 Solución de Problemas

### "javac no se reconoce"
- Instala Java JDK (no solo JRE)
- Agrega `%JAVA_HOME%\bin` al PATH
- Reinicia la terminal

### "No se pueden cargar las imágenes"
- Verifica que `resources/images/` exista
- Asegúrate de que los recursos se copiaron al JAR

### "UnsupportedClassVersionError"
- Compila con la misma versión de Java que usarás para ejecutar
- Verifica: `java -version` y `javac -version` coinciden

**Más soluciones en:** [GUIA_COMPILACION_COMPLETA.md#solucion-problemas](GUIA_COMPILACION_COMPLETA.md#solucion-problemas)

## 📝 Licencia

Este proyecto mantiene la licencia **GPL v2** del proyecto original.

Ver [LICENCE.txt](LICENCE.txt) para más detalles.

## 🙏 Créditos

- **Proyecto Original**: SudokuJ 1.0.1 (2007)
  - **Autores:** Romain Huet y Nicolas Raynaud
  - Sitio: https://code.google.com/archive/p/sudokuj07/
  - Licencia: GPL v2
  
- **Actualización v2.0.0** (19-02-2026)
  - Por: https://github.com/Mostremos/SudokuJ
  - Recuperación, reversionado y mejoras para Java moderno
  - Eliminación de dependencias nativas, corrección de bugs, i18n

## 📖 Recursos Adicionales

- [Guía de Compilación Completa](docs/compilacion/GUIA_COMPILACION_COMPLETA.md)
- [Detalles de la Actualización](docs/compilacion/README_ACTUALIZACION.md)
- [Estructura del Proyecto](ESTRUCTURA_PROYECTO.md)
- [Preparar para GitHub](PREPARAR_GITHUB.md)
- [Java Downloads](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Launch4j](http://launch4j.sourceforge.net/)

## 🎯 Estado del Proyecto

- ✅ Descompilación completa
- ✅ Reemplazo de biblioteca nativa
- ✅ Corrección de bugs
- ✅ Actualización para Java 11+
- ✅ Documentación completa
- ✅ Scripts de compilación
- ⏳ Compilación y pruebas (requiere JDK instalado)
- ⏳ Creación de ejecutables (requiere compilación exitosa)

## 💡 Contribuir

Si encuentras problemas o quieres mejorar el proyecto:

1. Revisa la [Guía de Compilación](GUIA_COMPILACION_COMPLETA.md)
2. Verifica que sigues los pasos correctamente
3. Documenta cualquier problema encontrado
4. Sugiere mejoras

---

**¡Disfruta jugando Sudoku! 🎮**
