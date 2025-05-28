import greenfoot.*;

/**
 * Οθόνη ήττας όταν ο παίκτης χάνει όλες τις ζωές ή 
 * εξαντλείται ο χρόνος.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class GameOverScreen extends World {
    
    private InfoBoard messageBoard;
    
    public GameOverScreen(int score) {
        super(800, 600, 1);
        setBackground("final_bg.png");
        Greenfoot.playSound("gameover.mp3");
        
        GreenfootImage sig = new GreenfootImage(300, 30);
        sig.setColor(new Color(255, 255, 255, 120)); 
        sig.setFont(new greenfoot.Font("Monospaced", true, false, 16));
        sig.drawString("Created by Eleni Sidiraki", 5, 20);
        getBackground().drawImage(sig, 10, getHeight() - 35); 
        

        String message = "GAME OVER\n\n" +
                        "Η αποστολή σου απέτυχε.\n" +
                        "Η μαύρη τρύπα σε κατάπιε ή δεν πρόλαβες.\n\n" +
                        "Μην απογοητεύεσαι,μπορείς να προσπαθήσεις ξανά!\n\n" +
                        "➤ Πάτησε ENTER για να ξεκινήσεις από την αρχή.";

        messageBoard = new InfoBoard(message);
        addObject(messageBoard, getWidth()/2, getHeight()/2);
        
        SoundManager.playLoop("space_theme.mp3", 60);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            SoundManager.stop();
            Greenfoot.setWorld(new StartScreen());
        }
    }
     
}
