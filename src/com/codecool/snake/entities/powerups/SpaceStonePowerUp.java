package com.codecool.snake.entities.powerups;

public class SpaceStonePowerUp extends PowerUp {

    private static final String IMAGE_NAME = "SpaceStone";
    private static final double SPEED_CHANGE = 0.1;

    public SpaceStonePowerUp() {
        super(IMAGE_NAME);
    }

    public static double getSpeedChange() {
        return SPEED_CHANGE;
    }

    @Override
    public String getMessage() {
        return "Got space stone power-up";
    }
}
