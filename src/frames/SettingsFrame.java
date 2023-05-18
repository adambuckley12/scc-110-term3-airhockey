package frames;

import frames.base.BaseFrame;
import panels.base.BasePanel;
import shapes.Rectangle;
import shapes.Sphere;
import shapes.Text;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class SettingsFrame extends BaseFrame {

    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    public SettingsFrame(BasePanel parentPanel) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Game Frame");

    }

    private BufferedImage buffer;
    private Graphics2D graphics;
    private boolean rendered = false;


    private final Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/assets/images/Settings.png");
    @Override
    public void customPaint(Graphics gr, int width, int height) {
        Text maxGoalsText = new Text(String.valueOf(this.parentPanel.maxGoals), 320, 105, 50, new Color(0f, 0f, 0f, 1.0f), 1);
        Text audiotext = new Text(String.valueOf(this.parentPanel.audioEnabled), 240, 200, 50, new Color(0f, 0f, 0f, 1.0f), 1);
        shapes.add(maxGoalsText);
        shapes.add(audiotext);

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

                for (BaseShape o : shapes) {
                    graphics.setColor(o.colour);
                    if (o instanceof Sphere) {
                        graphics.fillOval(o.x - o.width / 2, o.y - o.height / 2, o.width, o.height);
                    }

                    if (o instanceof Rectangle) {
                        graphics.fillRect(o.x - o.width / 2, o.y - o.height / 2, o.width, o.height);
                    }

                    if (o instanceof Text) {
                        graphics.setFont(new Font("TimesRoman", Font.PLAIN, o.width));
                        graphics.drawString(o.text, o.x - o.width / 2, o.y - o.height / 2);
                    }
                }
            }

            window.drawImage(buffer, 0 ,0 , this);//this.getInsets().left
        }
        shapes.remove(maxGoalsText);
        shapes.remove(audiotext);

    }

    @Override
    public void updatePositions() {

    }

    @Override
    public void MouseEvent(MouseEvent e) {

        //Max Goals Button
            //Top Left: 40, 60
            //Bottom Right: 390, 120
        if (e.getX() >= 40 && e.getX() <= 390 && e.getY() >= 60 && e.getY() <= 120) {
            System.out.println("Max Goals Button Clicked");
            super.parentPanel.maxGoals++;
            if ( super.parentPanel.maxGoals > 15) {
                super.parentPanel.maxGoals = 1;
            }

        }

        //Audio Button
            //Top Left: 40, 160
            //Bottom Right: 450, 210
        if (e.getX() >= 40 && e.getX() <= 450 && e.getY() >= 160 && e.getY() <= 210) {
            System.out.println("Audio Button Clicked");
            super.parentPanel.audioEnabled = !super.parentPanel.audioEnabled;
        }

        //Exit GlobalSettings Button
            //Top Left: 40, 505
            //Bottom Right: 390, 570
        if (e.getX() >= 40 && e.getX() <= 390 && e.getY() >= 505 && e.getY() <= 570) {
            super.parentPanel.currentFrame = new MainFrame(super.parentPanel);
        }




    }
}
