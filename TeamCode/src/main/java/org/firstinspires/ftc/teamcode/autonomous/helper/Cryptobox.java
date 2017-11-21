package org.firstinspires.ftc.teamcode.autonomous.helper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;

/**
 * Created by josh on 11/12/17.
 */

@Autonomous(name = "Cryptobox", group = "Other")
@Disabled
public class Cryptobox extends LinearOpMode {

    RevbotHardware robot;

    public Cryptobox() {}

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
    }

}
