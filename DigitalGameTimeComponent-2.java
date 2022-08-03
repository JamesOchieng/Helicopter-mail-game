package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.IOException;
import java.util.Calendar;

public class DigitalGameTimeComponent extends Component implements   ITimer {
    Image[] digitImages = new Image[21];
    private int[] timeValue;
    private int ledColor;
    private static int HM_COLON_IDX = 2;
    private static int MS_COLON_IDX = 5;
    static int milliSeconds = 0;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int millisec= 0;
    private static long mStartTime = 0L;
    private Calendar rightNow;
    private static final int numDigitsShowing = 6;
    Image[] cockpitDigits = new Image[numDigitsShowing];
    Image colonImage;
    private long stopTime;
    private long startTime;
    private long fullPausedTime;
    private long startPausedTime;
    private long UpdateMillis;

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
            digitImages[10] = Image.createImage("/LED_digit_0_with_dot.png");
            digitImages[11] = Image.createImage("/LED_digit_1_with_dot.png");
            digitImages[12] = Image.createImage("/LED_digit_2_with_dot.png");
            digitImages[13] = Image.createImage("/LED_digit_3_with_dot.png");
            digitImages[14] = Image.createImage("/LED_digit_4_with_dot.png");
            digitImages[15] = Image.createImage("/LED_digit_5_with_dot.png");
            digitImages[16] = Image.createImage("/LED_digit_6_with_dot.png");
            digitImages[17] = Image.createImage("/LED_digit_7_with_dot.png");
            digitImages[18] = Image.createImage("/LED_digit_8_with_dot.png");
            digitImages[19] = Image.createImage("/LED_digit_9_with_dot.png");


            colonImage = Image.createImage("/LED_colon.png");
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < numDigitsShowing; i++){
            cockpitDigits[i] = digitImages[0];
            cockpitDigits[HM_COLON_IDX] = colonImage;
            //cockpitDigits[MS_COLON_IDX]= colonImage;
        }
        rightNow = Calendar.getInstance();
        startTime = rightNow.getTimeInMillis();

    }


    protected Dimension calcPreferredSize(){
        return new Dimension(colonImage.getWidth()*numDigitsShowing
                , colonImage.getHeight());

    }
    public void laidOut(){
        this.start();

    }

    public boolean animate(){
        setCurrentTime();
        return true;
    }

    public void start() {
        rightNow = Calendar.getInstance();
        mStartTime = rightNow.getTimeInMillis();

        getComponentForm().registerAnimated(this);
    }

    @Override
    public void resetElapsedTime() {

    }

    @Override
    public void startElapsedTime() {
        rightNow = Calendar.getInstance();
        if(startTime == 0){

            startTime = rightNow.getTimeInMillis();
            //System.out.println("startElapsedTime + 1");
        }
        else if( startPausedTime != 0){
            fullPausedTime = fullPausedTime + rightNow.getTimeInMillis() - startPausedTime;
           // System.out.println("startElapsedTime + 2  " + fullPausedTime );
            setCurrentTime();
        }

    }

    @Override
    public void stopElapsedTime() {
       // rightNow = Calendar.getInstance();
        //startPausedTime = rightNow.getTimeInMillis();
              }

    @Override
    public int[] getElapsedTime() {
        return timeValue = new int[]{minutes,seconds,millisec};
    }

    private void setCurrentTime(){
      // final long start = mStartTime;
        rightNow = Calendar.getInstance();
        stopTime = rightNow.getTimeInMillis();
        //System.out.println("startElapsedTime + 3  " + fullPausedTime );
        long millis = stopTime - startTime - fullPausedTime;

        long milliSeconds = millis / 100;
        //System.out.println("startElapsedTime + 3  " + milliSeconds );
        millisec = (int)milliSeconds;
        seconds = (int) (millis / 1000);
        minutes = ((int)millis / 60000) % 60;
        seconds = seconds % 60;
        //int hour = minutes / 60;
        //hour = hour % 60;
        setTime(minutes, seconds, (int)milliSeconds);


    }


    public void setLedColor(int ledColor){
        this.ledColor = ledColor;
    }





    private void setTime(int m, int s, int ms){

        cockpitDigits[0] = digitImages[m/10];
        cockpitDigits[1] = digitImages[m%10];
        cockpitDigits[3] = digitImages[s/10];
        cockpitDigits[4] = digitImages[(s%10) + 10];
        cockpitDigits[5] = digitImages[ms%10];

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
