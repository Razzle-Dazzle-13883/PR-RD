package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Robot {
    DcMotor leftFront;
    DcMotor leftRear;
    DcMotor rightFront;
    DcMotor rightRear;

    IMU imu;

    final int TICKS_PER_INCH = 45; // 11.87 in per rev; 537.7 ticks per rev; 537.7/11.87 ticks per inch

    int leftFrontPos = 0;
    int leftBackPos = 0;
    int rightFrontPos = 0;
    int rightBackPos = 0;

    private LinearOpMode myOpMode;
    public Robot(LinearOpMode opMode){
        this.myOpMode = opMode;
    }

    public void initHardware(){
        // Declare our motors
        // Make sure your ID's match your configuration
        leftFront = myOpMode.hardwareMap.dcMotor.get("leftFront");
        leftRear = myOpMode.hardwareMap.dcMotor.get("leftRear");
        rightFront = myOpMode.hardwareMap.dcMotor.get("rightFront");
        rightRear = myOpMode.hardwareMap.dcMotor.get("rightRear");

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        initIMU();
    }

    public void robotMove(double frontLeftPower, double backLeftPower, double frontRightPower, double backRightPower){
        leftFront.setPower(frontLeftPower);
        leftRear.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightRear.setPower(backRightPower);
    }

    public void robotBrakeBehavior(){
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(int lF, int lB, int rF, int rB, double speed) {

        leftFrontPos -= lF;
        leftBackPos -= lB;
        rightFrontPos -= rF;
        rightBackPos -= rB;

        leftFront.setTargetPosition(leftFrontPos);
        leftRear.setTargetPosition(leftBackPos);
        rightFront.setTargetPosition(rightFrontPos);
        rightRear.setTargetPosition(rightBackPos);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double driftFactor = 1.042;
        leftFront.setPower(speed);
        leftRear.setPower(speed);
        rightFront.setPower(speed);
        rightRear.setPower(speed);

        waitDrive();
    }
    private void waitDrive() {
        while (leftFront.isBusy() && rightFront.isBusy() && leftRear.isBusy() && rightRear.isBusy() && myOpMode.opModeIsActive()) ;
    }

    private void initIMU() {
        // Retrieve the IMU from the hardware map
        imu = myOpMode.hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }
}