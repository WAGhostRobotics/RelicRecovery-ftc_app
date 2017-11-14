package org.firstinspires.ftc.teamcode.autonomous.helper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;
import org.firstinspires.ftc.teamcode.robot.RevbotValues;

/**
 * Created by josh on 11/12/17.
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

    /***
     * Initialize the claw to closed position.
     */
    public void initClaw() {
        robot.clawRight.setPosition(RevbotValues.RIGHT_CLAW_CLOSED_VALUE);
        robot.clawLeft.setPosition(RevbotValues.LEFT_CLAW_CLOSED_VALUE);
    }
}
