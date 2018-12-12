package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;

public class SoulStonePowerUp extends PowerUp {

    private static final String IMAGE_NAME = "SoulStone";
    private static final int HEALTH_CHANGE = 5;

    public SoulStonePowerUp() {
        super(IMAGE_NAME);
    }

    public static int getHealthChange() {
        return HEALTH_CHANGE;
    }

    @Override
    public String getMessage() {
        return "Got soul stone powerup";
    }
}
