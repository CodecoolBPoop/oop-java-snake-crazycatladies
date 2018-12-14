package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;

public class StarLordEnemy extends SimpleEnemy {

    public StarLordEnemy() {
        super("StarLord");
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            WhereIsGamora shout = new WhereIsGamora(getPosition());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage from Star Lord");
    }
}
