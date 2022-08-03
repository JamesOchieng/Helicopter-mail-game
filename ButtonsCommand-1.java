package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.GridBagLayout;
import com.codename1.ui.plaf.Border;

public class ButtonsCommand extends Container{
    private Button accelerateBtn;
    private Button brakeBtn;
    private Button leftTurnBtn;
    private Button rightTurnBtn;
    private char[] shapes = new char[]{
            FontImage.MATERIAL_KEYBOARD_ARROW_UP,
            FontImage.MATERIAL_KEYBOARD_ARROW_DOWN,
            FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT,
            FontImage.MATERIAL_KEYBOARD_ARROW_LEFT};

    public ButtonsCommand(){
        super(new GridBagLayout());

        accelerateBtn = updateButton(0);
        brakeBtn = updateButton(1);
        rightTurnBtn = updateButton(2);
        leftTurnBtn = updateButton(3);

        this.add(brakeBtn).add(accelerateBtn).add(leftTurnBtn).add(rightTurnBtn);

    }


    private Button updateButton(int shapeValue) {
        Button obj = new Button(shapes[shapeValue]);
        obj.getAllStyles().setBgTransparency(255);
        obj.getUnselectedStyle().setBgColor(ColorUtil.YELLOW);
        obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
        obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        obj.getAllStyles().setPadding(TOP, 5);
        obj.getAllStyles().setPadding(BOTTOM, 5);
        return obj;
    }

    public void addListeners(Command[] cmd) {
        accelerateBtn.addActionListener(cmd[0]);
        brakeBtn.addActionListener(cmd[1]);
        leftTurnBtn.addActionListener(cmd[2]);
        rightTurnBtn.addActionListener(cmd[3]);
    }


}