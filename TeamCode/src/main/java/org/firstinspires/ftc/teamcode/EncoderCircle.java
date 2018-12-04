package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="EC", group="13406")
public class EncoderCircle extends OpMode {

    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor rightScoop = null;
    private DcMotor leftScoop = null;
    private DcMotor mainScoop = null;
    private CRServo yeetus = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightScoop = hardwareMap.get(DcMotor.class, "rightScoop");
        leftScoop = hardwareMap.get(DcMotor.class, "leftScoop");
        mainScoop = hardwareMap.get(DcMotor.class, "mainScoop");
        yeetus = hardwareMap.get(CRServo.class, "yeetus");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftScoop.setDirection(DcMotor.Direction.FORWARD);
        rightScoop.setDirection(DcMotor.Direction.REVERSE);
        mainScoop.setDirection(DcMotor.Direction.FORWARD);

    }


    @Override
    public void init_loop() {
    }


    @Override
    public void start() {
    }

    @Override
    public void loop() {

        double leftPower;
        double rightPower;


        double drive = -gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);


        leftFront.setPower(leftPower);
        rightFront.setPower(rightPower);
        leftBack.setPower(leftPower);
        rightBack.setPower(rightPower);
        rightScoop.setPower(gamepad2.right_stick_y*2/3);
        leftScoop.setPower(gamepad2.left_stick_y*2/3);
        mainScoop.setPower(gamepad2.left_trigger*3/4);
        mainScoop.setPower(-gamepad2.right_trigger*3/4);
        yeetus.setPower(gamepad1.right_trigger*2/3);
        yeetus.setPower(-gamepad1.left_trigger*2/3);

        int position = mainScoop.getCurrentPosition();
        telemetry.addData("Encoder Position", position);
    }

    @Override
    public void stop() {
    }

}