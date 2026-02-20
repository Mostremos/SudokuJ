/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.CheckSolutionCommand;
import sudoku.commands.ClearPossibilitiesCommand;
import sudoku.commands.PlayPauseCommand;
import sudoku.commands.RepaintCommand;
import sudoku.commands.SetActiveFigureCommand;
import sudoku.commands.SetBackgroundImageCommand;
import sudoku.commands.SetHighlightCommand;
import sudoku.commands.SetPossibilityCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.SetValueCommand;
import sudoku.commands.TicTacCommand;
import sudoku.commands.UnsetHighlightCommand;
import sudoku.core.Cell;
import sudoku.core.PlayedCell;
import sudoku.gui.GUIDashBoard;
import sudoku.gui.GUIGrid;
import sudoku.gui.GUIGridP;
import sudoku.gui.GUIMessage;
import sudoku.gui.GUIStatus;
import sudoku.gui.HighlightManager;
import sudoku.gui.UserData;

public class GUIGameZone
extends MediatorExtension {
    private UserData userdata;
    private HighlightManager highlight;
    private Timer clock;
    private int figure;
    private Color[] highlightColors;
    private GUIDashBoard dashboard;
    private GUIGrid grid = new GUIGridP(this);
    private GUIStatus status = new GUIStatus(this);
    private GUIMessage message;
    private JLayeredPane swing_layers;
    private JPanel swing_gameZone;
    private JPanel swing_messageZone;
    private JComponent swing_message;
    private static final int ALPHA = 40;
    private static Image swing_var_backgroundImage = new ImageIcon("background/abstract1.jpg").getImage();
    private static Color swing_var_backgroundDefaultColor = new Color(0, 153, 204);

    public GUIGameZone(Mediator parent, UserData udata) {
        super(parent);
        this.dashboard = new GUIDashBoard(this);
        this.message = new GUIMessage(this);
        this.swing_layers = new JLayeredPane();
        this.swing_layers.setPreferredSize(new Dimension(600, 600));
        this.swing_gameZone = new JPanel(){

            public void paintComponent(Graphics g) {
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (swing_var_backgroundImage.getWidth(null) > 0) {
                    g.drawImage(swing_var_backgroundImage, 0, 0, this);
                } else {
                    g.setColor(swing_var_backgroundDefaultColor);
                    g.fillRect(0, 0, 600, 600);
                }
                g.setColor(new Color(0, 0, 0, 40));
                Dimension d = this.getPreferredSize();
                int x = Math.min(GUIGameZone.this.status.getJComponent().getX(), Math.min(GUIGameZone.this.dashboard.getJComponent().getX(), GUIGameZone.this.grid.getJComponent().getX()));
                g.fillRoundRect(x - 10, this.getY() + 10, d.width + 20, d.height, 20, 20);
            }
        };
        BoxLayout layout = new BoxLayout(this.swing_gameZone, 3);
        this.swing_gameZone.setLayout(layout);
        JComponent swing_dashboard = this.dashboard.getJComponent();
        swing_dashboard.setMaximumSize(swing_dashboard.getPreferredSize());
        JComponent swing_status = this.status.getJComponent();
        swing_status.setMaximumSize(swing_status.getPreferredSize());
        JComponent swing_grid = this.grid.getJComponent();
        swing_grid.setMaximumSize(swing_grid.getPreferredSize());
        this.swing_gameZone.add(Box.createRigidArea(new Dimension(0, 30)));
        this.swing_gameZone.add(swing_dashboard);
        this.swing_gameZone.add(Box.createRigidArea(new Dimension(0, 20)));
        this.swing_gameZone.add(swing_grid);
        this.swing_gameZone.add(Box.createRigidArea(new Dimension(0, 10)));
        this.swing_gameZone.add(swing_status);
        this.swing_gameZone.addMouseWheelListener(new MouseWheelListener(){

            public void mouseWheelMoved(MouseWheelEvent event) {
                int way = event.getWheelRotation();
                if (way > 0) {
                    if (GUIGameZone.this.figure == 9 || GUIGameZone.this.figure == 0) {
                        GUIGameZone.this.figure = 1;
                    } else {
                        GUIGameZone gUIGameZone = GUIGameZone.this;
                        gUIGameZone.figure = gUIGameZone.figure + 1;
                    }
                } else if (GUIGameZone.this.figure == 1 || GUIGameZone.this.figure == 0) {
                    GUIGameZone.this.figure = 9;
                } else {
                    GUIGameZone gUIGameZone = GUIGameZone.this;
                    gUIGameZone.figure = gUIGameZone.figure - 1;
                }
                GUIGameZone.this.handle(new SetActiveFigureCommand(GUIGameZone.this.figure));
            }
        });
        this.swing_layers.add((Component)this.swing_gameZone, JLayeredPane.DEFAULT_LAYER);
        this.swing_gameZone.setBounds(0, 0, 600, 600);
        this.swing_message = this.message.getJComponent();
        this.swing_messageZone = new JPanel();
        this.swing_messageZone.setOpaque(false);
        this.swing_messageZone.setLayout(null);
        this.swing_messageZone.add(this.swing_message);
        this.swing_layers.add((Component)this.swing_messageZone, JLayeredPane.POPUP_LAYER);
        this.swing_messageZone.setBounds(0, 0, 600, 600);
        Dimension dm = this.swing_message.getPreferredSize();
        this.swing_message.setBounds((int)((600.0 - dm.getWidth()) / 2.0), 140, (int)dm.getWidth(), (int)dm.getHeight());
        this.userdata = udata;
        this.highlight = new HighlightManager();
        this.clock = new Timer(1000, new ActionListener(){

            public void actionPerformed(ActionEvent event) {
                GUIGameZone.this.userdata.getClock().addSecond();
                GUIGameZone.this.sendCommand(new TicTacCommand(GUIGameZone.this.userdata.getClock()));
            }
        });
        this.clock.start();
        this.figure = 0;
        this.highlightColors = new Color[10];
        int i = 0;
        while (i < this.highlightColors.length) {
            this.highlightColors[i] = null;
            i = (short)(i + 1);
        }
        this.handle(new SetActiveFigureCommand(1));
    }

    public JComponent getJComponent() {
        return this.swing_layers;
    }

    public void handle(Command command) {
        boolean blockCommand = false;
        if (command instanceof SetValueCommand) {
            SetValueCommand c = (SetValueCommand)command;
            Cell cell = this.userdata.getGrid().getCell(c.getCellX() - 1, c.getCellY() - 1);
            if (cell instanceof PlayedCell) {
                c.setOldValue(cell.getValue());
                if (cell.getValue() == this.figure) {
                    c.setNewValue(0);
                } else {
                    c.setNewValue(this.figure);
                }
            } else {
                blockCommand = true;
            }
        } else if (command instanceof SetPossibilityCommand) {
            SetPossibilityCommand c = (SetPossibilityCommand)command;
            int x = c.getCellX() - 1;
            int y = c.getCellY() - 1;
            if (this.figure != 0) {
                Cell cell = this.userdata.getGrid().getCell(x, y);
                if (cell instanceof PlayedCell) {
                    PlayedCell pcell = (PlayedCell)cell;
                    c.setPossibility(this.figure);
                    c.setActive(!pcell.getPossibility(this.figure));
                    c.setOldValue(pcell.getValue());
                    c.setNewValue(0);
                } else {
                    blockCommand = true;
                }
            } else {
                ClearPossibilitiesCommand cc = new ClearPossibilitiesCommand(c.getCellX(), c.getCellY());
                Cell cell = this.userdata.getGrid().getCell(x, y);
                if (cell instanceof PlayedCell) {
                    PlayedCell pcell = (PlayedCell)cell;
                    cc.setPossibilities(pcell.getPossibilities());
                    cc.setOldValue(pcell.getValue());
                    cc.setNewValue(0);
                    this.handle(cc);
                }
                blockCommand = true;
            }
        } else if (command instanceof SetHighlightCommand) {
            Color color;
            SetHighlightCommand c = (SetHighlightCommand)command;
            this.highlightColors[c.getFigure()] = color = this.highlight.lockColor();
            c.setColor(color);
        } else if (command instanceof UnsetHighlightCommand) {
            UnsetHighlightCommand c = (UnsetHighlightCommand)command;
            this.highlight.unlockColor(this.highlightColors[c.getFigure()]);
        }
        if (!blockCommand) {
            super.handle(command);
        }
    }

    public void receiveCommand(Mediator mediator, Command command) {
        Color color;
        int y;
        Command c;
        if (command instanceof SetUserDataCommand) {
            c = (SetUserDataCommand)command;
            int i = 1;
            while (i < 10) {
                this.highlight.unlockColor(this.highlightColors[i]);
                this.handle(new UnsetHighlightCommand(i));
                ++i;
            }
            this.userdata = ((SetUserDataCommand)c).getUserData();
            JComponent swing_grid = this.grid.getJComponent();
            if (this.userdata.getMode() == 0) {
                this.clock.stop();
                this.handle(new TicTacCommand(((SetUserDataCommand)c).getUserData().getClock()));
                if (((SetUserDataCommand)c).getUserData().isPaused()) {
                    this.clock.stop();
                    swing_grid.setVisible(false);
                } else {
                    this.clock.start();
                    swing_grid.setVisible(true);
                }
            } else {
                this.clock.stop();
                swing_grid.setVisible(true);
                ((SetUserDataCommand)c).getUserData().getClock().reset();
                this.handle(new TicTacCommand(((SetUserDataCommand)c).getUserData().getClock()));
            }
        } else if (command instanceof SetValueCommand) {
            c = (SetValueCommand)command;
            int cx = ((SetValueCommand)c).getCellX() - 1;
            int cy = ((SetValueCommand)c).getCellY() - 1;
            int newVal = ((SetValueCommand)c).getNewValue();
            Cell cell = this.userdata.getGrid().getCell(cx, cy);
            cell.setValue(newVal);
            if (newVal > 0) {
                this.userdata.getGrid().clearPossibilityInPeers(cx, cy, newVal);
            }
        } else if (command instanceof SetPossibilityCommand) {
            c = (SetPossibilityCommand)command;
            int x = ((SetPossibilityCommand)c).getCellX() - 1;
            y = ((SetPossibilityCommand)c).getCellY() - 1;
            Cell cell = this.userdata.getGrid().getCell(x, y);
            if (cell instanceof PlayedCell) {
                PlayedCell pcell = (PlayedCell)cell;
                pcell.setPossibility(((SetPossibilityCommand)c).getPossibility(), ((SetPossibilityCommand)c).isActive());
                this.userdata.getGrid().getCell(x, y).setValue(((SetPossibilityCommand)c).getNewValue());
            }
        } else if (command instanceof ClearPossibilitiesCommand) {
            c = (ClearPossibilitiesCommand)command;
            int x = ((ClearPossibilitiesCommand)c).getCellX() - 1;
            y = ((ClearPossibilitiesCommand)c).getCellY() - 1;
            Cell cell = this.userdata.getGrid().getCell(x, y);
            if (cell instanceof PlayedCell) {
                PlayedCell pcell = (PlayedCell)cell;
                boolean[] possibilities = ((ClearPossibilitiesCommand)c).getPossibilities();
                int i = 0;
                while (i < 9) {
                    pcell.setPossibility(i + 1, possibilities[i]);
                    ++i;
                }
                this.userdata.getGrid().getCell(x, y).setValue(((ClearPossibilitiesCommand)c).getNewValue());
            }
        } else if (command instanceof SetActiveFigureCommand) {
            this.figure = ((SetActiveFigureCommand)command).getFigure();
        } else if (command instanceof PlayPauseCommand) {
            JComponent swing_grid = this.grid.getJComponent();
            if (((PlayPauseCommand)command).isPaused()) {
                this.clock.stop();
                swing_grid.setVisible(false);
            } else {
                this.clock.start();
                swing_grid.setVisible(true);
            }
        } else if (command instanceof SetHighlightCommand) {
            c = (SetHighlightCommand)command;
            this.highlightColors[((SetHighlightCommand)c).getFigure()] = ((SetHighlightCommand)c).getColor();
        } else if (command instanceof UnsetHighlightCommand) {
            c = (UnsetHighlightCommand)command;
            this.highlightColors[((UnsetHighlightCommand)c).getFigure()] = null;
        } else if (command instanceof SetBackgroundImageCommand) {
            c = (SetBackgroundImageCommand)command;
            if (((SetBackgroundImageCommand)c).getImageName() != null) {
                swing_var_backgroundImage = new ImageIcon(((SetBackgroundImageCommand)c).getImageName()).getImage();
            }
        } else if (command instanceof CheckSolutionCommand && ((CheckSolutionCommand)(c = (CheckSolutionCommand)command)).isGridFull() && ((CheckSolutionCommand)c).isValid()) {
            this.clock.stop();
            int i = 0;
            while (i < this.highlightColors.length) {
                if (this.highlightColors[i] != null) {
                    this.handle(new UnsetHighlightCommand(i));
                }
                i = (short)(i + 1);
            }
        }
        this.swing_layers.repaint();
        if (!(command instanceof RepaintCommand)) {
            super.receiveCommand(mediator, command);
        }
        if (command instanceof SetValueCommand) {
            c = (SetValueCommand)command;
            Color color2 = this.highlightColors[((SetValueCommand)c).getNewValue()];
            if (color2 != null) {
                SetHighlightCommand cm = new SetHighlightCommand(((SetValueCommand)c).getNewValue());
                cm.setColor(color2);
                this.sendCommand(cm);
            }
        } else if (command instanceof SetPossibilityCommand) {
            c = (SetPossibilityCommand)command;
            Color color3 = this.highlightColors[((SetPossibilityCommand)c).getNewValue()];
            if (color3 != null) {
                SetHighlightCommand cm = new SetHighlightCommand(((SetPossibilityCommand)c).getNewValue());
                cm.setColor(color3);
                this.sendCommand(cm);
            }
        } else if (command instanceof ClearPossibilitiesCommand && (color = this.highlightColors[((ClearPossibilitiesCommand)(c = (ClearPossibilitiesCommand)command)).getNewValue()]) != null) {
            SetHighlightCommand cm = new SetHighlightCommand(((ClearPossibilitiesCommand)c).getNewValue());
            cm.setColor(color);
            this.sendCommand(cm);
        }
    }
}

