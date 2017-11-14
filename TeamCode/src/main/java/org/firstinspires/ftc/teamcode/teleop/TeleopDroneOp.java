package org.firstinspires.ftc.teamcode.teleop;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.R;

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

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor strafe;
    private DcMotor cubeLift;
    private Servo claw1;
    private Servo claw2;

    public double leftPower;
    public double rightPower;
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
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        strafe = hardwareMap.get(DcMotor.class, "strafe");
        cubeLift = hardwareMap.get(DcMotor.class, "cubeLift");
        claw1 = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class, "claw2");
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
                leftDrive.setPower(directSave[0] * gamepad1.right_trigger);
                rightDrive.setPower(directSave[1] * gamepad1.right_trigger);
                strafe.setPower(directSave[2] * gamepad1.right_trigger);
            }else{

               if (!smartDirect || (smartDirect && Math.abs(gamepad1.left_stick_y) >= Math.abs(gamepad1.left_stick_x))){

                 leftDrive.setPower(leftPower);
                 rightDrive.setPower(rightPower);

                   if(smartDirect && Math.abs(gamepad1.left_stick_y) >= Math.abs(gamepad1.left_stick_x)){
                       strafe.setPower(0);
                   }

              }

               if (!smartDirect || (smartDirect && Math.abs(gamepad1.left_stick_y) < Math.abs(gamepad1.left_stick_x))){

                 strafe.setPower(-strafePower);

                   if(smartDirect && Math.abs(gamepad1.left_stick_y) < Math.abs(gamepad1.left_stick_x)){
                       leftDrive.setPower(0);
                       rightDrive.setPower(0);
                   }

                }

               if(gamepad1.right_bumper){

                    directSave[0] = leftDrive.getPower();
                    directSave[1] = rightDrive.getPower();
                    directSave[2] = strafe.getPower();

               }
            }
            if(gamepad1.dpad_up) {
                cubeLift.setPower(-1.0);
                up.start();
            }
            else if(gamepad1.dpad_down) {
                cubeLift.setPower(0.5);
                down.start();
            }
            else {
                cubeLift.setPower(0.0);
                up.stop();
                down.stop();
            }

            if(gamepad1.dpad_left){
                claw1.setPosition(0.2);
                claw2.setPosition(0.8);
            }
            if(gamepad1.dpad_right){

                claw1.setPosition(0.8);
                claw2.setPosition(0.2);

            }

            telemetry.addData("Status", "Running \nServoPosition: " + claw1.getPosition());
            telemetry.update();


        }
    }
}
