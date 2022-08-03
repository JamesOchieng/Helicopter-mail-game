package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import java.io.IOException;
import java.util.*;

/**
 * This is the helicopter class, the user is able to minipulate the helicopter
 * with the included methods
 **/
public abstract  class Helicopter extends MovableObject implements ISteerable,
        IDrawable {

    private  int lastSkyScraperReached = 0;
    private int heading ;
    private static int speed;
    private  int stickAngle = 0;
    private int fuelLevel = 1000;
    private int fuelConsumption = 1;
    private  final int  damageHelicopter = 10;
    private  final int damageBird = 3;
    private static int damageLevel = 0;
    private static int maxSpeed = 25;
    private static int gasUpdate = 0;
    private int color, size;
    private static int gameLife = 3;
    Random rand = new Random();
    private ArrayList<Image> sprites;
    private ArrayList<Image> spritesBlade;    private int frames;
    private static int currFrame = 0;




    /**
     * helicopter constructer, size and color and initated from here
     **/

    public Helicopter(int color, int size){
        super(color, size);
        setMaxSpeed(25);
        setSpeed();
        setHeading();



    }




    /**
     *The methods below are getters and setters for color, size,
     * maxspeed, heading, stick angle, fuel level, fuel consuption rate
     * damege level, lastSkyScraperReached and game life
     **/



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
        System.out.println("life " + gameLife );
    }

    public int getMaxSpeed(){
        return maxSpeed;
    }
    public int getStickAngle(){
        return stickAngle;
    }
    public int getDamageLevel(){
        return damageLevel;
    }
    public void setMaxSpeed(int newSpeed){
         maxSpeed = newSpeed;
    }
    public void setLastSkyScraperReached(int newSky){
        lastSkyScraperReached=+newSky;
    }

    public void setDamageLevel(int newDamage){
        if(newDamage  == 0){
            damageLevel= 0;
        }
        else if ((damageLevel+newDamage) > 99){
            damageLevel = 100;
        }
        else {damageLevel += newDamage;}

        int damage = getDamageLevel() * (damageLevel/100);
        setMaxSpeed(getMaxSpeed() - damage);

    }

    public void setFuelLevel(int newFueLLvl){
        if((newFueLLvl+fuelLevel) > 1000){
            fuelLevel = 1000;
        }
        fuelLevel += newFueLLvl;
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

        speed = rand.nextInt(((maxSpeed - 5) + 5) + 5);
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
            if(gasUpdate == 6){
                fuelLevel -= fuelConsumption;
                gasUpdate=0;
            }
            gasUpdate++;

        }else {
            fuelLevel = 0;
        }



    }
    /**
     * The bottom methods change the direction or the stick angle of the
     * helicpter
     **/
    public void right(){
        setStickAngle(this.getStickAngle() - 5);
        this.setHeading(this.getHeading() + this.getStickAngle());
    }

    public void left(){
        setStickAngle(this.getStickAngle() + 5);
        this.setHeading(this.getHeading() + this.getStickAngle());

    }

    /**
     * The bottom methods accelarate and decelerate the helicopter
     * while adjusting the fuel
     **/

    public void accelerate(){
        if(getSpeed() <= maxSpeed-10 && fuelLevel > 0){
            int curSpeed =  getSpeed() + 10;
            int currfuel = getFuelLvl();
            fuelLevel -= 5;
            super.setSpeed(curSpeed);
            System.out.println("Accelerating by 10");

        }
        else {
            setSpeed(maxSpeed);
            System.out.println("Already at Max Speed");
        }
    }

    public void accelerate(int newspeed){
            setSpeed(newspeed);
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
                + getStickAngle() + " fuelLevel = "  + getFuelLvl() +
                " damageLevel = " + getDamageLevel();
    }

    protected void initSpirit(String spiritPath, int framecount){
        frames = framecount;
        sprites = new ArrayList<>();
        spritesBlade = new ArrayList<>();


        for(int x = 1; x < frames; ++x) {
            String currPath = "/"+spiritPath+x+".png";
            String bladePath = "/rotorBlade"+x+".png";
            try {
                sprites.add(Image.createImage(currPath));
                spritesBlade.add(Image.createImage(bladePath));

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
            g.drawImage(currImage.rotate(getHeading() - 90), xVal, yVal, size,
                    size);

                Image bladeImage = spritesBlade.get(currFrame);
                g.drawImage(bladeImage, xVal, yVal, size,
                        size);

        }catch (Exception e){
            //System.out.println("Can1 draw images");
        }
        ++currFrame;
        currFrame= currFrame % frames;
    }



}
