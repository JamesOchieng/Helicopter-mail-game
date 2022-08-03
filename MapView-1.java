package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.geom.Point;


import java.util.ArrayList;

public class MapView extends Container implements IObserver {
    private ArrayList<GameObject> gw;
    private  int height;
    private static int width;
    static private Coordinates originMapView;

    private Coordinates minLocation;
    private Coordinates maxLocation;

    public Coordinates getMinLocation() {
        return minLocation;
    }

    public Coordinates getMaxLocation() {
        return maxLocation;
    }

    public MapView(){



        this.getAllStyles().setBorder(Border.
                createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
        this.setLayout(new BorderLayout());
        this.getAllStyles().setBgColor(ColorUtil.WHITE);
        this.getAllStyles().setBgTransparency(255);

    }

    public void start() {

        getComponentForm().registerAnimated(this);
    }



    public boolean animate(){
        //setCurrentTime();
        return true;
    }



    public int getCurrHeight() {
        return height ;
    }

    public int getCurrWidth() {
        return width;
    }

    public void laidOut(){

    }

    public void stop() {
        getComponentForm().deregisterAnimated(this);
    }



    public static Coordinates getMapViewOrigin() { return originMapView; }

    public void paint(Graphics g){
        super.paint(g);
        Point relPointrint = new Point(this.getX(), this.getY());
        //GameWorldObjects newItr = gw.getGamesObjects().getGameIterator();

        if(gw != null) {
            //System.out.println("this is height " + height);
            // System.out.println("this is height " + width);

            for (GameObject i : gw) {
                if(i instanceof IDrawable)
                {
                    ((IDrawable) i).draw(g);
                }
            }

        }


    }

    @Override
    public void update(ArrayList<GameObject> newGameworld) {
        gw = newGameworld;
        this.repaint();

    }

}
