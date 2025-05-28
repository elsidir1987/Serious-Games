/**
 * Αναπαριστά μία ερώτηση του παιχνιδιού.
 * Περιλαμβάνει το κείμενο της ερώτησης, τις επιλογές απαντήσεων, τη σωστή απάντηση και το hint.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class Question {
    private String questionText;
    private String[] answers; // length = 4
    private int correctIndex; // index 0-3
    private String hint;

    public Question(String questionText, String[] answers, int correctIndex, String hint) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctIndex = correctIndex;
        this.hint = hint;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getAnswerAt(int index) {
        return answers[index];
    }

    public boolean isCorrect(int index) {
        return index == correctIndex;
    }

    public String getHint() {
        return hint;
    }
    
    public int getCorrectIndex() {
    return correctIndex;
    }

}
