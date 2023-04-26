public class Settings {
    private int balls;
    private int speed;
    private Boolean Multiplayer;
    private int maxScore;

    public Settings(int balls, boolean multiplayer, int speed, int maxScore) {
        this.balls = balls;
        this.Multiplayer = multiplayer;
        this.speed = speed;
        this.maxScore = maxScore;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getBalls() {
        return balls;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMultiplayer(Boolean multiplayer) {
        this.Multiplayer = multiplayer;
    }

    public Boolean getMultiplayer() {
        return Multiplayer;
    }

    public void SettingsWindow() {
        GameArena settingsWindow = new GameArena(900, 600, true);
    }



}
