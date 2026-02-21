/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.CheckSolutionCommand;
import sudoku.commands.ClearPossibilitiesCommand;
import sudoku.commands.HintCellHighlightCommand;
import sudoku.commands.SetCirclesCommand;
import sudoku.commands.SetHighlightCommand;
import sudoku.commands.SetPossibilityCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.CompletionFlashCommand;
import sudoku.commands.SetValueCommand;
import sudoku.commands.UnsetHighlightCommand;
import sudoku.core.Cell;
import sudoku.core.PlayedCell;

public class GUIGridPCell
extends MediatorExtension {
    private int row;
    private int col;
    private boolean fixed;
    private boolean wrong;
    private boolean hintHighlighted;
    private Timer animation_timer;
    private int animation_time;
    private Color start_color;
    private Color end_color;
    private JPanel swing_cell;
    private JLabel swing_value;
    private JPanel swing_panelValue;
    private JPanel swing_panelPossibilitiesF;
    private JPanel swing_panelPossibilitiesC;
    private JLabel[] swing_arrayPossibilitiesF;
    private JPanel[] swing_arrayPossibilitiesC;
    private static final int ALPHA = 255;
    private static final int CELL_SIZE = 48;
    private static final int CIRCLE_SIZE = 5;
    public static final int ANIM_DELAY = 60;
    private static final int ANIM_START_MARKER = 60;
    private static final int ANIM_SECOND_MARKER = 120;
    public static final int ANIM_END_MARKER = 900;
    public static final int ANIM_ROW_DELAY = 150;
    public static final int ANIM_COL_DELAY = 50;
    private static final String ID_VALUE = "VALUE";
    private static final String ID_POSSF = "POSSF";
    private static final String ID_POSSC = "POSSC";
    private static Hashtable<String, Color> colors_cache = new Hashtable();
    private static boolean CIRCLES = false;
    private static Font swing_var_font = new Font("Tahoma", 0, 10);
    private static Color swing_var_fixedBackgroundColor = new Color(235, 234, 218, 255);
    private static Color swing_var_playableBackgroundColor = new Color(255, 255, 255, 255);
    private static Color swing_var_fixedFontColor = new Color(111, 111, 111);
    private static Color swing_var_playableFontColor = new Color(50, 50, 50);
    private static Border swing_var_border = new LineBorder(new Color(191, 184, 172, 255), 1);
    private static Color swing_var_victoryBackgroundColor = new Color(194, 245, 118);
    private static Color swing_var_defeatBackgroundColor = new Color(255, 102, 102);
    private static Color swing_var_hintBackgroundColor = new Color(255, 235, 156); // Amarillo suave para celdas de pista

    public GUIGridPCell(Mediator parent, int row, int col) {
        super(parent);
        this.row = row;
        this.col = col;
        this.fixed = false;
        this.construct();
    }

    public GUIGridPCell(Mediator parent, int row, int col, int value) {
        super(parent);
        this.row = row;
        this.col = col;
        this.fixed = true;
        this.construct();
    }

    private void construct() {
        this.wrong = false;
        this.animation_timer = new Timer(0, null);
        this.animation_time = 900;
        this.start_color = null;
        this.end_color = null;
        this.swing_cell = new JPanel();
        this.swing_cell.setBorder(swing_var_border);
        this.swing_cell.setLayout(new CardLayout());
        this.swing_cell.setPreferredSize(new Dimension(48, 48));
        this.swing_panelValue = new JPanel();
        this.swing_panelValue.setBackground(new Color(0, 0, 0, 0));
        this.swing_panelValue.setLayout(new BorderLayout());
        this.swing_panelPossibilitiesF = new JPanel();
        this.swing_panelPossibilitiesF.setBackground(new Color(0, 0, 0, 0));
        this.swing_panelPossibilitiesF.setLayout(new GridLayout(3, 3, 0, 0));
        this.swing_panelPossibilitiesC = new JPanel();
        this.swing_panelPossibilitiesC.setBackground(new Color(0, 0, 0, 0));
        this.swing_panelPossibilitiesC.setLayout(new GridLayout(3, 3, 0, 0));
        this.swing_value = new JLabel(){

            public void paintComponent(Graphics g) {
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                super.paintComponent(g);
            }
        };
        this.swing_value.setHorizontalAlignment(0);
        this.swing_value.setFont(swing_var_font.deriveFont(1, 22.0f));
        this.swing_value.setText("");
        this.swing_panelValue.add((Component)this.swing_value, "Center");
        this.swing_arrayPossibilitiesF = new JLabel[9];
        this.swing_arrayPossibilitiesC = new JPanel[9];
        int p = 0;
        while (p < 9) {
            this.swing_arrayPossibilitiesF[p] = new JLabel("" + (p + 1)){

                public void paintComponent(Graphics g) {
                    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    super.paintComponent(g);
                }
            };
            this.swing_arrayPossibilitiesF[p].setForeground(new Color(98, 98, 98));
            this.swing_arrayPossibilitiesF[p].setHorizontalAlignment(0);
            this.swing_arrayPossibilitiesF[p].setFont(swing_var_font.deriveFont(1, 10.0f));
            this.swing_arrayPossibilitiesF[p].setVisible(false);
            this.swing_panelPossibilitiesF.add(this.swing_arrayPossibilitiesF[p]);
            this.swing_arrayPossibilitiesC[p] = new JPanel(){

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    int circle_margin = 6;
                    g.setColor(new Color(98, 98, 98));
                    g.fillOval(circle_margin, circle_margin, 5, 5);
                }
            };
            this.swing_arrayPossibilitiesC[p].setBackground(new Color(0, 0, 0, 0));
            this.swing_arrayPossibilitiesC[p].setVisible(false);
            this.swing_panelPossibilitiesC.add(this.swing_arrayPossibilitiesC[p]);
            ++p;
        }
        this.swing_cell.add((Component)this.swing_panelValue, ID_VALUE);
        this.swing_cell.add((Component)this.swing_panelPossibilitiesF, ID_POSSF);
        this.swing_cell.add((Component)this.swing_panelPossibilitiesC, ID_POSSC);
        this.swing_cell.addMouseListener(new MouseListener(){

            public void mousePressed(MouseEvent event) {
                if (event.getButton() == 1) {
                    GUIGridPCell.this.handle(new SetValueCommand(GUIGridPCell.this.row, GUIGridPCell.this.col));
                } else if (event.getButton() == 3) {
                    GUIGridPCell.this.handle(new SetPossibilityCommand(GUIGridPCell.this.row, GUIGridPCell.this.col));
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
    }

    private void setValueView() {
        ((CardLayout)this.swing_cell.getLayout()).show(this.swing_cell, ID_VALUE);
    }

    private void setPossibilitiesView() {
        if (CIRCLES) {
            ((CardLayout)this.swing_cell.getLayout()).show(this.swing_cell, ID_POSSC);
        } else {
            ((CardLayout)this.swing_cell.getLayout()).show(this.swing_cell, ID_POSSF);
        }
    }

    private void setPossibility(int p) {
        if (p > 0 && p < 10) {
            this.swing_arrayPossibilitiesF[--p].setVisible(true);
            this.swing_arrayPossibilitiesC[p].setVisible(true);
        }
    }

    private void unsetPossibility(int p) {
        if (p > 0 && p < 10) {
            this.swing_arrayPossibilitiesF[--p].setVisible(false);
            this.swing_arrayPossibilitiesC[p].setVisible(false);
        }
    }

    private void setValue(int value) {
        if (value > 0) {
            this.swing_value.setText("" + value);
            this.setValueView();
        } else {
            this.swing_value.setText("");
            this.setPossibilitiesView();
        }
    }

    private void setFixed(boolean fixed) {
        if (fixed) {
            this.swing_cell.setBackground(swing_var_fixedBackgroundColor);
            this.swing_value.setForeground(swing_var_fixedFontColor);
        } else {
            this.swing_cell.setBackground(swing_var_playableBackgroundColor);
            this.swing_value.setForeground(swing_var_playableFontColor);
        }
        this.fixed = fixed;
    }

    private void setHighlight(Color color) {
        if (color != null) {
            this.swing_cell.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 255));
        }
    }

    private void unsetHighlight() {
        if (this.hintHighlighted) {
            this.swing_cell.setBackground(swing_var_hintBackgroundColor);
        } else if (this.fixed) {
            this.swing_cell.setBackground(swing_var_fixedBackgroundColor);
            this.swing_value.setForeground(swing_var_fixedFontColor);
        } else {
            this.swing_cell.setBackground(swing_var_playableBackgroundColor);
            this.swing_value.setForeground(swing_var_playableFontColor);
        }
    }

    public JComponent getJComponent() {
        return this.swing_cell;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        CheckSolutionCommand c;
        if (command instanceof SetValueCommand) {
            SetValueCommand c2 = (SetValueCommand)command;
            this.wrong = false;
            if (c2.getCellX() == this.row && c2.getCellY() == this.col) {
                this.setValue(c2.getNewValue());
                this.unsetHighlight();
            }
        } else if (command instanceof SetPossibilityCommand) {
            SetPossibilityCommand c3 = (SetPossibilityCommand)command;
            this.wrong = false;
            if (c3.getCellX() == this.row && c3.getCellY() == this.col) {
                this.setPossibilitiesView();
                this.swing_cell.setBackground(swing_var_playableBackgroundColor);
                if (c3.isActive()) {
                    this.setPossibility(c3.getPossibility());
                } else {
                    this.unsetPossibility(c3.getPossibility());
                }
                this.unsetHighlight();
                this.setValue(c3.getNewValue());
            }
        } else if (command instanceof ClearPossibilitiesCommand) {
            ClearPossibilitiesCommand c4 = (ClearPossibilitiesCommand)command;
            this.wrong = false;
            if (c4.getCellX() == this.row && c4.getCellY() == this.col) {
                this.setPossibilitiesView();
                this.swing_cell.setBackground(swing_var_playableBackgroundColor);
                boolean[] possibilities = c4.getPossibilities();
                int i = 0;
                while (i < 9) {
                    if (possibilities[i]) {
                        this.setPossibility(i + 1);
                    } else {
                        this.unsetPossibility(i + 1);
                    }
                    ++i;
                }
                this.unsetHighlight();
                this.setValue(c4.getNewValue());
            }
        } else if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c5 = (SetUserDataCommand)command;
            this.wrong = false;
            this.hintHighlighted = false;
            Cell cell = c5.getUserData().getGrid().getCell(this.row - 1, this.col - 1);
            if (cell instanceof PlayedCell) {
                PlayedCell pcell = (PlayedCell)cell;
                this.setFixed(false);
                int i = 1;
                while (i < 10) {
                    if (pcell.getPossibility(i)) {
                        this.setPossibility(i);
                    } else {
                        this.unsetPossibility(i);
                    }
                    i = (short)(i + 1);
                }
                this.setValue(cell.getValue());
            } else {
                this.setFixed(true);
                this.setValue(cell.getValue());
            }
        } else if (command instanceof HintCellHighlightCommand) {
            HintCellHighlightCommand hc = (HintCellHighlightCommand) command;
            if (hc.getRow() == this.row && hc.getCol() == this.col) {
                this.hintHighlighted = true;
                this.swing_cell.setBackground(swing_var_hintBackgroundColor);
            }
        } else if (command instanceof SetHighlightCommand) {
            SetHighlightCommand c6 = (SetHighlightCommand)command;
            this.wrong = false;
            if (this.swing_value.getText().equals("" + c6.getFigure())) {
                this.setHighlight(c6.getColor());
            }
        } else if (command instanceof UnsetHighlightCommand) {
            UnsetHighlightCommand c7 = (UnsetHighlightCommand)command;
            this.wrong = false;
            if (this.swing_value.getText().equals("" + c7.getFigure())) {
                this.unsetHighlight();
            }
        } else if (command instanceof CompletionFlashCommand) {
            CompletionFlashCommand c9 = (CompletionFlashCommand)command;
            if (c9.containsCell(this.row, this.col)) {
                this.wrong = false;
                this.animation_timer.stop();
                this.animation_time = 0;
                this.end_color = swing_var_victoryBackgroundColor;
                this.animation_timer = new Timer(60, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        GUIGridPCell.this.animation_time += 60;
                        if (GUIGridPCell.this.animation_time <= 300) {
                            float t = GUIGridPCell.this.animation_time / 300f;
                            java.awt.Color start = GUIGridPCell.this.fixed ? swing_var_fixedBackgroundColor : swing_var_playableBackgroundColor;
                            int r = (int)((1-t) * start.getRed() + t * GUIGridPCell.this.end_color.getRed());
                            int g = (int)((1-t) * start.getGreen() + t * GUIGridPCell.this.end_color.getGreen());
                            int b = (int)((1-t) * start.getBlue() + t * GUIGridPCell.this.end_color.getBlue());
                            GUIGridPCell.this.swing_cell.setBackground(new java.awt.Color(Math.min(255,r), Math.min(255,g), Math.min(255,b), 255));
                        } else if (GUIGridPCell.this.animation_time <= 600) {
                            float t = (GUIGridPCell.this.animation_time - 300f) / 300f;
                            java.awt.Color start = GUIGridPCell.this.fixed ? swing_var_fixedBackgroundColor : swing_var_playableBackgroundColor;
                            int r = (int)(t * start.getRed() + (1-t) * GUIGridPCell.this.end_color.getRed());
                            int g = (int)(t * start.getGreen() + (1-t) * GUIGridPCell.this.end_color.getGreen());
                            int b = (int)(t * start.getBlue() + (1-t) * GUIGridPCell.this.end_color.getBlue());
                            GUIGridPCell.this.swing_cell.setBackground(new java.awt.Color(Math.min(255,r), Math.min(255,g), Math.min(255,b), 255));
                        } else {
                            GUIGridPCell.this.animation_timer.stop();
                            GUIGridPCell.this.unsetHighlight();
                        }
                    }
                });
                Timer launch = new Timer((this.row - 1) * 50 + (this.col - 1) * 20, e -> GUIGridPCell.this.animation_timer.start());
                launch.setRepeats(false);
                launch.start();
            }
        } else if (command instanceof SetCirclesCommand) {
            SetCirclesCommand c8 = (SetCirclesCommand)command;
            CIRCLES = c8.isCircles();
            if (this.swing_value.getText().equals("")) {
                this.setPossibilitiesView();
            }
        } else if (command instanceof CheckSolutionCommand && (c = (CheckSolutionCommand)command).isGridFull()) {
            this.wrong = c.getGrid().getSolution(this.row - 1, this.col - 1) != c.getGrid().getCell(this.row - 1, this.col - 1).getValue();
            this.animation_timer.stop();
            this.animation_time = 0;
            this.end_color = c.isValid() ? swing_var_victoryBackgroundColor : swing_var_defeatBackgroundColor;
            this.animation_timer = new Timer(60, new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    int b;
                    int g;
                    int r;
                    float pcStart;
                    float pcEnd;
                    GUIGridPCell gUIGridPCell = GUIGridPCell.this;
                    gUIGridPCell.animation_time = gUIGridPCell.animation_time + 60;
                    GUIGridPCell.this.start_color = GUIGridPCell.this.fixed ? swing_var_fixedBackgroundColor : swing_var_playableBackgroundColor;
                    if (GUIGridPCell.this.animation_time <= 60) {
                        pcEnd = (float)GUIGridPCell.this.animation_time / 60.0f;
                        pcStart = 1.0f - pcEnd;
                        r = (int)(pcStart * (float)GUIGridPCell.this.start_color.getRed() + pcEnd * (float)GUIGridPCell.this.end_color.getRed());
                        g = (int)(pcStart * (float)GUIGridPCell.this.start_color.getGreen() + pcEnd * (float)GUIGridPCell.this.end_color.getGreen());
                        b = (int)(pcStart * (float)GUIGridPCell.this.start_color.getBlue() + pcEnd * (float)GUIGridPCell.this.end_color.getBlue());
                    } else {
                        if (GUIGridPCell.this.animation_time <= 120) {
                            return;
                        }
                        if (GUIGridPCell.this.end_color != swing_var_defeatBackgroundColor || !GUIGridPCell.this.wrong) {
                            pcEnd = ((float)GUIGridPCell.this.animation_time - 120.0f) / 780.0f;
                            pcStart = 1.0f - pcEnd;
                            r = (int)(pcEnd * (float)GUIGridPCell.this.start_color.getRed() + pcStart * (float)GUIGridPCell.this.end_color.getRed());
                            g = (int)(pcEnd * (float)GUIGridPCell.this.start_color.getGreen() + pcStart * (float)GUIGridPCell.this.end_color.getGreen());
                            b = (int)(pcEnd * (float)GUIGridPCell.this.start_color.getBlue() + pcStart * (float)GUIGridPCell.this.end_color.getBlue());
                        } else {
                            return;
                        }
                    }
                    String s = String.valueOf(r) + g + b;
                    if (colors_cache.containsKey(s)) {
                        GUIGridPCell.this.swing_cell.setBackground((Color)colors_cache.get(s));
                    } else {
                        Color color = new Color(Math.min(r, 255), Math.min(g, 255), Math.min(b, 255), 255);
                        colors_cache.put(s, color);
                        GUIGridPCell.this.swing_cell.setBackground(color);
                    }
                    if (GUIGridPCell.this.animation_time >= 900) {
                        GUIGridPCell.this.animation_timer.stop();
                    }
                }
            });
            Timer timer_launch = new Timer((this.row - 1) * 150 + (this.col - 1) * 50, new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    GUIGridPCell.this.animation_timer.start();
                }
            });
            timer_launch.setRepeats(false);
            timer_launch.start();
        }
        super.receiveCommand(mediator, command);
    }
}

