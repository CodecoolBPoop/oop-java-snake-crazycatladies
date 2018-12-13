package com.codecool.snake.entities.powerups;

public class PowerStonePowerUp extends PowerUp {

    private static final String IMAGE_NAME = "PowerStone";
    private static final int BODY_SIZE_CHANGE = 1;
    private static final double SCORE_CHANGE = 150;

    public PowerStonePowerUp() {
        super(IMAGE_NAME);
    }

    public static double getScoreChange() {
        return SCORE_CHANGE;
    }

    public static int getBodySizeChange() {
        return BODY_SIZE_CHANGE;
    }

    @Override
    public String getMessage() {
        return "Got power stone power up";
    }
}
