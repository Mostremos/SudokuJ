/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.SetUserDataCommand;
import sudoku.core.Cell;
import sudoku.core.Grid;
import sudoku.gui.GUIFigureControl;

public class GUIDashBoard
extends MediatorExtension {
    private GUIFigureControl[] figures = new GUIFigureControl[10];
    private JPanel swing_dashboard;
    private static final int CELL_MARGIN = 2;

    public GUIDashBoard(Mediator parent) {
        super(parent);
        int l = this.figures.length;
        int i = 0;
        while (i < l) {
            this.figures[i] = new GUIFigureControl(this, i);
            ++i;
        }
        this.swing_dashboard = new JPanel();
        this.swing_dashboard.setBackground(new Color(0, 0, 0, 0));
        FlowLayout fl = new FlowLayout(1, 2, 0);
        this.swing_dashboard.setLayout(fl);
        int i2 = 1;
        while (i2 < 10) {
            JComponent fp = this.figures[i2].getJComponent();
            this.swing_dashboard.add(fp);
            ++i2;
        }
        JComponent fp = this.figures[0].getJComponent();
        this.swing_dashboard.add(fp);
    }

    public JComponent getJComponent() {
        return this.swing_dashboard;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c = (SetUserDataCommand)command;
            short[] initTotal = new short[10];
            int i = 1;
            while (i < 10) {
                initTotal[i] = 0;
                i = (short)(i + 1);
            }
            Grid grid = c.getUserData().getGrid();
            int x = 0;
            while (x < 9) {
                int y = 0;
                while (y < 9) {
                    Cell cell = grid.getCell(x, y);
                    int n = cell.getValue();
                    initTotal[n] = (short)(initTotal[n] + 1);
                    y = (short)(y + 1);
                }
                x = (short)(x + 1);
            }
            int i2 = 1;
            while (i2 < 10) {
                this.figures[i2].setTotal(initTotal[i2]);
                i2 = (short)(i2 + 1);
            }
            this.figures[0].setTotal(-81);
        }
        super.receiveCommand(mediator, command);
    }
}

