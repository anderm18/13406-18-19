package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name= "AutoEncoders", group = "1")
public class EnconderAuto extends LinearOpMode {

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
    private Servo colorArm = null;
    private CRServo yeetus = null;

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;


    public void mainUp(){

        mainScoop.setPower(1);
        sleep(250);
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {

        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = leftFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightFrontTarget = rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            leftFront.setPower(Math.abs(speed));
            rightFront.setPower(Math.abs(speed));
            leftBack.setPower(Math.abs(speed));
            rightBack.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget, newRightBackTarget, newLeftBackTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftFront.getCurrentPosition(),
                        rightFront.getCurrentPosition());
                        leftBack.getCurrentPosition();
                        rightBack.getCurrentPosition();

                telemetry.update();
            }

            // Stop all motion;
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }





    public void encoderDown(double speed,
                             double downInches,
                             double timeoutS) {
        int newDownTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newDownTarget = lifter.getCurrentPosition() + (int)(downInches * COUNTS_PER_INCH);


            lifter.setTargetPosition(newDownTarget);


            // Turn On RUN_TO_POSITION
            lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            lifter.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newDownTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftFront.getCurrentPosition(),
                        rightFront.getCurrentPosition());
                leftBack.getCurrentPosition();
                rightBack.getCurrentPosition();

                telemetry.update();
            }

            // Stop all motion;
            lifter.setPower(0);

            // Turn off RUN_TO_POSITION
            lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }


    public void Down(){

        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lifter.setTargetPosition(11200);

        lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lifter.setPower(.5);

        while(lifter.isBusy())
        {
            //wait until target position is reached
        }

        lifter.setPower(0);


        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void DriveForwardDistance(){

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setTargetPosition(1120);
        leftFront.setTargetPosition(1120);
        rightBack.setTargetPosition(1120);
        rightFront.setTargetPosition(1120);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftBack.setPower(.5);
        leftFront.setPower(.5);
        rightFront.setPower(.5);
        rightBack.setPower(.5);

        while(rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy())
        {
            //wait until target position is reached
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    @Override
    public void runOpMode() {

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
        colorArm = hardwareMap.get(Servo.class, "colorArm");
        yeetus = hardwareMap.get(CRServo.class, "yeetus");

        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        colorArm.setDirection(Servo.Direction.FORWARD);


        waitForStart();

       // encoderDown(DRIVE_SPEED, 50, 5);

        Down();

        encoderDrive(DRIVE_SPEED,  -5,  -5, .5);  // S1: Forward 47 Inches with 5 Sec timeout
        mainUp();
        encoderDrive(DRIVE_SPEED, -1, -1, .5);
        mainUp();
        encoderDrive(TURN_SPEED,   6, -6, .5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 2, 2, .5);
        mainUp();
        encoderDrive(TURN_SPEED,   8, -8, .5);
        mainUp();
        encoderDrive(TURN_SPEED,   8, -8, .5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 11,11,.5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 11,11,.5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 11,11,.5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 11,11,.5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 11,11,.5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 5, 5, .5);
        yeetus.setPower(1);
        sleep(2000);
        yeetus.setPower(-1);
        sleep(1000);
        encoderDrive(DRIVE_SPEED, -8, 8, .5);
        encoderDrive(DRIVE_SPEED, -8, 8, .5);
        encoderDrive(DRIVE_SPEED, -115,-115,.5);

       /* mainUp();
        encoderDrive(TURN_SPEED, -14, 14, .5);
        mainUp();
        encoderDrive(DRIVE_SPEED, -3.5,-3.5,.5);

        colorArm.resetDeviceConfigurationForOpMode();
        colorArm.setPosition(.65);

        sleep(3000);
        int RGB = sensorColor.red() + sensorColor.blue() + sensorColor.green();
        if (RGB > 90){

            encoderDrive(DRIVE_SPEED, 7.25, 7.25, .5);
            encoderDrive(TURN_SPEED, 2, -2, .5);
            encoderDrive(DRIVE_SPEED, 6.5, 6.5, 4.0);
            int RGB2 = sensorColor.red() + sensorColor.blue() + sensorColor.green();
            if (RGB2 > 90){

                mainUp();
                colorArm.resetDeviceConfigurationForOpMode();
                colorArm.setPosition(Servo.MIN_POSITION);

                encoderDrive(DRIVE_SPEED, -20,-20,.5);

                encoderDrive(DRIVE_SPEED, -10,-10,.5);

                encoderDrive(DRIVE_SPEED, -20,-20,.5);

                colorArm.resetDeviceConfigurationForOpMode();
                colorArm.setPosition(Servo.MIN_POSITION);

                encoderDrive(TURN_SPEED, -20, 20, .5);

                encoderDrive(DRIVE_SPEED, -20, -20, .5);

                encoderDrive(TURN_SPEED, -14,-14,.5);

                encoderDrive(DRIVE_SPEED, -20, -20, .5);
                encoderDrive(DRIVE_SPEED, -20, -20, .5);

                yeetus.setPower(1);
                sleep(250);
                yeetus.setPower(-1);
                sleep(250);

                mainUp();

                encoderDrive(DRIVE_SPEED, 80,80,.5);
                stop();


            }
            else{

                encoderDrive(TURN_SPEED, 9, -9, 1.0);

            }
        }
        else{

            encoderDrive(TURN_SPEED, 8, -8, 1.0);

        }*/



        // S2: Turn Right 12 Inches with 4 Sec timeout
       // encoderDrive(DRIVE_SPEED, -24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout


    }

}