import greenfoot.*;

/**
 * Ενδιάμεση οθόνη που εμφανίζεται μετά την 
 * ολοκλήρωση του επιπέδου 1.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class LevelTransitionScreen extends World {
    private int score;
    private int lives;
    private boolean spaceWasReleased = false;
    private SimpleTimer delayTimer = new SimpleTimer();
    

    public LevelTransitionScreen(Counter scoreCounter, int lives) {
        super(800, 600, 1);
        setBackground("final_bg.png");

        String message = "Γύρος 1 ολοκληρώθηκε!\n" +
                         "Μάζεψες 5 data capsules.\n" +
                         "Έχεις " + lives + " ζωές.\n" +
                         "Πάτησε SPACE για το επόμενο επίπεδο.";

        addObject(new InfoBoard(message), getWidth() / 2, getHeight() / 2);
        this.score = score;
        this.lives = lives;
        delayTimer.mark();
    }

    public void act() {
        if (delayTimer.millisElapsed() < 1000) return;

        // 2) Μόνο όταν έχει αφήσει το SPACE
        if (!Greenfoot.isKeyDown("space")) {
            spaceWasReleased = true;
        }

        // 3) Και μετά επιτρέπεται να συνεχίσει
        if (spaceWasReleased && Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Level2World(score, lives));
        }
    }
}
