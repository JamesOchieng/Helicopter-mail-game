package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.*;

/**
 * This is the helicopter class, the user is able to minipulate the helicopter
 * with the included methods
 **/
public abstract  class Helicopter extends MovableObject implements ISteerable,
        IDrawable {

    private static int lastSkyScraperReached = 1;
    private int heading ;
    private static int speed = 5;
    private  int stickAngle = 0;
    private int fuelLevel = 1000;
    private int fuelConsumption = 1;
    private static int damageLevel = 0;
    private int maxSpeed = 25;
    private int color, size;
    private static int gameLife = 3;
    Random rand = new Random();
    private ArrayList<Image> sprites;
    private int frames;
    private static int currFrame = 0;

    /**
     * helicopter constructer, size and color and initated from here
     **/

    public Helicopter(int color, int size){
        super(color, size);
        setSpeed();
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


    public int getGameLife(){return gameLife;}
    public int getLastSkyScraperReached(){
        return lastSkyScraperReached;
    }

    public int getFuelConsumption(){
        return fuelConsumption;
    }

    public int getFuelLvl(){
        return fuelLevel;
    }

    public void setGameLife(int newgameLife) {
        gameLife = newgameLife;
    }

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


    public void setDamageLevel(int newDamage){
        damageLevel = newDamage;
    }

    public void setFuelLevel(int newFueLLvl){
        fuelLevel = newFueLLvl;
    }

    public  void setLocation(double x, double y){
        Coordinates location = new Coordinates(x,y);
        super.setLocation(location);
    }

    public void resetDamageLvl(){
        damageLevel = 0;
    }
    public void setStickAngle(int stickAngle){
        if (stickAngle <= -40) {
            this.stickAngle = -40;
        } else if (stickAngle >= 40) {
            this.stickAngle = 40;
        } else {
            this.stickAngle = stickAngle;
        }
    }

    public void setSpeed(){

        super.setSpeed(speed);
    }
    public void setHeading(){
        heading = rand.nextInt((359 - 0) + 1) ;
        super.setHeading(heading);
    }

    public void changeColor() {
        int damage = ColorUtil.red(getColor() - 28);
        color= ColorUtil.rgb(damage,0,0);
        super.setColor(color);
    }



    /**
     * The move method, updats the helicopter location as the time changes
     *
     * @param GAME_TICK
     * */
    public void move(int GAME_TICK, int min, int max) {


        if(fuelLevel > fuelConsumption && damageLevel < 100) {
            super.move(GAME_TICK, min, max);
            fuelLevel -= fuelConsumption;


        }else {
            fuelLevel = 0;
            System.out.println("Current Helicopter is out of fuel.");
        }



    }
    /**
     * The bottom methods change the direction or the stick angle of the
     * helicpter
     **/
    public void right(){
        setStickAngle(this.getStickAngle() - 5);
        this.setHeading(this.getHeading() + this.getStickAngle());
        System.out.println(getHeading());
    }

    public void left(){
        setStickAngle(this.getStickAngle() + 5);
        this.setHeading(this.getHeading() + this.getStickAngle());
        System.out.println(getHeading());

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
        return "Helicopter: loc=" + this.getLocation().getXCoordinate()+","+
                this.getLocation().getYCoordinate() + " "+
                "color= ["  + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) +"] " +
                " size=" + this.getSize() +" heading = " + this.getHeading() + " speed= "
                + this.getSpeed() + " maxSpeed = " + getMaxSpeed() + " " +
                "stickAngle "
                + getStickAngle() + " fuelLevel = "  + getFuelLevel() +
                " damageLevel = " + getDamageLevel();
    }

    protected void initSpirit(String spiritPath, int framecount){
        frames = framecount;
        sprites = new ArrayList<>();

        for(int x = 1; x < frames; ++x) {
            String currPath = "/"+spiritPath+x+".png";

            try {
                sprites.add(Image.createImage(currPath));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("could not create image from " + currPath);
                System.exit(1);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(ColorUtil.rgb(50, 50, 78));
        int xVal = (int) getLocation().getXStartCord(getSize());
        int yVal = (int) getLocation().getYStartCord(getSize());
        int size = getSize();

        try {
            Image currImage = sprites.get(currFrame);
            g.drawImage(currImage.rotate(90-heading), xVal, yVal, size,
                    size);
        }catch (Exception e){
            System.out.println("Can draw images");
        }
        ++currFrame;
        currFrame%=frames;
    }

}
