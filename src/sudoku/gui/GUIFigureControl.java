/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.SetActiveFigureCommand;
import sudoku.commands.SetHighlightCommand;
import sudoku.commands.SetPossibilityCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.SetValueCommand;
import sudoku.commands.UnsetHighlightCommand;

public class GUIFigureControl
extends MediatorExtension {
    private int figure;
    private boolean active;
    private boolean finished;
    private Color highlighted;
    private int total;
    private JPanel swing_figureControl;
    private JLabel swing_figure;
    private JLabel swing_eraseLabel;
    private static final int ALPHA = 255;
    private static final int ALPHA_DIFF = 75;
    private static final int CELL_SIZE = 24;
    private static Font swing_var_font = new Font("Tahoma", 0, 10);
    private static LineBorder swing_var_notFinishedBorder = new LineBorder(new Color(0, 0, 0, 40), 2);
    private static LineBorder swing_var_finishedBorder = new LineBorder(new Color(0, 0, 0, 40), 2);
    private static LineBorder swing_var_activeBorder = new LineBorder(new Color(240, 0, 30, 255), 2);
    private static Color swing_var_notFinishedTextColor = new Color(111, 111, 111);
    private static Color swing_var_notFinishedBackgroundColor = new Color(245, 244, 228, 255);
    private static Color swing_var_finishedTextColor = new Color(111, 111, 111, 180);
    private static Color swing_var_finishedBackgroundColor = new Color(255, 255, 255, 180);
    private static Color swing_var_activeTextColor = new Color(240, 0, 30, 255);

    public GUIFigureControl(Mediator parent, int fig) {
        super(parent);
        this.figure = fig;
        this.active = false;
        this.finished = false;
        this.highlighted = null;
        this.total = 0;
        this.swing_figureControl = new JPanel();
        this.swing_figureControl.addMouseListener(new MouseListener(){

            public void mousePressed(MouseEvent event) {
                if (event.getButton() == 1) {
                    GUIFigureControl.this.handle(new SetActiveFigureCommand(GUIFigureControl.this.figure));
                } else if (event.getButton() == 3) {
                    if (GUIFigureControl.this.highlighted != null) {
                        GUIFigureControl.this.handle(new UnsetHighlightCommand(GUIFigureControl.this.figure));
                    } else if (GUIFigureControl.this.figure != 0) {
                        GUIFigureControl.this.handle(new SetHighlightCommand(GUIFigureControl.this.figure));
                    }
                }
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        });
        this.swing_figure = new JLabel(){

            public void paintComponent(Graphics g) {
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                super.paintComponent(g);
            }
        };
        this.swing_figure.setPreferredSize(new Dimension(24, 24));
        this.swing_figure.setHorizontalAlignment(0);
        this.swing_figure.setFont(swing_var_font.deriveFont(1, 20.0f));
        this.swing_figure.setText("" + this.figure);
        this.swing_figureControl.add(this.swing_figure);
        if (this.figure == 0) {
            this.swing_figureControl.removeAll();
            this.swing_eraseLabel = new JLabel("X");
            this.swing_eraseLabel.setFont(swing_var_font.deriveFont(java.awt.Font.BOLD, 22f));
            this.swing_eraseLabel.setForeground(new Color(220, 0, 0));
            this.swing_eraseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            this.swing_eraseLabel.setPreferredSize(new Dimension(24, 24));
            this.swing_figureControl.add(this.swing_eraseLabel);
        }
        this.setFinished(this.finished);
        this.setActive(this.active);
    }

    public void setTotal(int number) {
        this.total = number;
        this.setFinished(this.total >= 9);
    }

    private void setActive(boolean active) {
        if (active) {
            this.swing_figureControl.setBorder(swing_var_activeBorder);
            if (this.figure == 0 && this.swing_eraseLabel != null) {
                this.swing_eraseLabel.setForeground(swing_var_activeTextColor);
            } else {
                this.swing_figure.setForeground(swing_var_activeTextColor);
            }
            this.active = true;
        } else {
            this.setFinished(this.finished);
            this.active = false;
        }
    }

    private void setFinished(boolean finished) {
        JLabel label = (this.figure == 0 && this.swing_eraseLabel != null) ? this.swing_eraseLabel : this.swing_figure;
        if (finished) {
            this.swing_figureControl.setBorder(swing_var_finishedBorder);
            if (this.highlighted == null) {
                this.swing_figureControl.setBackground(swing_var_finishedBackgroundColor);
            } else {
                this.swing_figureControl.setBackground(new Color(this.highlighted.getRed(), this.highlighted.getGreen(), this.highlighted.getBlue(), 255));
            }
            label.setForeground(swing_var_finishedTextColor);
            this.finished = true;
        } else {
            this.swing_figureControl.setBorder(swing_var_notFinishedBorder);
            if (this.highlighted == null) {
                this.swing_figureControl.setBackground(swing_var_notFinishedBackgroundColor);
            } else {
                this.swing_figureControl.setBackground(new Color(this.highlighted.getRed(), this.highlighted.getGreen(), this.highlighted.getBlue(), 255));
            }
            label.setForeground(this.figure == 0 ? new Color(220, 0, 0) : swing_var_notFinishedTextColor);
            this.finished = false;
        }
    }

    private void setHighlight(Color color) {
        if (color != null) {
            this.swing_figureControl.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 255));
            this.highlighted = color;
            this.setActive(this.active);
        }
    }

    private void unsetHighlight() {
        this.highlighted = null;
        this.setFinished(this.finished);
        this.setActive(this.active);
    }

    public JComponent getJComponent() {
        return this.swing_figureControl;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        UnsetHighlightCommand c;
        if (command instanceof SetUserDataCommand) {
            this.setActive(this.active);
        } else if (command instanceof SetActiveFigureCommand) {
            this.setActive(((SetActiveFigureCommand)command).getFigure() == this.figure);
        } else if (command instanceof SetValueCommand) {
            SetValueCommand c2 = (SetValueCommand)command;
            if (c2.getNewValue() == this.figure) {
                ++this.total;
            } else if (c2.getOldValue() == this.figure) {
                --this.total;
            }
            if (this.total >= 9) {
                this.setFinished(true);
                this.setActive(this.active);
            } else {
                this.setFinished(false);
                this.setActive(this.active);
            }
        } else if (command instanceof SetPossibilityCommand) {
            SetPossibilityCommand c3 = (SetPossibilityCommand)command;
            if (c3.getNewValue() == this.figure) {
                ++this.total;
            } else if (c3.getOldValue() == this.figure) {
                --this.total;
            }
            if (this.total >= 9) {
                this.setFinished(true);
                this.setActive(this.active);
            } else {
                this.setFinished(false);
                this.setActive(this.active);
            }
        } else if (command instanceof SetHighlightCommand) {
            SetHighlightCommand c4 = (SetHighlightCommand)command;
            if (c4.getFigure() == this.figure) {
                this.setHighlight(c4.getColor());
            }
        } else if (command instanceof UnsetHighlightCommand && (c = (UnsetHighlightCommand)command).getFigure() == this.figure) {
            this.unsetHighlight();
        }
    }
}

