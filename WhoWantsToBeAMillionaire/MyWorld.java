import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Δεν χρησιμοποιείται στο τελικό παιχνίδι.
 * Αρχικά δημιουργήθηκε από το Greenfoot, αλλά η εκκίνηση του παιχνιδιού γίνεται μέσω StartScreen.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        Greenfoot.setWorld(new StartScreen());

    }
}
