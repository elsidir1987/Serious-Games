import greenfoot.*;

/**
 * Το HUD (Head-Up Display) του παιχνιδιού.
 * Εμφανίζει χρήσιμες πληροφορίες για τον παίκτη, όπως το τρέχον επίπεδο,
 * το συνολικό σκορ και τα διαθέσιμα hints.
 * Ανανεώνεται δυναμικά κατά τη διάρκεια του παιχνιδιού.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class HUD extends Actor {
    private GameWorld worldRef;

    public HUD(GameWorld world) {
        this.worldRef = world;
        updateImage();
    }

    public void act() {
        updateImage(); // ανανεώνεται συνεχώς — μπορείς να το βάλεις και με delay
    }

    private void updateImage() {
        int currentScore = worldRef.getScore();
        int secured = worldRef.getSecuredAmount();

        GreenfootImage img = new GreenfootImage(250, 60);
        img.setColor(new Color(19,39,89)); // ημιδιαφανές φόντο
        img.fill();

        img.setColor(Color.WHITE);
        for (int i = 0; i < 2; i++) {
            img.drawRect(i, i, img.getWidth() - 1 - 2 * i, img.getHeight() - 1 - 2 * i);
        }

        img.setColor(Color.WHITE);
        img.setFont(new Font("SansSerif", true, false, 16));
        img.drawString("Ποσό: €" + currentScore, 10, 20);
        img.drawString("Καταχωρημένο: €" + secured, 10, 45);

        setImage(img);
    }
}
