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

    private Point2D heading;
    private static Random rnd = new Random();

    public IronManEnemy() {
        super(-10, "IronMan");

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        //TODO: head x,y coordinate? getposition?
    }

    @Override
    public void step() {
        Vec2d headPosition;
        headPosition = Snake.headPosition; //TODO: vektornak erre kéne mutatnia(végpont)

        if (isOutOfBounds()) {
            heading = Utils.createHeadPointer(headPosition);
        } else {

        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }
}
