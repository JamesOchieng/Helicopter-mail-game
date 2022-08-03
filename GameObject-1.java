package org.csc133.a1;
/**
 * Movable and fixed object classes are extending to GameObject
 **/
public abstract class GameObject {
    private int size;
    private Coordinates location;
    private int color;


    /**
     * The methods below are getters and setters foe size
     * location, and color
     **/
    public  void setSize(int initSize){
        size = initSize;
    }

    public  int getSize(){return size; }

    public  void setLocation(Coordinates newLocation){
        location = newLocation;
    }

    public  Coordinates getLocation(){
        return location;
    }

    public  void setColor(){

    }

    public  void changeColor(){

    }
}
