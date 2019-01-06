package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="CVAuto", group="Double Sample")

public class CVAuto extends LinearOpMode {

    // Declare OpMode members.


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
    static final double     DRIVE_SPEED             = .75;
    static final double     TURN_SPEED              = .75;


    private GoldAlignDetector detector;

    public void setAllMotors(double power){

        leftBack.setPower(power);
        leftFront.setPower(power);
        rightBack.setPower(power);
        rightFront.setPower(power);

    }

    public void DriveForwardDistance(){

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setTargetPosition(-2120);
        leftFront.setTargetPosition(-2120);
        rightBack.setTargetPosition(-2120);
        rightFront.setTargetPosition(-2120);

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



    private boolean motorsBusy(){
        return leftFront.isBusy() && leftFront.isBusy() && rightFront.isBusy() && rightBack.isBusy();
    }

    public void mainUp(){

        mainScoop.setPower(1);
        sleep(250);
    }

    public void Down(){

        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lifter.setTargetPosition(11200);

        lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lifter.setPower(1);

        while(lifter.isBusy())
        {
            //wait until target position is reached
        }

        lifter.setPower(0);


        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.downscale = 0.3;
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 75;
        detector.alignPosOffset = 0;

        detector.enable();

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

        Down();

        encoderDrive(DRIVE_SPEED,  -5,  -5, .5);
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
        encoderDrive(DRIVE_SPEED, 3,3,.5);
        mainUp();
        encoderDrive(TURN_SPEED, -10,10,.5);
        mainUp();
        encoderDrive(TURN_SPEED, -10,10,.5);
        mainUp();
        encoderDrive(TURN_SPEED, -8,8,.5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 15, 15, .5);
        mainUp();
        encoderDrive(DRIVE_SPEED, 15, 15, .5);
        mainUp();
        encoderDrive(DRIVE_SPEED,15,15,.5);

        while(detector.getAligned() == false){
           leftFront.setPower(-.2);
           rightBack.setPower(-.2);
           leftBack.setPower(-.2);
           rightFront.setPower(-.2);
        }

        if (detector.getAligned() == true){
            encoderDrive(TURN_SPEED, -20,20,.5);
            mainUp();
            encoderDrive(TURN_SPEED, -20,20,.5);
            mainUp();
            encoderDrive(TURN_SPEED, -20,20,.5);
            mainUp();
            DriveForwardDistance();
        }




        detector.disable();

    }



    private void moveEncoder(int ticksLeft, int ticksRight, double speed){
        int lfPose = leftFront.getCurrentPosition() + ticksLeft;
        int lrPose = leftFront.getCurrentPosition() + ticksLeft;
        int rfPos = rightFront.getCurrentPosition() + ticksRight;
        int rrPos = rightBack.getCurrentPosition() + ticksRight;

        leftFront.setTargetPosition(lfPose);
        leftBack.setTargetPosition(lrPose);
        rightFront.setTargetPosition(rfPos);
        rightFront.setTargetPosition(rrPos);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        leftBack.setPower(speed);
        rightFront.setPower(speed);
        rightFront.setPower(speed);
    }


}
