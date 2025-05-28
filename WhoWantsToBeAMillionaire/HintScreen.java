import greenfoot.*;

/**
 * Εμφανίζει την βοήθεια (hint) για την τρέχουσα ερώτηση.
 * Δείχνει είτε το κείμενο του hint, είτε μήνυμα ότι δεν υπάρχουν διαθέσιμες βοήθειες.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class HintScreen extends World {
    private GameWorld previousWorld;

    public HintScreen(String messageText, GameWorld gameWorld) {
        super(800, 600, 1);
        this.previousWorld = gameWorld;

        GreenfootImage bg = new GreenfootImage("game_bg.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        // Σκιά + panel
        GreenfootImage shadow = new GreenfootImage(620, 220);
        shadow.setColor(new Color(0, 0, 0, 60));
        shadow.fill();
        getBackground().drawImage(shadow, 95, 190);

        GreenfootImage panel = new GreenfootImage(600, 200);
        panel.setColor(new Color(255, 255, 255, 220));
        panel.fill();
        getBackground().drawImage(panel, 100, 200);

        // Κείμενο
        String[] lines = {
            "💡 Μήνυμα:",
            messageText,
            "",
            "Πάτησε [space] για συνέχεια"
        };

        int y = 220;
        for (String line : lines) {
            Label lbl = new Label(line, 22, new Color(10,20,70), new Color(0,0,0,0), "SansSerif", true);
            addObject(lbl, getWidth()/2, y);
            y += 40;
        }
    }

    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(previousWorld);
        }
    }
}
