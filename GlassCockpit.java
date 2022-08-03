package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;


public class GlassCockpit extends Container implements Observer{

    DigitalGameTimeComponent time;
    public GlassCockpit(){
        this.setLayout(new GridLayout(2, 6));
        this.add(new Form("Game Time")).
                add(new Form("FUEL")).
                add(new Form("DAMAGE")).
                add(new Form("LIVES")).
                add(new Form("LAST")).
                add(new Form("HEADING"));
        setGameTimeLabel();
        //setFuelLabel();
       // setDamageLabel();
        //setLivesLabel();
        //setLastReachedSkyscraperLabel();
        //setHeadingLabel();
    }


    public void setGameTimeLabel(){
        Form clockForm = new Form("Game Time");
        time = new DigitalGameTimeComponent();
        Style clockStyle = new Style();
       // Font t = Font.createSystemFont(Font.FACE_SYSTEM,
                //Font.STYLE_BOLD,
               // Font.SIZE_MEDIUM);
        clockStyle.setBgColor(ColorUtil.rgb(255,153,51));
        clockStyle.setFgColor(ColorUtil.WHITE);

        clockForm.getAllStyles().setFgColor(ColorUtil.BLACK);
        clockForm.getAllStyles().setBgTransparency(255);
        clockForm. getAllStyles().setBgColor(ColorUtil.GRAY);
       add(time.newDefaultClock(2, 2,
             ColorUtil.GREEN));
        //clockForm.getAllStyles().setFgColor(ColorUtil.BLACK);



    }

    public void setFuelLabel(){
        Form fuelLabel = new Form("FUEL");
        fuelLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.add(fuelLabel);



    }

    public void setDamageLabel(){
        Form damageLabel = new Form("DAMAGE");
        damageLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.add(damageLabel);
    }

    public void setLivesLabel(){
        Form leftLivesLabel = new Form("Lives");
        leftLivesLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.add(leftLivesLabel);

    }

    public void setHeadingLabel(){
        Form headingLabel = new Form("HEADING");
        headingLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.add(headingLabel);
    }

    public void setLastReachedSkyscraperLabel(){
        Form lastSkyScrapperLabel = new Form("LAST");
        lastSkyScrapperLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.add(lastSkyScrapperLabel);
    }


    @Override
    public void update(Observable o, Object arg) {

    }

}
