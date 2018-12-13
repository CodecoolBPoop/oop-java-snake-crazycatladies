package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

import java.util.Optional;

import static java.lang.StrictMath.round;


public class Snake implements Animatable {
    private static float speed = 2;
    private static final int maxHealth = 100;
    private static final double startingScore = 0;
    private static final double SCORE_PER_STEP = 0.1;

    private int health = 100;
    private double score = 0;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;

    public int getHealth() {
        return health;
    }

    public double getScore() {
        return round(score);
    }

    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);
        changeScore(SCORE_PER_STEP);
        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    public Vec2d getHeadPosition() {
        return head.getPosition();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeScore(double diff) {
        score += diff;
    }

    public void changeHealth(int diff) {
        health += diff;
        System.out.println("Snake health: " + health);
    }

    public void changeSpeed(double diff) {
        speed += diff;
        System.out.println("Snake speed: " + speed);
    }

    public void resetScore() {
        score = startingScore;
    }

    public void resetHealth() {
        health = maxHealth;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            displayAlert();
        }
    }

    public void displayAlert() {
    
        Globals.getInstance().display.clear();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("GAME OVER!");
        alert.setHeaderText("Your score is: " + round(score));
        alert.setContentText("Start a new game?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        Platform.runLater(() -> {
            System.out.println(score);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("testing new game");
                newGame();
            } else if (result.get() == ButtonType.CANCEL) {
                System.out.println("testing exit game");
                Platform.exit();
            }
        });

    }

    public void newGame() {
        Globals.getInstance().display.clear();
        Globals.getInstance().game.init();
        Globals.getInstance().game.start();
        resetHealth();
        resetScore();
    }


    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            if (prev instanceof SnakeHead) {
                currentPart.setPosition(((SnakeHead) prev).getAccessPoint());
                prev = currentPart;
            } else {
                currentPart.setPosition(prev.getPosition());
                prev = currentPart;
            }
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }
}
