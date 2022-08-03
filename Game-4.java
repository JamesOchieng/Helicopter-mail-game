package org.csc133.a3;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;

import java.lang.String;
/**
 * The game class initiates the game wth play method
 **/
public class Game extends Form implements Runnable {
    private GameWorld gw;
    private GlassCockpit scoreView;
    private MapView map;
    private AccelerateCommand accelCmd;
    private BrakeCommand brakeCmd;
    private LeftTurnCommand leftTurnCmd;
    private RightTurnCommand rightTurnCmd;
    private HelpCommand helpCommand;
    private AboutCommand about;
    private ExitCommand exitCmd;

    UITimer timer;
    private final int GAME_TICK = 300;
    private ButtonsCommand buttons;
    ActivateStrategyCommand activateStrategy;
    SoundControllCommand soundControllCmd;


    public Game() {
        setTitle("SkyMail 3000");
        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        this.setLayout(new BorderLayout());

        scoreView = new GlassCockpit();
        buttons = new ButtonsCommand();

        map = new MapView();
        gw = new GameWorld();

        gw.addObservers(map);
        gw.addObservers(scoreView); /* will fix later*/

        this.add(BorderLayout.NORTH, scoreView);
        this.add(BorderLayout.CENTER, map);
        this.add(BorderLayout.SOUTH, buttons);

        this.show();
        gw.setMaxLoc(map.getHeight(), map.getWidth());
        gw.init(scoreView.getGameClockComponent());
        //play();

        show();

        timer = new UITimer(this);
        timer.schedule(GAME_TICK, true, this);

        accelCmd = new AccelerateCommand(gw);
        brakeCmd = new BrakeCommand(gw);
        leftTurnCmd = new LeftTurnCommand(gw);
        rightTurnCmd = new RightTurnCommand(gw);
        helpCommand = new HelpCommand();
        about = new AboutCommand();
        exitCmd = new ExitCommand(gw);
        activateStrategy = new ActivateStrategyCommand(gw);
        soundControllCmd = new SoundControllCommand(gw);

        Command[] cmd = new Command[]{accelCmd, brakeCmd, leftTurnCmd,
                rightTurnCmd};
        buttons.addListeners(cmd);


        //addKeyListener('x', exitCommand);
        toolbar.addCommandToSideMenu(about);
        toolbar.addCommandToSideMenu(helpCommand);
        toolbar.addCommandToSideMenu(activateStrategy);
        toolbar.addCommandToSideMenu(soundControllCmd);
        toolbar.addCommandToSideMenu(exitCmd);
        //toolbar.addCommandToRightSideMenu(exitCmd);

        show();

    }






    /**
     * The play method allows the player to interact with th game
     *
     **/
    private void play() {
// code here to accept and
// execute user commands that
// operate on the game world
//(refer to “Appendix - CN1
//Notes” for accepting
//keyboard commands via a text
//field located on the form)
        Label myLabel=new Label("Enter a Command:");
        this.addComponent(myLabel);
        final TextField myTextField=new TextField();
        this.addComponent(myTextField);
        this.show();
        myTextField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                String sCommand=myTextField.getText().toString();
                if (sCommand == null || sCommand.equals(""))
                    return;
                myTextField.clear();
                switch (sCommand.charAt(0)) {
                    case 'x':
                        gw.exit();
                        break;

                    case 'a':
                        gw.accelerate();
                        break;

                    case 'b':
                        gw.brake();
                        break;

                    case 'l':
                        gw.left();
                        break;

                    case 'r':
                        gw.right();
                        break;

                    case 'c':
                        gw.helicopterCollision();
                        break;

                    case '1':
                        gw.helicopterCollisionSkyScraper(1);
                        break;
                    case '2':
                        gw.helicopterCollisionSkyScraper(2);
                        break;
                    case '3':
                        gw.helicopterCollisionSkyScraper(3);
                        break;
                    case '4':
                        gw.helicopterCollisionSkyScraper(4);
                        break;
                    case '5':
                        gw.helicopterCollisionSkyScraper(5);
                        break;
                    case '6':
                        gw.helicopterCollisionSkyScraper(6);
                        break;
                    case '7':
                        gw.helicopterCollisionSkyScraper(7);
                        break;
                    case '8':
                        gw.helicopterCollisionSkyScraper(8);
                        break;
                    case '9':
                        gw.helicopterCollisionSkyScraper(9);
                        break;

                    case 'e':
                        gw.helicopterCollisionRefuelling();
                        break;

                    case 'g':
                        gw.helicopterCollisionBird();
                        break;

                    case 't':
                        // gw.updateGameClock(GAME_TICK, );
                        break;

                    case 'd':
                        gw.generateDisplay();
                        break;

                    case 'm':
                        gw.outputMap();
                        break;

                    case 'y':
                        gw.confirm();
                        break;

                    case 'n':
                        gw.notConfirm();
                        break;

                }
            }
        });
    }

    @Override
    public void run() {
        gw.updateGameClock(GAME_TICK);
    }
}