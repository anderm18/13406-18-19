package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.github.pmtischler.base.BlackBox;
import java.io.FileInputStream;

@Autonomous (name = "Auto", group = "1")
public class Auto extends OpMode {

    private FileInputStream inputStream;
    private BlackBox.Player player;

    public void init() {
        try {

            inputStream = hardwareMap.appContext.openFileInput("recordedTeleop");

            player = new BlackBox.Player(inputStream, hardwareMap);
        } catch (Exception e) {
            e.printStackTrace();
            requestOpModeStop();
        }
    }
    public void loop() {
        try {
            player.playback(time);
        } catch (Exception e) {
            e.printStackTrace();
            requestOpModeStop();
        }
    }
    public void stop() {
        try {
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
