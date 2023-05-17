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
     * @param soundName
     */
    public void playSound(String soundName) {
        if (!parentPanel.audioEnabled) return;

        new Thread(() -> {  //TODO MAKE THIS LESS CANCER AND JUST GET BETTER AUDIO FILES SO NO NEED FOR NEW THREAD SCUFFED SHIT
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                //only play half a second of the sound as files rly long for some
                Thread.sleep(1000);
                clip.stop();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }).start();

    }

    public abstract void MouseEvent(MouseEvent e);

}
