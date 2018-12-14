package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.sun.javafx.geom.Vec2d;

public class WhereIsGamora extends GameEntity implements Animatable {

    private int stepsLeft = 100;

    public WhereIsGamora(Vec2d StarLordPosition) {
        setImage(Globals.getInstance().getImage("WhereIsGamora"));
        setPosition(StarLordPosition);
    }

    @Override
    public void step() {
        if (stepsLeft > 0) {
            stepsLeft --;
        } else {
            destroy();
        }
    }
}
