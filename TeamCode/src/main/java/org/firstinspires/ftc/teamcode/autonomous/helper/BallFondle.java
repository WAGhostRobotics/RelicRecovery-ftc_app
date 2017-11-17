package org.firstinspires.ftc.teamcode.autonomous.helper;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;
import org.firstinspires.ftc.teamcode.robot.RevbotValues;

/**
 * Created by 3565 on 11/11/2017.
 */

@Autonomous(name = "BallFondle", group = "Other")
@Disabled
public class BallFondle extends LinearOpMode {

    RevbotHardware robot;

    public BallFondle(RevbotHardware aRobot) {
        robot = aRobot;
    }

    @Override
    public void runOpMode() throws InterruptedException {
    }

    /***
     * Initialize the fondler to center position.
     */
    public void initFondler() {
        robot.fondler.setPosition(0.5);
    }

    public boolean isBlue() {
        return (robot.color.blue() > robot.color.red() && robot.color.blue() > robot.color.green());
    }

    public boolean isRed() {
        return (robot.color.red() > robot.color.blue() && robot.color.red() > robot.color.green());
    }

    /***
     *
     * @param teamColor The color of the current alliance (yes, I know that's not how you're supposed to use "Color"s
     */
    public void fondleBalls(String teamColor) {

        if ((teamColor.equals("blue") && isBlue()) || (teamColor.equals("red") && isRed())) {
            robot.fondler.setPosition(RevbotValues.FONDLER_RIGHT_VALUE);
        } else if ((teamColor.equals("blue") && isRed()) || (teamColor.equals("red") && isBlue())) {
            robot.fondler.setPosition(RevbotValues.FONDLER_LEFT_VALUE);
        }

        robot.fondler.setPosition(RevbotValues.FONDLER_CENTER_VALUE);
    }

}
