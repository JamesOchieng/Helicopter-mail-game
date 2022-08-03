package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundOnCommand extends Command {
    private GameWorld gw;


    public SoundOnCommand(GameWorld gw) {
        super("Sound ON");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        gw.playSound();
        //System.out.println("bird attaching james");
    }

}
