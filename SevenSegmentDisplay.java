package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import java.awt.*;
import java.io.IOException;
import java.util.Stack;

public class SevenSegmentDisplay extends Component {

    private int value;
    private int digitsCount;
    private int color;
    Stack<Integer> stackValues;
    Point tempPoint = new Point();
    Image temp;

    Image[] digits;
    Image[] digitImages = new Image[10];
    public SevenSegmentDisplay(int value, int digitsCount, int color){
        this.value = value;
        this.digitsCount = digitsCount;
        this.color = color;
        digits = new Image[digitsCount];
        initImage();
        update(value, color);

    }

    public void update(int attValue, int color) {
        stackValues = new Stack<>();
        this.color = color;
        while (attValue > 0){
            stackValues.push(attValue % 10);
            attValue /= 10;
        }

        while (stackValues.size() != digitsCount){
            stackValues.add(0);
        }
        repaint();
    }

    public void initImage(){
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
            temp =  Image.createImage("/LED_colon.png");
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < digitsCount; i++){
            digits[i] = digitImages[0];
            //cockpitDigits[HM_COLON_IDX] = colonImage;
            //cockpitDigits[MS_COLON_IDX]= colonImage;
        }
    }

    public void laidOut(){ this.start(); }

    public void start() { getComponentForm().registerAnimated(this);}

    public void stop() { getComponentForm().deregisterAnimated(this); }



    public boolean animate(){
        setCurrentValue();
        return true;
    }

    private void setCurrentValue() {
        setValue(stackValues);
    }

    private void setValue(Stack<Integer> stackValues) {
        int stackSize = stackValues.size();
        //System.out.println(stackSize);
        while (!stackValues.isEmpty()){
            for(int x = 0; x < stackSize; x++){
                digits[x] = digitImages[stackValues.pop()];
            }
        }


    }

    public void paint(Graphics g){
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = digits[0].getWidth();
        int digitHeight = digits[0].getHeight();
        int clockWidth = digitsCount*digitWidth;

        float scaleFactor = Math.min(getInnerHeight()/(float)digitHeight,
                getInnerWidth()/(float)clockWidth);
        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*digitsCount;

        int displayX = getX() + (getWidth() - displayClockWidth)/2;
        int displayY = getY() + (getHeight() -displayDigitHeight)/2;


        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(color);

        g.fillRect(displayX+COLOR_PAD, displayY+COLOR_PAD,
                displayClockWidth-COLOR_PAD*2,
                displayDigitHeight-COLOR_PAD*2);

        for (int digitIndex = 0; digitIndex < digitsCount; digitIndex++){
            g.drawImage(digits[digitIndex], displayX + digitIndex *
                            displayDigitWidth,displayY, displayDigitWidth,
                    displayDigitHeight);
        }

    }


}
