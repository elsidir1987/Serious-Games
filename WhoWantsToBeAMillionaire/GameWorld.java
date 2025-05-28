import greenfoot.*;
import java.util.*;

/**Η κύρια σκηνή του παιχνιδιού.
 * Εδώ φορτώνονται οι ερωτήσεις, διαχειρίζεται η λογική των επιπέδων,
 * οι σωστές/λάθος απαντήσεις, τα hints και η μουσική.
 * @author Sidiraki Eleni Msc in Applied Informatics 2025
 */

public class GameWorld extends World {
    private QuestionBank questionBank;
    private Question currentQuestion;
    private int level;
    private int questionsAnswered;
    private int score;
    private int hintsLeft = 3;
    private QuestionTimer timer;
    private int totalQuestionsAnswered = 0;
    
    private Label questionLabel;
    private List<AnswerButton> answerButtons = new ArrayList<>();
    private HintButton hintButton;
    private HUD hud;

    private final int baseTime = 20;
    private int[][] levelRewards = {
        {100, 200, 300, 400, 500},
        {1000,1500,2000,4000,5000},
        {10000,20000,40000,80000,150000}
    };
    private int securedAmount = 0;
    private GreenfootSound suspenseMusic = new GreenfootSound("thinking.mp3");
    private GreenfootSound waitingMusic = new GreenfootSound("waiting.mp3");


