package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class LeftTurnCommand extends  Command{

    private GameWorld gw;
    public LeftTurnCommand(GameWorld gw)
    {
        super("Turn Left");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.left();
    }
}