package frames;

import frames.base.BaseFrame;
import panels.MainPanel;
import panels.base.BasePanel;
import shapes.Text;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SettingsFrame extends BaseFrame {

    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    public SettingsFrame(BasePanel parentPanel) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Game Frame");

        //shapes.add(new Text("Settings", 10, 10, 10, "000000", 1));


    }


    private BufferedImage buffer;
    private Graphics2D graphics;
    private boolean rendered = false;

    private final Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/assets/images/settingsBackgroundAR1x1.jpg");
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
            window.drawImage(buffer, 0 ,0 , this);//this.getInsets().left
        }

    }

    @Override
    public void updatePositions() {

    }
}
