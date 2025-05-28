import greenfoot.*;

/**
 * Γραφικό εφέ fade-in/fade-out κατά την έναρξη επιπέδων.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */


public class Fader extends Actor {
    private GreenfootImage fadeImg;
    private static final int WIDTH=800;
    private static final int HEIGHT=600;
    private int alpha = 255;
    private int fadeSpeed = 5;
    private boolean fadingOut = true;

    public Fader() {
        fadeImg = new GreenfootImage(WIDTH,HEIGHT);
        
        fadeImg.setColor(new Color(255,255,255,128));
        fadeImg.fillRect(0,0,800,600);
        fadeImg.setColor(new Color(0,0,0,128));
        fadeImg.fillRect(0,0,WIDTH-10,HEIGHT-10);
        
        setImageAlpha(alpha);
    }
    
    public void act() {
        if (fadingOut) {
            alpha -= fadeSpeed;
            if (alpha <= 0) {
                alpha = 0;
                getWorld().removeObject(this);
            }
        } else {
            alpha += fadeSpeed;
            if (alpha >= 255) alpha = 255;
        }
        setImageAlpha(alpha);
    }

    private void setImageAlpha(int alpha) {
        fadeImg.setTransparency(alpha);
        setImage(fadeImg);
    }

    public void fadeIn() {
        fadingOut = false;
    }
    
}

