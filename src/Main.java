import panels.MainPanel;
import shapes.Sphere;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Sphere sphere = new Sphere(-1, -1, -1, -1, -1, Color.BLACK);
        MainPanel panel = new MainPanel(900, 600);
    }
}
