import greenfoot.*;

/**
 * Το διαστημόπλοιο του παίκτη: κινείται, συλλέγει capsules, 
 * αποφεύγει εμπόδια και επηρεάζεται από βαρύτητα.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class Spaceship extends SmoothMover {
    
    private GreenfootImage[] images = new GreenfootImage[3];
    private int imageIndex = 0;
    private int animateDelay = 10;
    private int frameCount = 0;
    
    public Spaceship() {
        images[0] = new GreenfootImage("spaceship1.png");
        images[1] = new GreenfootImage("spaceship2.png");
        images[2] = new GreenfootImage("spaceship3.png");
        setImage(images[0]);
    }

    public void act() {
        checkKeys();
        animate();
        collectData();
        checkGravity();
    }
    
    private void checkKeys() {
        boolean moved=false;
        
        if (Greenfoot.isKeyDown("left")) {
            setRotation(getRotation() - 5);
            moved=true;
        }

        if (Greenfoot.isKeyDown("right")) {
            setRotation(getRotation() + 5);
            moved=true;
        }

        if (Greenfoot.isKeyDown("up")) {
            moveForward();
            GreenfootSound thrustSound = new GreenfootSound("thrust.wav");
            thrustSound.setVolume(50);  // Από 0 (σίγαση) έως 100 (μέγιστο)
            thrustSound.play();

            moved=true;
        }
        
        if (Greenfoot.isKeyDown("down")) {
            moveForward();
            GreenfootSound thrustSound = new GreenfootSound("thrust.wav");
            thrustSound.setVolume(50);  // Από 0 (σίγαση) έως 100 (μέγιστο)
            thrustSound.play();

            moved=true;
        }
        
        if (moved) {
            double angle = Math.toRadians(getRotation());
            int dx = (int)(Math.cos(angle) * 20);
            int dy = (int)(Math.sin(angle) * 20);
            getWorld().addObject(new Explosion(), getX() - dx, getY() - dy);
        }
    }
    private void moveForward() {
        move(4);
    }
    
    private void animate() {
        frameCount++;
        if (frameCount >= animateDelay) {
            frameCount = 0;
            imageIndex = (imageIndex + 1) % images.length;
            setImage(images[imageIndex]);
        }
    }
  
    private void collectData() {
        DataPoint d = (DataPoint) getOneIntersectingObject(DataPoint.class);
        if (d != null) {
            getWorld().removeObject(d);
            HUD hud = (getWorld() instanceof Level1World) ? ((Level1World) getWorld()).getHUD()
                    : ((Level2World) getWorld()).getHUD();
            hud.addScore(10);
        }

        if (getWorld() instanceof Level1World) {
            HUD hud = ((Level1World) getWorld()).getHUD();
            if (hud.getScore() >= 50) {
                Greenfoot.setWorld(new Level2World(hud.getScore(), hud.getLives()));
            }
        }
    }

    private void checkGravity() {
        BlackHole bh = (BlackHole) getWorld().getObjects(BlackHole.class).get(0);
        int dx = bh.getX() - getX();
        int dy = bh.getY() - getY();
        int dist = (int)Math.sqrt(dx * dx + dy * dy);

        if (dist < 80) {
            HUD hud = (getWorld() instanceof Level1World) ? ((Level1World) getWorld()).getHUD()
                      : ((Level2World) getWorld()).getHUD();

            hud.loseLife();

            if (hud.getLives() <= 0) {
                Greenfoot.setWorld(new GameOverScreen(hud.getScore()));
            } else {
                setLocation(100, 300); // Reset
            }
        }
    }
}
