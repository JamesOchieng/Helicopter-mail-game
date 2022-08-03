package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BirdAttackCommand extends Command {
    private GameWorld gw;

    public BirdAttackCommand(GameWorld gw) {
        super("Bird Engage");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.birdEngage();
        //System.out.println("bird attaching james");
    }

}
