import greenfoot.*;

/**
 * Εμφανίζει το σκορ και τις ζωές του παίκτη στην οθόνη.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class HUD extends Actor {
    private Counter scoreCounter = new Counter("Score: ");
    private Counter lifeCounter = new Counter("Lives: ");
    private int lives;

    public HUD(Counter scoreCounter) {
        this.scoreCounter=scoreCounter;
        this.lives=3;
        lifeCounter.setValue(lives);
    }
    
    public HUD(Counter scoreCounter, int lives) {
        this.scoreCounter = scoreCounter;
        this.lives = lives;
        lifeCounter.setValue(lives);
    }

    protected void addedToWorld(World world) {
        getWorld().addObject(scoreCounter, 80, 30);
        getWorld().addObject(lifeCounter, 700, 30);
    }

    public void addScore(int value) {
        scoreCounter.add(value);
    }

    public void loseLife() {
        lives--;
        lifeCounter.setValue(lives);
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return scoreCounter.getValue();
     }
}
