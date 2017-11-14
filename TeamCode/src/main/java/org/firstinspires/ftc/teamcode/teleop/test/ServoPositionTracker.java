package org.firstinspires.ftc.teamcode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 3565 on 11/3/2017.
 */

@TeleOp(name = "Servo Position Tracker",group = "Test")
public class ServoPositionTracker extends LinearOpMode {

    Servo claw1;
    Servo claw2;

    @Override
    public void runOpMode() throws InterruptedException {
        claw1 = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class, "claw2");

        waitForStart();

        while (opModeIsActive()) {
            double pos1 = claw1.getPosition();
            double pos2 = claw2.getPosition();
            claw1.setPosition(0.5);
            claw2.setPosition(0.5);

            telemetry.addData("Claw 1:", pos1);
            telemetry.addData("Claw 2:", pos2);
            telemetry.update();
        }
    }
}
