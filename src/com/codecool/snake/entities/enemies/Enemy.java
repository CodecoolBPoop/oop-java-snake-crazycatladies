package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{
    private final int damage;
    protected double speed = 1.0;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void changeSpeed(double diff) {
        speed += diff;
        System.out.println("Enemy speed: " + speed);
    }
}
