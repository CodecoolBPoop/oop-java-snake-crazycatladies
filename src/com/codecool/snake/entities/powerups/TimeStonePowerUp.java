package com.codecool.snake.entities.powerups;

public class TimeStonePowerUp extends PowerUp {

    private static final String IMAGE_NAME = "TimeStone";
    private static final double ENEMY_SPEED_CHANGE = -0.2;

    public TimeStonePowerUp() {
        super(IMAGE_NAME);
    }

    public static double getEnemySpeedChange() {
        return ENEMY_SPEED_CHANGE;
    }

    @Override
    public String getMessage() {
        return "Got time stone power-up";
    }
}
