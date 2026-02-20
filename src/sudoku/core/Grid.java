/*
 * Decompiled with CFR 0.152.
 */
package sudoku.core;

import java.io.IOException;
import java.io.Serializable;
import sudoku.core.Cell;
import sudoku.core.Core;
import sudoku.core.Difficulty;
import sudoku.core.FixedCell;
import sudoku.core.PlayedCell;

public class Grid
implements Cloneable,
Serializable {
    private short[][] solution = new short[9][9];
    private Cell[][] game = new Cell[9][9];

    public Grid() {
        this.clear();
    }

    public Grid(Grid grid) {
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                this.solution[i][j] = grid.solution[i][j];
                this.game[i][j] = grid.game[i][j].clone();
                ++j;
            }
            ++i;
        }
    }

    public void clear() {
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                this.solution[i][j] = 0;
                this.game[i][j] = new PlayedCell();
                ++j;
            }
            ++i;
        }
    }

    public void generate(Difficulty level) {
        short[] grid = Core.generate(level.ordinal());
        int c = 0;
        while (c < 81) {
            short v;
            int i = c % 9;
            int j = c / 9;
            this.solution[i][j] = v = grid[c];
            this.game[i][j] = v > 0 ? new FixedCell(v) : new PlayedCell();
            ++c;
        }
    }

    public void reset() {
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                short v = this.solution[i][j];
                this.game[i][j] = v > 0 ? new FixedCell(v) : new PlayedCell();
                ++j;
            }
            ++i;
        }
    }

    public boolean validate() {
        short[] grid = new short[81];
        int c = 0;
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                grid[c] = (short)this.game[i][j].getValue();
                ++c;
                ++j;
            }
            ++i;
        }
        return Core.validate(grid);
    }

    public boolean solve() {
        short[] grid = new short[81];
        int c = 0;
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                grid[c] = (short)this.game[i][j].getValue();
                ++c;
                ++j;
            }
            ++i;
        }
        if (Core.solve(grid)) {
            // CORREGIDO: El bucle anterior tenía un bug - ahora copiamos correctamente
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
        return false;
    }

    public Cell getCell(int x, int y) {
        if (x < 9 && x >= 0 && y < 9 && y >= 0) {
            return this.game[x][y];
        }
        return null;
    }

    public void setCell(int x, int y, Cell cell) {
        if (x < 9 && x >= 0 && y < 9 && y >= 0) {
            this.game[x][y] = cell;
        }
    }

    /**
     * Borra la posibilidad del número dado en todas las celdas de la misma fila,
     * columna y cuadro 3x3 (excepto la celda en x,y).
     */
    public void clearPossibilityInPeers(int x, int y, int number) {
        if (number < 1 || number > 9) return;
        for (int j = 0; j < 9; j++) {
            if (j != y) clearPossibilityAt(x, j, number);
        }
        for (int i = 0; i < 9; i++) {
            if (i != x) clearPossibilityAt(i, y, number);
        }
        int boxRow = (x / 3) * 3;
        int boxCol = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int rx = boxRow + i;
                int ry = boxCol + j;
                if (rx != x || ry != y) clearPossibilityAt(rx, ry, number);
            }
        }
    }

    private void clearPossibilityAt(int x, int y, int number) {
        Cell cell = this.game[x][y];
        if (cell instanceof PlayedCell) {
            ((PlayedCell) cell).setPossibility(number, false);
        }
    }

    public int getSolution(int x, int y) {
        if (x < 9 && x >= 0 && y < 9 && y >= 0) {
            short s = this.solution[x][y];
            if (s < 0) {
                s = (short)-s;  // CORREGIDO: cast explícito
            }
            return s;
        }
        return 0;
    }

    public boolean checkCell(int x, int y) {
        if (x < 9 && x >= 0 && y < 9 && y >= 0) {
            short s = this.solution[x][y];
            if (s < 0) {
                s = (short)-s;  // CORREGIDO: cast explícito
            }
            return this.game[x][y].getValue() == s;
        }
        return false;
    }

    /**
     * Obtiene una pista: una celda vacía y su valor correcto.
     * @return int[3] con {x, y, valor} o null si no hay solución o no hay celdas vacías
     */
    public int[] getHint() {
        if (!this.solve()) {
            return null;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.game[i][j].getValue() == 0) {
                    int val = this.getSolution(i, j);
                    if (val > 0) {
                        return new int[] { i, j, val };
                    }
                }
            }
        }
        return null;
    }

    public boolean check() {
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                if (!this.checkCell(i, j)) {
                    return false;
                }
                j = (short)(j + 1);
            }
            i = (short)(i + 1);
        }
        return true;
    }

    public String toString() {
        String s = "+---+---+---+---+---+---+---+---+---+\n";
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                s = String.valueOf(s) + "| ";
                short v = (short)this.game[i][j].getValue();
                s = v > 0 ? String.valueOf(s) + v : String.valueOf(s) + " ";
                s = String.valueOf(s) + " ";
                j = (short)(j + 1);
            }
            s = String.valueOf(s) + "|\n";
            s = String.valueOf(s) + "+---+---+---+---+---+---+---+---+---+\n";
            i = (short)(i + 1);
        }
        return s;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid);
        Grid.pause();
        grid.generate(Difficulty.EASY);
        System.out.println(grid);
        Grid.pause();
        grid.clear();
        System.out.println(grid);
        Grid.pause();
        grid.generate(Difficulty.HARD);
        System.out.println(grid);
        Grid.pause();
        System.out.println("Valeur de la case (0, 0) : " + grid.getCell(0, 0).getValue());
        System.out.println("Valeur de la case (4, 2) : " + grid.getCell(4, 2).getValue());
        Grid.pause();
        grid.getCell(3, 1).setValue(9);
        System.out.println("Changement de la case (3, 1) en 9");
        System.out.println(grid);
        Grid.pause();
        System.out.println("La case est-elle correcte ? " + (grid.checkCell(3, 1) ? "oui" : "non"));
        System.out.println("La grille est-elle correcte ? " + (grid.check() ? "oui" : "non"));
        System.out.println("La grille est-elle valide ? " + (grid.validate() ? "oui" : "non"));
        System.out.println("La grille a-t-elle une solution ? " + (grid.solve() ? "oui" : "non"));
        Grid.pause();
        Grid grid_c = new Grid();
        grid_c.getCell(0, 0).setValue(3);
        grid_c.getCell(8, 8).setValue(2);
        grid_c.getCell(4, 3).setValue(8);
        grid_c.getCell(2, 7).setValue(1);
        System.out.println(grid_c);
        Grid.pause();
        System.out.println("La grille est-elle valide ? " + (grid_c.validate() ? "oui" : "non"));
        System.out.println("La grille a-t-elle une solution ? " + (grid_c.solve() ? "oui" : "non"));
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                grid_c.getCell(i, j).setValue(grid_c.getSolution(i, j));
                j = (short)(j + 1);
            }
            i = (short)(i + 1);
        }
        System.out.println(grid_c);
        Grid.pause();
    }

    private static void pause() {
        System.out.println("Appuyez sur une touche pour continuer...");
        try {
            System.in.read();
            System.in.skip(System.in.available());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

