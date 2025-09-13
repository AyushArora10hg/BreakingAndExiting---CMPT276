/**
 * Module declaration for the BreakingAndExiting project
 */
module CMPT276_Group23.BreakingAndExiting {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.mockito;

    opens ca.sfu.CMPT276_Group23.BreakingAndExiting to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.UI to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.UserInput to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories to org.junit.jupiter.api;
    opens ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound to org.junit.jupiter.api;

    exports ca.sfu.CMPT276_Group23.BreakingAndExiting;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.UserInput;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;
    exports ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound;



}