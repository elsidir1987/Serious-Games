import greenfoot.*;

/**
 * Level2: Αυξημένη δυσκολία με περιστρεφόμενους αστεροειδείς 
 * και στόχο 7 data capsules.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class Level2World extends World {
    private HUD hud;
    private Counter scoreCounter;
    private SimpleTimer timer = new SimpleTimer();
    
    public Level2World(int score, int lives) {
        super(800, 600, 1);
        setBackground("levels.png");
        
        scoreCounter=new Counter("Score: ");
        scoreCounter.setValue(score);
        hud = new HUD(scoreCounter, lives);
        
        addObject(hud, 80, 30);
        addObject(new Spaceship(), 100, 300);
        addObject(new BlackHole(), 500, 250);
        
        addObject(new InfoBoard(
            "ΓΥΡΟΣ 2\n\n" +
            "Οι συνθήκες γίνονται πιο δύσκολες...\n" +
            "Αστεροειδής περιστρέφονται γύρω από τη μαύρη τρύπα.\n\n" +
            "Στόχος: Μάζεψε 7 νέες data capsules.\n" +
            "Απόφυγε τα εμπόδια και τη βαρυτική έλξη.\n" +
            "Έχεις 90 δευτερόλεπτα.\n\n" +
            "Τρέχον σκορ: " + score + "\n" +
            "Ζωές: " + lives + "\n\n" +
            "➤ Πάτησε SPACE για να συνεχίσεις."
        ), getWidth()/2, getHeight()/2);


        addObject(new Fader(),400,300);
        // Περιστρεφόμενοι πλανήτες (εμπόδια)
        for (int i = 0; i < 3; i++){
            addObject(new Asteroid(500, 250, 80, 1.5), 0, 0);  // Κάνει κύκλο γύρω από το (500, 250)
            addObject(new Asteroid(500, 250, 100, -2), 0, 0);   // Αντίθετη φορά
        }
        for (int i = 0; i < 7; i++)
            addObject(new DataPoint(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(600));
            
        SoundManager.playLoop("leveltheme.mp3", 60);
    }

    public void act() {
        int elapsed = timer.millisElapsed() / 1000;
        showText("Χρόνος: " + (90 - elapsed), 400, 30);

        if (elapsed >= 90) {
            transitionTo(new GameOverScreen(hud.getScore()));
        } else if (getObjects(DataPoint.class).isEmpty()) {
            transitionTo(new VictoryScreen(hud.getScore(), hud.getLives()));
        }
    }
    
    private void transitionTo(World nextWorld) {
        SoundManager.stop();
        Greenfoot.setWorld(nextWorld);
    }
    
    public HUD getHUD() {
        return hud;
    }
     
}
