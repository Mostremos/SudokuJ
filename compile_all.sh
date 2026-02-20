#!/bin/bash
# Script para compilar todos los archivos Java

JAVAC="/c/Program Files/Android/openjdk/jdk-21.0.8/bin/javac.exe"
SRC="src"
OUT="target/classes"

echo "Compilando todos los archivos Java..."
echo

ERRORS=0
COUNT=0

# Compilar todos los archivos Java
find "$SRC" -name "*.java" | while read file; do
    COUNT=$((COUNT + 1))
    echo "[$COUNT] Compilando: $file"
    "$JAVAC" -d "$OUT" -sourcepath "$SRC" -encoding UTF-8 -cp "$OUT" "$file" 2>&1 | grep -i "error:" && ERRORS=$((ERRORS + 1))
done

echo
if [ $ERRORS -eq 0 ]; then
    echo "✓ Compilación exitosa!"
else
    echo "✗ Se encontraron $ERRORS errores"
fi
