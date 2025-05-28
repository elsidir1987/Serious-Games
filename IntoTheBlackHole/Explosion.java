import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * An explosion. It starts by expanding and then collapsing. 
 * The explosion will explode other objects that the explosion intersects.
 * 
 * @author Poul Henriksen
 * @version 1.0.1
 */
public class Explosion extends Actor
{
    /** How many images should be used in the animation of the explostion */
    private final static int IMAGE_COUNT= 12;
    
    /** 
     * The images in the explosion. This is static so the images are not
     * recreated for every object (improves performance significantly).
     */
    private static GreenfootImage[] images;
    
    /** Current size of the explosion */
    private int imageNo = 0;
    
    /** How much do we increment the index in the explosion animation. */
    private int increment=1;
    private int life = 15;
    
    
    /**
     * Create a new explosion.
     */
    public Explosion() 
    {
        initializeImages();
        setImage(images[0]);
        
    }    
    
    /** 
     * Create the images for explosion.
     */
    public synchronized static void initializeImages() 
    {
        if(images == null) {
            GreenfootImage baseImage = new GreenfootImage("explosion.png");
            images = new GreenfootImage[IMAGE_COUNT];
            for (int i = 0; i < IMAGE_COUNT; i++)
            {
                int size = (i+1) * ( baseImage.getWidth() / IMAGE_COUNT );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * Explode!
     */
    public void act()
    { 
        if (life % 3 == 0) {
            increment = (increment + 1) % images.length;
            setImage(images[increment]);
        }

        // κίνηση και διαφάνεια
        setLocation(getX() - Greenfoot.getRandomNumber(3) + 1, getY() + Greenfoot.getRandomNumber(3) - 1);
        getImage().setTransparency(life * 10);

        life--;
        
        if (life <= 0) {
            getWorld().removeObject(this);
        }
    }
}
