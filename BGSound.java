package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.*;

public class BGSound implements Runnable{
    private Media bgSound;

    public BGSound(String filePath){
        try {
            InputStream soundStream =
                    Display.getInstance().getResourceAsStream(getClass(), filePath);
            bgSound = MediaManager.createMedia(soundStream, "audio/wav",this);
        }catch (IOException e){
            System.out.println("Filename: " + filePath + "cant be played" );

            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        bgSound.setTime(0);
        bgSound.play();
    }

    public void pause() {
        bgSound.pause();
    }

    public void play() {
        bgSound.play();
    }

    public void setVolume(int volume) {
        bgSound.setVolume(volume);
    }
}
