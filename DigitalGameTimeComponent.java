package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;
import java.util.Calendar;
import java.io.IOException;
import java.util.Calendar;

public class DigitalGameTimeComponent extends Component {
    Image[] digitImages = new Image[21];
    private int ledColor;
    private static int HM_COLON_IDX = 2;
    private static int MS_COLON_IDX = 5;
    private static final int numDigitsShowing = 7;
    Image[] cockpitDigits = new Image[numDigitsShowing];
    Image colonImage;

    //public void start() {

    //    getComponentForm().registerAnimated(this);
    //}

    protected Dimension calcPreferredSize(){
        return new Dimension(colonImage.getWidth()*numDigitsShowing
                , colonImage.getHeight());
    }

    public boolean animate(){
        setCurrentTime();
        return true;
    }

   // public void laidOut(){
      //  this.start();
    //}

    public void stop() {
        getComponentForm().deregisterAnimated(this);
    }

    private void setCurrentTime(){
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR);
        setTime(hour == 0 ? 12: hour, rightNow.get(Calendar.MINUTE),
                rightNow.get(Calendar.SECOND));
    }

    public void setLedColor(int ledColor){
        this.ledColor = ledColor;
    }



    public DigitalGameTimeComponent(){
        try{
            digitImages[0] = Image.createImage("/LED_digit_0.png");
            digitImages[1] = Image.createImage("/LED_digit_1.png");
            digitImages[2] = Image.createImage("/LED_digit_2.png");
            digitImages[3] = Image.createImage("/LED_digit_3.png");
            digitImages[4] = Image.createImage("/LED_digit_4.png");
            digitImages[5] = Image.createImage("/LED_digit_5.png");
            digitImages[6] = Image.createImage("/LED_digit_6.png");
            digitImages[7] = Image.createImage("/LED_digit_7.png");
            digitImages[8] = Image.createImage("/LED_digit_8.png");
            digitImages[9] = Image.createImage("/LED_digit_9.png");

            colonImage = Image.createImage("/LED_colon.png");
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < numDigitsShowing; i++){
            cockpitDigits[i] = digitImages[0];
            cockpitDigits[HM_COLON_IDX] = colonImage;
            cockpitDigits[MS_COLON_IDX]= colonImage;
        }
        //setTime(2,2,2);

    }

    private void setTime(int h, int m, int s){
        cockpitDigits[0] = digitImages[0/10];
        cockpitDigits[1] = digitImages[0%10];
        cockpitDigits[3] = digitImages[0/10];
        cockpitDigits[4] = digitImages[0%10];
        cockpitDigits[6] = digitImages[0/10];

    }

    public void paint(Graphics g){
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = cockpitDigits[0].getWidth();
        int digitHeight = cockpitDigits[0].getHeight();
        int clockWidth = numDigitsShowing*digitWidth;

        float scaleFactor = Math.min(getInnerHeight()/(float)digitHeight,
                getInnerWidth()/(float)clockWidth);
        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*numDigitsShowing;

        int displayX = getX() + (getWidth() - displayClockWidth)/2;
        int displayY = getY() + (getHeight() -displayDigitHeight)/2;


        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(ledColor);

        g.fillRect(displayX+COLOR_PAD, displayY+COLOR_PAD,
                displayClockWidth-COLOR_PAD*2,
                displayDigitHeight-COLOR_PAD*2);

        for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++){
            g.drawImage(cockpitDigits[digitIndex], displayX + digitIndex *
                            displayDigitWidth,displayY, displayDigitWidth,
                    displayDigitHeight);
        }

    }

    DigitalGameTimeComponent newDefaultClock(int m, int p, int ledcolor){
        DigitalGameTimeComponent aClock = new DigitalGameTimeComponent();
        aClock.getAllStyles().setPadding(p,p,p,p);
        aClock.getAllStyles().setMargin(m,m,m,m);
        aClock.setLedColor(ledcolor);
        return aClock;

    }

}
