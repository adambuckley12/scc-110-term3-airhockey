package frames.base;

import panels.base.BasePanel;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    protected BasePanel parentPanel;

    public abstract void customPaint(Graphics gr, int width, int height);

    public abstract void updatePositions();
}
