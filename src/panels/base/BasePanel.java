package panels.base;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class BasePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    protected JFrame currentFrame;
    protected int width;
    protected int height;


}
