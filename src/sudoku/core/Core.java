package sudoku.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Implementación Java pura de las funciones nativas de generación,
 * resolución y validación de Sudokus.
 * Reemplaza la biblioteca nativa libcore.so para compatibilidad multiplataforma.
 */
public class Core {
    private static final Random random = new Random();
    
    // Niveles de dificultad: número de celdas a eliminar
    private static final int[] DIFFICULTY_CELLS = {
        35,  // EASY: 35 celdas vacías
        45,  // MEDIUM: 45 celdas vacías
        55   // HARD: 55 celdas vacías
    };

    /**
     * Genera un sudoku completo y luego elimina celdas según la dificultad.
     * @param difficulty Nivel de dificultad (0=EASY, 1=MEDIUM, 2=HARD)
     * @return Array de 81 elementos con el sudoku (0 = celda vacía)
     */
    public static short[] generate(int difficulty) {
        if (difficulty < 0 || difficulty >= DIFFICULTY_CELLS.length) {
            difficulty = 0;
        }
        
        // Generar un sudoku completo válido
        short[] completeGrid = generateCompleteSudoku();
        
        // Crear una lista de todas las posiciones
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            positions.add(i);
        }
        
        // Mezclar aleatoriamente
        Collections.shuffle(positions, random);
        
        // Eliminar celdas según la dificultad
        int cellsToRemove = DIFFICULTY_CELLS[difficulty];
        for (int i = 0; i < cellsToRemove && i < positions.size(); i++) {
            completeGrid[positions.get(i)] = 0;
        }
        
        return completeGrid;
    }

    /**
     * Genera un sudoku completo válido usando backtracking.
     */
    private static short[] generateCompleteSudoku() {
        short[] grid = new short[81];
        
        // Llenar la diagonal de las 3 cajas principales con números aleatorios
        fillDiagonalBoxes(grid);
        
        // Resolver el resto usando backtracking
        solveSudoku(grid);
        
        return grid;
    }

    /**
     * Llena las 3 cajas diagonales con números aleatorios válidos.
     */
    private static void fillDiagonalBoxes(short[] grid) {
        for (int box = 0; box < 9; box += 3) {
            fillBox(grid, box, box);
        }
    }

    /**
     * Llena una caja 3x3 con números aleatorios válidos.
     */
    private static void fillBox(short[] grid, int row, int col) {
        List<Short> numbers = new ArrayList<>();
        for (short i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, random);
        
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int pos = (row + i) * 9 + (col + j);
                grid[pos] = numbers.get(index++);
            }
        }
    }

    /**
     * Resuelve un sudoku usando backtracking.
     * @param grid Array de 81 elementos (modificado in-place)
     * @return true si se encontró una solución
     */
    public static boolean solve(short[] grid) {
        return solveSudoku(grid);
    }

    /**
     * Algoritmo de backtracking para resolver sudokus.
     */
    private static boolean solveSudoku(short[] grid) {
        for (int i = 0; i < 81; i++) {
            if (grid[i] == 0) {
                // Probar números del 1 al 9
                List<Short> numbers = new ArrayList<>();
                for (short n = 1; n <= 9; n++) {
                    numbers.add(n);
                }
                Collections.shuffle(numbers, random);
                
                for (short num : numbers) {
                    if (isValid(grid, i, num)) {
                        grid[i] = num;
                        if (solveSudoku(grid)) {
                            return true;
                        }
                        grid[i] = 0; // Backtrack
                    }
                }
                return false;
            }
        }
        return true; // Sudoku completo
    }

    /**
     * Valida si un sudoku es válido (sin duplicados en filas, columnas o cajas).
     * @param grid Array de 81 elementos
     * @return true si el sudoku es válido
     */
    public static boolean validate(short[] grid) {
        // Validar filas
        for (int row = 0; row < 9; row++) {
            boolean[] used = new boolean[10];
            for (int col = 0; col < 9; col++) {
                short val = grid[row * 9 + col];
                if (val != 0) {
                    if (used[val]) {
                        return false; // Duplicado en fila
                    }
                    used[val] = true;
                }
            }
        }
        
        // Validar columnas
        for (int col = 0; col < 9; col++) {
            boolean[] used = new boolean[10];
            for (int row = 0; row < 9; row++) {
                short val = grid[row * 9 + col];
                if (val != 0) {
                    if (used[val]) {
                        return false; // Duplicado en columna
                    }
                    used[val] = true;
                }
            }
        }
        
        // Validar cajas 3x3
        for (int box = 0; box < 9; box++) {
            boolean[] used = new boolean[10];
            int startRow = (box / 3) * 3;
            int startCol = (box % 3) * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int pos = (startRow + i) * 9 + (startCol + j);
                    short val = grid[pos];
                    if (val != 0) {
                        if (used[val]) {
                            return false; // Duplicado en caja
                        }
                        used[val] = true;
                    }
                }
            }
        }
        
        return true;
    }

    /**
     * Verifica si es válido colocar un número en una posición específica.
     */
    private static boolean isValid(short[] grid, int pos, short num) {
        int row = pos / 9;
        int col = pos % 9;
        
        // Verificar fila
        for (int c = 0; c < 9; c++) {
            if (grid[row * 9 + c] == num) {
                return false;
            }
        }
        
        // Verificar columna
        for (int r = 0; r < 9; r++) {
            if (grid[r * 9 + col] == num) {
                return false;
            }
        }
        
        // Verificar caja 3x3
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int p = (boxRow + i) * 9 + (boxCol + j);
                if (grid[p] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
