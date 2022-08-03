package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;
import com.codename1.ui.Container;



public class HelpCommand extends Command {
AccelerateCommand calle;

    public HelpCommand(){
        super("show Help");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        String gameWorldDetail = "a: Acceleration\n"+
                                "b: Brake\n"+
                                "e: Collide with blimp\n"+
                                "g: Collide with bird\n"+
                                "l: Turn Stick Left\n"+
                                "r: Turn Stick Right\n"+
                                "n: Collide with NPH\n" +
                                "s: Collide with Skyscraper\n" +
                                "x: Exit"+"";
        Dialog.show("Keybindings",gameWorldDetail, "close",null);

    }
}
