package panels.base;

import frames.base.BaseFrame;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class BasePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    public BaseFrame currentFrame;
    public boolean exiting = false;
    public int maxGoals = 1;
    public boolean audioEnabled = false;


    //ALL GLOBAL SETTINGS
    public double playerSpeed = 6;
    public int winner = 0;
    public float coefRestitution = 0.9f;
    public float coefFriction = 0.001f;
    protected int width;
    protected int height;

}
