package frames;

import frames.base.BaseFrame;
import panels.base.BasePanel;
import shapes.Text;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class WinnerFrame extends BaseFrame {

    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    public WinnerFrame(BasePanel parentPanel, int leftScore, int rightScore) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Winner Frame");
        //add score from game frame
        shapes.add(new Text("Score: " + leftScore + ":" + rightScore, 70, 200, 60, new Color(0f, 0f, 0f, 1f), 1));

    }

    private BufferedImage buffer;
    private Graphics2D graphics;
    private boolean rendered = false;


    private final Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/assets/images/WinnerFrame.png");
    @Override
    public void customPaint(Graphics gr, int width, int height) {



        Graphics2D window = (Graphics2D) gr;

        if (!rendered) {
            super.parentPanel.setSize(width, height);

            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            graphics = buffer.createGraphics();

            if (super.parentPanel.winner == 1) {
                shapes.add(new Text("Player 1 Wins!", 70, 100, 60, new Color(0f, 0f, 1f, 1f), 1));
            } else if (super.parentPanel.winner == 2) {
                shapes.add(new Text("Player 2 Wins!", 70, 100, 60, new Color(1f, 0f, 0f, 1f), 1));
            }

            //add score from game frame
            rendered = true;
        }

        synchronized (this) {
            if (!super.parentPanel.exiting) {
                graphics.clearRect(0, 0, width, height);

                if (backgroundImage != null)
                    graphics.drawImage(backgroundImage, 0, 0, width, height, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);

                for (BaseShape o : shapes) {
                    graphics.setColor(o.colour);
                    if (o instanceof Text) {
                        graphics.setFont(new Font("TimesRoman", Font.PLAIN, o.width));
                        graphics.drawString(o.text, o.x - o.width / 2, o.y - o.height / 2);
                    }
                }
            }

            window.drawImage(buffer, 0 ,0 , this);//this.getInsets().left
        }

    }

    @Override
    public void updatePositions() {
    //NOTHING IN WINNING FRAME TO MOVE
    }

    @Override
    public void MouseEvent(MouseEvent e) {

        //PLAY AGAIN
            //Top Left: 40, 360
            //Bottom Right: 360, 415

        if (e.getX() >= 35 && e.getX() <= 360 && e.getY() >= 334 && e.getY() <= 400) {
            System.out.println("Play Again Button Clicked");
            super.parentPanel.currentFrame = new GameFrame(super.parentPanel);
        }

        //Settings Button
            //Top Left: 35, 420
            //Bottom Right: 320, 480
        if (e.getX() >= 35 && e.getX() <= 320 && e.getY() >= 420 && e.getY() <= 480) {
            System.out.println("Settings Button Clicked");
            super.parentPanel.currentFrame = new SettingsFrame(super.parentPanel);
        }


        //Exit GlobalSettings Button
            //Top Left: 40, 540
            //Bottom Right: 390, 570
        if (e.getX() >= 35 && e.getX() <= 390 && e.getY() >= 505 && e.getY() <= 570) {
            super.parentPanel.currentFrame = new MainFrame(super.parentPanel);
        }

    }

}
