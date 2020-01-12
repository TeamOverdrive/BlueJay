package org.firstinspires.ftc.teamcode;

public class PointData {

    int r;
    int g;
    int b;

    public PointData() {
        r = 0; g = 0; b = 0;
    }
    public PointData(int r, int g, int b) {
        this.r = r; this.g = g; this.b = b;
    }
    public PointData(double[] rgb) {
        r = (int) rgb[0];
        g = (int) rgb[1];
        b = (int) rgb[2];
    }
    public double dist(int r, int g, int b) {
        return Math.sqrt((r - this.r)^2 + (g - this.g)^2 + (b - this.b)^2);
    }
}
