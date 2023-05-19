package frames.base;

import panels.base.BasePanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

public abstract class BaseFrame extends JFrame {
    protected BasePanel parentPanel;

    // Abstract method for custom painting
    public abstract void customPaint(Graphics gr, int width, int height);

    // Abstract method for updating component positions
    public abstract void updatePositions();

    /**
     * Plays a sound based on the provided sound name.
     * Sound options:
     * src/assets/audio/applause.wav
     * src/assets/audio/bounce.wav
     * src/assets/audio/drumroll.wav
     * src/assets/audio/fanfare.wav
     * src/assets/audio/hit.wav
     *
     * @param soundName The path of the sound file to play
     */
    public void playSound(String soundName) {
        // Check if audio is enabled in the parent panel
        if (!parentPanel.audioEnabled) return;
        try {
            // Load the sound file and play it using the Clip class
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            // Print an error message if there is an issue playing the sound
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // Abstract method for handling mouse events
    public abstract void MouseEvent(MouseEvent e);

    // Abstract method for handling key events
    public abstract void KeyPressed(KeyEvent e);

    public abstract void KeyReleased(KeyEvent e);
}
