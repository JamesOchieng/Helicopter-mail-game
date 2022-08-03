package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerHelicopter extends Helicopter implements IDrawable{
    private static int speed = 5;
    private int maxSpeed = 25;
    private int color;
    private int size = 800;
    private static PlayerHelicopter instanceHelicopter;
    private String SPRITE_FILE_PATH = "PlayerHello";
    private int frameCount = 5;



    public PlayerHelicopter(int x, int y){
        super(ColorUtil.rgb(255,0,0 ), 40);
        this.setLastSkyScraperReached(0);
        this.setGameLife(3);
        setSpeed();
        setHeading();
        super.setLocation(x,y);
        initSpirit(SPRITE_FILE_PATH, frameCount);


    }

    private static PlayerHelicopter getInstance(int x, int y){
        if(instanceHelicopter == null)
        {
            return new PlayerHelicopter(x, y);

        }
        else
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
                + getStickAngle() + " fuelLevel = "  + getFuelLevel() +
                " damageLevel = " + getDamageLevel();
    }


}
