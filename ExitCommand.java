package org.csc133.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.GridLayout;

public class ExitCommand extends Command {
    GameWorld gw;
    public ExitCommand(GameWorld gw)
    {
        super("Exit Game");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        ConfirmQuitCommand quit = new ConfirmQuitCommand(gw);
        CancelQuitCommand cancel = new CancelQuitCommand(gw);
        Dialog.show("Exit", "Confirm",quit, cancel);
    }

}
