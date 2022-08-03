package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Image;


import java.io.IOException;
import java.util.Random;
/**
 * The bird class will construct birds with random location with ser color
 **/
public class Bird extends MovableObject implements IDrawable{
    private int size = 70;
    private int heading ;
    private int speed;
    private boolean selected;
    private Coordinates location;
    Image birdImage;
    private String SPRITE_FILE_PATH = "birdPhase";
    private int frameCount = 3;

    Random rand = new Random();

    public Bird() {
        super(ColorUtil.BLACK);
        setSpeed();
        setHeading();
        setSize();
        selected = false;
        initSpirit(SPRITE_FILE_PATH, frameCount);

    }




    /**
     * The three methods below are getters and setters for size,
     * color, and location
     **/

    public  void setSize(){
        super.setSize(size);
    }

    public void setSpeed(){
        speed = rand.nextInt(((20 - 5) + 1) + 5);
        super.setSpeed(speed);
    }

    public void setHeading(){
        heading = rand.nextInt((359 - 0) + 1);
        super.setHeading(heading);
    }


    /**
     * The move method updates the bird location since the bird cant be
     * moved by the player
     *
     * @param GAME_TICK
     * @param minMapLoc
     * @param maxMapLoc*/
    public void move(int GAME_TICK, int minMapLoc, int maxMapLoc){

        Random rand = new Random();
        int headingadd = 0;
        int headingRand = rand.nextInt(5);
        if(headingRand == 4){
            headingadd = 10;
            System.out.println("update 1 headingAdd");

        }else if(headingRand == 0){
            headingadd = -10;
            System.out.println("update 2 headingAdd");
        }

        setHeading(getHeading() + headingadd);
        super.move(GAME_TICK,minMapLoc,maxMapLoc);
        //System.exit(0);


    }
    /**
     * toString prints all the helicopters attributes
     **/
    public String toString(){
        return "Bird: loc=" + getLocation().getXCoordinate()+","+
                getLocation().getYCoordinate()+" "+
                "color= ["  + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) +"] " +
                "size=" + this.getSize() +" heading = " + this.getHeading() + " speed= "
                + this.getSpeed();
    }

   /* @Override
    public void draw(Graphics g, Point pCmpRelPrint) {

        int digitWidth = birdImage.getWidth();
        int digitHeight = birdImage.getWidth();

        float scaleFactor = Math.min(getInnerHeight()/(float)digitHeight,
                getInnerWidth()/(float)digitWidth);

        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayBirdWidth = displayDigitWidth;

        int displayX = getX() + (getWidth() - displayBirdWidth)/2;
        int displayY = getY() + (getHeight() -displayDigitHeight)/2;

        g.setColor(getColor());
        int xVal = (int)this.getXLocation()+ pCmpRelPrint.getX();
        int yVal = (int)this.getYLocation()+ pCmpRelPrint.getY();
        int[] graphXPoints = {xVal, (xVal - 10), (xVal + 10), xVal};
        int[] graphYPoints = {(yVal + 20), (yVal - 20), (yVal - 20),
                (yVal + 20)};
        g.fillPolygon(graphXPoints, graphYPoints, 4);
        //g.drawImage(birdImage, displayX + displayDigitWidth,displayY,
                //displayDigitWidth, displayDigitHeight);




    }*/
}