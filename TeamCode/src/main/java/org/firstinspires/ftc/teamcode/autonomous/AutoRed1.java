package org.firstinspires.ftc.teamcode.autonomous;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.helper.BallFondle;
import org.firstinspires.ftc.teamcode.autonomous.helper.Claw;
import org.firstinspires.ftc.teamcode.autonomous.helper.Movement;
import org.firstinspires.ftc.teamcode.robot.RevbotHardware;

/**
 * Created by 3565 on 11/11/2017.
 */

@Autonomous(name = "Autonomous (Red-1)", group = "Auto")
public class AutoRed1 extends LinearOpMode {

    RevbotHardware robot = new RevbotHardware();
    Claw claw = new Claw();
    Movement movement = new Movement();
    BallFondle fondle = new BallFondle();

    SharedPreferences preferences;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        preferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);

        claw.initClaw();
        fondle.initFondler();

        telemetry.addData("Status: ", "Initialized");
        waitForStart();

        movement.strafeLeft(1, 1);

        fondle.fondleBalls("RED");

        movement.strafeLeft(1,1);

        movement.backward(4, 1);
    }
}
