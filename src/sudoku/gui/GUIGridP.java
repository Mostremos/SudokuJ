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
import sudoku.commands.ClearPossibilitiesCommand;
import sudoku.commands.SetPossibilityCommand;
import sudoku.commands.SetValueCommand;
import sudoku.gui.GUIGrid;
import sudoku.gui.GUIGridPRegion;

public class GUIGridP
extends GUIGrid {
    private GUIGridPRegion[][] regions = new GUIGridPRegion[3][3];
    private JPanel swing_grid;
    private static final int REGION_MARGIN = 5;

    public GUIGridP(Mediator parent) {
        super(parent);
        int j;
        int i = 0;
        while (i < 3) {
            j = 0;
            while (j < 3) {
                this.regions[i][j] = new GUIGridPRegion(this, i + 1, j + 1);
                ++j;
            }
            ++i;
        }
        this.swing_grid = new JPanel();
        this.swing_grid.setBackground(new Color(0, 0, 0, 0));
        this.swing_grid.setLayout(new GridLayout(3, 3, 5, 5));
        i = 0;
        while (i < 3) {
            j = 0;
            while (j < 3) {
                this.swing_grid.add(this.regions[i][j].getJComponent());
                ++j;
            }
            ++i;
        }
    }

    public JComponent getJComponent() {
        return this.swing_grid;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof ClearPossibilitiesCommand) {
            ClearPossibilitiesCommand c = (ClearPossibilitiesCommand)command;
            this.regions[(c.getCellX() - 1) / 3][(c.getCellY() - 1) / 3].receiveCommand(mediator, command);
        } else if (command instanceof SetPossibilityCommand) {
            SetPossibilityCommand c = (SetPossibilityCommand)command;
            this.regions[(c.getCellX() - 1) / 3][(c.getCellY() - 1) / 3].receiveCommand(mediator, command);
        } else if (command instanceof SetValueCommand) {
            SetValueCommand c = (SetValueCommand)command;
            this.regions[(c.getCellX() - 1) / 3][(c.getCellY() - 1) / 3].receiveCommand(mediator, command);
        } else {
            super.receiveCommand(mediator, command);
        }
    }
}

