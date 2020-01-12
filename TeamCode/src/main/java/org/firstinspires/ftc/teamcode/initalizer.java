package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import detectors.FoundationPipeline.Pipeline;
import detectors.ImageDetector;

@Autonomous(name = "WebCam Example Circle", group = "vision")
public class initalizer extends LinearOpMode {
    OpenCvCamera webcam;
    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam8"), cameraMonitorViewId);

       /* webcam.openCameraDevice();

        webcam.setPipeline(new visionPT());


        */
        ImageDetector detector = new ImageDetector(this, true);
        detector.start();
        Pipeline.doSkyStones = true;



       // webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        waitForStart();
    }
}
