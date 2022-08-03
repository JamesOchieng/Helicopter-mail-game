package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridBagLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

public class ActivateStrategyCommand extends Command {
    private GameWorld gw;
    private Button birdAttack;
    private BirdAttackCommand birdAttackCmd;
    private NonPlayerHelicopterAttackCommand nonPlayerHeliAtckCmd;

    private boolean attackMode;

    private String[] names = new String[]{
            "Attack Player Helicopter", "Retreat Player Helicopter"
    };

    public  ActivateStrategyCommand(GameWorld gw){
        super("Activate Strategy");
        this.gw = gw;
        //InteractionDialog dlg = new InteractionDialog("Hello");
       // dlg.setLayout(new GridLayout(1,2));

    }

    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        birdAttackCmd = new BirdAttackCommand(gw);
        nonPlayerHeliAtckCmd = new NonPlayerHelicopterAttackCommand(gw);

        attackMode = gw.isAttackMode();
        birdAttack = new Button();
        Dialog.show("Attack Strategy", "Pick Strategy", birdAttackCmd,
                nonPlayerHeliAtckCmd);



        //birdAttack.addActionListener((ee) -> gw.birdEngage());

    }




}
