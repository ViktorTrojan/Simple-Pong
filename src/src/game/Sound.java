package src.game;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    public static ClipAudio paddlehit = new ClipAudio("/src/res/sounds/paddlehit.wav");
    public static ClipAudio wallhit = new ClipAudio("/src/res/sounds/wallhit.wav");
}

class ClipAudio {

    private Clip clip;
    private final String fileName;
    AudioInputStream audioIn;

    public ClipAudio(String fileName) {
        this.fileName = fileName;
        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
        } catch (IOException | UnsupportedAudioFileException e) {
            System.out.println("Couldn't Load Soundfile: " + fileName);
        }
    }

    public void play(int volume) {
        new Thread(() -> {
            try {
                audioIn = AudioSystem.getAudioInputStream(getClass().getResource(fileName));

                clip = AudioSystem.getClip();
                clip.open(audioIn);
                setVolume(volume);
                clip.start();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            }
        }).start();
    }

    public void play(int volume, boolean loop) {
        setVolume(volume);
        if (loop == true) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    public void updateAudioVolume(int volume) {
        if (clip != null) {
            setVolume(volume);
            long clipPosition = clip.getMicrosecondPosition();
            clipPosition %= clip.getMicrosecondLength();
            clip.setMicrosecondPosition(clipPosition + 20);
        }
    }

    public void stopAudio() {
        if (clip != null) {
            clip.stop();
        }
    }

    private void setVolume(int volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if (volume > 0) {
            gainControl.setValue(-100 + (volume / 4 + 65));
        }
        if (volume <= 0) {
            gainControl.setValue(-80);
        }
    }
}
