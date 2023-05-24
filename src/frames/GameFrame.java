package frames;

import frames.base.BaseFrame;
import panels.base.BasePanel;
import physics.Physics;
import shapes.Rectangle;
import shapes.Sphere;
import shapes.Text;
import shapes.base.BaseShape;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameFrame extends BaseFrame {
    public static final Sphere player1 = new Sphere(50, 300, 0, 70, 1, new Color(0f, 0.2f, 0.9f, 1f), 0, 0);
    public static final Sphere player2 = new Sphere(850, 300, 0, 70, 1, new Color(1f, 0.2f, 0f, 1f), 0, 0);
    public static final Sphere gamePuck = new Sphere(451, 300, 0, 45, 2, new Color(0f, 0f, 0f, 1f), 0, 0);
    public final Rectangle leftGoal = new Rectangle(0, 300, 0, 40, 224, 0, new Color(0f, 0f, 0f, .1f));
    public final Rectangle rightGoal = new Rectangle(900, 300, 0, 40, 224, 0, new Color(0f, 0f, 0f, .1f));
    private final ArrayList<BaseShape> shapes = new ArrayList<>();
    private final Image backgroundImage = Toolkit.getDefaultToolkit().getImage("src/assets/images/AirHockeyTableImage-highres.png");
    public int leftGoalCount = 0;
    private final Text leftGoalText = new Text(String.valueOf(leftGoalCount), 100, 100, 50, new Color(0f, 0f, 0f, 1f), 1);
    public int rightGoalCount = 0;
    private final Text rightGoalText = new Text(String.valueOf(rightGoalCount), 800, 100, 50, new Color(0f, 0f, 0f, 1f), 2);
    private BufferedImage buffer;
    private Graphics2D graphics;
    private boolean rendered = false;

    public GameFrame(BasePanel parentPanel) {
        super.parentPanel = parentPanel;
        super.setTitle("Adam Buckley SCC110 Air Hockey Term 3 - Game Frame");
        super.playSound("src/assets/audio/fanfare.wav");

        shapes.add(leftGoal);
        shapes.add(rightGoal);
        shapes.add(player1);
        shapes.add(player2);
        shapes.add(gamePuck);
        shapes.add(leftGoalText);
        shapes.add(rightGoalText);

    }

    public static void cheatCodes(int cheatNumber) {
        // if 1 make player 1 larger
        switch (cheatNumber) {
            case 1 -> {
                player1.width += 5;
                player1.height += 5;
            }
            case 2 -> {
                player2.width += 5;
                player2.height += 5;
            }
            case 3 -> {
                // make player 1 smaller
                player1.width -= 5;
                player1.height -= 5;
            }
            case 4 -> {
                // make player 2 smaller
                player2.width -= 5;
                player2.height -= 5;
            }
            case 5 ->
                // make player 1 nearly invisible
                    player1.colour = new Color(0, 0, 0, 0.02f);
            case 6 ->
                // make player 2 nearly invisible
                    player2.colour = new Color(0, 0, 0, 0.05f);
            case 7 ->
                // make player 1 visible
                    player1.colour = new Color(0, 0, 1, 1f);
            case 8 ->
                // make player 2 visible
                    player2.colour = new Color(1, 0, 0, 1f);
            case 9 ->
                // make puck invisible
                    gamePuck.colour = new Color(0, 0, 0, 0.02f);
            case 0 ->
                // make puck visible
                    gamePuck.colour = new Color(0, 0, 0, 1f);
            case ((int) '`' - 48) -> {
                // make puck rainbow (change it to random every second for 20 seconds)
                // make new thread to change colour every second
                new Thread(() -> {
                    for (int i = 0; i < 100; i++) {
                        try {
                            Thread.sleep(100);
                            gamePuck.colour = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1f);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }


        }


    }

    @Override
    public void customPaint(Graphics gr, int width, int height) {
        Graphics2D window = (Graphics2D) gr;

        if (!rendered) {
            super.parentPanel.setSize(width, height);

            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            graphics = buffer.createGraphics();

            // Completed this initialization, so that we don't do it again
            rendered = true;
        }

        synchronized (this) {
            // Loop to handle rendering shapes onto the panel each frame
            if (!super.parentPanel.exiting) {
                graphics.clearRect(0, 0, width, height);
                //check for background image and render if there
                if (backgroundImage != null)
                    graphics.drawImage(backgroundImage, 0, 0, width, height, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);

                for (BaseShape o : shapes) {
                    //loop through all shapes, check type and draw onto frame
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

            window.drawImage(buffer, 0, 0, this); //draw final image to window
        }
    }

    private void keepPlayerInFrameArea()
    {
        int centerX = parentPanel.getWidth()/2;

        int greatestX = parentPanel.getWidth();
        int greatestY = parentPanel.getHeight();

        // simple checks to make sure player is in the correct half and cannot move over center line
        if (player1.x + player1.width / 2 > centerX) {
            player1.x = centerX - player1.width / 2;
        }

        if (player2.x - player2.width / 2 < centerX) {
            player2.x = centerX + player2.width / 2;
        }

        if (player1.x - player1.width / 2 < 0) {
            player1.x = player1.width / 2;
        }

        if (player2.x + player2.width / 2 > greatestX) {
            player2.x = greatestX - player2.width / 2;
        }

        if (player1.y - player1.height / 2 < 0) {
            player1.y = player1.height / 2;
        }

        if (player2.y - player2.height / 2 < 0) {
            player2.y = player2.height / 2;
        }

        if (player1.y + player1.height / 2 > greatestY) {
            player1.y = greatestY - player1.height / 2;
        }

        if (player2.y + player2.height / 2 > greatestY) {
            player2.y = greatestY - player2.height / 2;
        }
    }

    @Override
    public void updatePositions() {



        // Make sure players are kept within bounds, include radius in calculations
        keepPlayerInFrameArea();

        for (BaseShape shape : shapes) {
            if (shape instanceof Rectangle) continue; // No need to update positions of rectangles as they are static

            shape.xVelocity *= (1.0 - super.parentPanel.coefFriction);
            shape.yVelocity *= (1.0 - super.parentPanel.coefFriction);

            int initialX = shape.x;
            int initialY = shape.y;

            shape.x += Math.round(shape.xVelocity); // Round to nearest integer
            shape.y += Math.round(shape.yVelocity);


            // ONLY NEED TO CALCULATE THIS FOR PUCK ASSUME PLAYER PADDLE DOESN'T BOUNCE AS CONTROLLED
            if (shape != gamePuck) continue;
            for (BaseShape comparison : shapes) {
                // Make sure that we skip the current object
                if (comparison.x == shape.x && comparison.y == shape.y) continue;

                if (comparison instanceof Sphere) {
                    if (((Sphere) shape).collides((Sphere) comparison)) {
                        // The point at which they touch
                        shape.x = initialX;
                        shape.y = initialY;
                        // Deflect
                        Physics.deflect(shape, comparison);
                        // Write code to play sound from file here

                        super.playSound("src/assets/audio/hit.wav");
                    }
                } else if (comparison instanceof Rectangle) {

                    // Check if in left or right goal (at this point we know the shape = gamePuck from "if (shape != gamePuck) continue;"
                    if (comparison == leftGoal) {
                        if ((shape.x < comparison.x + comparison.width / 2) && (shape.y > comparison.y - comparison.height / 2) && (shape.y < comparison.y + comparison.height / 2)) { // If center in goal

                            leftGoalCount++;
                            leftGoalText.text = String.valueOf(leftGoalCount);
                            resetPositions(1);


                            if (leftGoalCount >= super.parentPanel.maxGoals)
                            {
                                gameWon(2);
                                break;
                            }
                            else
                            {
                                super.playSound("src/assets/audio/applause.wav");
                            }

                        }
                    } else if (comparison == rightGoal) {
                        if ((shape.x > comparison.x - comparison.width / 2) && (shape.y > comparison.y - comparison.height / 2) && (shape.y < comparison.y + comparison.height / 2)) { // If in goal

                            rightGoalCount++;
                            rightGoalText.text = String.valueOf(rightGoalCount);
                            resetPositions(2);


                            if (rightGoalCount >= super.parentPanel.maxGoals)
                            {
                                gameWon(1);
                                break;
                            }
                            else
                            {
                                super.playSound("src/assets/audio/applause.wav");
                            }


                        }
                    }

                    // Deflect off arena edge if in contact and not in line with goal
                    puckDeflectionOffArenaEdge(shape);
                }
            }
        }
    }


    @Override
    public void MouseEvent(MouseEvent e) {

        // Menu Exit Button:
        if (e.getX() > 775 && e.getY() < 58) {
            // If the mouse click is within the exit button area
            // Go back to the main frame
            super.parentPanel.currentFrame = new MainFrame(super.parentPanel);

            // Reset positions and goal counts
            resetPositions(0);
            leftGoalCount = 0;
            rightGoalCount = 0;
        }

    }

    @Override
    public void KeyPressed(KeyEvent e) {

        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();

        switch (key) {
            case 'w' ->
                // Set Sphere PLayer1 y velocity to 5
                    GameFrame.player1.yVelocity = -5;
            case 's' ->
                // Set Sphere PLayer1 y velocity to -5
                    GameFrame.player1.yVelocity = 5;
            case 'a' ->
                // Set Sphere PLayer2 x velocity to -5
                    GameFrame.player1.xVelocity = -5;
            case 'd' ->
                // Set Sphere PLayer2 x velocity to 5
                    GameFrame.player1.xVelocity = 5;
            case 'r' ->
                // Reset positions (in case of puck stuck glitch)d
                    resetPositions(0);
        }

        switch (keyCode) {
            case KeyEvent.VK_UP ->
                // Set Sphere PLayer1 y velocity to 5
                    GameFrame.player2.yVelocity = -5;
            case KeyEvent.VK_DOWN ->
                // Set Sphere PLayer1 y velocity to -5
                    GameFrame.player2.yVelocity = 5;
            case KeyEvent.VK_LEFT ->
                // Set Sphere PLayer2 x velocity to -5
                    GameFrame.player2.xVelocity = -5;
            case KeyEvent.VK_RIGHT ->
                // Set Sphere PLayer2 x velocity to 5
                    GameFrame.player2.xVelocity = 5;

            case KeyEvent.VK_ESCAPE -> {
                // If the escape key is pressed, go back to the main frame
                super.parentPanel.currentFrame = new MainFrame(super.parentPanel);

                // Reset positions and goal counts
                resetPositions(0);
                leftGoalCount = 0;
                rightGoalCount = 0;
            }
        }


    }

    @Override
    public void KeyReleased(KeyEvent e) {

        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();

        switch (key) {
            case 'w', 's' ->
                // Set Sphere PLayer1 y velocity to 0
                    GameFrame.player1.yVelocity = 0;
            case 'a', 'd' ->
                // Set Sphere PLayer2 x velocity to 0
                    GameFrame.player1.xVelocity = 0;
        }

        switch (keyCode) {
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN ->
                // Set Sphere PLayer1 y velocity to 0
                    GameFrame.player2.yVelocity = 0;
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT ->
                // Set Sphere PLayer2 x velocity to 0
                    GameFrame.player2.xVelocity = 0;
        }
        // Handle Cheat Codes
        GameFrame.cheatCodes((int) e.getKeyChar() - 48); // 1 = 48 in ascii

    }


    private void resetPositions(int scorer) {


        // Reset velocities
        gamePuck.xVelocity = 0;
        gamePuck.yVelocity = 0;

        player1.xVelocity = 0;
        player1.yVelocity = 0;

        player2.xVelocity = 0;
        player2.yVelocity = 0;


        // 0 for none, 1 for left, 2 for right

        if (scorer == 0) {
            // Move the puck to the center
            gamePuck.x = 450;
            gamePuck.y = 300;
        } else if (scorer == 2) {
            // Move the puck to the right of center
            gamePuck.x = 500;
            gamePuck.y = 300;
        } else if (scorer == 1) {
            // Move the puck to the left
            gamePuck.x = 400;
            gamePuck.y = 300;
        }

        // Move players to the left and right
        player1.x = 50;
        player1.y = 300;

        player2.x = 850;
        player2.y = 300;
    }

    private void puckDeflectionOffArenaEdge(BaseShape puck) {

        // If the puck is inline with a goal, allow it to pass through
        if (puck.y < 300 + 100 && puck.y > 300 - 100) {
            return;
        }

        int radius = puck.width / 2;
        int border = 16;
        boolean didCollide = false;

        // Check for collision with the arena edges
        if (puck.x + radius > 900 - border) {
            puck.x = 900 - 17 - radius;
            puck.xVelocity *= -1;
            didCollide = true;
        }
        if (puck.x - radius < border) {
            puck.x = 17 + radius; // Get it just inside the border
            puck.xVelocity *= -1;
            didCollide = true;
        }
        if (puck.y + radius > 600 - border) {
            puck.y = 600 - 17 - radius;
            puck.yVelocity *= -1;
            didCollide = true;
        }
        if (puck.y - radius < border) {
            puck.y = 17 + radius;
            puck.yVelocity *= -1;
            didCollide = true;
        }

        if (didCollide) {
            super.playSound("src/assets/audio/bounce.wav");

            // Assume a small loss of energy on bounce (sound and heat)
            puck.xVelocity *= super.parentPanel.coefRestitution;
            puck.yVelocity *= super.parentPanel.coefRestitution;
        }
    }

    private void gameWon(int winner) {
        // Set the winner in the parent panel
        super.parentPanel.winner = winner;


        // Go to the winner frame and reset goal counts and positions
        super.parentPanel.currentFrame = new WinnerFrame(super.parentPanel, leftGoalCount, rightGoalCount);
        leftGoalCount = 0;
        rightGoalCount = 0;
        resetPositions(0);
    }


}
