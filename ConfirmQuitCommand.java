package org.csc133.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.GridLayout;

public class ConfirmQuitCommand extends Command {
    GameWorld gw;

    public ConfirmQuitCommand(GameWorld gw){
        super("Confirm Quit");
        this.gw = gw;

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        gw.confirm();
    }

}
