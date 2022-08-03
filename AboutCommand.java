package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {

    public AboutCommand(){
        super("About");

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        String gameWorldDetail = "James Ochieng\n"+"CSC 133\n"
                                    +"Version a3";
        Dialog.show("About",gameWorldDetail, "close",null);
    }
}
