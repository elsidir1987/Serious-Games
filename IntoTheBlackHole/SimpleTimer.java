/**
 * A simple timer class that allows you to keep track of how much time
 * has passed between events.
 * 
 * @Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
 */
public class SimpleTimer
{
    private long lastMark = System.currentTimeMillis();
    
    /**
     * Marks the current time.  You can then in future call
     * millisElapsed() to find out the elapsed milliseconds
     * since this mark() call was made.
     * 
     * A second mark() call will reset the mark, and millisElapsed()
     * will start increasing from zero again.
     */
    public void mark()
    {
        lastMark = System.currentTimeMillis();
    }
    
    /**
     * Returns the amount of milliseconds that have elapsed since mark()
     * was last called.  This timer runs irrespective of Greenfoot's
     * act() cycle, so if you call it many times during the same Greenfoot frame,
     * you may well get different answers.
     */
    public int millisElapsed()
    {
        return (int) (System.currentTimeMillis() - lastMark);
    }
}