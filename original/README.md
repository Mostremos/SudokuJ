# Código Original - SudokuJ v1.0.1

Este directorio contiene el código y recursos originales de SudokuJ v1.0.1, tal como fueron distribuidos por el autor original.

## 📁 Estructura

- **binaries/** - Archivos binarios originales
  - `sudokuj.jar` - JAR ejecutable original
  - `libcore.so` - Biblioteca nativa Linux (32-bit)
  - `options.ser` - Archivo de configuración serializado

- **resources/** - Recursos originales
  - `background/` - Imágenes de fondo
  - `images/` - Iconos e imágenes de la interfaz

- **src-decompiled/** - Código fuente descompilado
  - Código Java extraído del JAR original usando CFR
  - **Nota:** Este código no fue modificado, es la descompilación directa

- **LICENCE.txt** - Licencia GPL v2 original
- **LISEZMOI.txt** - README original en francés

## 🔍 Información del Proyecto Original

- **Nombre:** SudokuJ
- **Versión:** 1.0.1
- **Autores:** Romain Huet y Nicolas Raynaud
- **Sitio Original:** https://code.google.com/archive/p/sudokuj07/
- **Estado:** Discontinuado
- **Licencia:** GPL v2
- **Fecha:** 2007

## ⚠️ Problemas Conocidos

1. **Biblioteca Nativa:** `libcore.so` solo funciona en Linux 32-bit
2. **Java Antiguo:** Requiere Java 5.0, no funciona con versiones modernas
3. **Errores de Swing:** Uso incorrecto de `getGraphics()` en `paint()`
4. **Carga de Recursos:** Rutas relativas no funcionan en JAR

## 📝 Uso

Este código se proporciona como referencia histórica y para:
- Comparar con la versión actualizada
- Entender cómo funcionaba el código original
- Aprender sobre descompilación de JARs
- Realizar actualizaciones alternativas

## 🔄 Actualización

La versión actualizada (v2.0.0) se encuentra en el directorio raíz del proyecto y:
- ✅ Funciona en Windows, Linux y macOS
- ✅ Compatible con Java 11+
- ✅ Sin dependencias nativas
- ✅ Bugs corregidos
- ✅ Mejoras de compatibilidad

---

**Nota:** Este código se mantiene solo como referencia. Para usar el juego, utiliza la versión actualizada en el directorio raíz.
