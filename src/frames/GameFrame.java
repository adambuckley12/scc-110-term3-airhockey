package frames;

import frames.base.BaseFrame;
import panels.base.BasePanel;
import physics.Physics;
import shapes.Rectangle;
import shapes.Sphere;
import shapes.Text;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends BaseFrame {
    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    public static final Sphere player1 = new Sphere(50, 300, 0, 70, 1, new Color(0f, 0.2f, 0.9f, 1f), 0, 0);
    public static final Sphere player2 = new Sphere(850, 300, 0, 70, 1, new Color(1f, 0.2f, 0f, 1f), 0, 0);

    public final Sphere puck1 = new Sphere(451, 300, 0, 45, 2, new Color(0f, 0f, 0f, 1f), 0, 0);

    public final Rectangle leftGoal = new Rectangle(0, 300, 0, 40, 224, 0, new Color(0f, 0f, 0f, .1f));
    public final Rectangle rightGoal = new Rectangle(900, 300, 0, 40, 224, 0, new Color(0f, 0f, 0f, .1f));

    public int leftGoalCount = 0;
    public int rightGoalCount = 0;

    private final Text leftGoalText = new Text(String.valueOf(leftGoalCount), 100, 100, 50, new Color(0f, 0f, 0f, 1f), 1);
    private final Text rightGoalText = new Text(String.valueOf(rightGoalCount), 800, 100, 50, new Color(0f, 0f, 0f, 1f), 2);

    private float coeffRestitution = 0.9f;


    public GameFrame(BasePanel parentPanel) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Game Frame");

        // place shapes


        // test color
        shapes.add(new Rectangle(451, 300, 0, 872, 570, 0, new Color(0f, 0f, 0.1f, .1f)));
        shapes.add(leftGoal);
        shapes.add(rightGoal);
        shapes.add(player1);
        shapes.add(player2);
        shapes.add(puck1);
        shapes.add(leftGoalText);
        shapes.add(rightGoalText);

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

            window.drawImage(buffer, 0, 0, this);//this.getInsets().left
        }
    }

    @Override
    public void updatePositions() {

        // Make Sure Players are kept bounds. include radius in calculations

        if (player1.x + player1.width/2 > 450) {
            player1.x = 450 - player1.width/2;
        }

        if (player2.x - player2.width/2 < 450) {
            player2.x = 450 + player2.width/2;
        }

        if (player1.x - player1.width/2 < 0) {
            player1.x = player1.width / 2;
        }

        if (player2.x + player2.width/2 > 900) {
            player2.x = 900 - player2.width/2;
        }

        if (player1.y - player1.height/2 < 0) {
            player1.y = player1.height / 2;
        }

        if (player2.y - player2.height/2 < 0) {
            player2.y = player2.height / 2;
        }

        if (player1.y + player1.height/2 > 600) {
            player1.y = 600 - player1.height/2;
        }

        if (player2.y + player2.height/2 > 600) {
            player2.y = 600 - player2.height/2;
        }







        for (BaseShape o : shapes) {
            if (o instanceof Rectangle) continue; //no need to update positions of rectangles they are static

            //TODO!!! MOVE THIS SOMEWHERE ELSE SO ITS NOT REPEATEDLY CHECKED EACH FRAME FOR PERFORMANCE ONLY NEEDS CHECKING ONCE PER FRAME


            // Taking current velocity
            // Adding Friction (0.2% of current velocity) //TODO give options for friction in settings ???? (Add settings to each Panel)
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

            o.x += Math.round(o.xVelocity); //900, 10d = 910
            o.y += Math.round(o.yVelocity);

            // TODO another setting - max speed maybe???

            // Detect if collides with anything on path before moving and then move to make smoother?? maybe???

            //ONLY NEED TO CALC THIS FOR PUCK ASSUME PLAYER PADDLE DOESNT BOUNCE AS CONTROLLED
            if (o != this.puck1) continue;
            for (BaseShape comparison : shapes) {
                // make sure that we skip the current object
                if (comparison.x == o.x && comparison.y == o.y) continue;

                if (comparison instanceof Sphere) {
                    if (o.collides((Sphere) comparison)) {
                        // the point at which they touch
                        o.x = initialX;
                        o.y = initialY;
                        // deflect
                        Physics.deflect(o, comparison);
                        //write code to play sound from file here

                        super.playSound("src/assets/audio/hit.wav");


                    }
                } else if (comparison instanceof Rectangle) {
                    //TODO Maybe assume non elastic collisions? (very big maybe)
                    //Check if in goal
                    if (comparison == leftGoal)
                        if ((o.x < comparison.x + comparison.width / 2) && (o.y > comparison.y - comparison.height / 2) && (o.y < comparison.y + comparison.height / 2)) {

                            leftGoalCount++;
                            leftGoalText.text = String.valueOf(leftGoalCount);
                            resetPositions();
                            super.playSound("src/assets/audio/applause.wav");

                            break;
                        }
                    if (comparison == rightGoal)
                        if ((o.x > comparison.x - comparison.width / 2) && (o.y > comparison.y - comparison.height / 2) && (o.y < comparison.y + comparison.height / 2)) {
                            rightGoalCount++;
                            rightGoalText.text = String.valueOf(rightGoalCount);
                            resetPositions();

                            super.playSound("src/assets/audio/applause.wav");

                            break;
                        }

                   /* switch (o.within((Rectangle) comparison)) {

                        //check if in left or right goal

                        // collision with left/right
                        case 1 -> {
                            // IF INLINE WITH Y HEIGHT OF GOAL DONT BOUNCE. Goal is 224px tall and centered at 300px
if (o.y < 300 + 100 && o.y > 300 - 100) {
                        continue;
                    }
                            o.x = (int) (initialX - Math.round(o.xVelocity));
                            o.xVelocity *= -1;// 890 - 10
                            //System.out.println(o.xVelocity + " - " + o.yVelocity);
                        }

                        // collision with the top/bottom
                        case 2 -> {
                            o.y = (int) (initialY - Math.round(o.yVelocity));
                            o.yVelocity *= -1;
                        }
                    }*/

                    // it is possible for the puck to be let outside the rectangle by going through the goal at enough angle to be outside and then move out the goal before center goes over line.
                    // This makes sure it reenters the rectangle (honestly could have done all collisions like this but already made BaseShape.within()  TODO?? in case i have non rectangular shaped maps??)
                    if (o.y < 300 + 100 && o.y > 300 - 100) {
                        continue;
                    }

                    int radius = o.width/2;
                    int border = 16;



                    if (o.x + radius > 900 - border) {
                        o.x = 900 - 17 - radius;
                        o.xVelocity *= -1;
                    }
                    if (o.x - radius < border) {
                        o.x = 17 + radius; // get it just inside by
                        o.xVelocity *= -1;
                    }
                    if (o.y + radius > 600 - border) {
                        o.y = 600 - 17 - radius;
                        o.yVelocity *= -1;
                    }
                    if (o.y - radius < border) {
                        o.y = 17 + radius;
                        o.yVelocity *= -1;
                    }


                }
            }
        }

    }

    @Override
    public void MouseEvent(MouseEvent e) {

        //Menu Exit Button:
        if (e.getX() > 775 && e.getY() < 58) {
            //super.playSound("src/assets/audio/click.wav");
            super.parentPanel.currentFrame = new MainFrame(super.parentPanel);
            resetPositions();
            leftGoalCount = 0;
            rightGoalCount = 0;
        }

    }

    public void resetPositions() {


        //move puck to center
        puck1.x = 450;
        puck1.y = 300;

        //move players to left and right
        player1.x = 50;
        player1.y = 300;

        player2.x = 850;
        player2.y = 300;

        // SET PUCK VELOCITY TO RANDOM VALUES between -50 and 50
        // TODO: THIS IS FOR TESTING ONLY REMOVE BEFORE SUBMITTING
        puck1.xVelocity = (int) (Math.random() * 100) - 50;
        puck1.yVelocity = (int) (Math.random() * 100) - 50;


        player1.xVelocity = 0;
        player1.yVelocity = 0;

        player2.xVelocity = 0;
        player2.yVelocity = 0;
    }

}
