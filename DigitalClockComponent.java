/**
 *James Ochieng
 * Digital Clock
 * 3/18/2021
 * */
package org.csc133.a2w;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;
import java.util.Calendar;
/**
 * DigitalClockComponent class will create our clock object*/
public class DigitalClockComponent extends Component {
    Image[] digitImages = new Image[10];
    Image colonImage;
/**
 * declared variables to the led-color value, number of digits used
 * and positions od colons
 * */
    private int ledColor;
    private static int HM_COLON_IDX = 2;
    private static int MS_COLON_IDX = 5;
    private static final int numDigitsShowing = 8;
    Image[] clockDigits = new Image[numDigitsShowing];
    /**
     *This method will initiate the animation
     * */
    public void start() {

        getComponentForm().registerAnimated(this);
    }
    /**
     *This method will get the current time from the host device
     * It will get the hours, minitues, seconds
     *
     * */
    private void setCurrentTime(){
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR);
        setTime(hour == 0 ? 12: hour, rightNow.get(Calendar.MINUTE),
                rightNow.get(Calendar.SECOND));
    }
    /**
     *This method will, terminare the animation
     * */
    public void stop() {
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut(){
        this.start();
    }
    /**
     *This method will use the setcurrenttime  method to determine
     * if there has been a change
     * */
    public boolean animate(){
        setCurrentTime();
        return true;
    }
    /**
     *This method will, set the proper size for the display
     * */
    protected Dimension calcPreferredSize(){
        return new Dimension(colonImage.getWidth()*numDigitsShowing
                , colonImage.getHeight());
    }
    /**
     *This method will add all the images into an array
     * and set the clock initial state to zero
     * */
    public DigitalClockComponent(){
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
            clockDigits[i] = digitImages[0];
            clockDigits[HM_COLON_IDX] = colonImage;
            clockDigits[MS_COLON_IDX]= colonImage;
        }
    }
    /**
     *This method will set the digits to the right position
     *
     * */
    private void setTime(int h, int m, int s){
        clockDigits[0] = digitImages[h/10];
        clockDigits[1] = digitImages[h%10];
        clockDigits[3] = digitImages[m/10];
        clockDigits[4] = digitImages[m%10];
        clockDigits[6] = digitImages[s/10];
        clockDigits[7] = digitImages[s%10];
    }

    /**
     *This method will the led color
     * */

    public void setLedColor(int ledColor){
        this.ledColor = ledColor;
    }
    /**
     *This method will set the value in the right position based on the
     * screen width and height. The orher lines of code will set the digits
     * to a black background
     * */
    public void paint(Graphics g){
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
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
            g.drawImage(clockDigits[digitIndex], displayX + digitIndex *
                        displayDigitWidth,displayY, displayDigitWidth,
                    displayDigitHeight);
        }

    }
}
