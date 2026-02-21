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
import sudoku.commands.SetLanguageCommand;
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
    private JButton swing_newGrid;
    private JButton swing_saveGrid;
    private JButton swing_loadGrid;
    private JButton swing_resetGrid;
    private JButton swing_hintBtn;
    private JButton swing_showGrid;
    private JButton swing_checkGrid;
    private boolean isPaused = false;
    public static ImageIcon swing_var_pauseIcon = new ImageIcon("images/pause.png");
    public static ImageIcon swing_var_playIcon = new ImageIcon("images/play.png");

    public GUIPlayerToolbar(Mediator parent) {
        super(parent);
        this.swing_newGrid = new JButton(new ImageIcon("images/blank_document.png"));
        this.swing_newGrid.setToolTipText(I18n.get("toolbar.new_grid"));
        this.swing_newGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                NewGridCommand c = new NewGridCommand();
                c.setMode(0);
                GUIPlayerToolbar.this.handle(c);
            }
        });
        this.swing_toolbar.add(this.swing_newGrid);
        this.swing_saveGrid = new JButton(new ImageIcon("images/disk.png"));
        this.swing_saveGrid.setToolTipText(I18n.get("menu.save_game"));
        this.swing_saveGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new SaveCommand(0));
            }
        });
        this.swing_toolbar.add(this.swing_saveGrid);
        this.swing_loadGrid = new JButton(new ImageIcon("images/open_dir.png"));
        this.swing_loadGrid.setToolTipText(I18n.get("menu.load_game"));
        this.swing_loadGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new LoadCommand(0));
            }
        });
        this.swing_toolbar.add(this.swing_loadGrid);
        this.swing_resetGrid = new JButton(new ImageIcon("images/refresh.png"));
        this.swing_resetGrid.setToolTipText(I18n.get("menu.reset"));
        this.swing_resetGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new ResetCommand());
            }
        });
        this.swing_toolbar.add(this.swing_resetGrid);
        this.swing_toolbar.addSeparator();
        this.swing_hintBtn = new JButton(new ImageIcon("images/help.png"));
        this.swing_hintBtn.setToolTipText(I18n.get("menu.hint"));
        this.swing_hintBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new HintCommand());
            }
        });
        this.swing_toolbar.add(this.swing_hintBtn);
        this.swing_showGrid = new JButton(new ImageIcon("images/grid_full.png"));
        this.swing_showGrid.setToolTipText(I18n.get("menu.show_solution"));
        this.swing_showGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new ShowSolutionCommand());
            }
        });
        this.swing_toolbar.add(this.swing_showGrid);
        this.swing_checkGrid = new JButton(new ImageIcon("images/check.png"));
        this.swing_checkGrid.setToolTipText(I18n.get("menu.check_solution"));
        this.swing_checkGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new CheckSolutionCommand());
            }
        });
        this.swing_toolbar.add(this.swing_checkGrid);
        this.swing_toolbar.addSeparator();
        this.swing_playpause = new JButton(swing_var_pauseIcon);
        this.swing_playpause.setToolTipText(I18n.get("menu.pause"));
        this.swing_playpause.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new PlayPauseCommand());
            }
        });
        this.swing_toolbar.add(this.swing_playpause);
        this.swing_undo = new JButton(new ImageIcon("images/arrow_left.png"));
        this.swing_undo.setToolTipText(I18n.get("menu.undo"));
        this.swing_undo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new Undo());
            }
        });
        this.swing_toolbar.add(this.swing_undo);
        this.swing_redo = new JButton(new ImageIcon("images/arrow_right.png"));
        this.swing_redo.setToolTipText(I18n.get("menu.redo"));
        this.swing_redo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIPlayerToolbar.this.handle(new Redo());
            }
        });
        this.swing_toolbar.add(this.swing_redo);
    }

    private void setPaused(boolean pause) {
        this.isPaused = pause;
        if (pause) {
            this.swing_playpause.setIcon(swing_var_playIcon);
            this.swing_playpause.setToolTipText(I18n.get("menu.resume"));
        } else {
            this.swing_playpause.setIcon(swing_var_pauseIcon);
            this.swing_playpause.setToolTipText(I18n.get("menu.pause"));
        }
    }

    private void refreshLanguage() {
        this.swing_newGrid.setToolTipText(I18n.get("toolbar.new_grid"));
        this.swing_saveGrid.setToolTipText(I18n.get("menu.save_game"));
        this.swing_loadGrid.setToolTipText(I18n.get("menu.load_game"));
        this.swing_resetGrid.setToolTipText(I18n.get("menu.reset"));
        this.swing_hintBtn.setToolTipText(I18n.get("menu.hint"));
        this.swing_showGrid.setToolTipText(I18n.get("menu.show_solution"));
        this.swing_checkGrid.setToolTipText(I18n.get("menu.check_solution"));
        this.swing_undo.setToolTipText(I18n.get("menu.undo"));
        this.swing_redo.setToolTipText(I18n.get("menu.redo"));
        this.setPaused(this.isPaused);
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
        if (command instanceof SetLanguageCommand) {
            this.refreshLanguage();
        }
        super.receiveCommand(mediator, command);
    }
}

