package sudoku.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import sudoku.core.Difficulty;

public class Options implements Serializable {
    private static final long serialVersionUID = 1L;
    private String backgroundImage = "";
    private boolean circles = false;  // false = números en notas, true = puntos
    private Difficulty level = Difficulty.EASY;
    private String language = "es";   // es, en, fr, pt, zh, ja, ru
    private transient String file;

    public Options() {
        this.file = getOptionsPath();
        this.load();
    }

    /** Ruta de options en directorio de usuario (evita conflicto con original) */
    private static String getOptionsPath() {
        String dir;
        String os = System.getProperty("os.name", "").toLowerCase();
        if (os.contains("win")) {
            String appData = System.getenv("APPDATA");
            dir = (appData != null ? appData : System.getProperty("user.home")) + File.separator + "SudokuJ";
        } else {
            dir = System.getProperty("user.home") + File.separator + ".sudokuj";
        }
        new File(dir).mkdirs();
        return dir + File.separator + "options.ser";
    }

    public boolean getCircles() {
        return this.circles;
    }

    public void setCircles(boolean circles) {
        this.circles = circles;
        this.save();
    }

    public String getBackgroungImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String image) {
        this.backgroundImage = image;
        this.save();
    }

    public Difficulty getLevel() {
        return this.level;
    }

    public void setLevel(Difficulty level) {
        this.level = level;
        this.save();
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        if (language != null && java.util.Set.of("es", "en", "fr", "pt", "zh", "ja", "ru").contains(language)) {
            this.language = language;
            this.save();
        }
    }

    private void save() {
        try {
            if (this.file == null) this.file = getOptionsPath();
            try (FileOutputStream fos = new FileOutputStream(this.file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this);
            }
        } catch (Exception ignored) {}
    }

    private void load() {
        try {
            if (this.file == null) this.file = getOptionsPath();
            try (FileInputStream fis = new FileInputStream(this.file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object o = ois.readObject();
                if (o instanceof Options) {
                    Options opt = (Options) o;
                    this.circles = opt.circles;
                    this.backgroundImage = opt.backgroundImage;
                    this.level = opt.level;
                    if (opt.language != null && java.util.Set.of("es", "en", "fr", "pt", "zh", "ja", "ru").contains(opt.language)) {
                        this.language = opt.language;
                    }
                }
            }
        } catch (Exception ignored) {}
    }
}

