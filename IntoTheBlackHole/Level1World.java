import greenfoot.*;

/**
 * Level1: Ο παίκτης μαζεύει 5 data capsules 
 * και αποφεύγει τη μαύρη τρύπα μέσα σε 60 δευτερόλεπτα.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
*/

public class Level1World extends World {
    private HUD hud;
    private SimpleTimer timer = new SimpleTimer();
    private Counter scoreCounter;
    private int targetScore = 50;
    
    public Level1World() {
        super(800, 600, 1);
        setBackground("levels.png");
        
        scoreCounter=new Counter("Score: ");
        hud=new HUD(scoreCounter);
        addObject(hud, 80, 30);
        
        addObject(new Fader(),400,300);
        addObject(new Spaceship(), 100, 300);
        addObject(new BlackHole(), 500, 250);
        
        
        for (int i = 0; i < 5; i++)
            addObject(new DataPoint(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(600));
        
        addObject(new InfoBoard(
            "ΓΥΡΟΣ 1\n\n" +
            "Στόχος: Μάζεψε 5 data capsules.\n" +
            "Απόφυγε τη μαύρη τρύπα στο κέντρο.\n" +
            "Έχεις 60 δευτερόλεπτα και 3 ζωές.\n\n" +
            "Μάζεψε κάθε κάψουλα για +10 πόντους.\n" +
            "Καλή επιτυχία!\n\n" +
            "➤ Πάτησε SPACE για να ξεκινήσεις."
        ), getWidth()/2, getHeight()/2);

        SoundManager.playLoop("leveltheme.mp3",60);
    }

    public HUD getHUD() {
        return hud;
    }
    
    public void act() {
        int elapsed = timer.millisElapsed() / 1000;
        showText("Χρόνος: " + (60 - elapsed), 400, 30);

        if (elapsed >= 60) {
            SoundManager.stop();
            Greenfoot.setWorld(new GameOverScreen(hud.getScore()));
        }
        checkWinCondition();
    }

    private void checkWinCondition() {
        if (hud.getScore() >= targetScore) {
            SoundManager.stop();
            Greenfoot.setWorld(new LevelTransitionScreen(scoreCounter, hud.getLives()));
        }
    }
 
}
