package levelsreader;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class ImageParser {

    /**
     * load image.
     *
     * @param s string
     * @return image
     */
    public java.awt.Image load(String s) {
        if (s == null) {
            return null;
        }
        if (s.contains("(")) {
            s = s.substring(6, s.length() - 1);
        }
        java.awt.Image img;
        try {
            img = ImageIO.read(ClassLoader.getSystemResourceAsStream(s));
//                    ImageIO.read(new File (s));
            return img;
        } catch (IOException e) {
            return null;
        }
    }
}
