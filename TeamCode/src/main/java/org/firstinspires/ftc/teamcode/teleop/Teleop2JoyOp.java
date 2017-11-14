package org.firstinspires.ftc.teamcode.teleop;

import android.app.AlertDialog;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by 3565 on 10/6/2017.
 */

@TeleOp(name = "TeleOp 2Joy", group = "Test Programs")
public class Teleop2JoyOp extends LinearOpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor strafe;

    @Override
    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        strafe = hardwareMap.get(DcMotor.class, "strafe");

        telemetry.addData("Status:", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            leftDrive.setPower(this.gamepad1.left_stick_y);
            rightDrive.setPower(-this.gamepad1.right_stick_y);
            strafe.setPower(setStrafePower(gamepad1));

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

    public double setStrafePower(Gamepad gamepad) {
        if (gamepad.right_bumper) {
            return -1.0;
        } else if (gamepad.left_bumper) {
            return 1.0;
        } else {
            return 0;
        }
    }
}