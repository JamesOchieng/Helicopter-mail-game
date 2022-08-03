package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Sky scrapers have there location chosen manually when
 * created
 **/
public class SkyScraper extends FixedObject implements IDrawable {

    private int size, color;
    private static int counter = 0;
    private double xLocat,yLocat;
    private String SPRITE_FILE_PATH = "skyscrapper";
    private int frameCount = 10;
    private int beenReached = 0;
    private Sound collisionSkySound = new Sound("/helitosky.wav");
    private final int sequencNum;

    public SkyScraper(String scrappercount, int sequencNum){
        super(ColorUtil.rgb(255,0,255 ), 8);
        counter++;
        this.sequencNum = sequencNum;
        initSpirit(scrappercount);
    }



    /**
     * All the methods below are getters and setters for color, size, location
     * and sequence number
     **/






    public  void setLocation(double x, double y){
        Coordinates location = new Coordinates(x,y);
        super.setLocation(location);
    }


    public int getSequencNum(){
        return sequencNum;
    }
    /**
     * toString prints all the helicopters attributes
     **/
    public String toString(){
        return "SkyScrapper: loc=" + this.getLocation().getYCoordinate()+","+
                this.getLocation().getYCoordinate()+" "+
                "color= ["  + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) +"] " +
                "size=" + this.getSize() + " " +
                "seqNum=" + getSequencNum();
    }


    @Override
    public void collideWithObject(GameObject otherObject) {

    }
}
