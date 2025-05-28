import greenfoot.*;

/**
 * Κεντρική διαχείριση της μουσικής υπόκρουσης για όλες 
 * τις οθόνες/επίπεδα.
 * 
 * Author Sidiraki Eleni,Serious Game in Master in Applied Informatics
 * @version 1.0 2025
*/
public class SoundManager {
    private static GreenfootSound currentMusic;
    private static GreenfootSound victorySound = new GreenfootSound("victory.mp3");
    
    public static void playLoop(String filename, int volume) {
        stop(); // σταματά προηγούμενη μουσική
        currentMusic = new GreenfootSound(filename);
        currentMusic.setVolume(volume);
        currentMusic.playLoop();
    }

    public static void stop(){
        if(currentMusic!=null){
            currentMusic.stop();
        }
    }
    
    public static void playVictoryMusic() {
        stopAll();
        victorySound.setVolume(70);
        victorySound.play();
    }

    public static void stopAll() {
    
        victorySound.stop();
    }
}
