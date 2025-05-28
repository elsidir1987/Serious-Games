import greenfoot.*;

/**
 * Εμφανίζεται όταν ο παίκτης απαντήσει σωστά σε μία ερώτηση.
 * Δείχνει το ποσό που κέρδισε και μετά από μερικά δευτερόλεπτα
 * συνεχίζει με την επόμενη ερώτηση.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */
public class SuccessScreen extends World {
    private GameWorld previousWorld;
    private int score;
    private int timer = 180;// 3 δευτερόλεπτα (60 fps * 3)
    private int totalAnswered;

    public SuccessScreen(GameWorld gameWorld, int score, int totalSoFar) {
        super(800, 600, 1);
        this.previousWorld = gameWorld;
        this.score = score;
        this.totalAnswered = totalSoFar;

        // Background ίδιο με το GameWorld
        GreenfootImage bg = new GreenfootImage("game_bg.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        // Δημιουργία panel όπως τα κουμπιά
        int panelWidth = 500;
        int panelHeight = 120;

        GreenfootImage panel = new GreenfootImage(panelWidth, panelHeight);
        panel.setColor(new Color(19, 39, 89)); // ίδιο μπλε
        panel.fill();
        
        // Λευκό περίγραμμα 3px
        panel.setColor(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            panel.drawRect(i, i, panelWidth - 1 - 2 * i, panelHeight - 1 - 2 * i);
        }

        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = (getHeight() - panelHeight) / 2;
        getBackground().drawImage(panel, panelX, panelY);

        // Κείμενο
        String msg = "Κέρδισες €" + score + "!";

        String msg2;
        if (previousWorld.getLevel() == 3 && totalAnswered == 14) {
            msg2 = "💰 Τελευταία ερώτηση για τις €150.000!";
        } else {
            msg2 = "Προχωράμε στην επόμενη ερώτηση...";
        }

        Label line1 = new Label(msg, 28, Color.WHITE, new Color(0,0,0,0), "Serif", true);
        Label line2 = new Label(msg2, 20, Color.WHITE, new Color(0,0,0,0), "Serif", false);

        addObject(line1, getWidth()/2, getHeight()/2 - 20);
        addObject(line2, getWidth()/2, getHeight()/2 + 20);
    }

    public void act() {
        timer--;
        if (timer <= 0) {
            Greenfoot.setWorld(previousWorld);
            previousWorld.loadNextQuestion();
        }
    }

}
