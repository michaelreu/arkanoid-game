package levelsreader;

import java.util.HashMap;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class ColorsParser {

    /**
     *
     * @return hash map hith colors.
     */
    private HashMap<String, java.awt.Color> getColors() {
        HashMap<String, java.awt.Color> colors = new HashMap<>();
        colors.put("red", java.awt.Color.RED);
        colors.put("Red", java.awt.Color.RED);
        colors.put("RED", java.awt.Color.RED);
        colors.put("white", java.awt.Color.WHITE);
        colors.put("White", java.awt.Color.WHITE);
        colors.put("WHITE", java.awt.Color.WHITE);
        colors.put("yellow", java.awt.Color.YELLOW);
        colors.put("Yellow", java.awt.Color.YELLOW);
        colors.put("YELLOW", java.awt.Color.YELLOW);
        colors.put("pink", java.awt.Color.PINK);
        colors.put("Pink", java.awt.Color.PINK);
        colors.put("PINK", java.awt.Color.PINK);
        colors.put("orange", java.awt.Color.ORANGE);
        colors.put("Orange", java.awt.Color.ORANGE);
        colors.put("ORANGE", java.awt.Color.ORANGE);
        colors.put("green", java.awt.Color.GREEN);
        colors.put("Green", java.awt.Color.GREEN);
        colors.put("GREEN", java.awt.Color.GREEN);
        colors.put("lightGray", java.awt.Color.LIGHT_GRAY);
        colors.put("LIGHTGRAY", java.awt.Color.LIGHT_GRAY);
        colors.put("LightGray", java.awt.Color.LIGHT_GRAY);
        colors.put("Lightgray", java.awt.Color.LIGHT_GRAY);
        colors.put("lightgray", java.awt.Color.LIGHT_GRAY);
        colors.put("gray", java.awt.Color.GRAY);
        colors.put("Gray", java.awt.Color.GRAY);
        colors.put("GRAY", java.awt.Color.GRAY);
        colors.put("cyan", java.awt.Color.CYAN);
        colors.put("Cyan", java.awt.Color.CYAN);
        colors.put("CYAN", java.awt.Color.CYAN);
        colors.put("blue", java.awt.Color.BLUE);
        colors.put("Blue", java.awt.Color.BLUE);
        colors.put("BLUE", java.awt.Color.BLUE);
        colors.put("black", java.awt.Color.BLACK);
        colors.put("Black", java.awt.Color.BLACK);
        colors.put("BLACK", java.awt.Color.BLACK);
        return colors;
    }

    /**
     *
     *
     * @param s String * @return **java.awt.Color** color
     * @return returns corresponding Color of inputed String.
     */
    public java.awt.Color colorFromStr(String s) {
        if (s == null) {
            return null;
        }
        if (s.contains("(")) {
            s = s.substring(6, s.length() - 1);
        }
        if (s.startsWith("RGB(")) { //case RGB format was inputed.
            s = s.substring(4);
            // x = R, y = G, z
            int r, g, b;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (s.charAt(i) != ',') {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    return null;
                }
                sb.append(s.charAt(i));
                i++;
            }
            i++;
            r = Integer.parseInt(sb.toString());
            sb = new StringBuilder();
            while (s.charAt(i) != ',') {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    return null;
                }
                sb.append(s.charAt(i));
                i++;
            }
            i++;
            g = Integer.parseInt(sb.toString());
            sb = new StringBuilder();
            while (s.charAt(i) != ')') {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    return null;
                }
                sb.append(s.charAt(i));
                i++;
            }
            i++;
            b = Integer.parseInt(sb.toString());
            return new java.awt.Color(r, g, b);
        }   //case standard name was inputed.
        HashMap<String, java.awt.Color> colors = this.getColors();
        if (colors.containsKey(s)) {
            return colors.get(s);
        }
        return null;
    }
}
