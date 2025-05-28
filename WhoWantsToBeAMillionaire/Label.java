import greenfoot.*;
import java.awt.Font;

/**
 * Εμφανίζει προσαρμοσμένο κείμενο στην οθόνη.
 * Χρησιμοποιείται για τίτλους, πληροφορίες, οδηγίες και απαντήσεις.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class Label extends Actor {
    private GreenfootImage image;
    private int fontSize;
    private Color textColor;
    private Color bgColor;
    private String value;
    private String fontFamily;
    private boolean bold;

    public Label(String text, int fontSize) {
        this(text, fontSize, new Color(255,255,255), new Color(0, 0, 0, 0), "SansSerif", false);
    }

    public Label(String text, int fontSize, Color textColor, Color bgColor) {
        this(text, fontSize, textColor, bgColor, "SansSerif", false);
    }

    public Label(String text, int fontSize, Color textColor, Color bgColor, String fontFamily, boolean bold) {
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.fontFamily = fontFamily;
        this.bold = bold;
        setValue(text);
    }

    public void setValue(String text) {
        this.value = text;

        // Γραμματοσειρά
        greenfoot.Font font = new greenfoot.Font(fontFamily, bold, false, fontSize);

        // Εικόνα σταθερού πλάτους
        int width = 500;
        int height = fontSize + 20;

        GreenfootImage img = new GreenfootImage(width, height);
        img.setFont(font);
        img.setColor(bgColor);
        img.fill();

        img.setColor(textColor);

        // Εκτύπωση στο περίπου κέντρο
        img.drawString(text, 30, height - 10);

        setImage(img);
    }

    public String getValue() {
        return value;
    }
}
