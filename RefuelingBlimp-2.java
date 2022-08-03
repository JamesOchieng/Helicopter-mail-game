package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;
/**
 * The refueling blimp extends to FixedObjects.
 **/
public class RefuelingBlimp extends FixedObject implements IDrawable{

    private int capacity;
    private int size, color;
    private double xLocat,yLocat;
    Random rand = new Random();
    private String SPRITE_FILE_PATH = "refuelblimp";
    private int frameCount = 3;

    /**
     * Each loction of the blimp is randomly chosens
     **/
    public RefuelingBlimp(){
        super(ColorUtil.rgb(26,12,78 ));
        //this.xLocat = Math.random()*(1024.0 - 0.0) + 0.0;
        //this.yLocat = Math.random()*(768.0 - 0.0) + 0.0;
        setCapacity();
        setSize();
        initSpirit(SPRITE_FILE_PATH, frameCount);
    }

    /**
     * The methods below are getters and setters for size, color, location,
     * capacity,
     **/
    public void setSize() {
        this.size = rand.nextInt((25 - 10) + 1) + 10;
        super.setSize(size);
    }



    public void setEmptyCapacity() {
        color = ColorUtil.rgb(18,247,43 );
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
        return "RefuelingBlimp: loc=" + this.getLocation().getXCoordinate()+","+
                this.getLocation().getYCoordinate()+" "+
                "color= ["  + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) +"] " +
                "size=" + this.getSize() + " " +
                "capacity=" + getCapacity();
    }


}
