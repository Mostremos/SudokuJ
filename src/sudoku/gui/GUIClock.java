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
import sudoku.commands.RepaintCommand;
import sudoku.commands.TicTacCommand;
import sudoku.gui.Clock;

public class GUIClock
extends MediatorExtension {
    private int h;
    private int m;
    private int s;
    private JPanel swing_clock;
    private JLabel swing_title;
    private JLabel swing_hourMin;
    private JLabel swing_sec;
    private static final int ALPHA = 230;
    private static Font swing_var_text = new Font("Tahoma", 0, 10);
    private static Color swing_var_fontColor = new Color(255, 255, 255, 230);

    public GUIClock(Mediator parent) {
        super(parent);
        this.s = 0;
        this.m = 0;
        this.h = 0;
        this.__construct();
    }

    public GUIClock(Mediator parent, int hour, int min, int sec) {
        super(parent);
        this.setTime(hour, min, sec);
        this.__construct();
    }

    private void __construct() {
        this.swing_clock = new JPanel();
        this.swing_clock.setBackground(new Color(0, 0, 0, 0));
        this.swing_clock.setLayout(new BoxLayout(this.swing_clock, 2));
        this.swing_title = new JLabel();
        this.swing_title.setFont(swing_var_text.deriveFont(1, 13.0f));
        this.swing_title.setText(sudoku.util.I18n.get("status.clock"));
        this.swing_title.setForeground(swing_var_fontColor);
        this.swing_hourMin = new JLabel();
        this.swing_hourMin.setFont(swing_var_text.deriveFont(1, 20.0f));
        this.swing_hourMin.setForeground(swing_var_fontColor);
        this.swing_sec = new JLabel();
        this.swing_sec.setFont(swing_var_text.deriveFont(1, 16.0f));
        this.swing_sec.setForeground(swing_var_fontColor);
        this.swing_clock.setLayout(new BoxLayout(this.swing_clock, 2));
        this.swing_title.setAlignmentY(1.0f);
        this.swing_hourMin.setAlignmentY(1.0f);
        this.swing_sec.setAlignmentY(1.0f);
        this.swing_clock.add(this.swing_title);
        this.swing_clock.add(Box.createRigidArea(new Dimension(10, 0)));
        this.swing_clock.add(this.swing_hourMin);
        this.swing_clock.add(Box.createRigidArea(new Dimension(7, 0)));
        this.swing_clock.add(this.swing_sec);
        this.updateTime();
    }

    public void updateTime() {
        String t = "";
        t = this.h > 99 ? String.valueOf(t) + "9+" : (this.h < 10 ? String.valueOf(t) + "0" + this.h : String.valueOf(t) + this.h);
        t = String.valueOf(t) + ":";
        if (this.m < 10) {
            t = String.valueOf(t) + "0";
        }
        t = String.valueOf(t) + this.m;
        this.swing_hourMin.setText(t);
        this.swing_sec.setText(String.valueOf(this.s < 10 ? "0" : "") + this.s);
    }

    public void setTime(int hour, int min, int sec) {
        this.h = Math.abs(hour);
        this.m = min % 60;
        this.s = sec % 60;
    }

    public JComponent getJComponent() {
        return this.swing_clock;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof TicTacCommand) {
            TicTacCommand c = (TicTacCommand)command;
            Clock clk = c.getClock();
            this.setTime(clk.getHours(), clk.getMinutes(), clk.getSeconds());
            this.updateTime();
            this.handle(new RepaintCommand());
        }
    }
}

