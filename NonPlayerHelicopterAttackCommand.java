package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NonPlayerHelicopterAttackCommand extends Command {
    private GameWorld gw;

    public NonPlayerHelicopterAttackCommand(GameWorld gw){
        super("Enemy helicopter Engage");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.nonPlayerHeliEngage();
        //System.out.println("bird attaching james");
    }
}
