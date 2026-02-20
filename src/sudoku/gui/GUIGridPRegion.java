/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.ClearPossibilitiesCommand;
import sudoku.commands.SetPossibilityCommand;
import sudoku.commands.SetValueCommand;
import sudoku.gui.GUIGridPCell;

public class GUIGridPRegion
extends MediatorExtension {
    private GUIGridPCell[][] cells = new GUIGridPCell[3][3];
    private JPanel swing_region;
    private static final int CELL_MARGIN = 2;

    public GUIGridPRegion(Mediator parent, int row, int col) {
        super(parent);
        int j;
        int i = 0;
        while (i < 3) {
            j = 0;
            while (j < 3) {
                this.cells[i][j] = new GUIGridPCell(this, 3 * row + i - 2, 3 * col + j - 2);
                ++j;
            }
            ++i;
        }
        this.swing_region = new JPanel();
        this.swing_region.setBackground(new Color(0, 0, 0, 0));
        this.swing_region.setLayout(new GridLayout(3, 3, 2, 2));
        i = 0;
        while (i < 3) {
            j = 0;
            while (j < 3) {
                this.swing_region.add(this.cells[i][j].getJComponent());
                ++j;
            }
            ++i;
        }
    }

    public JComponent getJComponent() {
        return this.swing_region;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof ClearPossibilitiesCommand) {
            ClearPossibilitiesCommand c = (ClearPossibilitiesCommand)command;
            int i = c.getCellX() - (c.getCellX() - 1) / 3 * 3 - 1;
            int j = c.getCellY() - (c.getCellY() - 1) / 3 * 3 - 1;
            this.cells[i][j].receiveCommand(mediator, command);
        } else if (command instanceof SetPossibilityCommand) {
            SetPossibilityCommand c = (SetPossibilityCommand)command;
            int i = c.getCellX() - (c.getCellX() - 1) / 3 * 3 - 1;
            int j = c.getCellY() - (c.getCellY() - 1) / 3 * 3 - 1;
            this.cells[i][j].receiveCommand(mediator, command);
        } else if (command instanceof SetValueCommand) {
            SetValueCommand c = (SetValueCommand)command;
            int i = c.getCellX() - (c.getCellX() - 1) / 3 * 3 - 1;
            int j = c.getCellY() - (c.getCellY() - 1) / 3 * 3 - 1;
            this.cells[i][j].receiveCommand(mediator, command);
        } else {
            super.receiveCommand(mediator, command);
        }
    }
}

