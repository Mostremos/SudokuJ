/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import jguic.Command;
import jguic.Mediator;
import jguic.MediatorExtension;
import sudoku.commands.CheckSolutionCommand;
import sudoku.commands.FindSolutionCommand;
import sudoku.commands.LoadCommand;
import sudoku.commands.PlayPauseCommand;
import sudoku.commands.PublishCommand;
import sudoku.commands.RepaintCommand;
import sudoku.commands.SaveCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.ShowSolutionCommand;
import sudoku.commands.ValidateGridCommand;

public class GUIMessage
extends MediatorExtension {
    private boolean pause = false;
    private int time = 0;
    private int alpha = 0;
    private boolean stop = false;
    private int alpha_inc = -7;
    private JPanel swing_message = new JPanel();
    private JTextArea swing_text;
    private Timer swing_timer;
    private JPanel swing_icon;
    private Image swing_currentImage;
    private static Font swing_var_text = new Font("Tahoma", 0, 10);
    private static Color swing_var_fontColor = new Color(255, 255, 255);
    private static Image swing_var_successImage = new ImageIcon("images/success.png").getImage();
    private static Image swing_var_errorImage = new ImageIcon("images/error.png").getImage();
    private static Image swing_var_pauseImage = new ImageIcon("images/pause_big.png").getImage();
    private static final int ALPHA = 100;
    private static int REPAINT_DELAY = 30;
    private static int DISAPPEARING_DELAY = 1500;
    public static final int MESSAGE_WIDTH = 400;

    public GUIMessage(Mediator parent) {
        super(parent);
        this.swing_message.setBackground(new Color(0, 0, 0, 100));
        this.swing_message.setBorder(new LineBorder(new Color(0, 0, 0, 20), 2));
        this.swing_message.setMinimumSize(new Dimension(400, 0));
        this.swing_message.setMaximumSize(new Dimension(400, 200));
        this.swing_text = new JTextArea(){

            protected void paintComponent(Graphics g) {
                ((Graphics2D)g).setComposite(AlphaComposite.getInstance(3, Math.min(1.0f, (float)GUIMessage.this.alpha / 100.0f)));
                super.paintComponent(g);
            }
        };
        this.swing_text.setEditable(false);
        this.swing_text.setOpaque(false);
        this.swing_text.setForeground(swing_var_fontColor);
        this.swing_text.setFont(swing_var_text.deriveFont(1, 14.0f));
        this.swing_text.setPreferredSize(new Dimension(340, 40));
        this.swing_text.setMinimumSize(new Dimension(340, 0));
        this.swing_text.setMaximumSize(new Dimension(340, 200));
        this.swing_text.setWrapStyleWord(true);
        this.swing_text.setLineWrap(true);
        this.swing_icon = new JPanel(){

            protected void paintComponent(Graphics g) {
                ((Graphics2D)g).setComposite(AlphaComposite.getInstance(3, Math.min(1.0f, (float)GUIMessage.this.alpha / 100.0f)));
                g.drawImage(GUIMessage.this.swing_currentImage, 0, 0, this);
            }
        };
        this.swing_icon.setPreferredSize(new Dimension(40, 32));
        this.swing_icon.setOpaque(false);
        this.swing_message.add(this.swing_icon);
        this.swing_message.add(this.swing_text);
        this.swing_timer = new Timer(DISAPPEARING_DELAY, new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUIMessage.this.swing_message.setBackground(new Color(0, 0, 0, GUIMessage.this.alpha));
                if (GUIMessage.this.time == 0) {
                    GUIMessage.this.swing_timer.setDelay(REPAINT_DELAY);
                    GUIMessage.this.time = 1;
                }
                if (GUIMessage.this.pause && GUIMessage.this.swing_currentImage == swing_var_pauseImage && (GUIMessage.this.alpha < 50 || GUIMessage.this.alpha > 100)) {
                    GUIMessage.this.alpha_inc = -GUIMessage.this.alpha_inc;
                }
                if (GUIMessage.this.stop) {
                    GUIMessage.this.alpha_inc = -10;
                }
                GUIMessage gUIMessage = GUIMessage.this;
                gUIMessage.alpha = gUIMessage.alpha + GUIMessage.this.alpha_inc;
                if (GUIMessage.this.alpha < 0) {
                    GUIMessage.this.alpha = 0;
                    GUIMessage.this.swing_timer.stop();
                    GUIMessage.this.swing_message.setVisible(false);
                    if (GUIMessage.this.pause) {
                        GUIMessage.this.pause();
                    }
                }
                GUIMessage.this.handle(new RepaintCommand());
            }
        });
        this.swing_message.setVisible(false);
    }

    public void start(String message, Image image) {
        this.stop();
        this.stop = false;
        this.swing_text.setText(message);
        this.swing_currentImage = image;
        this.time = 0;
        this.alpha = 100;
        if (this.swing_currentImage != swing_var_pauseImage) {
            REPAINT_DELAY = 30;
            DISAPPEARING_DELAY = 1500;
            this.alpha_inc = -5;
        }
        this.swing_message.setBackground(new Color(0, 0, 0, 100));
        this.swing_message.setVisible(true);
        this.swing_timer.setDelay(DISAPPEARING_DELAY);
        this.swing_timer.start();
    }

    public void stop() {
        this.stop = true;
    }

    public void pause() {
        REPAINT_DELAY = 60;
        DISAPPEARING_DELAY = 0;
        this.alpha_inc = -3;
        this.start("Jeu en pause...", swing_var_pauseImage);
    }

    public JComponent getJComponent() {
        return this.swing_message;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof PlayPauseCommand) {
            boolean bl = this.pause = !this.pause;
            if (this.pause) {
                this.pause();
            }
        } else {
            this.stop();
        }
        if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c = (SetUserDataCommand)command;
            if (c.getUserData().isPaused()) {
                this.pause = true;
                this.pause();
            } else {
                this.pause = false;
                this.stop();
            }
        } else if (command instanceof CheckSolutionCommand) {
            CheckSolutionCommand c = (CheckSolutionCommand)command;
            if (!c.isGridFull()) {
                this.start("La grille n'est pas compl\u00e8te", swing_var_errorImage);
            } else if (c.isValid()) {
                this.start("Vous avez remport\u00e9 la partie !", swing_var_successImage);
            } else {
                this.start("Votre grille n'est pas valide", swing_var_errorImage);
            }
        } else if (command instanceof ShowSolutionCommand) {
            ShowSolutionCommand c = (ShowSolutionCommand)command;
            if (!c.hasSolution()) {
                this.start("La grille n'a pas de solution", swing_var_errorImage);
            }
        } else if (command instanceof SaveCommand) {
            SaveCommand c = (SaveCommand)command;
            if (c.isSaved()) {
                this.start("La " + (c.getMode() == 1 ? "grille" : "partie") + " a \u00e9t\u00e9 sauvegard\u00e9e", swing_var_successImage);
            } else {
                this.start("Il y a eu une erreur lors de la sauvegarde de la " + (c.getMode() == 1 ? "grille" : "partie"), swing_var_errorImage);
            }
        } else if (command instanceof LoadCommand) {
            LoadCommand c = (LoadCommand)command;
            if (!c.isLoad()) {
                this.start("Il y a eu une erreur lors du chargement de la " + (c.getMode() == 1 ? "grille" : "partie"), swing_var_errorImage);
            } else if (!c.isValid()) {
                this.start("Le fichier ne contient pas de " + (c.getMode() == 1 ? "grille" : "partie") + " valide", swing_var_errorImage);
            }
        } else if (command instanceof FindSolutionCommand) {
            FindSolutionCommand c = (FindSolutionCommand)command;
            if (c.hasSolution()) {
                this.start("Une solution a \u00e9t\u00e9 trouv\u00e9e", swing_var_successImage);
            } else {
                this.start("La grille n'a pas de solution, supprimez quelques cases ou v\u00e9rifiez qu'il n'y a pas de conflits", swing_var_errorImage);
            }
        } else if (command instanceof ValidateGridCommand) {
            ValidateGridCommand c = (ValidateGridCommand)command;
            if (c.isValid()) {
                this.start("La grille est valide, vous pouvez la publier", swing_var_successImage);
            } else {
                this.start("La grille n'a pas de solution ou en a plusieurs", swing_var_errorImage);
            }
        } else if (command instanceof PublishCommand) {
            PublishCommand c = (PublishCommand)command;
            if (c.isValid()) {
                if (c.isPublished()) {
                    this.start("La grille a \u00e9t\u00e9 publi\u00e9e", swing_var_successImage);
                } else {
                    this.start("Il y a eu une erreur lors de la publication de la grille", swing_var_errorImage);
                }
            } else {
                this.start("La grille n'est pas valide", swing_var_errorImage);
            }
        }
        super.receiveCommand(mediator, command);
    }
}

