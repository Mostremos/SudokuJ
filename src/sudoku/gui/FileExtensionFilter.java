/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileFilter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FileExtensionFilter
extends FileFilter {
    private String description;
    private ArrayList<String> extensions = new ArrayList();

    public FileExtensionFilter() {
        this.description = "";
    }

    public FileExtensionFilter(String description) {
        this.description = description;
    }

    public FileExtensionFilter(String description, String extension) {
        this.description = description;
        this.extensions.add(extension);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addExtension(String extension) {
        this.extensions.add(extension);
    }

    public void removeExtension(String extension) {
        this.extensions.remove(extension);
    }

    public void clearExtensions() {
        this.extensions.clear();
    }

    public ArrayList<String> getExtensions() {
        return this.extensions;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        boolean accepted = this.extensions.size() == 0;
        int i = 0;
        while (i < this.extensions.size()) {
            if (file.getName().endsWith(this.extensions.get(i)) && !accepted) {
                accepted = true;
            }
            ++i;
        }
        return accepted;
    }
}

