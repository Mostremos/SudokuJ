#!/bin/bash
# Script de compilación mejorado para Linux/macOS
# Compila SudokuJ sin necesidad de Maven

set -e  # Salir si hay errores

echo "========================================"
echo "   Compilando SudokuJ v2.0.0"
echo "========================================"
echo

# Configuración
SRC="src"
OUT="target/classes"
JAR="target/sudokuj-2.0.0.jar"
MAIN_CLASS="sudoku.Main"
ERRORS="compile_errors.txt"

# Verificar Java
if ! command -v javac &> /dev/null; then
    echo "[ERROR] Java JDK no encontrado"
    echo
    echo "Por favor:"
    echo "1. Instala Java JDK 11 o superior"
    echo "2. Agrega javac al PATH"
    echo
    echo "Ubuntu/Debian: sudo apt install openjdk-17-jdk"
    echo "Fedora: sudo dnf install java-17-openjdk-devel"
    echo "macOS: brew install openjdk@17"
    echo
    exit 1
fi

# Mostrar versión de Java
echo "[INFO] Versión de Java:"
javac -version
java -version 2>&1 | head -1
echo

# Crear directorios
echo "[1/4] Creando directorios..."
mkdir -p "$OUT"
mkdir -p target
rm -f "$ERRORS"
echo "[OK] Directorios creados"
echo

# Compilar
echo "[2/4] Compilando archivos Java..."
echo "Esto puede tardar unos momentos..."
echo

COUNT=0
ERR_COUNT=0

# Encontrar y compilar todos los archivos Java
while IFS= read -r file; do
    COUNT=$((COUNT + 1))
    echo "[$COUNT] Compilando: $file"
    if ! javac -d "$OUT" -sourcepath "$SRC" -encoding UTF-8 -cp "$OUT" "$file" >>"$ERRORS" 2>&1; then
        ERR_COUNT=$((ERR_COUNT + 1))
        echo "        [ERROR] Fallo al compilar"
    fi
done < <(find "$SRC" -name "*.java" -type f)

echo
echo "[INFO] Archivos compilados: $COUNT"
if [ $ERR_COUNT -gt 0 ]; then
    echo "[WARNING] Errores encontrados: $ERR_COUNT"
    echo "Revisa $ERRORS para más detalles"
else
    echo "[OK] Compilación exitosa"
fi
echo

# Verificar si hay errores críticos
if [ -s "$ERRORS" ]; then
    if grep -q "error:" "$ERRORS"; then
        echo "[ERROR] Errores críticos encontrados. Abortando."
        cat "$ERRORS"
        exit 1
    fi
fi

# Copiar recursos
echo "[3/4] Copiando recursos..."
if [ -d "resources" ]; then
    cp -r resources/* "$OUT/" 2>/dev/null || true
    echo "[OK] Recursos copiados"
else
    echo "[WARNING] Directorio resources no encontrado"
fi
echo

# Crear JAR
echo "[4/4] Creando JAR..."
jar cfe "$JAR" "$MAIN_CLASS" -C "$OUT" . >/dev/null 2>&1

if [ -f "$JAR" ]; then
    echo "[OK] JAR creado exitosamente"
    echo
    echo "========================================"
    echo "   Compilación Completada"
    echo "========================================"
    echo
    echo "Archivo: $JAR"
    echo "Tamaño: $(du -h "$JAR" | cut -f1)"
    echo
    echo "Para ejecutar:"
    echo "  java -jar $JAR"
    echo
    echo "O hacer ejecutable y ejecutar directamente:"
    echo "  chmod +x $JAR"
    echo "  ./$JAR"
    echo
else
    echo "[ERROR] No se pudo crear el JAR"
    echo "Verifica los errores en $ERRORS"
    exit 1
fi

# Limpiar archivo de errores si está vacío
if [ -f "$ERRORS" ] && [ ! -s "$ERRORS" ]; then
    rm -f "$ERRORS"
fi

echo "¡Compilación exitosa!"
