package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command{
    private GameWorld gw;
    public BrakeCommand(GameWorld gw){
        super("Brake");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.brake();
        System.out.println("Speed has been decreased");
    }
}
