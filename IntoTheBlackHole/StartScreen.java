
import greenfoot.*;

/**
 * Αρχική οθόνη με οδηγίες παιχνιδιού και
 * έναρξη με το πλήκτρο SPACE.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class StartScreen extends World {
    
    private boolean instructionsVisible = true;

    public StartScreen() {
        super(800, 600, 1);
        GreenfootImage bg = new GreenfootImage("start_screen.png");
        bg.scale(800, 600); 
        setBackground(bg);
        
        GreenfootImage sig = new GreenfootImage(300, 30);
        sig.setColor(new Color(255, 255, 255, 120)); 
        sig.setFont(new greenfoot.Font("Monospaced", true, false, 16));
        sig.drawString("Created by Eleni Sidiraki", 5, 20);
        getBackground().drawImage(sig, 10, getHeight() - 35); 

        
    }
    
    public void started() {
        SoundManager.playLoop("space_theme.mp3", 60);
    }

    public void stopped() {
        SoundManager.stop();
    }
    
    public void act() {
        if (instructionsVisible && Greenfoot.isKeyDown("space")) {
            removeObjects(getObjects(InfoBoard.class));
            SoundManager.stop();
            Greenfoot.setWorld(new Level1World());
            instructionsVisible = false;
        }

        if (instructionsVisible && getObjects(InfoBoard.class).isEmpty()) {
            addObject(new InfoBoard(
                "INTO THE BLACK HOLE\n\n" +
                "Ως νέος Guardian of the Galaxy, η αποστολή σου είναι κρίσιμη,\n" +
                "σύλλεξε data capsules από το βαθύ διάστημα.\n\n" +
                "• Χρησιμοποίησε τα ΒΕΛΗ για να κινηθείς και να στρίψεις.\n" +
                "• Απόφυγε τις μαύρες τρύπες και τους πλανήτες.\n" +
                "• Μάζεψε τις data capsules για να κερδίσεις σκορ.\n\n" +
                "Καλή επιτυχία, Guardian!\n\n" +
                "➤ Πάτησε SPACE για να ξεκινήσεις."
            ), getWidth()/2, getHeight()/2);

        }

    }

}
