package org.firstinspires.ftc.teamcode;

import android.content.Context;
import com.github.pmtischler.base.BlackBox;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.Position;

import java.io.FileOutputStream;

@TeleOp(name="RecordedTeleop", group="1")
public class RecordedTeleop extends Drive {

    private FileOutputStream outputStream;
    private BlackBox.Recorder recorder;

    @Override
    public void init() {
        super.init();

        try {

            outputStream = hardwareMap.appContext.openFileOutput("recordedTeleop",
                    Context.MODE_PRIVATE);

            recorder = new BlackBox.Recorder(hardwareMap, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            requestOpModeStop();
        }

    }
    public void loop() {
        super.loop();

        try {

            recorder.record("leftFront", time);
            recorder.record("rightFront", time);
            recorder.record("rightBack", time);
            recorder.record("leftBack", time);
            recorder.record("rightScoop", time);
            recorder.record("leftScoop", time);
            recorder.record("mainScoop", time);
            recorder.record("yeetus", time);

        } catch (Exception e) {
            e.printStackTrace();
            requestOpModeStop();
        }
    }
    public void stop() {
        super.stop();

        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}