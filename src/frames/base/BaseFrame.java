package frames.base;

import panels.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.sound.sampled.*;


public abstract class BaseFrame extends JFrame {
    protected BasePanel parentPanel;


    public abstract void customPaint(Graphics gr, int width, int height);

    public abstract void updatePositions();

    /**
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
        if (!parentPanel.audioEnabled) return;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public abstract void MouseEvent(MouseEvent e);

}
