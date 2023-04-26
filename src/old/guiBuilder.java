//package old;
//
//public class guiBuilder {
//
//        private Settings settings = new Settings(1, true, 5 , 6); // Default Settings for the game.
//
//        private volatile boolean viewSettings = true;
//
//        public guiBuilder() {
//            GameArena gameArena = new GameArena(900, 600, true);
//            gameArena.setBackgroundImage("src/images/AirHockeyTableImage.png");
//            SettingsWindow(gameArena);
//
//
//        }
//
//        private void startGame(GameArena window) {
//            DefaultAirHockeyTable(window);
//        }
//
//        private void endGame(GameArena window) {
//            window.clearGameArena();
//            SettingsWindow(window);
//        }
//
//        private void DefaultAirHockeyTable(GameArena window) {
//            window.clearGameArena();
//            window.setSize(900, 600);
//            window.clearGameArena();
//            window.setBackgroundImage("src/images/AirHockeyTableImage.png");
//            /*Image Table Positioning: (inside pixel of image drawn lines on table)
//            Top Left: 80, 68
//            Top Right: 820, 68
//            Bottom Left: 80, 523
//            Bottom Right: 820, 523
//
//            Left Player Sphere Start Pos = 400
//            Right Player Sphere Start Pos = 500
//             */
//        }
//
//        private void loopSleep(int time) {
//            try {
//                Thread.sleep(time);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        private void SettingsWindow(GameArena window) {
//            window.setBackgroundImage("src/images/settingsBackgroundAR1x1.jpg");
//            UpdateSettingsWindow(window);
//            window.addKeyListener(window);
//
//
//
//            //Key Listener
//            while (viewSettings) {
//                if (window.letterPressed('a'))
//                {
//                    loopSleep(100);
//                    if (settings.getSpheres() >= 5)
//                    {
//                        settings.setSpheres(1);
//                    }
//                    else
//                    {
//                        settings.setSpheres(settings.getSpheres() + 1);
//                    }
//                    UpdateSettingsWindow(window);
//
//                }
//                if (window.letterPressed('s'))
//                {
//                    loopSleep(100);
//                    if (settings.getSpeed() >= 10)
//                    {
//                        settings.setSpeed(1);
//                    }
//                    else
//                    {
//                        settings.setSpeed(settings.getSpeed() + 1);
//                    }
//                    UpdateSettingsWindow(window);
//                }
//                if (window.letterPressed('d'))
//                {
//                    loopSleep(100);
//                    if (settings.getMaxScore() >= 20)
//                    {
//                        settings.setMaxScore(1);
//                    }
//                    else
//                    {
//                        settings.setMaxScore(settings.getMaxScore() + 1);
//                    }
//                    UpdateSettingsWindow(window);
//                }
//                if (window.letterPressed('f'))
//                {
//                    loopSleep(100);
//                    settings.setMultiplayer(!settings.getMultiplayer());
//                    UpdateSettingsWindow(window);
//                }
//                //Start Game
//                if (window.letterPressed('g'))
//                {
//                    loopSleep(100);
//                    startGame(window);
//                }
//            }
//
//            //KeyBind Listening
//
//
//        }
//
//
//        private void UpdateSettingsWindow(GameArena window)
//        {
//            window.clearGameArena();
//            window.setSize(900, 600);
//
//            //TITLE TEXT
//            Text title = new Text("Settings", 50, 30, 70, "BLACK");
//            window.addText(title);
//
//            //SphereS TEXT
//            Text SpheresText = new Text("Spheres: " + settings.getSpheres(), 15, 40, 150, "BLACK");
//            window.addText(SpheresText);
//
//            //SPEED TEXT
//            Text speedText = new Text("Speed: " + settings.getSpeed(), 15, 40, 200, "BLACK");
//            window.addText(speedText);
//
//            //MAX SCORE TEXT
//            Text maxScoreText = new Text("Max Score: " + settings.getMaxScore(), 15, 40, 250, "BLACK");
//            window.addText(maxScoreText);
//
//            //MULTIPLAYER TEXT
//            Text multiplayerText = new Text("Multiplayer: " + settings.getMultiplayer(), 15, 40, 300, "BLACK");
//            window.addText(multiplayerText);
//
//            //Player1 Keybinds
//            Text player1Keybinds = new Text("Player 1 Keybinds: ", 15, 40, 350, "BLACK");
//
//
//            //Cycle through settings. (Spheres, Speed, Max Score, Multiplayer) (1, 2, 3, 4)
//            Text cycleSettingsText = new Text("Press the corresponding number to cycle through settings.", 15, 40, 500, "BLACK");
//            Text cycleKeybindsText = new Text("A: Spheres, S: Speed, D: Max Score, F: Multiplayer", 15, 40, 525, "BLACK");
//            Text settingsStartGame = new Text("Press Enter to start the game.", 15, 40, 550, "BLACK");
//            window.addText(cycleSettingsText);
//            window.addText(cycleKeybindsText);
//            window.addText(settingsStartGame);
//
//
//        }
//}
//
