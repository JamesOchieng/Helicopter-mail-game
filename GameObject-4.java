package org.csc133.a3;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.HashMap;

/**
 * Movable and fixed object classes are extending to GameObject
 **/
public abstract class GameObject implements ICollider {
    private int size;
    private Coordinates location;
    private int color;
    private HashMap<GameObject, Long> collidedList;
    private  final int collisiontime = 5000;


    /**
     * The methods below are getters and setters foe size
     * location, and color
     **/

    public GameObject(int color){
        this.color = color;
        setLocation();
        collidedList = new HashMap<>();
    }

    public GameObject(int color, int size){
        this.color = color;
        this.size = size;
        setLocation();
        collidedList = new HashMap<>();

    }

    public  void setSize(int initSize){
        size = initSize;
    }

    public  int getSize(){return size; }

    public  void setLocation(Coordinates newLocation){
        this.location = new Coordinates(newLocation);
    }

    public  void setLocation( double xLocat, double yLocat){
        location = new Coordinates(xLocat,yLocat);    }

    public void setLocation(){
        double xLocat = Math.random()*(2048.0 - 0.0) + 0.0;
        double yLocat = Math.random()*(1135.0 - 250.0) + 250.0;
        xLocat  = Math.round(xLocat*10.0)/10.0;
        yLocat = Math.round(yLocat*10.0)/10.0;
        location = new Coordinates(xLocat,yLocat);
    }

    public  Coordinates getLocation(){
        return this.location;
    }

    public  double getXLocation(){
        return location.getXCoordinate();
    }

    public  double getYLocation(){
        return location.getYCoordinate();
    }

    public  void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return  color;
    }

    public  void changeColor(){

    }

    @Override
    public void handleCollision(GameObject otherObject) {
        long timemilli;
        if (collidedList.get(otherObject) != null &&
                (System.currentTimeMillis() - collidedList.get(otherObject)) <= collisiontime)
        {
            //System.out.println("handle 1 ");

           return;
        }else
            //System.out.println("handle 2 ");
        timemilli = System.currentTimeMillis();
        collidedList.put(otherObject, timemilli);
        otherObject.collideWithObject(this);

    }

    public abstract void collideWithObject(GameObject otherObject);

    @Override
    public boolean collidesWith(GameObject otherObject){
        double currObjx = this.getLocation().getXCoordinate();
        double currObjy = this.getLocation().getYCoordinate();

        double otherObjectX = otherObject.getLocation().getXCoordinate();
        double otherObjectY = otherObject.getLocation().getYCoordinate();

        double xDistance = Math.abs(currObjx - otherObjectX);
        double yDistance = Math.abs(currObjy - otherObjectY);

        if(xDistance < (this.getSize()/2)+(otherObject.getSize()/2) &&
                yDistance < (this.getSize()/2)+(otherObject.getSize()/2)){

            return true;
        }
        else
            return false;
    }


}