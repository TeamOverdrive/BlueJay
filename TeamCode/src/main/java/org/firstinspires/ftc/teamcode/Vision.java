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

public class Vision extends Robot{

    OpenCvCamera webcam;

    int cameraMonitorViewId;

    int skystonePos;

    public Vision(LinearOpMode linearOpMode) {

        super.linearOpMode = linearOpMode;

        cameraMonitorViewId = linearOpMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", linearOpMode.hardwareMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(super.linearOpMode.hardwareMap.get(WebcamName.class, "Webcam8"), cameraMonitorViewId);

        webcam.openCameraDevice();

        webcam.setPipeline(new pipeline());

        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

    }
    public void init() {

        webcam.openCameraDevice();

        webcam.setPipeline(new pipeline());

        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

    }
class pipeline extends OpenCvPipeline {
    int skystonePos = 0;
    public Mat processFrame(Mat input) {
        findSkystone(input, skystonePos);
        return input;
    }
    public int leastYellow(PointData[] points) {
        // DO NOT input less than 3 points
        if (points[0].dist(255,100,0) >= points[1].dist(255,100,0 ) &&
                points[0].dist(255,100,0) >= points[2].dist(255,100,0 )) {
            return 0;
        }
        else if (points[1].dist(255,100,0) >= points[2].dist(255,100,0 ) &&
                points[1].dist(255,100,0) >= points[0].dist(255,100,0 )) {
            return 1;
        } else {
            return 2;
        }
    }
    public Mat findSkystone(Mat img, int skystonePos) {
        PointData[] points = new PointData[3];
        points[0] = new PointData(img.get(50,75));
        points[1] = new PointData(img.get(150,75));
        points[2]  = new PointData(img.get(250,75));
        skystonePos = leastYellow(points);
        if (skystonePos == 0) {
            Imgproc.circle(img,new Point(50,75),5, new Scalar(0,255,0));
            Imgproc.circle(img,new Point(150,75),5, new Scalar(0,0,0));
            Imgproc.circle(img,new Point(250,75),5, new Scalar(0,0,0));

        } else if (skystonePos == 1) {
            Imgproc.circle(img,new Point(50,75),5, new Scalar(0, 0,0));
            Imgproc.circle(img,new Point(150,75),5, new Scalar(0,255,0));
            Imgproc.circle(img,new Point(250,75),5, new Scalar(0,0,0));

        } else {
            Imgproc.circle(img,new Point(50,75),5, new Scalar(0, 0,0));
            Imgproc.circle(img,new Point(150,75),5, new Scalar(0,0,0));
            Imgproc.circle(img,new Point(250,75),5, new Scalar(0,255,0));
        }
        return img;
    }


}
}
