package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.ArrayList;

/**
 * fixed object class is connected to GameObject
 **/
public abstract class FixedObject  extends GameObject implements IDrawable{
    private ArrayList<Image> sprites;

    /**
     * The methods below are getters and setters foe size
     * location, and color
     *
     * @param color
     */
    public FixedObject(int color) {
        super(color);
    }

    public FixedObject(int color, int size) {
        super(color, size);
    }

    protected void initSpirit(String spiritPath, int framecount){
        int frames = framecount;
        sprites = new ArrayList<>();

        for(int x = 1; x < frames; x++) {
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
    @Override
    public void draw(Graphics g) {
        g.setColor(ColorUtil.rgb(50,50,78 ));
        int xVal = (int)this.getXLocation();
        int yVal = (int)this.getYLocation();
        int[] graphXPoints = {xVal, (xVal - 10), (xVal + 10), xVal};
        int[] graphYPoints = {(yVal + 20), (yVal - 20), (yVal - 20),
                (yVal + 20)};
        g.drawImage(sprites.get(0), xVal,yVal);
    }
}