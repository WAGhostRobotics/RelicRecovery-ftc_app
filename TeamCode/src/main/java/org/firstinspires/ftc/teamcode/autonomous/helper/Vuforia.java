package org.firstinspires.ftc.teamcode.autonomous.helper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;

/**
 * Created by josh on 11/12/17.
 */

@Autonomous(name = "Vuforia", group = "Other")
@Disabled
public class Vuforia extends LinearOpMode {

    RevbotHardware robot = new RevbotHardware();

    public Vuforia() {}

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
    }

    // This class includes methods relating to Vuforia

}
