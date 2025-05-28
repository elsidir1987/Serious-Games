import greenfoot.*;

/**
 * Κουμπί βοήθειας (hint).
 * Όταν ο παίκτης το πατήσει, εμφανίζεται ένα hint για την ερώτηση.
 * Μπορεί να χρησιμοποιηθεί έως 3 φορές συνολικά κατά τη διάρκεια του παιχνιδιού.
 * Το κείμενο του κουμπιού ενημερώνεται για να δείχνει πόσες βοήθειες απομένουν.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class HintButton extends Actor {
    private GameWorld worldRef;
    
    public HintButton(GameWorld world) {
        this.worldRef = world;
        updateImage();
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            worldRef.useHint();  // Θα μειώσει το hintsLeft + εμφανίσει μήνυμα
            updateImage();       // Ενημέρωση εμφάνισης κουμπιού
        }
    }

    public void updateImage() {
        GreenfootImage img = new GreenfootImage(120, 30);
        img.setColor(new Color(255,250,205));
        img.fill();

        img.setColor(Color.BLACK);
        for (int i = 0; i < 2; i++) {
            img.drawRect(i, i, img.getWidth() - 1 - 2 * i, img.getHeight() - 1 - 2 * i);
        }

        img.setFont(new Font(true, false, 16));
        img.drawString("Hint (" + worldRef.getHintsLeft() + ")", 10, 20);

        setImage(img);
    }
}
