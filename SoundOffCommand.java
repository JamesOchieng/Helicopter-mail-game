package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundOffCommand extends Command {
    private GameWorld gw;

    public SoundOffCommand(GameWorld gw) {
        super("Sound OFF");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.pauseSound();
        //System.out.println("bird attaching james");
    }
}
