package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("MyEnemy", new Image("my_enemy.png"));
        resources.addImage("TitanBackground", new Image("background_titan.jpg"));
        resources.addImage("SoulStone", new Image("infinity_stones/soul_stone_20x30.png"));
        resources.addImage("PowerStone", new Image("infinity_stones/power_stone_20x30.png"));
        resources.addImage("SpaceStone", new Image("infinity_stones/space_stone_20x30.png"));
        resources.addImage("TimeStone", new Image("infinity_stones/time_stone_20x30.png"));
        resources.addImage("RealityStone", new Image("infinity_stones/reality_stone_20x30.png"));
        resources.addImage("MindStone", new Image("infinity_stones/mind_stone_20x30.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
