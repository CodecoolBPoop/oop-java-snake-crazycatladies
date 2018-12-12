package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{
    private int damage;
    protected double speed = 1.0;

    public Enemy(int damage) {
        this.damage = damage;
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
}
