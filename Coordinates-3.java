package org.csc133.a3;

import java.awt.*;

/**
 * Coordinate is the equivalent of a Point
 * it the program to pass two variables as parameters.
 * this is how we hold x and y intercepts
 **/
public class Coordinates {
    private double xCoordinate;
    private double yCoordinate;

    public Coordinates(double x , double y)
    {
        setXCoordinate(x);
        setYCoordinate(y);
    }

    public Coordinates()
    {
        setXCoordinate(0);
        setYCoordinate(0);
    }

    public Coordinates(Coordinates newCord)
    {
        setXCoordinate(newCord.xCoordinate);
        setYCoordinate(newCord.yCoordinate);
    }

    public void setXCoordinate(double x){
        xCoordinate = x;
    }

    public void setYCoordinate(double y){
        yCoordinate = y;
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public Coordinates getCoordinate(){
        return new Coordinates(getXCoordinate(),getYCoordinate());
    }
    public double getXStartCord(int size){
        double startcord = getXCoordinate() - (size/2);
        return startcord;
    }

    public double getYStartCord(int size){
        double startcord = getYCoordinate() - (size/2);
        return startcord;
    }

    public  Coordinates coordiDifference(Coordinates temp1, Coordinates temp2){

        double xDifference = temp1.xCoordinate - temp2.xCoordinate;
        double yDifference = temp1.yCoordinate - temp2.yCoordinate;

        return new Coordinates(xDifference, yDifference);
    }
}