package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;
/**
 * Sky scrapers have there location chosen manually when
 * created
 **/
class SkyScraper extends FixedObject{

    private int size, color;
    static int counter;
    private double xLocat,yLocat;

    int sequencNum;

    public SkyScraper(){
        setSize(8);
        setColor();
        setSequencNume();
    }

    /**
     * All the methods below are getters and setters for color, size, location
     * and sequence number
     **/
    public void setSize(int initSize) {
        size = initSize;
    }




    public int getSize() {
        return size;
    }


    public void setLocation(double x, double y) {
        this.xLocat = x;
        this.yLocat = y;

    }


    public Coordinates getLocation() {
        return new Coordinates(xLocat,yLocat);
    }


    public void setColor() {
        color = ColorUtil.rgb(255,0,255 );

    }

    public int getColor(){
        return color;
    }


    public void changeColor() { }

    public void setSequencNume(){
        sequencNum = counter;
        counter++;
    }

    public int getSequencNum(){
        return sequencNum;
    }
    /**
     * toString prints all the helicopters attributes
     **/
    public String toString(){
        return "SkyScrapper: loc=" + getLocation().getYCoordinate()+","+
                getLocation().getYCoordinate()+" "+
                "color= ["  + ColorUtil.red(getColor()) + ","
                + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) +"] " +
                "size=" + getSize() + " " +
                "seqNum=" + getSequencNum();
    }

}
