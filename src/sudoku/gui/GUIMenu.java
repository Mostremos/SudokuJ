/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import jguic.util.DesactivateRedo;
import jguic.util.DesactivateUndo;
import jguic.util.Redo;
import jguic.util.Undo;
import jguic.util.UndoableCommand;
import sudoku.commands.CheckSolutionCommand;
import sudoku.commands.ExitCommand;
import sudoku.commands.HintCommand;
import sudoku.commands.FindSolutionCommand;
import sudoku.commands.LoadCommand;
import sudoku.commands.NewGridCommand;
import sudoku.commands.PlayPauseCommand;
import sudoku.commands.PublishCommand;
import sudoku.commands.ResetCommand;
import sudoku.commands.SaveCommand;
import sudoku.commands.SetBackgroundImageCommand;
import sudoku.commands.SetCirclesCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.ShowSolutionCommand;
import sudoku.commands.ValidateGridCommand;
import sudoku.commands.SetLanguageCommand;
import sudoku.core.Difficulty;
import sudoku.core.Grid;
import sudoku.util.I18n;

public class GUIMenu
extends MediatorExtension {
    private JMenuBar swing_menu = new JMenuBar();
    private JMenuItem swing_saveGame;
    private JMenuItem swing_loadGame;
    private JMenuItem swing_resetGame;
    private JMenuItem swing_publishGrid;
    private JMenuItem swing_saveGrid;
    private JMenuItem swing_playpause;
    private JMenuItem swing_undo;
    private JMenuItem swing_redo;
    private JMenuItem swing_hint;
    private JMenuItem swing_checkSolution;
    private JMenuItem swing_showSolutionP;
    private JMenuItem swing_findSolution;
    private JMenuItem swing_showSolutionC;
    private JMenuItem swing_validate;
    private JMenuItem swing_help;
    private JMenuItem swing_about;
    private JRadioButtonMenuItem possibilitiesWithFigures;
    private JRadioButtonMenuItem possibilitiesWithCircles;
    private static Image swing_var_iconImage = new ImageIcon("images/sudokuJ.png").getImage();
    private static Image swing_var_helpImage = new ImageIcon("images/help.png").getImage();
    private static Image swing_var_aboutImage = new ImageIcon("images/about.png").getImage();

    public GUIMenu(Mediator parent) {
        super(parent);
        this.constructFileMenu();
        this.constructActionsMenu();
        this.constructOptionsMenu();
        this.constructHelpMenu();
    }

    private void constructFileMenu() {
        JMenu swing_file = new JMenu(I18n.get("menu.file"));
        JMenu swing_newGrid = new JMenu(I18n.get("menu.new_grid"));
        JMenuItem swing_newGridEasy = new JMenuItem(I18n.get("menu.easy"));
        swing_newGridEasy.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.generateGrid(Difficulty.EASY);
            }
        });
        JMenuItem swing_newGridMedium = new JMenuItem(I18n.get("menu.medium"));
        swing_newGridMedium.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.generateGrid(Difficulty.MEDIUM);
            }
        });
        JMenuItem swing_newGridHard = new JMenuItem(I18n.get("menu.hard"));
        swing_newGridHard.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.generateGrid(Difficulty.HARD);
            }
        });
        JMenuItem swing_newGridLoad = new JMenuItem(I18n.get("menu.import_grid"));
        swing_newGridLoad.setAccelerator(KeyStroke.getKeyStroke(73, 2));
        swing_newGridLoad.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new LoadCommand(0));
            }
        });
        swing_newGrid.add(swing_newGridEasy);
        swing_newGrid.add(swing_newGridMedium);
        swing_newGrid.add(swing_newGridHard);
        swing_newGrid.addSeparator();
        swing_newGrid.add(swing_newGridLoad);
        swing_file.add(swing_newGrid);
        this.swing_resetGame = new JMenuItem(I18n.get("menu.reset"));
        this.swing_resetGame.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new ResetCommand());
            }
        });
        this.swing_saveGame = new JMenuItem(I18n.get("menu.save_game"));
        this.swing_saveGame.setAccelerator(KeyStroke.getKeyStroke(83, 2));
        this.swing_saveGame.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new SaveCommand(0));
            }
        });
        this.swing_loadGame = new JMenuItem(I18n.get("menu.load_game"));
        this.swing_loadGame.setAccelerator(KeyStroke.getKeyStroke(79, 2));
        this.swing_loadGame.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new LoadCommand(0));
            }
        });
        swing_file.add(this.swing_resetGame);
        swing_file.add(this.swing_saveGame);
        swing_file.add(this.swing_loadGame);
        swing_file.add(new JToolBar.Separator());
        JMenuItem swing_newGridCreator = new JMenuItem(I18n.get("menu.new_creator"));
        swing_newGridCreator.setAccelerator(KeyStroke.getKeyStroke(78, 3));
        swing_newGridCreator.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new NewGridCommand(new Grid(), 1));
            }
        });
        this.swing_publishGrid = new JMenuItem(I18n.get("menu.publish"));
        this.swing_publishGrid.setAccelerator(KeyStroke.getKeyStroke(88, 3));
        this.swing_publishGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new PublishCommand());
            }
        });
        this.swing_saveGrid = new JMenuItem(I18n.get("menu.save_grid"));
        this.swing_saveGrid.setAccelerator(KeyStroke.getKeyStroke(83, 3));
        this.swing_saveGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new SaveCommand(1));
            }
        });
        JMenuItem swing_loadGrid = new JMenuItem(I18n.get("menu.load_grid"));
        swing_loadGrid.setAccelerator(KeyStroke.getKeyStroke(79, 3));
        swing_loadGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new LoadCommand(1));
            }
        });
        swing_file.add(swing_newGridCreator);
        swing_file.add(this.swing_publishGrid);
        swing_file.add(this.swing_saveGrid);
        swing_file.add(swing_loadGrid);
        swing_file.add(new JToolBar.Separator());
        JMenuItem swing_exit = new JMenuItem(I18n.get("menu.exit"));
        swing_exit.setAccelerator(KeyStroke.getKeyStroke(81, 2));
        swing_exit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new ExitCommand());
            }
        });
        swing_file.add(swing_exit);
        this.swing_menu.add(swing_file);
    }

    private void constructActionsMenu() {
        JMenu actions = new JMenu(I18n.get("menu.actions"));
        this.swing_hint = new JMenuItem(I18n.get("menu.hint"));
        this.swing_hint.setAccelerator(KeyStroke.getKeyStroke(72, 256)); // Ctrl+H
        this.swing_hint.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new HintCommand());
            }
        });
        actions.add(this.swing_hint);
        actions.add(new JToolBar.Separator());
        this.swing_showSolutionP = new JMenuItem(I18n.get("menu.show_solution"));
        this.swing_showSolutionP.setAccelerator(KeyStroke.getKeyStroke(116, 0));
        this.swing_showSolutionP.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new ShowSolutionCommand());
            }
        });
        actions.add(this.swing_showSolutionP);
        this.swing_checkSolution = new JMenuItem(I18n.get("menu.check_solution"));
        this.swing_checkSolution.setAccelerator(KeyStroke.getKeyStroke(115, 0));
        this.swing_checkSolution.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new CheckSolutionCommand());
            }
        });
        actions.add(this.swing_checkSolution);
        actions.add(new JToolBar.Separator());
        this.swing_findSolution = new JMenuItem(I18n.get("menu.find_solution"));
        this.swing_findSolution.setAccelerator(KeyStroke.getKeyStroke(117, 0));
        this.swing_findSolution.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new FindSolutionCommand());
            }
        });
        actions.add(this.swing_findSolution);
        this.swing_showSolutionC = new JMenuItem(I18n.get("menu.show_solution"));
        this.swing_showSolutionC.setAccelerator(KeyStroke.getKeyStroke(118, 0));
        this.swing_showSolutionC.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new ShowSolutionCommand());
            }
        });
        actions.add(this.swing_showSolutionC);
        this.swing_validate = new JMenuItem(I18n.get("menu.validate"));
        this.swing_validate.setAccelerator(KeyStroke.getKeyStroke(119, 0));
        this.swing_validate.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new ValidateGridCommand());
            }
        });
        actions.add(this.swing_validate);
        actions.add(new JToolBar.Separator());
        this.swing_playpause = new JMenuItem(I18n.get("menu.pause"));
        this.swing_playpause.setAccelerator(KeyStroke.getKeyStroke(80, 0));
        this.swing_playpause.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new PlayPauseCommand());
            }
        });
        actions.add(this.swing_playpause);
        actions.add(new JToolBar.Separator());
        this.swing_undo = new JMenuItem(I18n.get("menu.undo"));
        this.swing_undo.setAccelerator(KeyStroke.getKeyStroke(90, 2));
        this.swing_undo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new Undo());
            }
        });
        actions.add(this.swing_undo);
        this.swing_redo = new JMenuItem(I18n.get("menu.redo"));
        this.swing_redo.setAccelerator(KeyStroke.getKeyStroke(89, 2));
        this.swing_redo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new Redo());
            }
        });
        actions.add(this.swing_redo);
        this.swing_menu.add(actions);
    }

    public void constructOptionsMenu() {
        JMenu options = new JMenu(I18n.get("menu.options"));
        this.possibilitiesWithFigures = new JRadioButtonMenuItem(I18n.get("menu.possibilities_numbers"));
        this.possibilitiesWithFigures.setSelected(true);
        this.possibilitiesWithFigures.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new SetCirclesCommand(false));
            }
        });
        this.possibilitiesWithCircles = new JRadioButtonMenuItem(I18n.get("menu.possibilities_dots"));
        this.possibilitiesWithCircles.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new SetCirclesCommand(true));
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(this.possibilitiesWithFigures);
        group.add(this.possibilitiesWithCircles);
        options.add(this.possibilitiesWithFigures);
        options.add(this.possibilitiesWithCircles);
        options.add(new JToolBar.Separator());
        JMenu langMenu = new JMenu(I18n.get("menu.language"));
        JMenuItem langEn = new JMenuItem(I18n.get("menu.lang_english"));
        langEn.addActionListener(e -> GUIMenu.this.handle(new SetLanguageCommand("en")));
        JMenuItem langEs = new JMenuItem(I18n.get("menu.lang_spanish"));
        langEs.addActionListener(e -> GUIMenu.this.handle(new SetLanguageCommand("es")));
        JMenuItem langFr = new JMenuItem(I18n.get("menu.lang_french"));
        langFr.addActionListener(e -> GUIMenu.this.handle(new SetLanguageCommand("fr")));
        langMenu.add(langEn);
        langMenu.add(langEs);
        langMenu.add(langFr);
        options.add(langMenu);
        options.add(new JToolBar.Separator());
        JMenuItem swing_backgroundImage = new JMenuItem(I18n.get("menu.background"));
        swing_backgroundImage.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMenu.this.handle(new SetBackgroundImageCommand());
            }
        });
        options.add(swing_backgroundImage);
        this.swing_menu.add(options);
    }

    public void constructHelpMenu() {
        JMenu help = new JMenu(I18n.get("menu.help"));
        this.swing_help = new JMenuItem(I18n.get("menu.help_topic"));
        this.swing_help.setAccelerator(KeyStroke.getKeyStroke(112, 0));
        help.add(this.swing_help);
        help.add(new JToolBar.Separator());
        this.swing_about = new JMenuItem(I18n.get("menu.about"));
        help.add(this.swing_about);
        this.swing_menu.add(help);
        this.swing_help.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                new HelpScreen();
            }
        });
        this.swing_about.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                new AboutScreen();
            }
        });
    }

    private void setPaused(boolean pause) {
        this.swing_playpause.setText(pause ? I18n.get("menu.resume") : I18n.get("menu.pause"));
    }

    /** Actualiza todos los textos del menú al idioma actual */
    public void refreshTexts() {
        this.swing_menu.removeAll();
        this.constructFileMenu();
        this.constructActionsMenu();
        this.constructOptionsMenu();
        this.constructHelpMenu();
        this.swing_menu.revalidate();
        this.swing_menu.repaint();
    }

    public JMenuBar getJMenuBar() {
        return this.swing_menu;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof DesactivateUndo) {
            this.swing_undo.setEnabled(false);
        } else if (command instanceof DesactivateRedo) {
            this.swing_redo.setEnabled(false);
        } else if (command instanceof UndoableCommand) {
            this.swing_undo.setEnabled(true);
            this.swing_redo.setEnabled(true);
        }
        if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c = (SetUserDataCommand)command;
            if (c.getUserData().getMode() == 0) {
                this.swing_saveGame.setEnabled(true);
                this.swing_resetGame.setEnabled(true);
                this.swing_hint.setEnabled(true);
                this.swing_checkSolution.setEnabled(true);
                this.swing_showSolutionP.setEnabled(true);
                this.swing_playpause.setEnabled(true);
                this.swing_publishGrid.setEnabled(false);
                this.swing_saveGrid.setEnabled(false);
                this.swing_findSolution.setEnabled(false);
                this.swing_showSolutionC.setEnabled(false);
                this.swing_validate.setEnabled(false);
            } else {
                this.swing_saveGame.setEnabled(false);
                this.swing_resetGame.setEnabled(false);
                this.swing_hint.setEnabled(false);
                this.swing_checkSolution.setEnabled(false);
                this.swing_showSolutionP.setEnabled(false);
                this.swing_playpause.setEnabled(false);
                this.swing_publishGrid.setEnabled(true);
                this.swing_saveGrid.setEnabled(true);
                this.swing_findSolution.setEnabled(true);
                this.swing_showSolutionC.setEnabled(true);
                this.swing_validate.setEnabled(true);
            }
            this.setPaused(c.getUserData().isPaused());
        } else if (command instanceof PlayPauseCommand) {
            this.setPaused(((PlayPauseCommand)command).isPaused());
        } else if (command instanceof SetCirclesCommand) {
            boolean circles = ((SetCirclesCommand)command).isCircles();
            this.possibilitiesWithFigures.setSelected(!circles);
            this.possibilitiesWithCircles.setSelected(circles);
        }
        super.receiveCommand(mediator, command);
    }

    private void generateGrid(Difficulty level) {
        NewGridCommand c = new NewGridCommand();
        Grid g = new Grid();
        g.generate(level);
        c.setGrid(g);
        c.setMode(0);
        c.setDifficulty(level);
        this.handle(c);
    }

    public class HelpScreen {
        private JFrame swing_helpFrame = new JFrame();
        private JPanel swing_helpPanel = null;

        public HelpScreen() {
            this.swing_helpFrame.setIconImage(swing_var_iconImage);
            this.swing_helpFrame.setSize(500, 400);
            this.swing_helpFrame.setResizable(false);
            this.swing_helpFrame.setTitle(I18n.get("help.title"));
            Dimension screen = this.swing_helpFrame.getToolkit().getScreenSize();
            this.swing_helpFrame.setLocation((int)(screen.getWidth() - (double)this.swing_helpFrame.getWidth()) / 2, (int)(screen.getHeight() - (double)this.swing_helpFrame.getHeight()) / 2);
            this.swing_helpPanel = new JPanel(new java.awt.BorderLayout());
            this.swing_helpPanel.setSize(500, 400);
            this.swing_helpPanel.setBackground(Color.WHITE);
            JTextArea rules = new JTextArea(I18n.get("help.rules"), 14, 42);
            rules.setFont(new Font("Tahoma", 0, 11));
            rules.setOpaque(true);
            rules.setBackground(Color.WHITE);
            rules.setWrapStyleWord(true);
            rules.setLineWrap(true);
            rules.setEditable(false);
            rules.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            JScrollPane scroll = new JScrollPane(rules);
            scroll.setBorder(null);
            scroll.getViewport().setBackground(Color.WHITE);
            this.swing_helpPanel.add(scroll, java.awt.BorderLayout.CENTER);
            this.swing_helpFrame.add(this.swing_helpPanel);
            this.swing_helpFrame.setVisible(true);
            this.swing_helpFrame.setDefaultCloseOperation(2);
        }
    }

    public class AboutScreen {
        private JFrame swing_aboutFrame = new JFrame();
        private JPanel swing_aboutPanel = null;

        public AboutScreen() {
            this.swing_aboutFrame.setIconImage(swing_var_iconImage);
            this.swing_aboutFrame.setSize(506, 392);
            this.swing_aboutFrame.setResizable(false);
            this.swing_aboutFrame.setTitle(I18n.get("about.title"));
            this.swing_aboutPanel = new JPanel(new java.awt.BorderLayout());
            this.swing_aboutPanel.setSize(500, 360);
            this.swing_aboutPanel.setBackground(Color.WHITE);
            Dimension screen = this.swing_aboutFrame.getToolkit().getScreenSize();
            this.swing_aboutFrame.setLocation((int)(screen.getWidth() - (double)this.swing_aboutFrame.getWidth()) / 2, (int)(screen.getHeight() - (double)this.swing_aboutFrame.getHeight()) / 2);
            JTextArea about = new JTextArea(I18n.get("about.text"), 6, 40);
            about.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
            about.setFont(new Font("Tahoma", 0, 11));
            about.setOpaque(true);
            about.setBackground(Color.WHITE);
            about.setWrapStyleWord(true);
            about.setLineWrap(true);
            about.setEditable(false);
            this.swing_aboutPanel.add(about, java.awt.BorderLayout.CENTER);
            this.swing_aboutFrame.add(this.swing_aboutPanel);
            this.swing_aboutFrame.setVisible(true);
            this.swing_aboutFrame.setDefaultCloseOperation(2);
        }
    }
}

