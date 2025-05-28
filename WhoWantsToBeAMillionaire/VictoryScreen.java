import greenfoot.*;

/**
 * Οθόνη νίκης που εμφανίζεται όταν ο παίκτης ολοκληρώσει και τις 15 ερωτήσεις.
 * Δείχνει το συνολικό σκορ και προσκαλεί τον παίκτη να ξαναπαίξει.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class VictoryScreen extends World {

    GreenfootSound victoryMusic = new GreenfootSound("win.mp3");
        
    public VictoryScreen(int finalScore) {
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
        panel.setColor(new Color(19, 39, 89, 180)); // ημιδιαφανές μπλε
        panel.fill();
        panel.setColor(new Color(255, 255, 255, 220)); // λευκό περίγραμμα
        for (int i = 0; i < 3; i++) {
            panel.drawRect(i, i, 599 - 2 * i, 299 - 2 * i);
        }
        getBackground().drawImage(panel, 100, 150);

        // ✅ Μηνύματα
        String[] lines = {
            "🎉 Συγχαρητήρια!",
            "",
            "💰 Συνολικό Σκορ: €" + finalScore,
            "",
            "Πάτησε [space] για να ξαναπαίξεις"
        };
        victoryMusic.play();
        
        int y = 180;
        for (String line : lines) {
            Label lbl = new Label(line, 22, Color.WHITE, new Color(0, 0, 0, 0), "SansSerif", true);
            addObject(lbl, getWidth() / 2, y);
            y += 40;
        }
    }

    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            victoryMusic.stop();
            Greenfoot.setWorld(new GameWorld(1,0,3,0));
        }
    }
}
