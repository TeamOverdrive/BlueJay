package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.nio.channels.Pipe;

public class skystoneCam {

    OpenCvCamera webcam;

    int cameraMonitorViewId;

    Pipeline pipeline;

    int skystonePos;

    public skystoneCam(LinearOpMode linearOpMode) {

        pipeline = new Pipeline();

        cameraMonitorViewId = linearOpMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", linearOpMode.hardwareMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(linearOpMode.hardwareMap.get(WebcamName.class, "Webcam8"), cameraMonitorViewId);

        webcam.openCameraDevice();

        webcam.setPipeline(pipeline);

        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

    }
}
class Pipeline extends OpenCvPipeline {

    public Pipeline() {

    }

    public Mat processFrame(Mat input) {

        double[] i = input.get(100,100);
        if (dist(i,255,100,0) < 50) {
            Imgproc.circle(input,new Point(100,100),5, new Scalar(0,255,0));
        } else {
            Imgproc.circle(input,new Point(100,100),5, new Scalar(0,0,0));

        }
        return input;
    }
    public double dist(double[] pointData, int r, int g, int b) {
        double returnVal = Math.sqrt(((r - pointData[0]) * (r - pointData[0])) + ((g - pointData[1]) * (g - pointData[1])) +
                ((b - pointData[2]) * (b - pointData[2])));
        return returnVal;
    }
}
