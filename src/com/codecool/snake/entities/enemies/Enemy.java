package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;


public abstract class Enemy extends GameEntity implements Animatable, Interactable {
    private int damage;
    protected double speed = 1.0;
    final int SPAWNDIRECTION = 0;

    public Enemy(int damage, String imageName) {

        this.damage = damage;
        setImage(Globals.getInstance().getImage(imageName));

    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void changeSpeed(double diff) {
        if (speed + diff > 0) {
            speed += diff;
        } else {
            speed = 0;
        }
        System.out.println("Enemy speed: " + speed);
    }

    public void neutralize() {
        this.setImage(Globals.getInstance().getImage("Bubble"));
        this.damage = 0;
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
