import greenfoot.*;

/**
 * Περιστρεφόμενο εμπόδιο γύρω από κεντρικό σημείο 
 * που προκαλεί απώλεια ζωής αν συγκρουστεί με το διαστημόπλοιο.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class Asteroid extends Actor {
    private int centerX;      // Κέντρο κύκλου
    private int centerY;
    private int radius;       // Ακτίνα περιστροφής
    private double angle;     // Τρέχουσα γωνία σε μοίρες
    private double speed;     // Ταχύτητα περιστροφής (π.χ. 1.5 μοίρες ανά act)

    private boolean infoShown=false;
    private SimpleTimer infoTimer=new SimpleTimer();
    
    public Asteroid(int centerX, int centerY, int radius, double speed) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.angle = Greenfoot.getRandomNumber(360); // Τυχαίο αρχικό σημείο
        this.speed = speed;
        setImage("asteroid.png"); // Πρόσθεσε ένα image στον φάκελο images
    }

    protected void addedToWorld(World world) {
        updatePosition(); 
    }

    public void act() {
        rotateAroundCenter();
        checkCollision();
        checkProximity();
        clearInfoAfterDelay();
    }
    
    private void rotateAroundCenter() {
        angle += speed;
        if (angle >= 360) angle -= 360;
        updatePosition();
    }
    
    private void updatePosition() {
        double rad = Math.toRadians(angle);
        int x = centerX + (int)(Math.cos(rad) * radius);
        int y = centerY + (int)(Math.sin(rad) * radius);
        setLocation(x, y);
    }
    
    private void checkCollision() {
        Spaceship ship = (Spaceship) getOneIntersectingObject(Spaceship.class);
        if (ship != null) {
            Level2World world = (Level2World) getWorld();
            world.getHUD().loseLife();
            Greenfoot.playSound("gameover.mp3");

            if (world.getHUD().getLives() <= 0) {
                Greenfoot.setWorld(new GameOverScreen(world.getHUD().getScore()));
            } else {
                ship.setLocation(100, 300); // Reset θέση
            }
        }
    }

    private void checkProximity() {
        Spaceship ship = (Spaceship) getOneObjectAtOffset(0, 0, Spaceship.class);
        if (ship != null && !infoShown) {
            getWorld().showText("Προσοχή: Βαρύτητα πλανήτη επηρεάζει την τροχιά!", 400, 550);
            infoTimer.mark();
            infoShown = true;
        }
    }
    
    private void clearInfoAfterDelay() {
        if (infoShown && infoTimer.millisElapsed() >= 3000) { 
            getWorld().showText("", 400, 550); 
            infoShown = false;
        }
    }
}

