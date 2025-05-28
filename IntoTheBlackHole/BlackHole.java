import greenfoot.*;

/**
 * Αντικείμενο μαύρης τρύπας που περιστρέφεται, 
 * έλκει αντικείμενα και τα απορροφά.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class BlackHole extends Actor {
    public BlackHole() {
        setImage("blackhole.png");
    }
    
    public void act()
    {
        attractObjects();
        spin();
    }

    private void attractObjects() {
        for (Actor actor : getWorld().getObjects(Actor.class)) {
            if (actor != this && !(actor instanceof DataPoint)) {

                int dx = getX() - actor.getX();
                int dy = getY() - actor.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

            
                if (distance <= 100) {
                    actor.setLocation(actor.getX() + dx / 20, actor.getY() + dy / 20);

                    if (distance < 20) {
                        absorb(actor);
                    }
                }
            }
        }
    }
    
    private void absorb(Actor a) {
        for (int i = 0; i < 5; i++) {
            GreenfootImage img = a.getImage();
            int w = img.getWidth();
            int h = img.getHeight();
            if (w > 5 && h > 5) {
                GreenfootImage scaled = new GreenfootImage(img);
                scaled.scale((int)(w * 0.8), (int)(h * 0.8));
                a.setImage(scaled);
                Greenfoot.delay(1);
            }
        }
        getWorld().removeObject(a);
    }


    private void spin() {
        setRotation(getRotation() + 2); // περιστροφή για εφέ
    }
}
