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
public class Auto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot(this);

        Vision cam = new Vision(this);

        waitForStart();

        robot.drive.brake();
        robot.getDrive().setUnit(robot.getDrive().INCHES);

    }
}
