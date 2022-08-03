package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;
/**
 * The bird class will construct birds with random location with ser color
 **/
public class Bird extends MovableObject{
    private int size;
    private Coordinates location;
    private int color;

    public Bird(){
        super.setHeading((int) (Math.random()*(359 - 0) + 0));
        super.setSpeed((int) (Math.random()*(10 - 5) + 5));
        setLocation();
        setColor();

    }


    public  void setSize(){
        size = 40;
    };

    public  int getSize(){return size; };

    public  void setLocation(){
        double xLocat = Math.random()*(1024.0 - 0.0) + 0.0;
        double yLocat = Math.random()*(768.0 - 0.0) + 0.0;
        xLocat  = Math.round(xLocat*10.0)/10.0;
        yLocat = Math.round(yLocat*10.0)/10.0;
        location = new Coordinates(xLocat,yLocat);
    }
    /**
     * The three methods below are getters and setters for size,
     * color, and location
     **/
    public  Coordinates getLocation(){
        return location;
    }

    public void setColor() {
        color = ColorUtil.rgb(215,0,8 );
    }

    public int getColor() {
        return color;
    }

    /**
     * The move method updates the bird location since the bird cant be
     * moved by the player
     **/
    public void move(){

        Random rand = new Random();
        if(rand.nextBoolean()  == true){
            heading += 5;
        }
        else {
            heading -= 5;
        }
        double currentX = getLocation().getXCoordinate();
        double currentY = getLocation().getYCoordinate();
            Coordinates newLocation;
            double theta = Math.toRadians(90 - heading);
            double deltaX = Math.cos(theta) * super.getSpeed();
            double deltaY = Math.sin(theta) * super.getSpeed();
            deltaX = Math.round(deltaX*10.0)/10.0;
            deltaY = Math.round(deltaY*10.0)/10.0;

            newLocation=new Coordinates(currentX + deltaX,currentY + deltaY);
            super.setLocation(newLocation);


    }
    /**
     * toString prints all the helicopters attributes
     **/
    public String toString(){
        return "Bird: loc=" + getLocation().getYCoordinate()+","+
                getLocation().getYCoordinate()+" "+
                "color= ["  + ColorUtil.red(getColor()) + ","
                + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) +"] " +
                "size=" + getSize() +" heading =" + getHeading() + "speed= "
                + getSpeed();
    }
}