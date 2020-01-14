package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import detectors.FoundationPipeline.Pipeline;
import detectors.ImageDetector;

@Autonomous(name = "WebCam TEST", group = "vision")
public class initalizer extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Vision cam = new Vision(this);
        waitForStart();
        if (cam.findSkystone.skystonePos == 1) {
            telemetry.addLine("YEET");
            telemetry.update();
        }
    }
}
