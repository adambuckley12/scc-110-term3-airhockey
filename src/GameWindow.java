public class GameWindow {
        public GameArena DefaultAirHockeyTable() {

            GameArena gameArena = new GameArena(900, 600, true);

            gameArena.setBackgroundImage("src/images/AirHockeyTableImage.png");
            /*Image Table Positioning: (inside pixel of image drawn lines on table)
            Top Left: 80, 68
            Top Right: 820, 68
            Bottom Left: 80, 523
            Bottom Right: 820, 523

            Left Player Ball Start Pos = 400
            Right Player Ball Start Pos = 500
             */
            return gameArena;
        }

}

