package org.csc133.a1;
/**
 * The movable class extends to GameObject, it hold speed and
 * heading. There getter and setter methods
 **/
public abstract class MovableObject extends GameObject{
    static int heading;
    static int speed;




    public int getHeading(){
        return heading;
    }

    public int getSpeed(){
        return  speed;
    }

    public void setHeading(int newHeading){
        this.heading = newHeading;

    }

    public void setSpeed(int newSpeed){
        this.speed = newSpeed;
    }
    public void move(){

    }

}
