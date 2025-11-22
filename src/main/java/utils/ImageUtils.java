package utils;

import javafx.scene.image.Image;
import java.io.InputStream;

public class ImageUtils {
    public static Image carregarImagemSegura(Class<?> clazz, String caminhoDoBanco) {
        if (caminhoDoBanco == null || caminhoDoBanco.isEmpty()) {
            return null;
        }
        try {
            if (!caminhoDoBanco.startsWith("/")) {
                caminhoDoBanco = "/" + caminhoDoBanco;
            }
            InputStream is = clazz.getResourceAsStream(caminhoDoBanco);
            if (is == null) return null;
            return new Image(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
