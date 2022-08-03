package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.codename1.ui.layouts.GridLayout;


public class GlassCockpit extends Container implements IObserver,ITimer  {

    DigitalGameTimeComponent time;
    SevenSegmentDisplay fuelLvl;
    SevenSegmentDisplay heading;
    SevenSegmentDisplay scrapperLvl;
    SevenSegmentDisplay livesLvl;
    SevenSegmentDisplay damageLvl;
    ArrayList<GameObject> gameObjects;
    private int fuel, damage, lives, last, currHeading;
    private int dangerColor = ColorUtil.MAGENTA;


    public GlassCockpit(){
        this.setLayout(new GridLayout(2, 6));
        this.add(new Form("Game Time")).
                add(new Form("FUEL")).
                add(new Form("DAMAGE")).
                add(new Form("LIVES")).
                add(new Form("LAST")).
                add(new Form("HEADING"));
        initGameTimeComponent();
        fuelLvl = new SevenSegmentDisplay(1000, 4, ColorUtil.GREEN);
        heading = new SevenSegmentDisplay(0,3, ColorUtil.YELLOW );
        scrapperLvl = new SevenSegmentDisplay(0,1,ColorUtil.CYAN);
        livesLvl = new SevenSegmentDisplay(3,1,ColorUtil.CYAN);
        damageLvl = new SevenSegmentDisplay(0,3,ColorUtil.GREEN);
        this.add(fuelLvl).add(damageLvl)
                .add(livesLvl).add(scrapperLvl).add(heading);


    }

    public void initGameTimeComponent(){
        time = new DigitalGameTimeComponent();
        this.add(time.newDefaultClock(2, 2, ColorUtil.GREEN));

        //System.out.println(Arrays.toString(time.getElapsedTime()));
    }

    public ITimer getGameClockComponent() {
        return time;
    }


    @Override
    public void update(ArrayList<GameObject> newGameworld) {
        gameObjects = newGameworld;
        for(GameObject currObj : gameObjects){
            if(currObj instanceof PlayerHelicopter){
                fuel = ((PlayerHelicopter)currObj).getFuelLvl();
                last = ((PlayerHelicopter)currObj).getLastSkyScraperReached();
                currHeading = ((PlayerHelicopter)currObj).getHeading();
                lives = ((PlayerHelicopter)currObj).getGameLife();
                damage = ((PlayerHelicopter)currObj).getDamageLevel();
                break;
            }
        }
        if(damage >= 85){
            dangerColor = ColorUtil.MAGENTA;
        }
        else if (damage >= 50){
            dangerColor = ColorUtil.YELLOW;
        }
        else
            dangerColor = ColorUtil.GREEN;

        fuelLvl.update(fuel, ColorUtil.GREEN);
        scrapperLvl.update(last, ColorUtil.BLUE );
        heading.update(currHeading, ColorUtil.YELLOW);
        livesLvl.update(lives, ColorUtil.CYAN);
        damageLvl.update(damage, dangerColor);
        this.repaint();
    }


    @Override
    public void resetElapsedTime() {

    }

    @Override
    public void startElapsedTime() {

    }

    @Override
    public void stopElapsedTime() {

    }

    @Override
    public int[] getElapsedTime() {
        return new int[0];
    }
}
