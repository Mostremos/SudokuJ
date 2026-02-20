package sudoku;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.UIManager;
import sudoku.gui.GUIMain;
import sudoku.util.ResourceLoader;

/**
 * Clase principal de SudokuJ - Actualizada para Java 11+
 */
public class Main {
    private Splash logo = new Splash();
    private Timer t;
    private Timer t_bar;
    private JFrame window;
    private boolean ready = false;

    public Main() {
        this.logo.setVisible(true);
        this.t = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.this.window != null && Main.this.ready) {
                    Main.this.t_bar.stop();
                    Main.this.t.stop();
                    Main.this.logo.setVisible(false);
                    Main.this.window.setVisible(true);
                }
            }
        });
        this.t.start();
        GUIMain main = new GUIMain();
        this.window = main.getJFrame();
        Dimension screen = this.window.getToolkit().getScreenSize();
        this.window.setLocation(
            (int)(screen.getWidth() - (double)this.window.getWidth()) / 2, 
            (int)(screen.getHeight() - (double)this.window.getHeight()) / 2
        );
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ready = true;
    }

    public static void main(String[] args) {
        // Configurar Look and Feel multiplataforma
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } else if (os.contains("mac")) {
                UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
            } else {
                // Linux y otros - usar Look and Feel del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (Exception e) {
            // Si falla, usar el Look and Feel por defecto
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Ignorar si también falla
            }
        }
        new Main();
    }

    /**
     * Ventana de splash screen al iniciar la aplicación
     */
    class Splash extends JWindow {
        private BufferedImage bg;
        private BufferedImage bar;
        private int x_bar = 0;

        public Splash() {
            Main.this.t_bar = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Splash.this.repaint();
                }
            });
            Main.this.t_bar.start();
            
            // Cargar imágenes desde recursos (classpath)
            ImageIcon bg_icon = loadImageIcon("/images/logo.png");
            if (bg_icon != null) {
                this.bg = new BufferedImage(bg_icon.getIconWidth(), bg_icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                this.bg.createGraphics().drawImage(bg_icon.getImage(), 0, 0, null);
            }
            
            ImageIcon bar_icon = loadImageIcon("/images/moving_bar.png");
            if (bar_icon != null) {
                this.bar = new BufferedImage(bar_icon.getIconWidth(), bar_icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                this.bar.createGraphics().drawImage(bar_icon.getImage(), 0, 0, null);
            }
            
            if (bg_icon != null) {
                this.x_bar -= this.bar != null ? this.bar.getWidth() / 2 : 0;
                this.setSize(bg_icon.getIconWidth(), bg_icon.getIconHeight());
            } else {
                this.setSize(400, 300);
            }
            
            Dimension screen = this.getToolkit().getScreenSize();
            this.setLocation(
                (int)(screen.getWidth() - (double)this.getWidth()) / 2, 
                (int)(screen.getHeight() - (double)this.getHeight()) / 2
            );
        }

        /**
         * Carga un ImageIcon desde el classpath
         */
        private ImageIcon loadImageIcon(String path) {
            return ResourceLoader.loadImageIcon(path);
        }

        @Override
        public void paint(Graphics g) {
            // CORREGIDO: Usar el parámetro Graphics g en lugar de getGraphics()
            if (this.bg != null) {
                g.drawImage(this.bg, 0, 0, this);
            }
            if (this.bar != null) {
                g.drawImage(this.bar, this.x_bar, 313, this);
            }
            this.x_bar += 10;
            if (this.x_bar > 0) {
                this.x_bar -= this.bar != null ? this.bar.getWidth() / 2 : 0;
            }
        }
    }
}

