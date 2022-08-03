package org.csc133.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {
    private GameWorld gw;
    public Game() {
        gw = new GameWorld();
        gw.init();
        play();
    }
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


                    /*case 'a':
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
                        gw.collision();
                        break;

                    case 'l':
                        gw.left();
                        break;

                    case '1': case'2': case'3': case'4': case'5':
                    case'6': case'7': case'8': case'9':
                        gw.helicopterCollisionSkyScraper();
                        break;

                    case 'e':
                        gw.helicopterCollisonRefuelingblimp();
                        break;

                    case 'g':
                        gw.helicopterCollionBird();
                        break;

                    case 't':
                        gw.updateGameClock();
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
*/
                }
            }
        });
        }
}
