# SudokuJ - Actualización a Java Moderno

Este proyecto es una actualización del juego SudokuJ original (versión 1.0.1) para que funcione con versiones modernas de Java (Java 11+) y sea multiplataforma.

## Cambios Realizados

### 1. Descompilación
- El JAR original (`sudokuj.jar`) fue descompilado usando CFR
- Todo el código fuente fue extraído y organizado

### 2. Reemplazo de Biblioteca Nativa
- **Problema**: El código original dependía de `libcore.so` (biblioteca nativa Linux) para generar y resolver sudokus
- **Solución**: Se creó una implementación Java pura en `sudoku.core.Core` que reemplaza las funciones nativas:
  - `generate(int difficulty)`: Genera sudokus de diferentes dificultades
  - `solve(short[] grid)`: Resuelve sudokus usando backtracking
  - `validate(short[] grid)`: Valida que un sudoku sea correcto

### 3. Correcciones de Compatibilidad
- **Main.java**: 
  - Corregido uso incorrecto de `getGraphics()` en `paint()` (ahora usa el parámetro `Graphics g`)
  - Mejorado Look and Feel para ser multiplataforma
  - Carga de recursos desde classpath usando `ResourceLoader`
  
- **Grid.java**: 
  - Corregido bug en el método `solve()` que copiaba incorrectamente la solución

- **Carga de Recursos**: 
  - Creada clase `ResourceLoader` para cargar imágenes desde el classpath
  - Permite que la aplicación funcione tanto desde JAR como desde sistema de archivos

### 4. Estructura del Proyecto
- Configurado proyecto Maven (`pom.xml`)
- Organizados recursos en directorio `resources/`
- Código fuente en `src/`

## Requisitos

- **Java 11 o superior** (JDK, no solo JRE)
- **Maven 3.6+** (opcional, para compilación con Maven)

## Compilación

### Opción 1: Usando Maven (Recomendado)

```bash
# Compilar el proyecto
mvn clean compile

# Crear JAR ejecutable
mvn clean package

# El JAR estará en: target/sudokuj-2.0.0.jar
```

### Opción 2: Compilación Manual

Si no tienes Maven instalado, puedes compilar manualmente:

```bash
# 1. Crear directorio de clases
mkdir -p target/classes

# 2. Compilar todos los archivos Java
javac -d target/classes -sourcepath src -encoding UTF-8 \
  $(find src -name "*.java")

# 3. Copiar recursos
cp -r resources/* target/classes/

# 4. Crear JAR
jar cfe target/sudokuj-2.0.0.jar sudoku.Main -C target/classes .
```

## Ejecución

### Desde JAR:
```bash
java -jar target/sudokuj-2.0.0.jar
```

### Desde código compilado:
```bash
java -cp target/classes sudoku.Main
```

## Crear Ejecutable .exe (Windows)

Para crear un ejecutable `.exe` nativo, necesitas Java 14+ con `jpackage`:

```bash
# Crear imagen de runtime
jpackage --input target \
  --name SudokuJ \
  --main-jar sudokuj-2.0.0.jar \
  --main-class sudoku.Main \
  --type exe \
  --dest dist
```

O usando Maven con el plugin jpackage (requiere Java 14+):
```bash
mvn clean package
# Luego usar jpackage manualmente o configurar el plugin
```

### Alternativa: Launch4j

Si no tienes Java 14+, puedes usar [Launch4j](http://launch4j.sourceforge.net/) para crear un `.exe` wrapper:

1. Descarga e instala Launch4j
2. Configura:
   - Output file: `SudokuJ.exe`
   - Jar: `target/sudokuj-2.0.0.jar`
   - Min JRE version: `11.0`
3. Genera el ejecutable

## Estructura del Proyecto

```
SuDoKuJ/
├── src/                    # Código fuente Java
│   ├── sudoku/            # Paquete principal
│   │   ├── core/          # Lógica del juego
│   │   ├── gui/           # Interfaz gráfica
│   │   ├── commands/      # Comandos del patrón Command
│   │   └── util/          # Utilidades
│   └── jguic/             # Framework GUI interno
├── resources/              # Recursos (imágenes, etc.)
│   ├── images/
│   └── background/
├── pom.xml                 # Configuración Maven
└── README_ACTUALIZACION.md # Este archivo
```

## Notas Importantes

1. **Observable/Observer**: El código usa `java.util.Observable` y `Observer` que están deprecados desde Java 9, pero siguen funcionando. Para una actualización futura, se podría migrar a un patrón Observer personalizado.

2. **Recursos**: Las imágenes se cargan desde el classpath. Si ejecutas desde el sistema de archivos, también intentará cargar desde rutas relativas como fallback.

3. **Compatibilidad**: El código ha sido actualizado para Java 11+, pero mantiene compatibilidad con el diseño original.

## Problemas Conocidos

- Algunos archivos GUI aún cargan imágenes desde rutas relativas directamente. Funcionará si los recursos están en el classpath o en el directorio de trabajo.

## Licencia

Este proyecto mantiene la licencia GPL v2 del proyecto original.

## Créditos

- **Proyecto original:** SudokuJ 1.0.1 (2007) — Autores: Romain Huet y Nicolas Raynaud — https://code.google.com/archive/p/sudokuj07/
- **Actualización v2.0.0:** 19-02-2026 — https://github.com/Mostremos/SudokuJ
