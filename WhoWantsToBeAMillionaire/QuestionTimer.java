import greenfoot.*;

/**
 * Χρονομετρητής ερωτήσεων του παιχνιδιού.
 * Μετράει αντίστροφα τον χρόνο που έχει ο παίκτης για να απαντήσει σε κάθε ερώτηση.
 * Όταν ο χρόνος τελειώσει, ενημερώνει το GameWorld για να τερματιστεί η ερώτηση.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class QuestionTimer extends Actor {
    private int timeLeft; // σε frames (1 sec = 60 frames)
    private GameWorld worldRef;

    public QuestionTimer(GameWorld world) {
        this.worldRef = world;
        this.timeLeft = 60 * 20; // 20 δευτερόλεπτα
        updateImage();
    }

    public void act() {
        timeLeft--;
        updateImage();

        if (timeLeft <= 0) {
            worldRef.timeIsUp();
        }
    }

    private void updateImage() {
        int seconds = timeLeft / 60;
        GreenfootImage img = new GreenfootImage(160, 30);
        img.setColor(new Color(19,39,89));
        img.fill();

        // Περίγραμμα
        img.setColor(Color.WHITE);
        for (int i = 0; i < 2; i++) {
            img.drawRect(i, i, img.getWidth() - 1 - 2 * i, img.getHeight() - 1 - 2 * i);
        }

        img.setFont(new Font(true, false, 16));
        img.setColor(Color.RED);
        img.drawString("Χρόνος: " + seconds + " sec", 10, 20);

        setImage(img);
    }

    public void reset(int seconds) {
        timeLeft = seconds*60;
        updateImage();
    }
}
