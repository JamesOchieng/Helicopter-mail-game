package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonHelicopter extends Helicopter implements IDrawable{
    private static int speed = 5;
    private int maxSpeed = 25;
    private int color;
    private int size = 800;
    private String SPRITE_FILE_PATH = "NonPlayerHello";
    private int frameCount = 4;

    public NonHelicopter(){
        super(ColorUtil.rgb(255,0,0 ), 40);
        setSpeed();
        setHeading();
        setSize();
        initSpirit(SPRITE_FILE_PATH, frameCount);

    }

    public  void setSize(){
        size = rand.nextInt(((25 - 10)+5) + 10);
        super.setSize(size);
    }

    public void setSpeed(){
        speed = rand.nextInt(((20 - 5) + 1) + 5);
        super.setSpeed(speed);
    }

    @Override
    public String toString() {
        return "NonHelicopter: loc=" + this.getLocation().getXCoordinate()+","+
                this.getLocation().getYCoordinate() + " "+
                "color= ["  + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) +"] " +
                " size=" + this.getSize() +" heading = " + this.getHeading() + " speed= "
                + this.getSpeed() + " maxSpeed = " + getMaxSpeed() + " " +
                "stickAngle "
                + getStickAngle() + " fuelLevel = "  + getFuelLevel() +
                " damageLevel = " + getDamageLevel();
    }

    /*@Override
    public void draw(Graphics g) {
        //int digitWidth = birdImage.getWidth();
        //int digitHeight = birdImage.getWidth();

        // float scaleFactor = Math.min(getInnerHeight()/(float)digitHeight,
        //getInnerWidth()/(float)digitWidth);

        //int displayDigitWidth = (int)(scaleFactor*digitWidth);
        //int displayDigitHeight = (int)(scaleFactor*digitHeight);
        //int displayBirdWidth = displayDigitWidth;

        //int displayX = getX() + (getWidth() - displayBirdWidth)/2;
        //int displayY = getY() + (getHeight() -displayDigitHeight)/2;

        g.setColor(getColor());
        int xVal = (int)this.getXLocation();
        int yVal = (int)this.getYLocation();
        int[] graphXPoints = {xVal, (xVal - 10), (xVal + 10), xVal};
        int[] graphYPoints = {(yVal + 20), (yVal - 20), (yVal - 20),
                (yVal + 20)};
        g.fillPolygon(graphXPoints, graphYPoints, 4);
        //g.drawImage(birdImage, displayX + displayDigitWidth,displayY,
        //displayDigitWidth, displayDigitHeight);
    }*/


}
