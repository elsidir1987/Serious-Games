import greenfoot.*;

/**
 * A variation of Actor that maintains precise (double) x and y coordinates
 */
public class SmoothMover extends Actor {
    private Vector movement;
    private double exactX;
    private double exactY;

    public SmoothMover() {
        this(new Vector());
    }
    
    public SmoothMover(Vector movement)
    {
        this.movement=movement;
    }
    
    public void setLocation(double x, double y) {
        exactX = x;
        exactY = y;
        super.setLocation((int) x, (int) y);
    }
    
    public void setLocation(int x,int y)
    {
        exactX=x;
        exactY=y;
        super.setLocation(x,y);
    }
    public void move(double distance) {
        double angle = Math.toRadians(getRotation());
        double dx = Math.cos(angle) * distance;
        double dy = Math.sin(angle) * distance;
        setLocation(exactX + dx, exactY + dy);
    }

    public double getExactX() {
        return exactX;
    }

    public double getExactY() {
        return exactY;
    }
    /*
     * Increase the speed with the given vector
     */
    public void addForce(Vector force)
    {
        movement.add(force);
    }
    public void accelerate(double factor)
    {
        movement.scale(factor);
        if(movement.getLength()<0.15){
            movement.setNeutral();
        }
    }
    /**
     * Return the speed of this actor
     */
    public double getSpeed()
    {
        return movement.getLength();
    }
    /**
     * Stop movement of this actor
     */
    public void stop()
    {
        movement.setNeutral();
    }
    /**
     * Return the current speed
     */
    public Vector getMovement()
    {
        return movement;
    }
    public void addedToWorld(World world) {
        exactX = getX();
        exactY = getY();
    }
}
