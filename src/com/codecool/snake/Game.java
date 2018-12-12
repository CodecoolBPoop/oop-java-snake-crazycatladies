package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

import java.sql.Time;
import java.util.List;


public class Game extends Pane {
    private static final int POWERUP_FRAME_TIME = 3;
    private static final double POWERUP_PROBABILITY = 0.4;
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private GameTimer powerupTimer = new GameTimer(POWERUP_FRAME_TIME);


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
        gameTimer.play();
        powerupTimer.play();
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

    private void spawnPowerUps(int numberOfPowerUps) {
        for (int i = 0; i < numberOfPowerUps; ++i) {
            int decider = Utils.getRandomWithin(0, 4);
            switch (decider) {
                case 0:
                    new SoulStonePowerUp();
                    break;
                case 1:
                    new PowerStonePowerUp();
                    break;
                case 2:
                    new SpaceStonePowerUp();
                    break;
                case 3:
                    new TimeStonePowerUp();
                    break;
                case 4:
                    new RealityStonePowerUp();
                    break;
            }
        }
    }

    private void maybeSpawnPowerUp(double probability) {
        if (Utils.doesEventHappen(probability)) {
            this.spawnPowerUps(1);
        }
    }

    public void slowDownEnemies(double speedChange) {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (GameEntity gameObject :
                gameObjs) {
            if (gameObject instanceof Enemy) {
                ((Enemy) gameObject).changeSpeed(speedChange);
            }
        }
    }

    public void neutralizeEnemies(int howMany) {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        int counter = 0;
        for (int i = 0; i < gameObjs.size(); i++) {
            if (counter >= howMany) break;
            if (gameObjs.get(i) instanceof Enemy) {
                ((Enemy) gameObjs.get(i)).neutralize();
                counter ++;
            }
        }
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void setBackgroundImage(Image backgroundImage) {
        setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
