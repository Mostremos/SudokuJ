# Guía para Subir a GitHub

## ✅ Checklist Antes de Subir

### 1. Verificar Estructura
- [x] `original/` - Código original organizado
- [x] `docs/` - Documentación completa
- [x] `src/` - Código fuente actualizado
- [x] `resources/` - Recursos para compilación
- [x] `release/` - Preparado para releases
- [x] `.gitignore` - Configurado correctamente

### 2. Archivos Importantes
- [x] `README.md` - Documentación principal
- [x] `ESTRUCTURA_PROYECTO.md` - Explicación de estructura
- [x] `pom.xml` - Configuración Maven
- [x] Scripts de compilación

### 3. Verificar .gitignore
- [x] `target/` excluido
- [x] `*.class` excluido
- [x] Archivos temporales excluidos
- [x] `release/*.jar` y `release/*.exe` excluidos (se generan)

## 🚀 Pasos para Subir a GitHub

### 1. Inicializar Repositorio Git

```bash
cd D:\Proyectos\SuDoKuJ
git init
git add .
git commit -m "Initial commit: SudokuJ v2.0.0 - Actualización completa"
```

### 2. Crear Repositorio en GitHub

1. Ve a https://github.com/new
2. Nombre del repositorio: `sudokuj` (o el que prefieras)
3. Descripción: "SudokuJ v2.0.0 - Juego de Sudoku actualizado para Java moderno"
4. **NO** inicialices con README, .gitignore o licencia (ya los tenemos)
5. Clic en "Create repository"

### 3. Conectar y Subir

```bash
# Agregar remoto (reemplaza TU-USUARIO con tu usuario de GitHub)
git remote add origin https://github.com/TU-USUARIO/sudokuj.git

# Cambiar a rama main (si es necesario)
git branch -M main

# Subir código
git push -u origin main
```

## 📦 Para Crear un Release

### 1. Compilar el Proyecto

```bash
# Windows
compile_windows.bat

# Linux/macOS
./compile_linux.sh

# O con Maven
mvn clean package
```

### 2. Preparar Release

```bash
# Windows
preparar_release.bat
```

Esto copiará:
- `target/sudokuj-2.0.0.jar` → `release/sudokuj-2.0.0.jar`
- `dist/SudokuJ.exe` → `release/SudokuJ.exe` (si existe)

### 3. Crear ZIP de Release

```bash
# Navegar a release/
cd release

# Crear ZIP (Windows)
# Seleccionar todos los archivos y crear ZIP
# Nombre: SudokuJ-v2.0.0-release.zip
```

### 4. Subir Release a GitHub

1. Ve a tu repositorio en GitHub
2. Clic en "Releases" → "Create a new release"
3. Tag: `v2.0.0`
4. Título: `SudokuJ v2.0.0`
5. Descripción:
   ```markdown
   ## SudokuJ v2.0.0
   
   Actualización completa del juego SudokuJ original (v1.0.1) para Java moderno.
   
   ### Cambios Principales
   - ✅ Compatible con Java 11+
   - ✅ Multiplataforma (Windows, Linux, macOS)
   - ✅ Sin dependencias nativas
   - ✅ Bugs corregidos
   - ✅ Mejoras de compatibilidad
   
   ### Archivos
   - `sudokuj-2.0.0.jar` - JAR ejecutable
   - `SudokuJ.exe` - Ejecutable Windows (opcional)
   
   ### Requisitos
   - Java 11 o superior
   
   Ver [README.md](README.md) para más información.
   ```
6. Subir archivo ZIP: `SudokuJ-v2.0.0-release.zip`
7. Publicar release

## 📝 Descripción del Repositorio (GitHub)

Usa esta descripción para el repositorio:

```
SudokuJ v2.0.0 - Juego de Sudoku actualizado desde la versión original 1.0.1 (discontinuada). Compatible con Java 11+, multiplataforma, sin dependencias nativas. Incluye documentación completa de compilación para Windows, Linux y macOS.
```

## 🏷️ Tags Recomendados

- `java`
- `sudoku`
- `game`
- `swing`
- `desktop-application`
- `gpl-2.0`
- `multiplatform`
- `legacy-update`

## 📋 README para GitHub

El `README.md` principal ya está preparado y incluye:
- ✅ Badges (Java, License, Platform)
- ✅ Descripción del proyecto
- ✅ Características
- ✅ Inicio rápido
- ✅ Enlaces a documentación
- ✅ Estructura del proyecto
- ✅ Instrucciones de compilación
- ✅ Solución de problemas

## 🔗 Enlaces Útiles

- **GitHub Releases:** https://github.com/TU-USUARIO/sudokuj/releases
- **Issues:** https://github.com/TU-USUARIO/sudokuj/issues
- **Wiki:** (opcional) Para documentación adicional

## ✅ Verificación Final

Antes de hacer push, verifica:

```bash
# Ver qué se va a subir
git status

# Ver archivos que se ignorarán
git status --ignored

# Ver tamaño del repositorio
du -sh .git
```

### Archivos que NO deben subirse:
- ❌ `target/` (generado)
- ❌ `*.class` (compilados)
- ❌ `release/*.jar` y `release/*.exe` (generados)
- ❌ Archivos temporales

### Archivos que SÍ deben subirse:
- ✅ Todo `src/`
- ✅ Todo `docs/`
- ✅ Todo `original/`
- ✅ `resources/`
- ✅ Scripts de compilación
- ✅ `pom.xml`
- ✅ `README.md` y documentación
- ✅ `.gitignore`

---

**¡Listo para subir a GitHub! 🚀**
