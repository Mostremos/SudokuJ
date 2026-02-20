# Guía Completa de Compilación - SudokuJ

## 📋 Índice
1. [Problemas Encontrados y Soluciones](#problemas-encontrados)
2. [Requisitos del Sistema](#requisitos)
3. [Instalación de Java JDK](#instalacion-java)
4. [Compilación en Windows](#compilacion-windows)
5. [Compilación en Linux](#compilacion-linux)
6. [Compilación en macOS](#compilacion-macos)
7. [Crear Ejecutable .exe (Windows)](#crear-exe)
8. [Crear Ejecutables en Linux/macOS](#crear-ejecutables-unix)
9. [Solución de Problemas](#solucion-problemas)

---

## 🔧 Problemas Encontrados y Soluciones {#problemas-encontrados}

### Problema 1: Biblioteca Nativa Incompatible
**Error Original:**
- El juego dependía de `libcore.so` (biblioteca nativa Linux de 32 bits)
- No funcionaba en Windows ni macOS
- No compatible con versiones modernas de Java

**Solución Implementada:**
- ✅ Reemplazada por implementación Java pura en `sudoku.core.Core`
- ✅ Algoritmos de generación, resolución y validación en Java
- ✅ Compatible con todas las plataformas

**Archivos Modificados:**
- `src/sudoku/core/Core.java` (completamente reescrito)

---

### Problema 2: Uso Incorrecto de Graphics en Swing
**Error Original:**
```java
public void paint(Graphics g) {
    this.getGraphics().drawImage(this.bg, 0, 0, this);  // ❌ INCORRECTO
    this.getGraphics().drawImage(this.bar, this.x_bar, 313, this);
}
```

**Problema:**
- `getGraphics()` puede retornar `null` o un objeto no válido
- No es la forma correcta de dibujar en Swing
- Causa errores en versiones modernas de Java

**Solución Implementada:**
```java
@Override
public void paint(Graphics g) {
    if (this.bg != null) {
        g.drawImage(this.bg, 0, 0, this);  // ✅ CORRECTO
    }
    if (this.bar != null) {
        g.drawImage(this.bar, this.x_bar, 313, this);
    }
}
```

**Archivos Modificados:**
- `src/sudoku/Main.java` (clase Splash)

---

### Problema 3: Carga de Recursos desde Rutas Relativas
**Error Original:**
```java
ImageIcon bg_icon = new ImageIcon("images/logo.png");  // ❌ No funciona en JAR
```

**Problema:**
- Las rutas relativas no funcionan cuando la app está empaquetada en JAR
- Depende del directorio de trabajo actual
- No es portable

**Solución Implementada:**
- ✅ Creada clase `ResourceLoader` para cargar desde classpath
- ✅ Fallback a sistema de archivos para desarrollo
- ✅ Compatible con JAR y ejecución directa

**Archivos Creados:**
- `src/sudoku/util/ResourceLoader.java`

**Archivos Modificados:**
- `src/sudoku/Main.java`

---

### Problema 4: Bug en Método solve() de Grid
**Error Original:**
```java
if (Core.solve(grid)) {
    while (c > 0) {  // ❌ BUG: c ya es 81, el bucle está mal
        i = --c / 9;
        j = c % 9;
        this.solution[i][j] = grid[c];
    }
}
```

**Problema:**
- El contador `c` ya había terminado en 81
- El bucle copiaba incorrectamente la solución
- La solución no se guardaba correctamente

**Solución Implementada:**
```java
if (Core.solve(grid)) {
    // ✅ CORRECTO: Reiniciar contador y copiar correctamente
    c = 0;
    i = 0;
    while (i < 9) {
        int j = 0;
        while (j < 9) {
            this.solution[i][j] = grid[c];
            ++c;
            ++j;
        }
        ++i;
    }
    return true;
}
```

**Archivos Modificados:**
- `src/sudoku/core/Grid.java`

---

### Problema 5: Look and Feel Específico de Windows
**Error Original:**
```java
UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
```

**Problema:**
- Solo funciona en Windows
- Falla en Linux y macOS
- No es multiplataforma

**Solución Implementada:**
```java
String os = System.getProperty("os.name").toLowerCase();
if (os.contains("windows")) {
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
} else if (os.contains("mac")) {
    UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
} else {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}
```

**Archivos Modificados:**
- `src/sudoku/Main.java`

---

### Problema 6: Constantes Mágicas
**Error Original:**
```java
this.window.setDefaultCloseOperation(3);  // ❌ ¿Qué es 3?
```

**Solución Implementada:**
```java
this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ✅ Claro y legible
```

**Archivos Modificados:**
- `src/sudoku/Main.java`

---

## 📦 Requisitos del Sistema {#requisitos}

### Mínimos:
- **Java JDK 11 o superior** (no solo JRE, necesitas el compilador)
- **Sistema operativo**: Windows 7+, Linux, macOS 10.12+
- **Memoria**: 512 MB RAM mínimo
- **Espacio en disco**: 50 MB

### Recomendados:
- **Java JDK 17 LTS** (recomendado para mejor rendimiento)
- **Maven 3.6+** (opcional, facilita la compilación)
- **Memoria**: 1 GB RAM
- **Espacio en disco**: 100 MB

---

## ☕ Instalación de Java JDK {#instalacion-java}

### Windows

#### Opción 1: Descarga Manual
1. Visita: https://adoptium.net/ (Eclipse Temurin) o https://www.oracle.com/java/technologies/downloads/
2. Descarga JDK 11 o superior (recomendado: JDK 17 LTS)
3. Ejecuta el instalador
4. **IMPORTANTE**: Marca la opción "Add to PATH" durante la instalación
5. Verifica la instalación:
   ```cmd
   java -version
   javac -version
   ```

#### Opción 2: Usando Chocolatey
```cmd
choco install openjdk17
```

#### Opción 3: Usando Winget
```cmd
winget install Microsoft.OpenJDK.17
```

### Linux

#### Ubuntu/Debian:
```bash
# Actualizar repositorios
sudo apt update

# Instalar JDK 17
sudo apt install openjdk-17-jdk

# Verificar
java -version
javac -version
```

#### Fedora/RHEL/CentOS:
```bash
sudo dnf install java-17-openjdk-devel
```

#### Arch Linux:
```bash
sudo pacman -S jdk-openjdk
```

### macOS

#### Opción 1: Homebrew (Recomendado)
```bash
# Instalar Homebrew si no lo tienes
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Instalar JDK
brew install openjdk@17

# Agregar al PATH (agrega esto a ~/.zshrc o ~/.bash_profile)
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

#### Opción 2: Descarga Manual
1. Visita: https://adoptium.net/
2. Descarga el instalador .pkg para macOS
3. Ejecuta el instalador
4. Configura JAVA_HOME:
   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home -v 17)
   ```

---

## 🪟 Compilación en Windows {#compilacion-windows}

### Método 1: Usando Maven (Recomendado)

#### Paso 1: Instalar Maven
```cmd
# Con Chocolatey
choco install maven

# O descarga manual desde: https://maven.apache.org/download.cgi
```

#### Paso 2: Verificar Maven
```cmd
mvn -version
```

#### Paso 3: Compilar
```cmd
cd D:\Proyectos\SuDoKuJ
mvn clean compile
mvn clean package
```

El JAR estará en: `target\sudokuj-2.0.0.jar`

#### Paso 4: Ejecutar
```cmd
java -jar target\sudokuj-2.0.0.jar
```

---

### Método 2: Compilación Manual (Sin Maven)

#### Paso 1: Preparar Directorios
```cmd
cd D:\Proyectos\SuDoKuJ
mkdir target\classes
```

#### Paso 2: Compilar Código Fuente
```cmd
javac -d target\classes -sourcepath src -encoding UTF-8 src\sudoku\*.java src\sudoku\**\*.java src\jguic\*.java src\jguic\**\*.java
```

Si hay muchos archivos, usa un script:

**compile_windows.bat:**
```batch
@echo off
setlocal enabledelayedexpansion

echo === Compilando SudokuJ ===

set SRC=src
set OUT=target\classes
set JAR=target\sudokuj-2.0.0.jar

REM Crear directorios
if not exist "%OUT%" mkdir "%OUT%"
if not exist "target" mkdir "target"

REM Compilar todos los archivos Java
echo Compilando archivos Java...
for /r "%SRC%" %%f in (*.java) do (
    echo Compilando: %%f
    javac -d "%OUT%" -sourcepath "%SRC%" -encoding UTF-8 -cp "%OUT%" "%%f" 2>>compile_errors.txt
    if errorlevel 1 (
        echo ERROR compilando: %%f
    )
)

REM Copiar recursos
echo Copiando recursos...
if exist "resources" (
    xcopy /E /I /Y "resources\*" "%OUT%\" >nul
)

REM Crear JAR
echo Creando JAR...
jar cfe "%JAR%" sudoku.Main -C "%OUT%" .

if exist "%JAR%" (
    echo.
    echo [OK] Compilacion exitosa!
    echo JAR: %JAR%
    echo.
    echo Ejecutar con: java -jar %JAR%
) else (
    echo ERROR: No se pudo crear el JAR
)

pause
```

#### Paso 3: Ejecutar Script
```cmd
compile_windows.bat
```

---

## 🐧 Compilación en Linux {#compilacion-linux}

### Método 1: Usando Maven

```bash
# Instalar Maven (si no está instalado)
sudo apt install maven  # Ubuntu/Debian
# o
sudo dnf install maven  # Fedora

# Compilar
cd ~/SuDoKuJ
mvn clean package

# Ejecutar
java -jar target/sudokuj-2.0.0.jar
```

### Método 2: Compilación Manual

```bash
#!/bin/bash
# compile_linux.sh

echo "=== Compilando SudokuJ ==="

SRC="src"
OUT="target/classes"
JAR="target/sudokuj-2.0.0.jar"

# Crear directorios
mkdir -p "$OUT"

# Compilar todos los archivos Java
echo "Compilando archivos Java..."
find "$SRC" -name "*.java" | while read file; do
    echo "Compilando: $file"
    javac -d "$OUT" -sourcepath "$SRC" -encoding UTF-8 -cp "$OUT" "$file" 2>>compile_errors.txt
done

# Verificar errores
if [ -s compile_errors.txt ]; then
    echo "ERRORES ENCONTRADOS:"
    cat compile_errors.txt
    exit 1
fi

# Copiar recursos
echo "Copiando recursos..."
if [ -d "resources" ]; then
    cp -r resources/* "$OUT/"
fi

# Crear JAR
echo "Creando JAR..."
mkdir -p target
jar cfe "$JAR" sudoku.Main -C "$OUT" .

if [ -f "$JAR" ]; then
    echo ""
    echo "[OK] Compilación exitosa!"
    echo "JAR: $JAR"
    echo ""
    echo "Ejecutar con: java -jar $JAR"
else
    echo "ERROR: No se pudo crear el JAR"
    exit 1
fi
```

Hacer ejecutable y ejecutar:
```bash
chmod +x compile_linux.sh
./compile_linux.sh
```

---

## 🍎 Compilación en macOS {#compilacion-macos}

### Método 1: Usando Maven

```bash
# Instalar Maven (si no está instalado)
brew install maven

# Compilar
cd ~/SuDoKuJ
mvn clean package

# Ejecutar
java -jar target/sudokuj-2.0.0.jar
```

### Método 2: Compilación Manual

Usa el mismo script que Linux (`compile_linux.sh`), funciona igual en macOS.

---

## 💻 Crear Ejecutable .exe (Windows) {#crear-exe}

### Método 1: Usando jpackage (Java 14+)

**Requisitos:**
- Java JDK 14 o superior
- WiX Toolset (para Windows): https://wixtoolset.org/

**Pasos:**

1. **Compilar el JAR primero:**
   ```cmd
   mvn clean package
   ```

2. **Crear ejecutable:**
   ```cmd
   jpackage --input target ^
     --name SudokuJ ^
     --main-jar sudokuj-2.0.0.jar ^
     --main-class sudoku.Main ^
     --type exe ^
     --dest dist ^
     --app-version 2.0.0 ^
     --vendor "SudokuJ Team" ^
     --win-dir-chooser ^
     --win-menu ^
     --win-shortcut
   ```

3. **El ejecutable estará en:** `dist\SudokuJ.exe`

---

### Método 2: Usando Launch4j (Recomendado para Java 8-13)

**Descarga:** http://launch4j.sourceforge.net/

**Configuración:**

1. Abre Launch4j
2. **Basic Tab:**
   - Output file: `SudokuJ.exe`
   - Jar: `target\sudokuj-2.0.0.jar`
   - Min JRE version: `11.0`
   - Max JRE version: (dejar vacío)
   - JRE search order: `registry, javaHome, environment`

3. **JRE Tab:**
   - Bundled JRE path: (opcional, para incluir JRE)
   - JRE path: (dejar vacío si quieres usar JRE del sistema)

4. **Version Info Tab (Opcional):**
   - File version: `2.0.0.0`
   - Product version: `2.0.0`
   - Company name: `SudokuJ Team`
   - Product name: `SudokuJ`

5. **Build wrapper:**
   - Click en "Build wrapper"
   - El .exe se generará

---

### Método 3: Usando jlink + Script (Java 9+)

```cmd
REM Crear runtime personalizado
jlink --add-modules java.base,java.desktop ^
      --output target\runtime ^
      --strip-debug ^
      --compress=2

REM Crear script launcher
echo @echo off > SudokuJ.bat
echo "%~dp0\runtime\bin\java.exe" -jar "%~dp0\sudokuj-2.0.0.jar" %%* >> SudokuJ.bat
```

---

## 🐧 Crear Ejecutables en Linux/macOS {#crear-ejecutables-unix}

### Linux: Usando jpackage

```bash
# Compilar primero
mvn clean package

# Crear AppImage o .deb
jpackage --input target \
  --name SudokuJ \
  --main-jar sudokuj-2.0.0.jar \
  --main-class sudoku.Main \
  --type app-image \
  --dest dist \
  --app-version 2.0.0 \
  --vendor "SudokuJ Team"
```

### macOS: Usando jpackage

```bash
# Compilar primero
mvn clean package

# Crear .app bundle
jpackage --input target \
  --name SudokuJ \
  --main-jar sudokuj-2.0.0.jar \
  --main-class sudoku.Main \
  --type dmg \
  --dest dist \
  --app-version 2.0.0 \
  --vendor "SudokuJ Team" \
  --mac-package-name "SudokuJ"
```

---

## 🔍 Solución de Problemas {#solucion-problemas}

### Error: "javac no se reconoce como comando"
**Solución:**
- Instala JDK (no solo JRE)
- Agrega `%JAVA_HOME%\bin` al PATH
- Reinicia la terminal

### Error: "No se puede encontrar o cargar la clase principal"
**Solución:**
- Verifica que el JAR tenga el manifest correcto
- Usa: `java -cp target/classes sudoku.Main` en su lugar

### Error: "No se pueden cargar las imágenes"
**Solución:**
- Verifica que `resources/images/` exista
- Asegúrate de que los recursos se copiaron al JAR
- Ejecuta desde el directorio del proyecto

### Error: "UnsupportedClassVersionError"
**Solución:**
- Compila con la misma versión de Java que usarás para ejecutar
- Verifica: `java -version` y `javac -version` coinciden

### Error: "OutOfMemoryError" al compilar
**Solución:**
```cmd
set JAVA_OPTS=-Xmx1024m
javac ...
```

### Error en Maven: "mvn no se reconoce"
**Solución:**
- Instala Maven
- Agrega Maven al PATH
- Reinicia la terminal

---

## 📚 Recursos Adicionales

- **Java Downloads**: https://adoptium.net/
- **Maven**: https://maven.apache.org/
- **Launch4j**: http://launch4j.sourceforge.net/
- **jpackage Guide**: https://docs.oracle.com/en/java/javase/14/docs/specs/man/jpackage.html

---

## ✅ Verificación Final

Después de compilar, verifica:

1. ✅ El JAR se creó: `target/sudokuj-2.0.0.jar`
2. ✅ Puedes ejecutarlo: `java -jar target/sudokuj-2.0.0.jar`
3. ✅ La ventana del juego se abre
4. ✅ Puedes generar un nuevo sudoku
5. ✅ Puedes jugar y resolver

---

**¡Feliz compilación! 🎮**
