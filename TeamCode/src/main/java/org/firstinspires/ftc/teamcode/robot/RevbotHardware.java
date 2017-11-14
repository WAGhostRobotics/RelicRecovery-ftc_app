package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by josh on 11/12/17.
 */

public class RevbotHardware {

    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor strafe = null;
    public DcMotor clawLower = null;
    public DcMotor clawUpper = null;

    public Servo clawRight = null;
    public Servo clawLeft = null;
    public Servo fondler = null;

    public ColorSensor color = null;

    public ElapsedTime elapsedTime = new ElapsedTime();

    HardwareMap hwMap = null;

    public RevbotHardware() {

    }

    public void init(HardwareMap aHwMap) {
        hwMap = aHwMap;

        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        strafe = hwMap.get(DcMotor.class, "strafe");
        clawLower = hwMap.get(DcMotor.class, "clawLower");
        clawUpper = hwMap.get(DcMotor.class, "clawUpper");

        clawRight = hwMap.get(Servo.class, "clawRight");
        clawLeft = hwMap.get(Servo.class, "clawLeft");
        fondler = hwMap.get(Servo.class, "fondler");

        color = hwMap.get(ColorSensor.class, "color");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }
}
