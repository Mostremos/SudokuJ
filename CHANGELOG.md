# Changelog

## [2.2.0] - 2026-02-19

### Añadido
- 7 idiomas: español, inglés, francés, portugués, chino, japonés, ruso
- Undo/Redo hasta el inicio de la partida (100 pasos)
- Auto-borrado de duplicados y notas al colocar número (fila, columna, cuadro)
- Efectos visuales (flash verde) al completar fila, columna, cuadro o grilla entera
- Resaltado persistente de celdas rellenadas por pista (marca amarilla)
- Menú "Acciones" con sección "Al crear grilla" (Encontrar solución, Mostrar solución, Validar)

### Corregido
- Bug en Verificar solución / Mostrar solución (uso correcto de `solution[row][col]`)
- Reiniciar restaura correctamente tras completar partida
- Borrado de notas solo en celdas vacías (no elimina valores colocados)

### Cambios técnicos
- `UndoManager` ampliado a 100 comandos
- `HintCellHighlightCommand` y sistema de celdas de pista
- `CompletionFlashCommand` con soporte para grilla completa
- Fuentes `SANS_SERIF` para zh/ja en etiquetas

---

## [2.1.0] - (Anterior)

- Recuperación y reversionado del proyecto original
- Eliminación de dependencias nativas
- Corrección de bugs
- i18n inicial (es/en/fr)
- Actualización para Java 11+

---

## [2.0.0] - (Anterior)

- Actualización completa desde v1.0.1
- Implementación Java pura (sin libcore.so)
- Multiplataforma
- Documentación y scripts de compilación

---

## [1.0.1] - 2007

- Versión original por Romain Huet y Nicolas Raynaud
- https://code.google.com/archive/p/sudokuj07/
