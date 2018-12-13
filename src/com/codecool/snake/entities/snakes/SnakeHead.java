package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;

import com.codecool.snake.entities.powerups.*;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 3;
    private Snake snake;
    private Vec2d accessPoint = new Vec2d();

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void setAccessPoint() {
        Vec2d headPosition = getPosition();
        double headRotation = getRotate();
        accessPoint.x = headPosition.x;
        accessPoint.y = headPosition.y + 20;
    }

    public Vec2d getAccessPoint() {
        return accessPoint;
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        setAccessPoint();
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy){
            snake.changeHealth(((Enemy) entity).getDamage());
        }
        if (entity instanceof SoulStonePowerUp){
            snake.changeHealth((SoulStonePowerUp.getHealthChange()));
        }
        if (entity instanceof PowerStonePowerUp) {
            snake.addPart(PowerStonePowerUp.getBodySizeChange());
            snake.changeScore(PowerStonePowerUp.getScoreChange());
        }
        if (entity instanceof SpaceStonePowerUp) {
            snake.changeSpeed(SpaceStonePowerUp.getSpeedChange());
        }
        if (entity instanceof TimeStonePowerUp) {
            Globals.getInstance().game.slowDownEnemies(TimeStonePowerUp.getEnemySpeedChange());
        }
        if (entity instanceof RealityStonePowerUp) {
            Globals.getInstance().game.neutralizeEnemies(RealityStonePowerUp.getNumberOfEnemiesToNeutralilze());
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
