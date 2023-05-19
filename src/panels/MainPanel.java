package panels;

import frames.GameFrame;
import frames.MainFrame;
import panels.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class MainPanel extends BasePanel {


    public MainFrame MainMenu = new MainFrame(this);

    public MainPanel(int width, int height) {
        this.initGame();
        this.setInitialSize(width, height);
    }

    public void initGame() {
        super.currentFrame = MainMenu;
        super.currentFrame.setResizable(false);
        super.currentFrame.setBackground(Color.BLACK);
        super.currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.currentFrame.setContentPane(this);
        super.currentFrame.setVisible(true);

        new Thread(this).start();

        super.addMouseListener(this);
        super.addMouseMotionListener(this);

        super.currentFrame.addKeyListener(this);

    }

    /**
     * Sets the height of the window, and the currently displayed frame.
     *
     * @param width  the new width of this component in pixels
     * @param height the new height of this component in pixels
     */
    public void setInitialSize(int width, int height) {
        super.width = width;
        super.height = height;

        super.setSize(width, height);

        if (super.currentFrame != null) {
            Insets insets = super.currentFrame.getInsets();
            int frameWidth = width + insets.left + insets.right;
            int frameHeight = height + insets.top + insets.bottom;
            super.currentFrame.setSize(frameWidth, frameHeight);
        }
    }


    /**
     * Stolen code from GameArena provided file
     */

    public void paint(Graphics gr) {
        super.currentFrame.customPaint(gr, width, height);
    }

    /**
     * Runnable overrides
     */
    @Override
    public void run() {
        try {
            while (!exiting) {

                super.currentFrame.updatePositions();

                this.repaint();
                Thread.sleep(10);
            }
        } catch (InterruptedException ignored) {
            // This exception is thrown when the thread is interrupted.
            // don't need to do anything here.
        }

        if (super.currentFrame != null)
            super.currentFrame.dispatchEvent(new WindowEvent(super.currentFrame, WindowEvent.WINDOW_CLOSING));
    }


    /**
     * KeyListener overrides
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();

        switch (key) {
            case 'w' -> {

                GameFrame.player1.yVelocity = -super.playerSpeed;
            }
            case 's' -> {

                GameFrame.player1.yVelocity = super.playerSpeed;
            }
            case 'a' -> {

                GameFrame.player1.xVelocity = -super.playerSpeed;
            }
            case 'd' -> {

                GameFrame.player1.xVelocity = super.playerSpeed;
            }

        }
        // same but with arrow keys for player 2
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> {

                GameFrame.player2.yVelocity = -super.playerSpeed;
            }
            case KeyEvent.VK_DOWN -> {

                GameFrame.player2.yVelocity = super.playerSpeed;
            }
            case KeyEvent.VK_LEFT -> {

                GameFrame.player2.xVelocity = -super.playerSpeed;
            }
            case KeyEvent.VK_RIGHT -> {

                GameFrame.player2.xVelocity = super.playerSpeed;
            }
        }

        //Handle Cheat Codes
        GameFrame.cheatCodes((int) e.getKeyChar() - 48); // 1 = 48 in ascii

    }


    @Override
    public void keyReleased(KeyEvent e) {
        this.currentFrame.KeyEvent(e);
    }

    /**
     * MouseListener overrides
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.currentFrame.MouseEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //not used
    }


    /**
     * MouseMotionListener overrides
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //not used
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //not used
    }
}
