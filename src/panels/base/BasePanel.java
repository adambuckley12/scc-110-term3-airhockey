package panels.base;

import frames.base.BaseFrame;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class BasePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    public BaseFrame currentFrame;
    protected int width;
    protected int height;

    public boolean exiting = false;


    //ALL GLOBAL SETTINGS

    public int maxGoals = 1;
    public boolean audioEnabled = false;

    public int winner = 0;

}
