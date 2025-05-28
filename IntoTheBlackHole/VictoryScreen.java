import greenfoot.*;

/**
 * Οθόνη νίκης με συνολικό σκορ και μήνυμα ολοκλήρωσης της αποστολής.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class VictoryScreen extends World {
    private InfoBoard messageBoard;

    public VictoryScreen(int score,int lives) {
        super(800, 600, 1);
        setBackground("final_bg.png"); 
        
        GreenfootImage sig = new GreenfootImage(300, 30);
        sig.setColor(new Color(255, 255, 255, 120)); 
        sig.setFont(new greenfoot.Font("Monospaced", true, false, 16));
        sig.drawString("Created by Eleni Sidiraki", 5, 20);
        getBackground().drawImage(sig, 10, getHeight() - 35); 

        
        SoundManager.stop();
        String message = "🎉 Κέρδισες! 🎉\n\n" +
                         "Η αποστολή σου ήταν επιτυχής!\n" +
                        "Μάζεψες συνολικά " + score + " points.\n" +
                        "Ζωές που απέμειναν: " + lives + "\n\n" +
                        "You're A Guardian of the Galaxy!\n\n" +
                        "➤ Πάτησε ENTER για να παίξεις ξανά.";

        messageBoard = new InfoBoard(message);
        addObject(messageBoard, getWidth()/2, getHeight()/2);
        SoundManager.playVictoryMusic();
        
    }

    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            SoundManager.stopAll();
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
