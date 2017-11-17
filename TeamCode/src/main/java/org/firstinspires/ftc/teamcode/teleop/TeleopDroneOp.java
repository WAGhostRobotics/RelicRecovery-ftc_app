package org.firstinspires.ftc.teamcode.teleop;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.autonomous.helper.Claw;
import org.firstinspires.ftc.teamcode.robot.RevbotHardware;
import org.firstinspires.ftc.teamcode.robot.RevbotValues;

/**
 * Created by 3565 on 10/20/2017.
 */
/*

                        _                            _
                   _.-'` `-._                    _,-' `'-._
                ,-'          `-.,____________,.-'    .-.   `-.
               /   .---.             ___            ( Y ) -   \
              /  ,' ,-. `.     __   / X \   __   .-. `-` .-.   \
             /   | |   | |    (__) | / \ | (__) ( X )   ( B )   \
            /    `. `-' ,'    __    \___/        `-` ,-. `-`     \
            |      `---`   ,-`  `-.       .---.     ( A )        |
            |             / -'  `- \    ,'  .  `.    `-`         |
            |            |          |   | -   - |                |
            !             \ -.  ,- /    `.  '  ,'                |
            |              `-.__,-'       `---`                  |
            |                  ________________                  |
            |             _,-'`                ``-._             |
            |          ,-'                          `-.          |
             \       ,'                                `.       /
              `.__,-'                                    `-.__,'
 */
@TeleOp(name="Drone Op", group="TeleOp")
public class TeleopDroneOp extends LinearOpMode {

    RevbotHardware robot = new RevbotHardware();
    Claw claw = new Claw();

    public double rightPower;
    public double leftPower;
    public double strafePower;
    public double turnPower;
    public double hyperPrecision;
    public boolean smartDirect;
    public double[] directSave = new double[3];

    MediaPlayer up;
    MediaPlayer down;

    public final double HP_STRENGTH=4;

    @Override
    public void runOpMode() throws InterruptedException {
                directSave[0] = 0;
        directSave[1] = 0;
        directSave[2] = 0;

        up = MediaPlayer.create(hardwareMap.appContext, R.raw.slideup);
        down = MediaPlayer.create(hardwareMap.appContext, R.raw.slidedown);

        telemetry.addData("Status:", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            hyperPrecision = gamepad1.left_trigger*HP_STRENGTH  + 1;
            leftPower = gamepad1.left_stick_y;
            rightPower = -gamepad1.left_stick_y;
            smartDirect = gamepad1.left_bumper;

            turnPower = gamepad1.right_stick_x;

            strafePower = -gamepad1.left_stick_x;
            leftPower -= turnPower;
            rightPower -= turnPower;

            strafePower /= hyperPrecision;
            leftPower /= hyperPrecision;
            rightPower /= hyperPrecision;
            turnPower /= hyperPrecision;

            //Tests to see if directSave is on. If so, use saved movement.

            if(gamepad1.right_trigger > 0.1){
                robot.leftDrive.setPower(directSave[0] * gamepad1.right_trigger);
                robot.rightDrive.setPower(directSave[1] * gamepad1.right_trigger);
                robot.strafe.setPower(directSave[2] * gamepad1.right_trigger);
            }else{

               if (!smartDirect || (smartDirect && Math.abs(gamepad1.left_stick_y) >= Math.abs(gamepad1.left_stick_x))){

                 robot.leftDrive.setPower(leftPower);
                 robot.rightDrive.setPower(rightPower);

                   if(smartDirect && Math.abs(gamepad1.left_stick_y) >= Math.abs(gamepad1.left_stick_x)){
                       robot.strafe.setPower(0);
                   }

              }

               if (!smartDirect || (smartDirect && Math.abs(gamepad1.left_stick_y) < Math.abs(gamepad1.left_stick_x))){

                 robot.strafe.setPower(-strafePower);

                   if(smartDirect && Math.abs(gamepad1.left_stick_y) < Math.abs(gamepad1.left_stick_x)){
                       robot.leftDrive.setPower(0);
                       robot.rightDrive.setPower(0);
                   }

                }

               if(gamepad1.right_bumper){

                    directSave[0] = robot.leftDrive.getPower();
                    directSave[1] = robot.rightDrive.getPower();
                    directSave[2] = robot.strafe.getPower();

               }
            }
            if(gamepad1.dpad_up) {
                robot.cubeLift.setPower(-1.0);
                up.start();
            }
            else if(gamepad1.dpad_down) {
                robot.cubeLift.setPower(0.5);
                down.start();
            }
            else {
                robot.cubeLift.setPower(0.0);
                up.stop();
                down.stop();
            }

            if(gamepad1.dpad_left){
                robot.clawLeft.setPosition(RevbotValues.LEFT_CLAW_CLOSED_VALUE);
                robot.clawRight.setPosition(RevbotValues.RIGHT_CLAW_CLOSED_VALUE);
            }
            if(gamepad1.dpad_right) {
                robot.clawLeft.setPosition(RevbotValues.LEFT_CLAW_OPENED_VALUE);
                robot.clawRight.setPosition(RevbotValues.RIGHT_CLAW_OPENED_VALUE);
            }

            telemetry.addData("Status", "Running \nServoPosition: " + robot.clawRight.getPosition());
            telemetry.update();


        }
    }
}
