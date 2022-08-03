package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;
import java.util.*;
import java.lang.Math;
/**
 * This is the helicopter class, the user is able to minipulate the helicopter
 * with the included methods
 **/
public class Helicopter extends MovableObject implements ISteerable {

    static int lastSkyScraperReached = 1;
    private int heading ;
    private static int speed = 50;
    private static int stickAngle = 0;
    private int fuelLevel = 100;
    private int fuelConsumption = 10;
    private static int damageLevel = 0;
    private int maxSpeed = 100;
    private int color, size;
    private static int gameLife = 3;
    Random rand = new Random();


    private double xLocat,yLocat;

    /**
     * helicopter constructer, size and color and initated from here
     **/

    public Helicopter(){
        setSize();
        setColor();
        setSpeed(speed);
        setHeading();



    }
    /**
     *The methods below are getters and setters for color, size,
     * maxspeed, heading, stick angle, fuel level, fuel consuption rate
     * damege level, lastSkyScraperReached and game life
     **/
    public void setLastSkyScraperReached(int newSkraperReached){
        lastSkyScraperReached = newSkraperReached;
    }

    public int getLastSkyScraperReached(){
        return lastSkyScraperReached;
    }

    public int getFuelConsumption(){
        return fuelConsumption;
    }

    public int getFuelLvl(){
        return fuelLevel;
    }

    public  int getHeading(){
        return  heading;
    }
    public int getSpeed(){ return  speed; }
    public int getMaxSpeed(){
        return maxSpeed;
    }
    public int getStickAngle(){
        return stickAngle;
    }
    public int getFuelLevel(){
        return fuelLevel;
    }
    public int getDamageLevel(){
        return damageLevel;
    }


    public void setSize() {
        this.size = 40;
    }


    public void setSize(int initSize) {
        this.size = initSize;
    }


    public int getSize() {

        return size;
    }


    public void setLocation(double x, double y) {
        this.xLocat = x;
        this.yLocat = y;
        super.setLocation(new Coordinates(x,y));

    }

    public void setDamageLevel(int newDamage){
        damageLevel = newDamage;
    }

    public void setFuelLevel(int newFueLLvl){
        fuelLevel = newFueLLvl;
    }


    public Coordinates getLocation() {

        return new Coordinates(xLocat,yLocat);
    }


    public void setColor() {

        color = ColorUtil.rgb(255,0,0 );
    }

    public void setSpeed(int newSpeed){

        speed = newSpeed;
    }
    public void setHeading(){
        heading = rand.nextInt((359 - 0) + 1) ;
        //setHeading(heading);
    }

    public void changeColor() {
        int damage = ColorUtil.red(getColor() - 28);
        color= ColorUtil.rgb(damage,0,0);
    }


    public int getColor() {
        return color;
    }

    /**
     * The move method, updats the helicopter location as the time changes
     **/
    public void move() {

        double currentX = super.getLocation().getXCoordinate();
        double currentY = super.getLocation().getYCoordinate();
        if(fuelLevel > fuelConsumption && damageLevel < 100) {
            Coordinates newLocation;
            double theta = Math.toRadians(90 - getHeading());
            double deltaX = Math.cos(theta) * getSpeed();
            double deltaY = Math.sin(theta) * getSpeed();
            deltaX = Math.round(deltaX*10.0)/10.0;
            deltaY = Math.round(deltaY*10.0)/10.0;

            fuelLevel -= fuelConsumption;
            newLocation=new Coordinates(currentX + deltaX,currentY + deltaY);
            super.setLocation(newLocation);
        }else {
            fuelLevel = 0;
        }



    }
    /**
     * The bottom methods change the direction or the stick angle of the
     * helicpter
     **/
    public void right(){
        stickAngle += 5;

    }

    public void left(){
        stickAngle -= 5;

    }

    /**
     * The bottom methods accelarate and decelerate the helicopter
     * while adjusting the fuel
     **/

    public void accelerate(){
        if(getSpeed() <= maxSpeed-10 && fuelLevel > 0){
            int curSpeed =  getSpeed() + 10;
            int currfuel = getFuelLevel();
            setFuelLevel((currfuel - fuelConsumption));

            super.setSpeed(curSpeed);
            setSpeed(curSpeed);
            System.out.println("Accelerating by 10");

        }
        else {
           setSpeed(maxSpeed);
            System.out.println("Already at Max Speed");
        }
    }

    public void decelerate(){
        if(getSpeed() > 0 && fuelLevel > 0){
        int curSpeed =  getSpeed() - 10;
        super.setSpeed(curSpeed);
        setSpeed(curSpeed);
        System.out.println(super.getSpeed() + "speed");
        System.out.println("Decelerating....");
            System.out.println(getSpeed() );
        }
        else {
            setSpeed(0);
            System.out.println("Full Stop.");
        }
    }
    public int collision(){
        if(damageLevel < 90){
            damageLevel += 10;
        }
        else {
            damageLevel = 100;
            speed = 0;
            gameLife--;

            if(gameLife == 0){
                return -8;
            }
            else {
                return -1;
            }
        }
        System.out.println("Collision with helicopter");
        changeColor();
        return 8;
    }
    /**
     * The bottom four methods similute a collision with different objects.
     * each colision has a different affect on the helicopter
     *
     * The bird collision damages the helicopter slightly
     **/
    public int birdcollision(){
        if(damageLevel < 90){
            damageLevel += 5;
        }
        else {
            damageLevel = 100;
            speed = 0;
            gameLife--;
            if(gameLife == 0){
                return -8;
            }
            else {
                return -1;
            }

        }
        changeColor();
        System.out.println("Collision with bird ");
        return 8;
    }
    /**
     * The refuel blimp refuels the helicopter when the two objects collide
     **/
    public void refuelFromBlimp(int currFuel){
        fuelLevel = currFuel;
        System.out.println("Getting fuel from blimp " + fuelLevel);

    }
    /**
     * The sky scraper collision, updates the the helicopters past visit
     **/
    public void collideWithSkyScrapr(int seqNum){
        System.out.println(lastSkyScraperReached + "  " + seqNum);
        if(lastSkyScraperReached+1 == seqNum){
            lastSkyScraperReached = seqNum;
            System.out.println("Arrived at the correct sky scraper");

        }
        else {
            System.out.println("Arrived at the wrong sky scraper");
        }

    }
    /**
     * toString prints all the helicopters attributes
     **/
    public String toString(){
        return "Helicopter: loc=" + getLocation().getXCoordinate()+","+
                getLocation().getYCoordinate() + " "+
                "color= ["  + ColorUtil.red(getColor()) + ","
                + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) +"] " +
                " size=" + getSize() +" heading = " + getHeading() + " speed= "
                + getSpeed() + " maxSpeed = " + getMaxSpeed() + " " +
                "stickAngle "
                + getStickAngle() + " fuelLevel = "  + getFuelLevel() +
                " damageLevel = " + getDamageLevel();
    }




}
