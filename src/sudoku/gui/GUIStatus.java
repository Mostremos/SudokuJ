/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.gui.GUIClock;
import sudoku.gui.GUILeftCells;

public class GUIStatus
extends MediatorExtension {
    private GUIClock clock = new GUIClock(this);
    private GUILeftCells mcells = new GUILeftCells(this);
    private JPanel swing_status = new JPanel();

    public GUIStatus(Mediator parent) {
        super(parent);
        this.swing_status.setPreferredSize(new Dimension(440, 25));
        this.swing_status.setLayout(new BoxLayout(this.swing_status, 2));
        this.swing_status.setBackground(new Color(0, 0, 0, 0));
        this.swing_status.add(this.mcells.getJComponent());
        this.swing_status.add(Box.createHorizontalGlue());
        this.swing_status.add(this.clock.getJComponent());
    }

    public JComponent getJComponent() {
        return this.swing_status;
    }
}

