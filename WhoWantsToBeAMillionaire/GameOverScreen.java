import greenfoot.*;

/**
 * Εμφανίζεται όταν ο παίκτης δώσει λάθος απάντηση ή τελειώσει ο χρόνος.
 * Δείχνει το κατοχυρωμένο ποσό και το μήνυμα λήξης του παιχνιδιού.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class GameOverScreen extends World {

    private GreenfootSound victoryMusic;
    
    public GameOverScreen(int securedAmount, String reason) {
        super(800, 600, 1);

        GreenfootImage bg = new GreenfootImage("game_bg.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        // 🔳 Σκιά
        GreenfootImage shadow = new GreenfootImage(620, 320);
        shadow.setColor(new Color(0, 0, 0, 60));
        shadow.fill();
        getBackground().drawImage(shadow, 95, 145);

        // 🪟 Panel
        GreenfootImage panel = new GreenfootImage(600, 300);
        panel.setColor(new Color(19, 39, 89, 180));
        panel.fill();
        panel.setColor(new Color(255, 255, 255, 220));
        for (int i = 0; i < 3; i++) {
            panel.drawRect(i, i, 599 - 2 * i, 299 - 2 * i);
        }
        getBackground().drawImage(panel, 100, 150);

        // ✅ Μηνύματα
        String[] lines = {
            "❌ Τέλος παιχνιδιού!",
            "",
            "🧠 " + reason,
            "",
            "💰 Κατοχυρωμένο Ποσό: €" + securedAmount,
            "",
            "Πάτησε [space] για επιστροφή"
        };

        victoryMusic= new GreenfootSound("win.mp3");
        victoryMusic.play();

        int y = 180;
        for (String line : lines) {
            Label lbl = new Label(line, 22, Color.WHITE, new Color(0, 0, 0, 0), "SansSerif", true);
            addObject(lbl, getWidth() / 2, y);
            y += 35;
        }
    }

    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            victoryMusic.stop();
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
