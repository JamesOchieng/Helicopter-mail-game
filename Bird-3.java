package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Image;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * The bird class will construct birds with random location with ser color
 **/
public class Bird extends MovableObject implements IDrawable, IStrategy{
    private int size = 65;
    private int heading ;
    private int speed;
    private boolean selected;
    private Coordinates location;
    private  final int damageBird = 3;
    private String SPRITE_FILE_PATH = "birdPhase";
    private int frameCount = 5;
    private static int currFrame = 0;
    private ArrayList<Image> sprites;
    private int frames;
    private Sound collisionSound = new Sound("/helitobird.wav");


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
        speed = rand.nextInt(((20 - 5) + 5) + 5);
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

        }else if(headingRand == 0){
            headingadd = -10;
        }

        setHeading(getHeading() + headingadd);
        super.move(GAME_TICK,minMapLoc,maxMapLoc);
        //System.exit(0);
        int speedRand = rand.nextInt(25);

        if(speedRand == 8){
            setSpeed();
        }

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

    @Override
    public void draw(Graphics g) {
        g.setColor(ColorUtil.rgb(50, 50, 78));
        int xVal = (int) getLocation().getXStartCord(getSize());
        int yVal = (int) getLocation().getYStartCord(getSize());
        int size = getSize();

        try {
            Image currImage = sprites.get(currFrame);
            g.drawImage(currImage.rotate(getHeading() - 90), xVal, yVal, size,
                    size);


        }catch (Exception e){
            //System.out.println("Can1 draw images");
        }
        ++currFrame;
        currFrame= currFrame % frames;
    }



    @Override
    public void collideWithObject(GameObject otherObject) {
        if(otherObject instanceof PlayerHelicopter){
            collisionSound.play();
            ((PlayerHelicopter)otherObject).setDamageLevel(damageBird);        }
    }





    @Override
    public void invokeStrategy(ArrayList<GameObject> gamesObjects) {
        setStrategy();
        Coordinates playerheli = null;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                 playerheli =
                        ((PlayerHelicopter) gamesObjects.get(i)).getLocation();
                break;
            }
        }

        Coordinates birdLoc = this.getLocation();
        double tempx = playerheli.getXCoordinate() - birdLoc.getXCoordinate();
        double tempy = playerheli.getYCoordinate() - birdLoc.getYCoordinate();
        int chaseheading = (int)Math.toDegrees(Math.atan2(tempy,tempx));
        this.setHeading(chaseheading);
    }

    @Override
    public void setStrategy() {
        System.out.println("bird attaching player");
    }
}