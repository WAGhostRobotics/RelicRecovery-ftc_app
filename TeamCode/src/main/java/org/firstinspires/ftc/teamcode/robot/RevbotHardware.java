package org.firstinspires.ftc.teamcode.robot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.settings.SettingsActivity;

/**
 * Created by josh on 11/12/17.
 */

public class RevbotHardware {

    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor strafe = null;
    public DcMotor cubeLift = null;

    public Servo clawRight = null;
    public Servo clawLeft = null;
    public Servo fondler = null;

    public ColorSensor color = null;

    public ElapsedTime elapsedTime = new ElapsedTime();

    HardwareMap hwMap = null;
    public Resources res = null;
    public Intent preferenceIntent = null;
    public SharedPreferences preferences = null;

    public RevbotHardware() {

    }

    public void init(HardwareMap aHwMap) {
        hwMap = aHwMap;

        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        strafe = hwMap.get(DcMotor.class, "strafe");
        cubeLift = hwMap.get(DcMotor.class, "cubeLift");

        clawRight = hwMap.get(Servo.class, "clawRight");
        clawLeft = hwMap.get(Servo.class, "clawLeft");
        fondler = hwMap.get(Servo.class, "fondler");

        color = hwMap.get(ColorSensor.class, "color");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        strafe.setDirection(DcMotor.Direction.REVERSE);
        cubeLift.setDirection(DcMotor.Direction.FORWARD);

        res = hwMap.appContext.getResources();
        preferenceIntent = new Intent(hwMap.appContext, SettingsActivity.class);
        preferences = PreferenceManager.getDefaultSharedPreferences(hwMap.appContext);
    }
}
