package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "teleop", group = "TELEOPS")
public class teleop extends LinearOpMode {

    Robot robot;
    @Override
    public void runOpMode() {

        Robot robot = new Robot(this);

        waitForStart();

        robot.drive.brake();

        while (opModeIsActive() && !isStopRequested()) {
            robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
            robot.teleop();
            if (gamepad1.left_bumper)
                robot.drive.dilatePower(0.35);
            if (gamepad1.right_bumper)
                robot.drive.kill();
            robot.drive.drive();
            if (gamepad1.y) {
                robot.initIMU();
            }
            if (gamepad2.left_stick_y > 0) {
                robot.lift.setPower(-gamepad2.left_stick_y);
                robot.lift.dilate(0.35);
                robot.lift.lift();
            } else {
                robot.lift.run(-gamepad2.left_stick_y);
            }
            telemetry.addLine("hi");
            telemetry.update();
            if (gamepad2.right_bumper) {
                robot.lift.grab();
            }
            if (gamepad2.left_bumper) {
                robot.lift.release();
            }
            if (gamepad2.x) {
                robot.lift.rotate(0.95);
            } else if (gamepad2.a) {
                robot.lift.rotate(0.3);
            } else if (gamepad2.b) {
                robot.lift.rotate(0.05);
            } else if(gamepad2.y) {
                robot.lift.rotate(0.4);
            }
            if (gamepad2.dpad_up) {
                robot.lift.cap();
            }
            if (gamepad2.dpad_down) {
                robot.lift.lock();
            }

        }
    }
}
