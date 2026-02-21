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
- [x] `CHANGELOG.md` - Historial de versiones
- [x] `ESTRUCTURA_PROYECTO.md` - Explicación de estructura
- [x] `pom.xml` - Configuración Maven
- [x] Scripts de compilación (1_compile_windows.bat, 2_crear_exe.bat, 3_build_release.bat)

### 3. Verificar .gitignore
- [x] `target/` excluido
- [x] `*.class` excluido
- [x] Archivos temporales excluidos
- [x] `release/*.jar` y `release/*.exe` excluidos (se generan)

## 🚀 Pasos para Subir a GitHub

### 1. Inicializar Repositorio Git (si es nuevo)

```bash
cd D:\Proyectos\SuDoKuJ
git init
git add .
git commit -m "SudokuJ v2.2.0 - Release completado"
```

### 2. Crear Repositorio en GitHub

1. Ve a https://github.com/new
2. Nombre: `sudokuj` (o SudokuJ)
3. Descripción: "SudokuJ v2.2.0 - Juego de Sudoku actualizado para Java moderno"
4. **NO** inicialices con README, .gitignore o licencia
5. Create repository

### 3. Conectar y Subir

```bash
git remote add origin https://github.com/Mostremos/SudokuJ.git
git branch -M main
git push -u origin main
```

## 📦 Para Crear un Release (v2.2.0)

### 1. Compilar

```bash
1_compile_windows.bat
2_crear_exe.bat    # opcional, para EXE Windows
```

### 2. Empaquetar release

```bash
3_build_release.bat
```

Genera:
- Carpeta `SudokuJ 2.2.0 Java` (JAR + LICENCE + README)
- Carpeta `SudokuJ 2.2.0 Windows` (EXE + archivos, si existe)

### 3. Crear ZIPs

- Comprimir `SudokuJ 2.2.0 Java` → `SudokuJ-2.2.0-Java.zip`
- Comprimir `SudokuJ 2.2.0 Windows` → `SudokuJ-2.2.0-Windows.zip`

### 4. Subir Release a GitHub

1. Repositorio → Releases → "Draft a new release"
2. Tag: `v2.2.0`
3. Título: `SudokuJ v2.2.0`
4. Descripción:
   ```markdown
   ## SudokuJ v2.2.0
   
   Actualización del juego SudokuJ (original v1.0.1) para Java 11+.
   
   ### Novedades v2.2.0
   - 7 idiomas (es, en, fr, pt, zh, ja, ru)
   - Undo/Redo hasta el inicio de la partida
   - Auto-borrado de duplicados y notas
   - Efectos visuales al completar fila/columna/cuadro/grilla
   - Pistas con resaltado persistente
   - Correcciones de Verificar/Mostrar solución y Reiniciar
   
   ### Archivos
   - **SudokuJ-2.2.0-Java.zip** — JAR para cualquier plataforma
   - **SudokuJ-2.2.0-Windows.zip** — Ejecutable Windows (opcional)
   
   ### Requisitos
   - Java 11 o superior
   
   Ver [CHANGELOG.md](CHANGELOG.md) para el historial completo.
   ```
5. Subir ambos ZIP
6. Publicar release

## 📝 Descripción del Repositorio (GitHub)

```
SudokuJ v2.2.0 - Juego de Sudoku actualizado desde la v1.0.1 original. Java 11+, multiplataforma, 7 idiomas, undo ampliado, efectos visuales. GPL v2.
```

## 🏷️ Tags Recomendados

- `java`
- `sudoku`
- `game`
- `swing`
- `desktop-application`
- `gpl-2.0`
- `multiplatform`

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
