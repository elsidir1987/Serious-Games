import greenfoot.*;

/**
 * Κουμπί απάντησης.
 * Περιέχει το κείμενο της απάντησης και ενημερώνει το GameWorld όταν πατηθεί.
 * Μπορεί να δείξει αν η απάντηση είναι σωστή ή λάθος με εφέ.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class AnswerButton extends Actor {
    private int index;
    private GameWorld worldRef;
    private String answerText;
    private GreenfootImage baseImage;

    public AnswerButton(int index, GameWorld worldRef) {
        this.index = index;
        this.worldRef = worldRef;
        setAnswerText("...");
    }

   public void setAnswerText(String text) {
        this.answerText = text;

        int width = 300;
        int height = 60;

        GreenfootImage baseImage = new GreenfootImage(width, height);
        baseImage.setColor(new Color(19, 39, 89)); // βαθύ μπλε
        baseImage.fill();

        // Λευκό περίγραμμα
        baseImage.setColor(Color.WHITE);
        for (int i = 0; i < 2; i++) {
            baseImage.drawRect(i, i, width - 1 - 2 * i, height - 1 - 2 * i);
        }

        // Ρύθμιση γραμματοσειράς
        Font font = new Font("SansSerif", true, false, 18);
        baseImage.setFont(font);
        baseImage.setColor(Color.WHITE);

        // ➤ Αν το κείμενο είναι μικρό, γράφεται σε μία γραμμή
        if (text.length() <= 20) {
            baseImage.drawString(text, 20, 35);
        } else {
            String[] lines = splitTextToTwoLines(text, 22); // σπάσιμο σε 2 σειρές
            baseImage.drawString(lines[0], 20, 25);
            baseImage.drawString(lines[1], 20, 48);
        }

        setImage(baseImage);
    }
    private String[] splitTextToTwoLines(String text, int maxLineLength) {
        int splitIndex = text.lastIndexOf(" ", maxLineLength);
        if (splitIndex == -1) splitIndex = maxLineLength;

        String firstLine = text.substring(0, splitIndex).trim();
        String secondLine = text.substring(splitIndex).trim();
        return new String[] { firstLine, secondLine };
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            worldRef.checkAnswer(index);
        }
    }
    
    public void showFeedback(boolean correct) {
        GreenfootImage feedbackImage = new GreenfootImage(300, 40);
    
        if (correct) {
            feedbackImage.setColor(new Color(0, 200, 0)); // Πράσινο
        } else {
            feedbackImage.setColor(new Color(200, 0, 0)); // Κόκκινο
        }

        feedbackImage.fill();
        feedbackImage.setColor(Color.BLACK);
        feedbackImage.drawRect(0, 0, 299, 39);
        feedbackImage.setFont(new Font(true, false, 18));
        feedbackImage.drawString(answerText, 10, 25);

        setImage(feedbackImage);
    }

    public void showSelected() {
        GreenfootImage selectedImage = new GreenfootImage(300, 40);
        selectedImage.setColor(Color.YELLOW);
        selectedImage.fill();
        selectedImage.setColor(Color.BLACK);
        selectedImage.drawRect(0, 0, 299, 39);
        selectedImage.setFont(new Font(true, false, 18));
        selectedImage.drawString(answerText, 10, 25);
        setImage(selectedImage);
    }
}
