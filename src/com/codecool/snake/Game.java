package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.IronManEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SpiderManEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.sql.Time;
import java.util.List;


public class Game extends Pane {
    private static final int POWERUP_FRAME_TIME = 3;
    private static final double POWERUP_PROBABILITY = 0.4;
    private static final double ENEMY_PROBABILITY = 0.6;
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private GameTimer powerupTimer = new GameTimer(POWERUP_FRAME_TIME);

    private Text healthBoard = new Text();
    private Text healthLabel = new Text();
    private Text scoreBoard = new Text();
    private Text scoreLabel = new Text();

    private GameTimer enemyTimer = new GameTimer(POWERUP_FRAME_TIME);


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(2);
        spawnPowerUps(4);
        spawnBoards(scoreBoard, 910, snake.getScore());
        spawnBoards(healthBoard, 160, snake.getHealth());
        spawnLabels(healthLabel, 20, "Health: ");
        spawnLabels(scoreLabel, 790, "Score: " );

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        powerupTimer.setup(() -> this.maybeSpawnPowerUp(gameLoop));
        enemyTimer.setup(() -> this.spawnEnemiesWhenRun(gameLoop));
        gameTimer.play();
        powerupTimer.play();
        enemyTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    public Snake getSnake() {
        return snake;
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) {
            new SimpleEnemy("DoctorStrange");
            new SpiderManEnemy();
        }
    }

    private void spawnEnemiesWhenRun(GameLoop gameLoop){
        if (gameLoop.isRunning() && Utils.doesEventHappen(ENEMY_PROBABILITY)) {
            int decider = Utils.getRandomWithin(0, 3);
            switch (decider) {
                case 0:
                    new SimpleEnemy("DoctorStrange");
                    break;
                case 1:
                    new SimpleEnemy("StarLord");
                    break;
                case 2:
                    new IronManEnemy();
                    break;
                case 3:
                    new SpiderManEnemy();
                    break;
            }
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

    private void spawnBoards(Text board, int xCoord, double getterFunction) {
        getChildren().add(board);

        board.setLayoutX(xCoord);
        board.setLayoutY(30);
        board.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
        board.setFill(Color.RED);
        board.setText(String.valueOf(getterFunction));
    }

    private void spawnLabels(Text label, int xCoord, String boardLabel) {
        getChildren().add(label);

        label.setLayoutX(xCoord);
        label.setLayoutY(30);
        label.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD, 30));
        label.setFill(Color.BLUE);
        label.setText(boardLabel);
    }

    public void updateHealthBoard() {
        int health = snake.getHealth();
        healthBoard.setText(String.valueOf(health));
    }

    public void updateScoreboard() {
        int score = (int) snake.getScore();
        if (score % 10 == 0) {
            scoreBoard.setText(String.valueOf(score));
        }
    }
/*

    private void maybeSpawnPowerUp(double probability) {
        if (Utils.doesEventHappen(probability)) {
            this.spawnPowerUps(1);
        }
    }
*/

    private void maybeSpawnPowerUp (GameLoop gameLoop){
        if (gameLoop.isRunning() && Utils.doesEventHappen(POWERUP_PROBABILITY)) {
            this.spawnPowerUps(1);
        }
    }

    public void slowDownEnemies ( double speedChange){
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (GameEntity gameObject :
                gameObjs) {
            if (gameObject instanceof Enemy) {
                ((Enemy) gameObject).changeSpeed(speedChange);
            }
        }
    }

    public void neutralizeEnemies ( int howMany){
                List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
                int counter = 0;
                for (int i = 0; i < gameObjs.size(); i++) {
                    if (counter >= howMany) break;
                    GameEntity currentEntity = gameObjs.get(i);
                    if (currentEntity instanceof Enemy && ((Enemy) currentEntity).getDamage() != 0) {
                        ((Enemy) currentEntity).neutralize();
                        counter++;
                    }
                }
            }

    private void setupInputHandling () {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void setBackgroundImage (Image backgroundImage){
        setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}