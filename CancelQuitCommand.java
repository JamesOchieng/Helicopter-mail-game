package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CancelQuitCommand extends Command {
    GameWorld gw;
    public CancelQuitCommand(GameWorld gw)
    {
        super("Cancel Exit");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        gw.notConfirm();
    }
}
