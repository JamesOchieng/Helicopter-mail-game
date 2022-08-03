package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;
/**
 * The refueling blimp extends to FixedObjects.
 **/
public class RefuelingBlimp extends FixedObject{

    private int capacity;
    private int size, color;
    private double xLocat,yLocat;
    /**
     * Each loction of the blimp is randomly chosens
     **/
    public RefuelingBlimp(){
        setSize();
        //this.xLocat = Math.random()*(1024.0 - 0.0) + 0.0;
        //this.yLocat = Math.random()*(768.0 - 0.0) + 0.0;
        setLocation();
        setCapacity();
        setColor();
    }

    /**
     * The methods below are getters and setters for size, color, location,
     * capacity,
     **/
    public void setSize() {
        Random rand = new Random();
        this.size = rand.nextInt((25 - 10) + 1) + 10;
    }


    public void setSize(int initSize) {
    }

    public int getSize() {
        return size;
    }

    public void setLocation(double x, double y) {


        this.xLocat = Math.random()*(1024.0 - 0.0) + 0.0;
        this.yLocat = Math.random()*(768.0 - 0.0) + 0.0;
        this.xLocat  = Math.round(xLocat*10.0)/10.0;
        this.yLocat = Math.round(yLocat*10.0)/10.0;

    }

    public void setLocation() {

        this.xLocat = Math.random()*(1024.0 - 0.0) + 0.0;
        this.yLocat = Math.random()*(768.0 - 0.0) + 0.0;
        this.xLocat  = Math.round(xLocat*10.0)/10.0;
        this.yLocat = Math.round(yLocat*10.0)/10.0;

    }

    public Coordinates getLocation() {

        return new Coordinates(xLocat,yLocat);
    }

    public void setColor() {
        color = ColorUtil.rgb(26,12,78 );
    }

    public void setEmptyCapacity() {
        color = ColorUtil.rgb(18,247,43 );
    }

    public void changeColor() {
        color = ColorUtil.rgb(18,47,43 );
    }

    public int getColor(){
        return color;
    }

    public void setCapacity(){
        capacity = size;
    }

    public void setCapacity(int newCapacity){
        capacity = newCapacity;
    }

    public int getCapacity(){
        return capacity;
    }
    /**
     * After a collision with a helicopter, the refuelHeli
     * sets the capacity of the blimp to zero
     **/
    public int refuelHeli(){
        int currFuel = getCapacity();
        setCapacity(0);
        changeColor();
        return currFuel;
    }
    /**
     * toString prints all the RefuelingBlimp attributes
     **/
    public String toString(){
        return "RefuelingBlimp: loc=" + getLocation().getXCoordinate()+","+
                getLocation().getYCoordinate()+" "+
                "color= ["  + ColorUtil.red(getColor()) + ","
                + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) +"] " +
                "size=" + getSize() + " " +
                "capacity=" + getCapacity();
    }
}
