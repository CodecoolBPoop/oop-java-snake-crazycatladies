package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;

import java.util.Random;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

public class IronManEnemy extends Enemy implements Interactable, Animatable {

    private static Random rnd = new Random();


    public IronManEnemy() {
        super(-15, "IronMan");

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        setRotate(SPAWNDIRECTION);
        speed = 1.5;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        } else {
            Vec2d headPosition = Globals.getInstance().game.getSnake().getHeadPosition();
            double direction = Utils.getDirection(headPosition, getPosition());
            setRotate(direction);
            Point2D heading = Utils.directionToVector(direction, speed);

            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage from Ironman");
    }
}
