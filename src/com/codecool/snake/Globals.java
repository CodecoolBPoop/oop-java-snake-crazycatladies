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
        resources.addImage("SnakeHead", new Image("inf_gauntlet.png"));
        resources.addImage("SnakeBody", new Image("body.png"));
        resources.addImage("IronMan", new Image("enemies/iron_man.gif"));
        resources.addImage("DoctorStrange", new Image("enemies/doctor_strange.png"));
        resources.addImage("StarLord", new Image("enemies/starLord.png"));
        resources.addImage("SpiderMan", new Image("enemies/spider_man.png"));
        resources.addImage("AmericanCaptain", new Image("enemies/american_captain.png"));
        resources.addImage("TitanBackground", new Image("background_titan.jpg"));
        resources.addImage("SoulStone", new Image("infinity_stones/soul_stone_20x30.png"));
        resources.addImage("PowerStone", new Image("infinity_stones/power_stone_20x30.png"));
        resources.addImage("SpaceStone", new Image("infinity_stones/space_stone_20x30.png"));
        resources.addImage("TimeStone", new Image("infinity_stones/time_stone_20x30.png"));
        resources.addImage("RealityStone", new Image("infinity_stones/reality_stone_20x30.png"));
        resources.addImage("MindStone", new Image("infinity_stones/mind_stone_20x30.png"));
        resources.addImage("Bubble", new Image("Bubble_40x40.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }

}
