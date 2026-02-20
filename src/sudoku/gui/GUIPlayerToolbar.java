/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import jguic.Command;
import jguic.Mediator;
import jguic.util.DesactivateRedo;
import jguic.util.DesactivateUndo;
import jguic.util.Redo;
import jguic.util.Undo;
import jguic.util.UndoableCommand;
import sudoku.commands.HintCommand;
import sudoku.commands.CheckSolutionCommand;
import sudoku.commands.LoadCommand;
import sudoku.commands.NewGridCommand;
import sudoku.commands.PlayPauseCommand;
import sudoku.commands.ResetCommand;
import sudoku.commands.SaveCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.ShowSolutionCommand;
import sudoku.gui.GUIToolbar;
import sudoku.util.I18n;

public class GUIPlayerToolbar
extends GUIToolbar {
    private JToolBar swing_toolbar = new JToolBar();
    private JButton swing_undo;
    private JButton swing_redo;
    private JButton swing_playpause;
    public static ImageIcon swing_var_pauseIcon = new ImageIcon("images/pause.png");
    public static ImageIcon swing_var_playIcon = new ImageIcon("images/play.png");

    public GUIPlayerToolbar(Mediator parent) {
        super(parent);
        JButton newGrid = new JButton(new ImageIcon("images/blank_document.png"));
        newGrid.setToolTipText(I18n.get("toolbar.new_grid"));
        newGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                NewGridCommand c = new NewGridCommand();
                c.setMode(0);
                GUIPlayerToolbar.this.handle(c);
            }
        });
        this.swing_toolbar.add(newGrid);
        JButton saveGrid = new JButton(new ImageIcon("images/disk.png"));
        saveGrid.setToolTipText("Sauvegarder la partie");
        saveGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new SaveCommand(0));
            }
        });
        this.swing_toolbar.add(saveGrid);
        JButton loadGrid = new JButton(new ImageIcon("images/open_dir.png"));
        loadGrid.setToolTipText("Charger une partie");
        loadGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new LoadCommand(0));
            }
        });
        this.swing_toolbar.add(loadGrid);
        JButton resetGrid = new JButton(new ImageIcon("images/refresh.png"));
        resetGrid.setToolTipText("Recommencer la partie");
        resetGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new ResetCommand());
            }
        });
        this.swing_toolbar.add(resetGrid);
        this.swing_toolbar.addSeparator();
        JButton hintBtn = new JButton(new ImageIcon("images/help.png"));
        hintBtn.setToolTipText(I18n.get("menu.hint"));
        hintBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new HintCommand());
            }
        });
        this.swing_toolbar.add(hintBtn);
        JButton showGrid = new JButton(new ImageIcon("images/grid_full.png"));
        showGrid.setToolTipText("Afficher la solution");
        showGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new ShowSolutionCommand());
            }
        });
        this.swing_toolbar.add(showGrid);
        JButton checkGrid = new JButton(new ImageIcon("images/check.png"));
        checkGrid.setToolTipText("V\u00e9rifier la solution");
        checkGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new CheckSolutionCommand());
            }
        });
        this.swing_toolbar.add(checkGrid);
        this.swing_toolbar.addSeparator();
        this.swing_playpause = new JButton(swing_var_pauseIcon);
        this.swing_playpause.setToolTipText("Mettre le jeu en pause");
        this.swing_playpause.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new PlayPauseCommand());
            }
        });
        this.swing_toolbar.add(this.swing_playpause);
        this.swing_undo = new JButton(new ImageIcon("images/arrow_left.png"));
        this.swing_undo.setToolTipText("Annuler la derni\u00e8re action");
        this.swing_undo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new Undo());
            }
        });
        this.swing_toolbar.add(this.swing_undo);
        this.swing_redo = new JButton(new ImageIcon("images/arrow_right.png"));
        this.swing_redo.setToolTipText("Refaire l'action");
        this.swing_redo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new Redo());
            }
        });
        this.swing_toolbar.add(this.swing_redo);
    }

    private void setPaused(boolean pause) {
        if (pause) {
            this.swing_playpause.setIcon(swing_var_playIcon);
            this.swing_playpause.setToolTipText("Mettre le jeu en lecture");
        } else {
            this.swing_playpause.setIcon(swing_var_pauseIcon);
            this.swing_playpause.setToolTipText("Mettre le jeu en pause");
        }
    }

    public JToolBar getJToolBar() {
        return this.swing_toolbar;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        Command c;
        if (command instanceof DesactivateUndo) {
            this.swing_undo.setEnabled(false);
        } else if (command instanceof DesactivateRedo) {
            this.swing_redo.setEnabled(false);
        } else if (command instanceof UndoableCommand) {
            this.swing_undo.setEnabled(true);
            this.swing_redo.setEnabled(true);
        }
        if (command instanceof SetUserDataCommand) {
            c = (SetUserDataCommand)command;
            this.setPaused(((SetUserDataCommand)c).getUserData().isPaused());
        }
        if (command instanceof PlayPauseCommand) {
            c = (PlayPauseCommand)command;
            this.setPaused(((PlayPauseCommand)c).isPaused());
        }
        super.receiveCommand(mediator, command);
    }
}

