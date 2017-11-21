package org.firstinspires.ftc.teamcode.autonomous.helper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;
import org.firstinspires.ftc.teamcode.robot.RevbotValues;

/**
 * Created by Josh on 11/12/17.
 */

@Autonomous(name = "Claw", group = "Other")
@Disabled
public class Claw extends LinearOpMode {

    RevbotHardware robot;

    public Claw() {}

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
    }

    public void openClaw() {
        setClawPos(RevbotValues.LEFT_CLAW_CLOSED_VALUE, RevbotValues.RIGHT_CLAW_CLOSED_VALUE);
    }

    public void closeClaw() {
        setClawPos(RevbotValues.LEFT_CLAW_CLOSED_VALUE, RevbotValues.RIGHT_CLAW_CLOSED_VALUE);
    }

    public void setClawPos(double leftPos, double rightPos) {
        robot.clawLeft.setPosition(leftPos);
        robot.clawRight.setPosition(rightPos);
    }
}
