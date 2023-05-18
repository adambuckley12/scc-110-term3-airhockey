package panels;

import frames.GameFrame;
//import frames.MainFrame;
import frames.MainFrame;
import frames.SettingsFrame;
import frames.WinnerFrame;
import frames.base.BaseFrame;
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


        if (e.getKeyChar() == 'w') {
            //Set Sphere PLayer1 y velocity to 5
            GameFrame.player1.yVelocity = -super.playerSpeed;

        }
        if (e.getKeyChar() == 's') {
            //Set Sphere PLayer1 y velocity to -5
            GameFrame.player1.yVelocity = super.playerSpeed;
        }
        if (e.getKeyChar() == 'a') {
            //Set Sphere PLayer2 x velocity to -5
            GameFrame.player1.xVelocity = -super.playerSpeed;
        }
        if (e.getKeyChar() == 'd') {
            //Set Sphere PLayer2 x velocity to 5
            GameFrame.player1.xVelocity = super.playerSpeed;
        }

        // same but with arrow keys for player 2

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //Set Sphere PLayer1 y velocity to 5
            GameFrame.player2.yVelocity = -super.playerSpeed;

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //Set Sphere PLayer1 y velocity to -5
            GameFrame.player2.yVelocity = super.playerSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //Set Sphere PLayer2 x velocity to -5
            GameFrame.player2.xVelocity = -super.playerSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //Set Sphere PLayer2 x velocity to 5
            GameFrame.player2.xVelocity = super.playerSpeed;
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyChar() == 'w') {
            //Set Sphere PLayer1 y velocity to 5
            GameFrame.player1.yVelocity = -0;

        }
        if (e.getKeyChar() == 's') {
            //Set Sphere PLayer1 y velocity to -5
            GameFrame.player1.yVelocity = 0;
        }
        if (e.getKeyChar() == 'a') {
            //Set Sphere PLayer2 x velocity to -5
            GameFrame.player1.xVelocity = -0;
        }
        if (e.getKeyChar() == 'd') {
            //Set Sphere PLayer2 x velocity to 5
            GameFrame.player1.xVelocity = 0;
        }

        // same but with arrow keys for player 2
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //Set Sphere PLayer1 y velocity to 5
            GameFrame.player2.yVelocity = -0;

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //Set Sphere PLayer1 y velocity to -5
            GameFrame.player2.yVelocity = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //Set Sphere PLayer2 x velocity to -5
            GameFrame.player2.xVelocity = -0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //Set Sphere PLayer2 x velocity to 5
            GameFrame.player2.xVelocity = 0;
        }


    }


    /**
     * MouseListener overrides
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        //Check current frame = main frame
        this.currentFrame.MouseEvent(e);

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
