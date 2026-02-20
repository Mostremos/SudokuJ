package sudoku.util;

import java.awt.Image;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 * Utilidad para cargar recursos (imágenes) desde el classpath.
 * Permite que la aplicación funcione tanto desde JAR como desde sistema de archivos.
 */
public class ResourceLoader {
    
    /**
     * Carga un ImageIcon desde el classpath o sistema de archivos.
     * @param path Ruta del recurso (debe empezar con / para classpath)
     * @return ImageIcon cargado o null si no se encuentra
     */
    public static ImageIcon loadImageIcon(String path) {
        // Intentar cargar desde classpath primero
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        
        try {
            InputStream is = ResourceLoader.class.getResourceAsStream(path);
            if (is != null) {
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                return new ImageIcon(buffer);
            }
        } catch (Exception e) {
            // Continuar con fallback
        }
        
        // Fallback: intentar cargar desde sistema de archivos (para desarrollo)
        try {
            return new ImageIcon(path.startsWith("/") ? path.substring(1) : path);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + path);
            return null;
        }
    }
    
    /**
     * Carga una Image desde el classpath o sistema de archivos.
     * @param path Ruta del recurso
     * @return Image cargada o null si no se encuentra
     */
    public static Image loadImage(String path) {
        ImageIcon icon = loadImageIcon(path);
        return icon != null ? icon.getImage() : null;
    }
}
