/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTable;
import jguic.Command;
import jguic.Mediator;
import sudoku.commands.SetUserDataCommand;
import sudoku.core.Grid;
import sudoku.gui.GUIGrid;

public class GUIGridT
extends GUIGrid {
    private JTable swing_table = new JTable(9, 9);

    public GUIGridT(Mediator parent) {
        super(parent);
        this.swing_table.setRowHeight(49);
        this.swing_table.setPreferredSize(new Dimension(440, 440));
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof SetUserDataCommand) {
            System.out.println("Dans GridT");
            SetUserDataCommand c = (SetUserDataCommand)command;
            Grid g = c.getUserData().getGrid();
            this.loadGrid(g);
        }
    }

    private void loadGrid(Grid g) {
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < 9) {
                if (g.getCell(i, j).getValue() != 0) {
                    this.swing_table.setValueAt(g.getCell(i, j).getValue(), i, j);
                } else {
                    this.swing_table.setValueAt("", i, j);
                }
                ++j;
            }
            ++i;
        }
    }

    public JComponent getJComponent() {
        return this.swing_table;
    }
}

