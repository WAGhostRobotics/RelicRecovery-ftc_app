package org.firstinspires.ftc.teamcode.autonomous.helper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;
import org.firstinspires.ftc.teamcode.robot.RevbotValues;

/**
 * Created by Josh on 11/12/17.
 */

@Autonomous(name = "Claw", group = "Other")
@Disabled
public class Claw extends LinearOpMode {

    RevbotHardware robot = new RevbotHardware();

    public Claw() {}

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
    }

    public void openClaw() {
        setClawPos(RevbotValues.RIGHT_CLAW_OPENED_VALUE, RevbotValues.LEFT_CLAW_OPENED_VALUE);
    }

    public void closeClaw() {
        setClawPos(RevbotValues.RIGHT_CLAW_CLOSED_VALUE, RevbotValues.LEFT_CLAW_CLOSED_VALUE);
    }

    public void setClawPos(double leftPos, double rightPos) {
        robot.clawLeft.setPosition(leftPos);
        robot.clawRight.setPosition(rightPos);
    }
}
