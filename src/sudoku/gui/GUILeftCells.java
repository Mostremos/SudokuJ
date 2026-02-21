/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.ClearPossibilitiesCommand;
import sudoku.commands.SetLanguageCommand;
import sudoku.util.I18n;
import sudoku.commands.SetPossibilityCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.SetValueCommand;
import sudoku.core.Grid;

public class GUILeftCells
extends MediatorExtension {
    private int missing = 81;
    private JPanel swing_leftCells = new JPanel();
    private JLabel swing_title;
    private JLabel swing_leftNumber;
    private static final int ALPHA = 230;

    /** Fuente que soporta CJK (chino/japonés) - Tahoma no muestra esos caracteres */
    private static Font getStatusFont(int style, float size) {
        String lang = I18n.getLanguage();
        Font base = ("zh".equals(lang) || "ja".equals(lang)) ? new Font(Font.SANS_SERIF, 0, 10) : new Font("Tahoma", 0, 10);
        return base.deriveFont(style, size);
    }
    private static Color swing_var_textColor = new Color(255, 255, 255, 230);

    public GUILeftCells(Mediator parent) {
        super(parent);
        this.swing_leftCells.setBackground(new Color(0, 0, 0, 0));
        this.swing_leftCells.setLayout(new BoxLayout(this.swing_leftCells, 2));
        this.swing_title = new JLabel();
        this.swing_title.setFont(getStatusFont(Font.BOLD, 13.0f));
        this.swing_title.setText(sudoku.util.I18n.get("status.cells_left"));
        this.swing_title.setForeground(swing_var_textColor);
        this.swing_leftNumber = new JLabel();
        this.swing_leftNumber.setFont(getStatusFont(Font.BOLD, 20.0f));
        this.swing_leftNumber.setForeground(swing_var_textColor);
        this.swing_title.setAlignmentY(1.0f);
        this.swing_leftNumber.setAlignmentY(1.0f);
        this.swing_leftCells.add(this.swing_leftNumber);
        this.swing_leftCells.add(Box.createRigidArea(new Dimension(10, 0)));
        this.swing_leftCells.add(this.swing_title);
        this.updateMissingCells();
    }

    public void add() {
        ++this.missing;
    }

    public void delete() {
        --this.missing;
    }

    public void updateMissingCells() {
        this.swing_leftNumber.setText(String.valueOf(this.missing < 10 ? "0" : "") + this.missing);
    }

    public JComponent getJComponent() {
        return this.swing_leftCells;
    }

    private void changedValue(int oldV, int newV) {
        if (oldV > 0 && newV == 0) {
            this.add();
            this.updateMissingCells();
        } else if (oldV == 0 && newV > 0) {
            this.delete();
            this.updateMissingCells();
        }
    }

    public void refreshLanguage() {
        this.swing_title.setFont(getStatusFont(Font.BOLD, 13.0f));
        this.swing_title.setText(sudoku.util.I18n.get("status.cells_left"));
        this.swing_leftNumber.setFont(getStatusFont(Font.BOLD, 20.0f));
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof SetLanguageCommand) {
            this.refreshLanguage();
        } else if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c = (SetUserDataCommand)command;
            Grid grid = c.getUserData().getGrid();
            this.missing = 0;
            int i = 0;
            while (i < 9) {
                int j = 0;
                while (j < 9) {
                    if (grid.getCell(i, j).getValue() == 0) {
                        ++this.missing;
                    }
                    ++j;
                }
                ++i;
            }
            this.updateMissingCells();
        } else if (command instanceof SetValueCommand) {
            SetValueCommand c = (SetValueCommand)command;
            this.changedValue(c.getOldValue(), c.getNewValue());
        } else if (command instanceof SetPossibilityCommand) {
            SetPossibilityCommand c = (SetPossibilityCommand)command;
            this.changedValue(c.getOldValue(), c.getNewValue());
        } else if (command instanceof ClearPossibilitiesCommand) {
            ClearPossibilitiesCommand c = (ClearPossibilitiesCommand)command;
            this.changedValue(c.getOldValue(), c.getNewValue());
        }
    }
}

