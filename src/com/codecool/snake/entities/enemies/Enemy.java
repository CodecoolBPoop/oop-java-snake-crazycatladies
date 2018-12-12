package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {
    private final int damage;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
