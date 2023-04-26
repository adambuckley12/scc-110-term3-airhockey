package panels;

import frames.MainFrame;
import panels.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainPanel extends BasePanel {

    /**
     * My Code
     */
    public MainPanel(int width, int height) {
        this.initGame();
        this.setSize(width, height);
    }

    public void initGame() {
        this.currentFrame = new MainFrame();
        this.currentFrame.setResizable(false);
        this.currentFrame.setBackground(Color.BLACK);
        this.currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.currentFrame.setContentPane(this);
        this.currentFrame.addKeyListener(this);

        super.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    /**
     * Sets the height of the window, and the currently displayed frame.
     * @param width the new width of this component in pixels
     * @param height the new height of this component in pixels
     */
    public void setSize(int width, int height) {
        super.width = width;
        super.height = height;
        super.setSize(width, height);

        if(super.currentFrame != null) {
            Insets insets = super.currentFrame.getInsets();
            int frameWidth = width + insets.left + insets.right;
            int frameHeight = height + insets.top + insets.bottom;
            super.currentFrame.setSize(frameWidth, frameHeight);
        }
    }


    /**
     * Runnable overrides
     */
    @Override
    public void run() {
        // KILL ME NOW
    }


    /**
     * KeyListener overrides
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    /**
     * MouseListener overrides
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    /**
     * MouseMotionListener overrides
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
