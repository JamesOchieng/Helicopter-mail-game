package org.csc133.a2;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Movable and fixed object classes are extending to GameObject
 **/
public abstract class GameObject extends Component {
    private int size;
    private Coordinates location;
    private int color;


    /**
     * The methods below are getters and setters foe size
     * location, and color
     **/

    public GameObject(int color){
        this.color = color;
        setLocation();
    }

    public GameObject(int color, int size){
        this.color = color;
        this.size = size;
        setLocation();
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
        double yLocat = Math.random()*(1335.0 - 250.0) + 250.0;
        xLocat  = Math.round(xLocat*10.0)/10.0;
        yLocat = Math.round(yLocat*10.0)/10.0;
        location = new Coordinates(xLocat,yLocat);
    }

    public  Coordinates getLocation(){
        return location;
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


}