package org.firstinspires.ftc.teamcode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by 3565 on 11/11/2017.
 */

@TeleOp(name = "Sensor Test", group = "Test")
public class SensorTest extends LinearOpMode {
    ColorSensor color;

    int red;
    int green;
    int blue;
    int argb;

    @Override
    public void runOpMode() throws InterruptedException {
        color = hardwareMap.get(ColorSensor.class, "color");

        waitForStart();

        while (opModeIsActive()) {
            red = color.red();
            green = color.green();
            blue = color.blue();
            argb = color.argb();

            telemetry.addData("Red: ", red);
            telemetry.addData("Green: ", green);
            telemetry.addData("Blue", blue);
            telemetry.addData("argb: ", argb);
            telemetry.update();
        }
    }
}
