package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.math.Line;
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

    pipeline findSkystone;

    int skystonePos;

    public Vision(LinearOpMode linearOpMode) {

        super.linearOpMode = linearOpMode;

        cameraMonitorViewId = linearOpMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", linearOpMode.hardwareMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(super.linearOpMode.hardwareMap.get(WebcamName.class, "Webcam8"), cameraMonitorViewId);

        webcam.openCameraDevice();

        findSkystone = new pipeline(linearOpMode);

        webcam.setPipeline(findSkystone);

        linearOpMode.sleep(1000);

        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

    }
    public void init() {

        webcam.openCameraDevice();

        webcam.setPipeline(new pipeline(linearOpMode));

        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

    }
class pipeline extends OpenCvPipeline {
    int skystonePos = 0;
    LinearOpMode linearOpMode;
    public pipeline(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
    }
    public Mat processFrame(Mat input) {
        Imgproc.medianBlur(input, input, 55);
        Imgproc.rectangle(input, new Point(40,140), new Point(100,100), new Scalar(255,0,0),3);
        Imgproc.rectangle(input, new Point(130,140), new Point(190,100), new Scalar(255,0,0),3);
        Imgproc.rectangle(input, new Point(220,140), new Point(280,100), new Scalar(255,0,0),3);

        double[] Pos1 = averageColor(input,75,125);
        double[] Pos2 = averageColor(input,165,125);
        double[] Pos3 = averageColor(input,255,125);
        try {
            if (Pos1[0] < 100) {
                Imgproc.rectangle(input, new Point(40, 140), new Point(100, 100), new Scalar(0, 255, 0), 3);
                skystonePos = 1;
            } else if (Pos2[0] < 100 && Pos2 != null) {
                Imgproc.rectangle(input, new Point(130, 140), new Point(190, 100), new Scalar(0, 255, 0), 3);
                skystonePos = 2;
            } else if (Pos3[0] < 100 && Pos3 != null) {
                Imgproc.rectangle(input, new Point(220, 140), new Point(280, 100), new Scalar(0, 255, 0), 3);
                skystonePos = 3;
            }
        } catch (Exception e) {

        }

        linearOpMode.telemetry.addData("Pos1[0]: ", Pos1[0]);
        linearOpMode.telemetry.addData("Pos1[1]: ", Pos1[1]);
        linearOpMode.telemetry.addData("Pos1[2]: ", Pos1[2]);
        linearOpMode.telemetry.update();


        return input;
    }
    public double[] averageColor(Mat img, int x, int y) {
        double[] returnVal = new double[2];
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 5; k++) {
                double[] pixel = img.get(i + y,k + x);
                for (int n = 0; n < pixel.length; n++) {
                    returnVal[n] += pixel[n];
                }
            }
        }
        for (int i = 0; i < returnVal.length; i++) {
            returnVal[i] = returnVal[i]/25;
        }
        return returnVal;
    }

}
}
