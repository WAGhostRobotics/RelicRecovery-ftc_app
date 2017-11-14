package org.firstinspires.ftc.teamcode.autonomous.test;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by 3565 on 10/27/2017.
 */

@Autonomous(name="AutoViewTest",group="Test")
public class AutoViewTest extends LinearOpMode {

    private DcMotor strafe;
    VuforiaLocalizer vuforia;
    @Override
    public void runOpMode() throws InterruptedException {

        strafe = hardwareMap.get(DcMotor.class, "strafe");
        // Initialize the Vuforia stuff (license key, camera direction, camera monitor view ID)
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AV+GL7P/////AAAAGV7nYsIVuU1VqFIOfsYp0KQh9xxfhpv8vYZhVm2dOSNCK0IZ89FNdUqXUDb6FTmwosSwYv2iGyNNaeH8OGd+EYA+URkJXmtxYXTSjSxlfL7ijgu118//656cnaSAP9MIVR/y49UXnlSr9iRk2N9zUunYC4EJUpPNn6cLW4wV1t4lHtxdKHu5OQ3n7hiJVkJw+5ax0SvQ9QW6H2XcR6BpNQgN0v15zs8anuqiaRoWzV5wIqBc2NWMnmNDCuRy9de9uJPRZFglQXX5Kq1wuVH7N/B+nVRpVmJ8jnIKpEVO+nM8l7HiCfOpwdteALuWimYChVWCms06HjOZ58U3UjEHjXjELlqS9w2iYMWPOvA17HMx";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        // Load the data
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        telemetry.addData("Vuforia:", "Initialized");
        telemetry.update();

        waitForStart();
        double direction = 1.0;

        while (opModeIsActive()){
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            while(vuMark != RelicRecoveryVuMark.UNKNOWN) {
                strafe.setPower(direction);
            }
            direction *= -1;
            while(vuMark == RelicRecoveryVuMark.UNKNOWN){
                strafe.setPower(direction);
            }
        }
    }
}
