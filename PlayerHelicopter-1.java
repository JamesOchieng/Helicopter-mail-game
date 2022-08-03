package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;

public class PlayerHelicopter extends Helicopter implements IDrawable{

    private int maxSpeed = 25;
    private int color;
    private int size = 45;
    private static PlayerHelicopter instanceHelicopter;
    private String SPRITE_FILE_PATH = "PlayerHelicopterBody";
    private int frameCount = 6;
    private Sound collisionHeliSound = new Sound("/helitoheli.wav");
    private Sound collisionBirdSound = new Sound("/helitobird.wav");
    private Sound collisionSkySound = new Sound("/helitosky.wav");
    private Sound collisionBlimpSound = new Sound("/helitoblimp.wav");
    private  final int damageBird = 3;


    public PlayerHelicopter(int x, int y){
        super(ColorUtil.rgb(255,0,0 ), 80);
        //this.setLastSkyScraperReached();
        this.setGameLife(3);
        super.setSpeed(5);
        setHeading(275);
        super.setLocation(x,y);
        initSpirit(SPRITE_FILE_PATH, frameCount);


    }

    private static synchronized PlayerHelicopter getInstance(int x, int y){
        if(instanceHelicopter == null)
        {
            return new PlayerHelicopter(x, y);

        }
        else
            System.out.println("no clone");
            return instanceHelicopter;
    }




    @Override
    public String toString() {
        return "PlayerHelicopter: loc=" + this.getLocation().getXCoordinate()+","+
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

            super.move(GAME_TICK,minMapLoc,maxMapLoc);



    }

    @Override
    public void collideWithObject(GameObject otherObject) {
        int nextSkyscrapper = getLastSkyScraperReached() + 1;
        int fuel = getFuelLvl();
        if(otherObject instanceof Bird){
            collisionBirdSound.play();
            setDamageLevel(damageBird);
        }
        else if (otherObject instanceof NonPlayerHelicopter){
            System.out.println("collide non heli");
            collisionHeliSound.play();
            //setDamageLevel(damageHelicopter);
        }
        else if (otherObject instanceof SkyScraper){
            if (((SkyScraper)otherObject).getSequencNum() == nextSkyscrapper){
                collisionSkySound.play();
                setLastSkyScraperReached((nextSkyscrapper));
                System.out.println("last sky " + getLastSkyScraperReached());

            }

        }
        else if (otherObject instanceof RefuelingBlimp){
            if (((RefuelingBlimp)otherObject).getCapacity() > 0){
                collisionBlimpSound.play();

                System.out.println("sound from reuuuf");

            }

        }
    }
}



