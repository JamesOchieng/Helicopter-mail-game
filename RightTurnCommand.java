package org.csc133.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class RightTurnCommand extends Command {
    private GameWorld gw;
    public RightTurnCommand(GameWorld gw)
    {
        super("Turn Right");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.right();
        System.out.println(" The helicopter has turned right");
    }
}
