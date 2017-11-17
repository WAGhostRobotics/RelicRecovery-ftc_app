package org.firstinspires.ftc.teamcode.autonomous;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.autonomous.helper.BallFondle;
import org.firstinspires.ftc.teamcode.autonomous.helper.Claw;
import org.firstinspires.ftc.teamcode.autonomous.helper.Movement;
import org.firstinspires.ftc.teamcode.robot.RevbotHardware;
import org.firstinspires.ftc.teamcode.settings.SettingsActivity;

/**
 * Created by 3565 on 11/11/2017.
 */

@Autonomous(name = "Autonomous (Red-1)", group = "Auto")
public class AutoRed1 extends LinearOpMode {

    RevbotHardware robot = new RevbotHardware();
    Claw claw = new Claw();
    Movement movement = new Movement();
    BallFondle fondle = new BallFondle();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.preferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);

        claw.closeClaw();
        fondle.initFondler();

        hardwareMap.appContext.startActivity(robot.preferenceIntent);

        telemetry.addData("Status: ", "Initialized");
        waitForStart();

        movement.strafeLeft(1, 1);

        fondle.fondleBalls(robot.preferences.getString(robot.res.getString(R.string.pref_alliance_key), "red"));

        movement.strafeLeft(1,1);

        movement.backward(4, 1);
    }
}
