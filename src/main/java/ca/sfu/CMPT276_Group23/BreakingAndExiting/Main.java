package ca.sfu.CMPT276_Group23.BreakingAndExiting;


import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.MainMenuScreen;
import javafx.application.Application;

/**
 * Main class calls the MainMenuScreen, starting the Breaking and Exiting.
 *
 * @author Group 23
 * @version 1.0
 */
public class Main {

    /**
     * Default constructor of main (Created to avoid javaDoc warning)
     */
    public Main(){}
    /**
     * Main method serves as the entry point for the Breaking and Exiting game.
     *
     * @param args Command-line arguments is passed to the MainMenuScreen application starting the game.
     */
    public static void main(String[] args) {

        Application.launch(MainMenuScreen.class, args);
    }
}

