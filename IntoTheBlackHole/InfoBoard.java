import greenfoot.*;

/**
 * InfoBoard εμφανίζει οδηγίες, μηνύματα και 
 * σκορ με ημιδιαφανές φόντο.
 * Πατάς SPACE για να συνεχίσεις.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class InfoBoard extends Actor {
    private GreenfootImage background;
    private String message;
    private boolean waiting = true;
    private int HEIGHT=480;
    private int WIDTH=780;

    public InfoBoard(String message) {
        this.message = message;
        createImage();
    }
    public InfoBoard(){
        this.message="";
        createImage();
        
    }
    private void createImage() {
        background = new GreenfootImage(780, 480);
        
        background.setColor(new Color(255,255,255,200));
        background.fillRect(0, 0, 780,480);
        
        background.setColor(new Color(0,0,0,180));
        background.fillRect(5,5,770,470);
        greenfoot.Font font = new greenfoot.Font("Monospaced", true, false, 20);
        background.setFont(font);

        String[] lines = message.split("\\n");
        int y = 50;
        for (String line : lines) {
            background.drawString(line, 30, y);
            y += 30;
        }
        setImage(background);
    }

    private boolean spaceWasReleased = false;

    public void act() {
    // Περιμένει πρώτα να αφήσει το πλήκτρο
        if (!Greenfoot.isKeyDown("space")) {
            spaceWasReleased = true;
        }   

        // Και μόνο αφού το έχει αφήσει, επιτρέπει να συνεχίσει
        if (spaceWasReleased && Greenfoot.isKeyDown("space") && waiting) {
            getWorld().removeObject(this);
            waiting = false;
        }
    }

    public int getWidth(){
        return WIDTH;
    }
    public int getHeight(){
        return HEIGHT;
    }
    
} 
