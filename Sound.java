package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.IOException;
import java.io.InputStream;

public class Sound {

    private Media sound;

    public Sound(String filePath){
        try {
            InputStream soundStream =
                    Display.getInstance().getResourceAsStream(getClass(), filePath);
            sound = MediaManager.createMedia(soundStream, "audio/wav");
        }catch (IOException e){
            System.out.println("Filename: " + filePath + "cant be played" );

            e.printStackTrace();
        }
    }
    public void play() {
        sound.setTime(0);
        sound.play();
    }

    public void setVolume(int volume) {
        sound.setVolume(volume);
    }
}
