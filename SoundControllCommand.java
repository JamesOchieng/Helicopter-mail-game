package org.csc133.a3;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class SoundControllCommand extends Command {
    private GameWorld gw;
    private SoundOnCommand soundon;
    private SoundOffCommand soundOff;

    public SoundControllCommand(GameWorld gw){
        super("Sound Controll");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        soundOff = new SoundOffCommand(gw);
        soundon = new SoundOnCommand(gw);
        Dialog.show("Sound Control","Pick",soundOff,soundon );

    }
}
