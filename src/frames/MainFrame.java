package frames;

import frames.base.BaseFrame;
import panels.base.BasePanel;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainFrame extends BaseFrame {
    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    public MainFrame(BasePanel parentPanel) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Main Menu");
    }


    private BufferedImage buffer;
    private Graphics2D graphics;
    private boolean rendered = false;

    private final Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/assets/images/MainMenuAirHockey.png");


    @Override
    public void customPaint(Graphics gr, int width, int height) {

        Graphics2D window = (Graphics2D) gr;

        if (!rendered) {
            super.parentPanel.setSize(width, height);

            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            graphics = buffer.createGraphics();

            //completed this initialisation, so that we don't do it again
            rendered = true;
        }

        synchronized (this) {
            if (!super.parentPanel.exiting) {
                graphics.clearRect(0, 0, width, height);

                if (backgroundImage != null)
                    graphics.drawImage(backgroundImage, 0, 0, width, height, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);

            }
            window.drawImage(buffer, 0 ,0 , this);
        }

    }

    @Override
    public void updatePositions() {

    }

    @Override
    public void MouseEvent(MouseEvent e) {

        /* Button positions:
             Start:
                Top Left: 45, 140
                Bottom Right: 245, 200
             GlobalSettings:
                Top Left: 45, 285
                Bottom Right: 245, 345
             Exit:
                Top Left: 45, 420
                Bottom Right: 245, 480
         */

        // Check if the mouse is within the start button

        if (e.getX() >= 45 && e.getX() <= 350 && e.getY() >= 140 && e.getY() <= 200) {
            System.out.println("Start button pressed");
            super.parentPanel.currentFrame = new GameFrame(super.parentPanel);
        }
        else if (e.getX() >= 45 && e.getX() <= 350 && e.getY() >= 285 && e.getY() <= 345) {
            System.out.println("GlobalSettings button pressed");
            super.parentPanel.currentFrame = new SettingsFrame(super.parentPanel);
        }
        else if (e.getX() >= 45 && e.getX() <= 350 && e.getY() >= 420 && e.getY() <= 480) {
            System.out.println("Exit button pressed");
            System.exit(0);
        }
        System.out.println("Mouse event in MainFrame");
    }

}
