package ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Sound player tasked with playing audio files for various game events/states.
 */
public class SoundPlayer {

    private Clip soundPlayer;

    /**
     * Sound player constructor method.
     *
     * <p>
     *     Constructor creates a sound player with a specific audio file
     *     to be played at a specific event. Ex: Level has a specific audio file
     *     that plays while level is being player.
     * </p>
     *
     * @param audioFile A specific audio file relating to an event/state of game.
     */
    public SoundPlayer(File audioFile) {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            soundPlayer = AudioSystem.getClip();
            soundPlayer.open(audioStream);
        } catch (IOException e) {
            System.err.println("File not Found");
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Wrong Audio Format");
        } catch (LineUnavailableException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * play method starts the playing of specific music depending on an
     * audio file.
     */
    public void play() {

        soundPlayer.start();
    }

    /**
     * stop method stops the playing of music. Method is in place to
     * stop music if game ends or screen changes.
     */
    public void stop() {

        soundPlayer.stop();
    }

}
