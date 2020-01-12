package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class visionPT extends OpenCvPipeline {

    public Mat processFrame(Mat input) {

        double[] i = input.get(100,100);
        if (i[1] > 100) {
            Imgproc.circle(input,new Point(100,100),5, new Scalar(0,255,0));
        } else {
            Imgproc.circle(input,new Point(100,100),5, new Scalar(0,0,0));

        }
        return input;
    }
}
