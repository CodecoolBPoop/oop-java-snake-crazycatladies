package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private static final int POWERUP_FRAME_TIME = 3;
    private static final double POWERUP_PROBABILITY = 0.4;
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private GameTimer powerupTimer = new GameTimer(POWERUP_FRAME_TIME);
    private GameTimer enemyTimer = new GameTimer(POWERUP_FRAME_TIME);


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        powerupTimer.setup(() -> this.maybeSpawnPowerUp(POWERUP_PROBABILITY));
        enemyTimer.setup(() -> this.spawnEnemiesWhenRun(POWERUP_PROBABILITY));
        gameTimer.play();
        powerupTimer.play();
        enemyTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) {
            new SimpleEnemy("SimpleEnemy");
            new SimpleEnemy("MyEnemy");
        }
    }

    private void spawnEnemiesWhenRun(double probability){
        if (Utils.doesEventHappen(probability)) {
            this.spawnEnemies(1);
        }
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void maybeSpawnPowerUp(double probability) {
        if (Utils.doesEventHappen(probability)) {
            this.spawnPowerUps(1);
        }
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
