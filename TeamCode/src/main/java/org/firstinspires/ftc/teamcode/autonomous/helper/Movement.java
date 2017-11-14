package org.firstinspires.ftc.teamcode.autonomous.helper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.RevbotHardware;

/**
 * Created by 3565 on 11/11/2017.
 */

@Autonomous(name = "Movement", group = "Other")
@Disabled
public class Movement extends LinearOpMode {

    RevbotHardware robot = new RevbotHardware();

    public Movement() {}

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
    }

    /***
     * Strafe left (using motor "strafe")
     * @param seconds seconds to wait
     * @param power power, between 0 and 1
     */
    public void strafeLeft(double seconds, double power) {
        robot.strafe.setPower(power);
        sleep((long) seconds*1000);
        robot.strafe.setPower(0);
    }

    /***
     * Strafe right (using motor "strafe")
     * @param seconds seconds to wait
     * @param power power, between 0 and 1
     */
    public void strafeRight(double seconds, double power){
        strafeLeft(seconds, -power);
    }

    /***
     * Drive forward (using motors "leftDrive" and "rightDrive)
     * @param seconds seconds to wait
     * @param power power, between 0 and 1
     */
    public void forward(double seconds, double power) {
        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(power);
        sleep((long) seconds*1000);
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }

    /***
     * Drive backward (using motors "leftDrive" and "rightDrive)
     * @param seconds seconds to wait
     * @param power power, between 0 and 1
     */
    public void backward(double seconds, double power){
        forward(seconds, -power);
    }

    /***
     * Turn left (using motors "leftDrive" and "rightDrive)
     * @param seconds seconds to wait
     * @param power power, between 0 and 1
     */
    public void turnLeft(double seconds, double power) {
        robot.leftDrive.setPower(-power);
        robot.rightDrive.setPower(power);
        sleep((long) seconds*1000);
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }

    /***
     * Turn right (using motors "leftDrive" and "rightDrive)
     * @param seconds seconds to wait
     * @param power power, between 0 and 1
     */
    public void turnRight(double seconds, double power) {
        turnLeft(seconds, -power);
    }

}
