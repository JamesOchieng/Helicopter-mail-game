package org.csc133.a1;
/**
 * Coordinate is the equivalent of a Pint
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
}
