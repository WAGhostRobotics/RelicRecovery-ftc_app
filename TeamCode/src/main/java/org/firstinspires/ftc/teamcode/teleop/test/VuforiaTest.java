package org.firstinspires.ftc.teamcode.teleop.test;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
 * Created by 3565 on 10/6/2017.
 */

@TeleOp(name = "Vuforia Test", group = "Test")
public class VuforiaTest extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";
    boolean played = false;

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {

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

        relicTrackables.activate();

        while (opModeIsActive()) {

            // Check if any VuMarks are visible
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                playNotificationSound(true);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }

            } else {
                telemetry.addData("VuMark", "not visible");
                playNotificationSound(false);
            }

            telemetry.update();
        }
    }

    public void playNotificationSound(boolean hasSeen) {

        if (!hasSeen) {
            played = false;
        }

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(hardwareMap.appContext, notification);
            if (hasSeen && !played) {
                r.play();
                played = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
