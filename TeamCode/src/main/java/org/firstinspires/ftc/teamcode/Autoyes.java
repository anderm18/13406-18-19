//IF YOU ARE JUST STARTING OUT AND ARE LEARNING, START WITH TRYING TO UNDERSTAND EVERYTHING BELOW LINE 184

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DistanceSensor;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name= "Autoyes", group = "1")
public class Autoyes extends LinearOpMode {

    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor rightScoop = null;
    private DcMotor leftScoop = null;
    private DcMotor mainScoop = null;
    private DcMotor lifter = null;
    ColorSensor sensorColor = null;
    DistanceSensor sensorDistance = null;
    int Ticks = 1120;

    public void down() {

        lifter.setPower(-1);
        sleep(3250);
        lifter.setPower(0);
        sleep(1000);

    }

    public void Stop() {

        lifter.setPower(0);
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
        sleep(250);
    }

    public void Forward(){

        leftFront.setPower(.5);
        leftBack.setPower(.5);
        rightBack.setPower(.5);
        rightFront.setPower(.5);

    }

    public void TurnLeft(){

        leftFront.setPower(.5);
        leftBack.setPower(.5);
        rightBack.setPower(-.5);
        rightFront.setPower(-.5);

    }

    public void TurnRight(){

        leftFront.setPower(-.5);
        leftBack.setPower(-.5);
        rightBack.setPower(.5);
        rightFront.setPower(.5);
    }

    public void Backward(){

        leftFront.setPower(-.5);
        leftBack.setPower(-.5);
        rightFront.setPower(-.5);
        rightBack.setPower(-.5);
    }

    public void mainUp(){

        mainScoop.setPower(.5);
        sleep(1000);
    }



    public void CTurnR(){

        leftFront.setPower(-.5);
        leftBack.setPower(-.5);
        rightBack.setPower(.5);
        rightFront.setPower(.5);

    }

    public void CTurnL(){

        leftFront.setPower(.5);
        leftBack.setPower(.5);
        rightBack.setPower(-.5);
        rightFront.setPower(-.5);

    }

    public void TL90Distance(){
        double d = 1120*.5;

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int newTarget = leftFront.getTargetPosition() + leftBack.getTargetPosition() + rightFront.getTargetPosition() +rightBack.getTargetPosition() + (int)d;
        //Set target pos.
        leftFront.setTargetPosition(newTarget);
        leftBack.setTargetPosition(newTarget);
        rightBack.setTargetPosition(newTarget);
        rightFront.setTargetPosition(newTarget);

        //set to RUN_TO_POSITION
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Drive Left
        TurnLeft();

        while (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy() )
        {
            //wait until target position is reached
        }

        //Stop and change modes back to normal
        Stop();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void TRDistance(int r){

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set target pos.
        leftFront.setTargetPosition(r);
        leftBack.setTargetPosition(r);
        rightBack.setTargetPosition(-r);
        rightFront.setTargetPosition(-r);

        //set to RUN_TO_POSITION
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Drive Right
        TurnRight();

        while (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy() )
        {
            //wait until target position is reached
        }

        //Stop and change modes back to normal
        Stop();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void DFDistance(int r){

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set target pos.
        leftFront.setTargetPosition(r);
        leftBack.setTargetPosition(r);
        rightBack.setTargetPosition(r);
        rightFront.setTargetPosition(r);

        //set to RUN_TO_POSITION
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Drive Forward
        Forward();

        while (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy() )
        {
            //wait until target position is reached
        }

        //Stop and change modes back to normal
        Stop();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void runOpMode(){

        telemetry.addData("Status", "Initialized");

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightScoop = hardwareMap.get(DcMotor.class, "rightScoop");
        leftScoop = hardwareMap.get(DcMotor.class, "leftScoop");
        mainScoop = hardwareMap.get(DcMotor.class, "mainScoop");
        sensorColor = hardwareMap.colorSensor.get("colorSensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorSensor");
        lifter = hardwareMap.get(DcMotor.class, "lifter");

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        mainScoop.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mainScoop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mainScoop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mainScoop.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftScoop.setDirection(DcMotor.Direction.FORWARD);
        rightScoop.setDirection(DcMotor.Direction.REVERSE);

        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;

        final double SCALE_FACTOR = 255;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        waitForStart();





        while (opModeIsActive()) {
            sensorColor.red();
            sensorColor.green();
            sensorColor.blue();

            sensorColor.alpha();
            sensorColor.argb();

            int RGB = sensorColor.red() + sensorColor.green() + sensorColor.blue();
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);
            telemetry.addData("RGB", RGB); //This is for display purposes, dont worry about it
            //yellow = 90, 110, 140, 130, 145, mean of 70
            //white = 200, 200, 240, 280, 330, mean of 100


        }






//For all my little Java learners, I made this very simple. Once you are ready to find out what everything means, see the methods describing the variables
        //They are at the top

        down();
        Forward(200);
        mainUp();
        TRDistance(.25);
        sleep(200);
        mainUp();
        Backward(400);
        mainUp();
        Turn360();
        Forward(600);
        sleep(1500);
        int RGB = sensorColor.red() + sensorColor.blue() + sensorColor.green();
        if (RGB >= 100){

            mainUp();
            TurnRight90();
            mainUp();
            Forward(500);
            mainUp();
            TurnLeft90();
            int RGB1 = sensorColor.red() + sensorColor.blue() + sensorColor.green();
            if (RGB1 >= 100) {

                mainUp();
                TurnRight90();
                mainUp();
                Forward(500);
                mainUp();
                TurnLeft90();
                Forward(500);
            }
            else{

                Forward(500);
            }
        }
        else{

            Forward(500);
        }

        stop();




    }




}
