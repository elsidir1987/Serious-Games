# 💰 Who Wants to Be a Millionaire - Serious Game

An educational knowledge game inspired by the TV show **"Who Wants to Be a Millionaire?"**, developed as a Serious Game for the MSc in Applied Informatics at the University of Macedonia.

## 🎯 Game Objective

The player must answer **15 knowledge questions** across **3 difficulty levels** (easy, medium, hard) to reach the final prize of **€150,000**.

The game is designed to:
- Strengthen general knowledge
- Encourage decision-making under pressure (with time limits)
- Develop strategic skills (via hint system)

## 👨‍🏫 Overview

- 📘 Levels: 3 (each with 5 questions)
- ⏳ Timed questions (more time per level)
- 🧠 3 available hints
- 🎵 Sound effects for thinking, correct and incorrect answers
- 📊 HUD displaying score, level, and remaining hints

---

## ▶️ How to Play

1. Start the game by pressing `space` on the start screen.
2. Read the question and select the answer: A, B, C, or D.
3. You may press the "Hint" button up to 3 times in total.
4. Try to answer all questions correctly without a mistake.
5. One wrong answer results in **Game Over**.

---

## 🖥️ Installation & Run Instructions

### Prerequisites
- Greenfoot (version 3.9 or later)
- Java 8 or 11 (not compatible with Java 17+)
- OS: Windows, macOS, or Linux

### Steps

1. **Clone the repository:**

```bash
git clone https://github.com/elsidir1987/WhoWantstoBeAMillionaire.git
cd WhoWantstoBeAMillionaire
```

2. **Open Greenfoot**
3. **Select**: `Project > Open Project` and open the game folder.
4. Press `Run` and... Good luck!

---

## 📁 Code Structure

| Class             | Role                                           |
|-------------------|------------------------------------------------|
| `GameWorld`       | Main game logic                                |
| `StartScreen`     | Starting screen and instructions               |
| `SuccessScreen`   | Transition screen after a correct answer       |
| `VictoryScreen`   | Victory screen                                 |
| `GameOverScreen`  | Failure screen                                 |
| `AnswerButton`    | Answer button handling                         |
| `HintButton`      | Hint button logic                              |
| `HUD`             | Displays score, level, hints                   |
| `Question`        | Question model                                 |
| `QuestionBank`    | Stores and manages all questions               |
| `QuestionTimer`   | Timer for each question                        |

---

## 🔧 Contributing

To suggest questions, improvements, or fixes:
1. Fork the repository
2. Make your changes
3. Open a Pull Request

---

## 📝 Credits

**Developer**: Eleni Sidiraki  
**Course**: Serious Games Programming  
**Program**: MSc in Applied Informatics – University of Macedonia  
**Year**: 2025  
