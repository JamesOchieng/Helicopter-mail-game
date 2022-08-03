package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;

public class NonPlayerHelicopter extends Helicopter implements IDrawable, IStrategy{
    private static int speed = 5;
    private int maxSpeed = 25;
    private int color;
    private int size = 45;
    private String SPRITE_FILE_PATH = "NonPlayerHello";
    private int frameCount = 5;
    private Sound collisionHeliSound = new Sound("/helitoheli.wav");
    private  final int  damageHelicopter = 10;



    public NonPlayerHelicopter(){
        super(ColorUtil.rgb(255,0,0 ), 80);
        setSpeed();
        setHeading();
        setSize();

        initSpirit(SPRITE_FILE_PATH, frameCount);

    }

    public  void setSize(){
        size = rand.nextInt(((25 - 10)+5) + 10);
        //super.setSize(80);
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
                + getStickAngle() + " fuelLevel = "  + getFuelLvl() +
                " damageLevel = " + getDamageLevel();
    }

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
        int speedRand = rand.nextInt(30);

        if(speedRand == 8){
            setSpeed();
        }

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

        Coordinates nonPlayerLoc = this.getLocation();
        double tempx = playerheli.getXCoordinate() - nonPlayerLoc.getXCoordinate();
        double tempy = playerheli.getYCoordinate() - nonPlayerLoc.getYCoordinate();
        int chaseheading = (int)Math.toDegrees(Math.atan2(tempy,tempx));
        this.setHeading(chaseheading);
    }

    @Override
    public void setStrategy() {

    }

    @Override
    public void collideWithObject(GameObject otherObject) {
        if (otherObject instanceof PlayerHelicopter){
            collisionHeliSound.play();
            ((PlayerHelicopter)otherObject).setDamageLevel(damageHelicopter);

            //  ((PlayerHelicopter)otherObject).setFuelLevel(capacity);
        //   setCapacity(0);
        }
    }
}
