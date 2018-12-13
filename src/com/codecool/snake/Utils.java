package com.codecool.snake;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static Point2D createHeadPointer(Vec2d headPosition){
        double headX = headPosition.x;
        double headY = headPosition.y;
        Point2D headPointer = new Point2D(headX,headY);
        return headPointer;
    }

    public static boolean doesEventHappen(double probability) {
        return Math.random() > 1 - probability;
    }

    public static int getRandomWithin(int min, int max) {
        if (max < min) {
            int y = min;
            min = max;
            max = y;
        }

        return (int) Math.round(Math.random() * (max - min) + min);
    }
}
