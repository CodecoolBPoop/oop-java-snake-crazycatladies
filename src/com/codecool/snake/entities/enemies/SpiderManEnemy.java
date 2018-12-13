package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

public class SpiderManEnemy extends Enemy implements Animatable, Interactable {

    private List<Integer> directionList = Arrays.asList(0, 180);
    double direction = directionList.get(new Random().nextInt(directionList.size()));

    private Point2D heading;
    private static Random rnd = new Random();

    public SpiderManEnemy() {
        super(-10, "SpiderMan");
        double windowDivider = 1.2;

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT / windowDivider);
        setRotate(SPAWNDIRECTION);

        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            if (direction == directionList.get(0)) {
                direction = directionList.get(1);
            } else {
                direction = directionList.get(0);
            }
        }
        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    /*@Override
    public void apply(GameEntity entity) {
        if(entity instanceof ){
            System.out.println(getMessage());
            destroy();
        }
    }*/
}
