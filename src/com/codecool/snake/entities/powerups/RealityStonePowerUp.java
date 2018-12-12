package com.codecool.snake.entities.powerups;

public class RealityStonePowerUp extends PowerUp {

    private static final String IMAGE_NAME = "RealityStone";
    private static final int ENEMIES_TO_NEUTRALIZE = 3;

    public RealityStonePowerUp() {
        super(IMAGE_NAME);
    }

    public static int getNumberOfEnemiesToNeutralilze() {
        return ENEMIES_TO_NEUTRALIZE;
    }

    @Override
    public String getMessage() {
        return "Got reality stone power-up";
    }
}
