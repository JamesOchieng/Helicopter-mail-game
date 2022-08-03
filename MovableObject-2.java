package org.csc133.a2;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The movable class extends to GameObject, it hold speed and
 * heading. There getter and setter methods
 **/
public abstract class MovableObject extends GameObject implements IDrawable{
    private int heading;
    private int speed;
    private GameWorld gameWorld;
    private ArrayList<Image> sprites;
    private int frames;
    private static int currFrame = 0;





    public MovableObject(int color){
        super(color);
    }

    public MovableObject(int color, int size){
        super(color, size);
    }

    public int getHeading(){
        return heading;
    }

    public int getSpeed(){
        return  speed;
    }

    protected void initSpirit(String spiritPath, int framecount){
        frames = framecount;
        sprites = new ArrayList<>();

        for(int x = 1; x < frames; ++x) {
            String currPath = "/"+spiritPath+x+".png";

            try {
                sprites.add(Image.createImage(currPath));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("could not create image from " + currPath);
                System.exit(1);
            }
        }
    }

    protected void initSpirit(String spiritPath){
        sprites = new ArrayList<>();


        String currPath = "/"+spiritPath+".png";

        try {
            sprites.add(Image.createImage(currPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not create image from " + currPath);
            System.exit(1);
        }

    }

    public void setHeading(int newHeading){
        while (newHeading >= 360){
            newHeading -= 360;
        }
        while (newHeading < 0){
            newHeading += 360;
        }
        this.heading = newHeading;

    }
    public void setSpeed(int newSpeed){
        this.speed = newSpeed;
    }


    public void move(int GAME_TICK, int height, int width) {

        //System.out.println("this is maps max x " + max.getXCoordinate());
        // System.out.println("this is maps max y " + max.getYCoordinate());

        // System.out.println("this is maps min x " + min.getXCoordinate());
        //System.out.println("this is maps min y " + min.getYCoordinate());

        double rate = Math.round(GAME_TICK/100L);
        double currentX = this.getLocation().getXCoordinate();
        double currentY = this.getLocation().getYCoordinate();
        Coordinates newLocation;

        double theta = Math.toRadians(heading);

        double deltaX = Math.cos(theta) * getSpeed() * rate;
        double deltaY = Math.sin(theta) * getSpeed() * rate;
        // deltaX = Math.round(deltaX*10.0)/10.0;
        //deltaY = Math.round(deltaY*10.0)/10.0;
        newLocation=new Coordinates(currentX + deltaX, currentY + deltaY);

        if(newLocation.getXCoordinate() < 0)
        {
            int stickAngle = 180 + heading;
            if(stickAngle > 360){
                stickAngle = stickAngle-360;
            }
            heading =stickAngle;
            System.out.println("move1");
            System.out.println("newLocation.getXCoordinate " +newLocation.getXCoordinate());


        }
        if(newLocation.getXCoordinate() > width ){
            int stickAngle = 180 + heading;
            if(stickAngle > 360){
                stickAngle = stickAngle-360;
            }
            heading =stickAngle;
            System.out.println("move2");
            System.out.println("newLocation.getXCoordinate " +newLocation.getXCoordinate());
            //System.out.println("max.getXCoordinate() " +max.getXCoordinate());



        }
        if(newLocation.getYCoordinate() < 250)
        {
            int stickAngle = 180 + heading;
            if(stickAngle > 360){
                stickAngle = stickAngle-360;
            }
            heading =stickAngle;
            System.out.println("move3");
            System.out.println("newLocation.getYCoordinate() " + newLocation.getYCoordinate());


        }
        if(newLocation.getYCoordinate() > (height+230) ){
            int stickAngle = 180 + heading;
            if(stickAngle > 360){
                stickAngle = stickAngle-360;
            }
            heading =stickAngle;
            System.out.println("move4");
            System.out.println("newLocation.getYCoordinate "+ newLocation.getYCoordinate() );
            //System.out.println("max.getYCoordinate "+ max.getYCoordinate() );


        }


        this.setLocation(currentX + deltaX, currentY + deltaY);

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(ColorUtil.rgb(50, 50, 78));
        int xVal = (int) getLocation().getXStartCord(getSize());
        int yVal = (int) getLocation().getYStartCord(getSize());
        int size = getSize();

        try {
            Image currImage = sprites.get(currFrame);
            g.drawImage(currImage.rotate(90-heading), xVal, yVal, size,
                    size);
        }catch (Exception e){
            System.out.println("Can draw images");
        }
        ++currFrame;
        currFrame%=frames;
    }


}
