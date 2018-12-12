package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Display;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

import javax.xml.transform.Result;
import java.util.Optional;


public class Snake implements Animatable {
    private static final float speed = 2;
    private static final int maxhealth = 100;
    private int health = 100;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
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

    public void changeHealth(int diff) {
        health += diff;
    }

    public void resetHealth() {
        health = maxhealth;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            displayAlert();
        }
    }

    public void displayAlert() {

     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER!");
        alert.setHeaderText("Your score is: ");
        alert.setContentText("Start a new game?");

        Platform.runLater(() -> {
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                System.out.println("testing new game");
                newGame();
            }
            else if(result.get() == ButtonType.CANCEL) {
                System.out.println("testing exit game");
                Platform.exit();
            }
        });

    }

    public void newGame() {
        System.out.println(health);
        Globals.getInstance().display.clear();
        Globals.getInstance().game.init();
        Globals.getInstance().game.start();
        resetHealth();
        System.out.println(health);
    }


    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