    public GameWorld(int level, int secured, int hintsLeft, int totalAnswered){
        super(800, 600, 1);
        this.level = level;
        this.securedAmount = secured;
        this.hintsLeft = hintsLeft;
        this.score = securedAmount;
        this.totalQuestionsAnswered = totalAnswered;
        setPaintOrder(Label.class, HintButton.class, AnswerButton.class);

        GreenfootImage bg = new GreenfootImage("game_bg.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        questionBank = new QuestionBank();
        prepareUI();
        loadNextQuestion();
    }

    public GameWorld() {
        this(1, 0, 3,0);
    }

    private void prepareUI() {
        timer = new QuestionTimer(this);
        addObject(timer, 100, 40);

        questionLabel = new Label("", 22, new Color(255,255,255), new Color(0,0,0,0), "Monospaced", true);
        addObject(questionLabel, getWidth()/2, 100);

        for (int i = 0; i < 4; i++) {
            answerButtons.add(new AnswerButton(i, this));
        }

        hintButton = new HintButton(this);
        addObject(hintButton, getWidth() - 100, 40);

        hud = new HUD(this);
        addObject(hud, 680, 540);
    }

    public void loadNextQuestion() {
        
        timer.reset(getTimeForLevel());
        
        if (questionsAnswered >= 5) {
            nextLevelOrWin();
            return;
        }
        
        
        currentQuestion = getRandomQuestionForLevel();
        if (currentQuestion == null) {
            showGameOver("Δεν υπάρχουν άλλες ερωτήσεις.");
            return;
        }
        
        clearPreviousQuestion();
        renderQuestionPanel();
        renderAnswerButtons();
        
    }

    private Question getRandomQuestionForLevel() {
        switch (level) {
            case 1:
                return questionBank.getRandomEasy();
            case 2:
                return questionBank.getRandomMedium();
            case 3:
                return questionBank.getRandomHard();
            default:
                return null;
        }
    }

    private int getTimeForLevel() {
        switch (level) {
            case 2:
                return baseTime * 2;
            case 3:
                return baseTime * 4;
            default:
                return baseTime;
        }
    }


    private void clearPreviousQuestion() {
        for (int i = 0; i < 4; i++) {
            if (answerButtons.get(i).getWorld() != null) {
                removeObject(answerButtons.get(i));
            }
        }

        List<Label> toRemove = getObjects(Label.class);
        for (Label lbl : toRemove) {
            removeObject(lbl);
        }
        GreenfootImage bg = new GreenfootImage("game_bg.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

    }

    private void renderQuestionPanel() {
        String fullText = ((totalQuestionsAnswered + 1) + ". " + currentQuestion.getQuestionText()).trim();
        List<String> lines = wrapText(fullText, 60);


        int panelWidth = 700;
        int lineHeight = 32;
        int minLines = 1;
        int numLines = lines.size();
        int panelHeight = Math.max(numLines, minLines) * lineHeight + 20;


        GreenfootImage panel = new GreenfootImage(panelWidth, panelHeight);
        panel.setColor(new Color(19, 39, 89));
        panel.fill();
        panel.setColor(Color.WHITE);
        for(int i = 0; i < 3; i++) {
            panel.drawRect(i, i, panelWidth - 1 - 2*i, panelHeight - 1 - 2*i);
        }

        panel.setFont(new Font("SansSerif", true, false, 22));
        int textX = 30;
        int textY = 35;
        for (String line : lines) {
            panel.drawString(line, textX, textY);
            textY += lineHeight;
        }

        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = 60;
        getBackground().drawImage(panel, panelX, panelY);
    }

    private void renderAnswerButtons() {
        String[] options = currentQuestion.getAnswers();
        String[] labels = {"🔸 Α: ", "🔸 Β: ", "🔸 Γ: ", "🔸 Δ: "};

        for (int i = 0; i < 4; i++) {
            answerButtons.get(i).setAnswerText(labels[i] + options[i]);
        }

        int startX = 200;
        int startY = 220;
        for (int i = 0; i < 4; i++) {
            int x = startX + (i % 2) * 300;
            int y = startY + (i / 2) * 100;
            addObject(answerButtons.get(i), x, y);
        }
    }

    public void checkAnswer(int index) {
        
        answerButtons.get(index).showSelected();
        waitingMusic.playLoop();
        Greenfoot.delay(getFeedbackDelay());
        waitingMusic.stop();
        suspenseMusic.play(); 
        
        if (currentQuestion.isCorrect(index)) {
            if (questionsAnswered < 5) {
                score = levelRewards[level - 1][questionsAnswered];
            }
            
            answerButtons.get(index).showFeedback(true);
            Greenfoot.delay(30);
            
            questionsAnswered++;
            totalQuestionsAnswered++;
            
            if (questionsAnswered == 5) {
                securedAmount = score;
                nextLevelOrWin();
            } else {
                Greenfoot.setWorld(new SuccessScreen(this, score,totalQuestionsAnswered));
            }
        } else {
            answerButtons.get(index).showFeedback(false);
            int correctIndex = currentQuestion.getCorrectIndex();
            answerButtons.get(correctIndex).showFeedback(true);
            Greenfoot.delay(40);
            showGameOver("Απάντησες λάθος στην ερώτηση.");
        }
    }

    private int getFeedbackDelay() {
        switch (level) {
            case 1:
                return 3 * 60;
            case 2:
                return 5 * 60;
            case 3:
                return 7 * 60;
            default:
                return 180;
        }
    }


    private void nextLevelOrWin() {
        if (level == 3) {
            Greenfoot.setWorld(new VictoryScreen(score));
        } else {
            level++;
            Greenfoot.setWorld(new LevelTransitionScreen(level, securedAmount, hintsLeft,totalQuestionsAnswered));
        }
    }

    public void timeIsUp() {
        showGameOver("Ο χρόνος έληξε!");
    }

    private void showGameOver(String message) {
        Greenfoot.setWorld(new GameOverScreen(securedAmount, message));
    }

    public void useHint() {
        if (hintsLeft > 0) {
            hintsLeft--;
            hintButton.updateImage();
            Greenfoot.setWorld(new HintScreen(currentQuestion.getHint(), this));
        } else {
            Greenfoot.setWorld(new HintScreen("Δεν έχεις άλλες βοήθειες!", this));
        }
    }

    private List<String> wrapText(String text, int maxCharsPerLine) {
        List<String> result = new ArrayList<>();

        if (text == null || text.trim().isEmpty()) return result;

        String[] manualLines = text.split("\\n");

        for (String manualLine : manualLines) {
            String[] words = manualLine.trim().split("\\s+");
            StringBuilder line = new StringBuilder();

            for (String word : words) {
                if (line.length() + word.length() + 1 > maxCharsPerLine) {
                    if (!line.toString().trim().isEmpty()) {
                        result.add(line.toString().trim());
                    }
                    line = new StringBuilder();
                }
                line.append(word).append(" ");
            }

            if (!line.toString().trim().isEmpty()) {
                result.add(line.toString().trim());
            }
        }
        // Αφαίρεσε την τελευταία γραμμή αν είναι κενή
            if (!result.isEmpty() && result.get(result.size() - 1).trim().isEmpty()) {
                result.remove(result.size() - 1);
            }

        return result;
    }

    public void showMessage(String text) {
        Label msg = new Label(text, 20);
        addObject(msg, getWidth()/2, getHeight()-50);
        Greenfoot.delay(100);
        removeObject(msg);
    }

    public int getQuestionsAnswered() {
    return questionsAnswered;
    }

    public int getTotalQuestionsAnswered() {return totalQuestionsAnswered;}
    public int getScore() { return score; }
    public int getSecuredAmount() { return securedAmount; }
    public int getLevel() { return level; }
    public int getHintsLeft() { return hintsLeft; }
}
