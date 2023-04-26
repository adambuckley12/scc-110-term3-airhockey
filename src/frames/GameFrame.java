package frames;

import frames.base.BaseFrame;
import panels.base.BasePanel;
import physics.Physics;
import shapes.Rectangle;
import shapes.Sphere;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class GameFrame extends BaseFrame {
    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    private final Sphere player1 = new Sphere(50, 300, 0, 50, 1, new Color(0f,0.2f,0.9f,1f), 10, -10);
    private final Sphere player2 = new Sphere(850, 300, 0, 50, 1, new Color(1f,0.2f,0f,1f), -10, 10);

    private final Sphere puck1 = new Sphere(451, 300, 0, 50, 2, new Color(0f,0f,0f,1f ), 1, 1);


    public GameFrame(BasePanel parentPanel) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Game Frame");

        // place shapes

        // test color
        shapes.add(new Rectangle(451, 300, 0, 872, 570, 0, new Color(0f,0f,0.1f,.1f )));
        shapes.add(player1);
        shapes.add(player2);
        shapes.add(puck1);

        // right left borders are 15px
        // top bottom borders are 15px

        // 900 - 30 / 2
        // 870
        // 435
    }

    private BufferedImage buffer;
    private Graphics2D graphics;
    private boolean rendered = false;
    private final Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/assets/images/AirHockeyTableImage-highres.png");
    @Override
    public void customPaint(Graphics gr, int width, int height) {
        Graphics2D window = (Graphics2D) gr;

        if (!rendered) {
            super.parentPanel.setSize(width, height);

            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			graphics = buffer.createGraphics();

			// Remember that we've completed this initialisation, so that we don't do it again...
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
                        graphics.fillRect(o.x - o.width / 2 , o.y - o.height / 2, o.width, o.height);
                    }
                }
            }

            window.drawImage(buffer, 0 ,0 , this);//this.getInsets().left
        }
    }

    @Override
    public void updatePositions() {
        for (BaseShape o : shapes) {
            if(o instanceof Rectangle) continue;
            //TODO if player1 in wrong half of screen, move to correct half



            //TODO if player2 in wrong half of screen, move to correct half


            // Taking current velocity
            // Adding Friction (0.2% of current velocity) //TODO give options for friction in settings (Add settings to each Panel)
            // Updating velocity
            // determining new coords.
                // collisions
                // goal etc

            // x velocity
            // y velocity
                // apply friction to x and y separate as same as adding friction on total velocity

            //TODO: give options for friction in settings (Add settings to each Panel? BasePanel?)
            double friction = 0.0005; // 0.2% friction

            o.xVelocity *= (1.0 - friction);
            o.yVelocity *= (1.0 - friction);

            int initialX = o.x;
            int initialY = o.y;

            o.x += Math.round(o.xVelocity); //900, 10 = 910
            o.y += Math.round(o.yVelocity);
            
            // TODO another setting - max speed.
            
            // Detect if collides with anything on path?
            
            
            for(BaseShape comparison: shapes) {
                // make sure that we skip the current object
                if(comparison.x == o.x && comparison.y == o.y) continue;

                if(comparison instanceof Sphere) {
                    if (o.collides((Sphere) comparison)) {
                        // the point at which they touch
                        o.x = initialX;
                        o.y = initialY;
//                        // TODO: Handle collisions (get new velocities and angles)
//
//                        //get distance between the two objects
//                        double distanceBetweenCenters = Math.sqrt(Math.pow(o.x - comparison.x, 2) + Math.pow(o.y - comparison.y, 2));


                        // deflect
                        Physics.deflect(o, comparison);

                    }
                } else if(comparison instanceof Rectangle) {
                    //TODO Maybe assume non elastic collisions? (very big maybe)
                    switch (o.within((Rectangle) comparison)) {
                        // collision with left/right
                        case 1 -> {
                            o.x = (int) (initialX - Math.round(o.xVelocity));
                            o.xVelocity *= -1;// 890 - 10
                            System.out.println(o.xVelocity + " - " + o.yVelocity);
                        }

                        // collision with the top/bottom
                        case 2 -> {
                            o.y = (int) (initialY - Math.round(o.yVelocity));
                            o.yVelocity *= -1;
                        }
                    }
                }
            }
        }
    }
}
