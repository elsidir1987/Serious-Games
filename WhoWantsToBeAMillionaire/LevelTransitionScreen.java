import greenfoot.*;

/**
 * Μεταβατική οθόνη ανάμεσα στα επίπεδα.
 * Ενημερώνει τον παίκτη για το νέο επίπεδο και εμφανίζει τα κατοχυρωμένα κέρδη και τα εναπομείναντα hints.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class LevelTransitionScreen extends World {
    private int level;
    private int securedAmount;
    private int hintsLeft;
    private int totalAnswered;

    private int delayFrames = 300; // 3 δευτερόλεπτα
    
    GreenfootSound victoryMusic = new GreenfootSound("win.mp3");
    public LevelTransitionScreen(int level, int securedAmount, int hintsLeft,int totalAnswered) {
        super(800, 600, 1);
        this.level = level;
        this.securedAmount = securedAmount;
        this.hintsLeft = hintsLeft;
        this.totalAnswered = totalAnswered;

        GreenfootImage bg = new GreenfootImage("startscreen.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        // Σκιά
        GreenfootImage shadow = new GreenfootImage(620, 320);
        shadow.setColor(new Color(0, 0, 0, 60));
        shadow.fill();
        getBackground().drawImage(shadow, 95, 145);

        // Panel
        GreenfootImage panel = new GreenfootImage(600, 300);
        panel.setColor(new Color(255, 255, 255, 170));
        panel.fill();
        getBackground().drawImage(panel, 100, 150);

        // Κείμενο
        String[] lines = {
            "🎉 Συγχαρητήρια!",
            "Έχεις κατοχυρώσει: €" + securedAmount,
            "",
            "Προχωράς στο Επίπεδο " + level + "!",
            "Οι ερωτήσεις θα είναι πιο δύσκολες...",
            "",
            "Υπολειπόμενα hints: " + hintsLeft,
        };
        victoryMusic.play();
        
        int y = 180;
        for (String line : lines) {
            Label lbl = new Label(line, 22, new Color(10,20,70), new Color(0,0,0,0), "SansSerif", true);
            addObject(lbl, getWidth() / 2, y);
            y += 35;
        }
    }

    public void act() {
        delayFrames--;
        if (delayFrames <= 0) {
            victoryMusic.stop();
            Greenfoot.setWorld(new GameWorld(level, securedAmount, hintsLeft,totalAnswered));
        }
    }
}
