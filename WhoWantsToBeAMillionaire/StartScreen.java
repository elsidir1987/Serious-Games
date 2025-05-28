import greenfoot.*;

/**
 * Η αρχική οθόνη του παιχνιδιού.
 * Εμφανίζει οδηγίες και μουσική έναρξης.
 * Ο παίκτης ξεκινά το παιχνίδι πατώντας [space].
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class StartScreen extends World {

    private GreenfootSound theme;
    private boolean musicStarted = false;
       
    public StartScreen(){
        super(800, 600, 1);

        // 📸 Background
        GreenfootImage bg = new GreenfootImage("startscreen.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        // 🔳 Σκιά
        GreenfootImage shadow = new GreenfootImage(640, 420);
        shadow.setColor(new Color(0, 0, 0, 60));
        shadow.fill();
        getBackground().drawImage(shadow, 90, 140);

        // 🪟 Panel
        GreenfootImage panel = new GreenfootImage(620, 400);
        panel.setColor(new Color(19, 39, 89, 200));
        panel.fill();

        panel.setColor(new Color(255, 255, 255, 220));
        for (int i = 0; i < 3; i++) {
            panel.drawRect(i, i, 620 - 2 * i, 400-1 - 2 * i);
        }
        getBackground().drawImage(panel, 95, 145);

        // 📘 Κείμενο οδηγιών
        String[] lines = {
            "📺 Καλώς ήρθες στο παιχνίδι:",
            "Ποιος θέλει να γίνει Εκατομμυριούχος;",
            "",
            "🔹 Απάντησε 5 ερωτήσεις σε κάθε επίπεδο.",
            "🔹 Κέρδισε μέχρι €150.000!",
            "🔹 Είσαι εσύ ο επόμενος τυχερός;",
            "🔹 Χρησιμοποίησε μέχρι 3 βοήθειες (hints).",
            "🔹 1 λάθος απάντηση = Τέλος παιχνιδιού!",
            "",
            "▶ Πάτησε [space] για να ξεκινήσεις!"
        };
        
        theme= new GreenfootSound("theme.mp3");
        
        int y = 180;
        for (String line : lines) {
            Label lbl = new Label(line, 20, Color.WHITE, new Color(0,0,0,0), "SansSerif", true);
            addObject(lbl, getWidth() / 2, y);
            y += 35;
        }
        
        GreenfootImage sig = new GreenfootImage("Created by Eleni Sidiraki", 16, Color.WHITE, new Color(0,0,0,0));
        getBackground().drawImage(sig, 600, 570);

    }
    
    public void act() {
        if (!musicStarted) {
            theme.playLoop();
            musicStarted = true;
        }

        if (Greenfoot.isKeyDown("space")) {
            theme.stop();
            Greenfoot.setWorld(new GameWorld(1, 0, 3, 0));
        }
   }
}
