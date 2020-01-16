package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Updown")
public class UPDOWN extends LinearOpMode {

    DcMotor liftLeft,liftRight;
    @Override
    public void runOpMode() {

        liftLeft = hardwareMap.get(DcMotor.class, "left_lift");
        liftRight = hardwareMap.get(DcMotor.class, "right_lift");

        while(opModeIsActive()) {

            if (gamepad1.a) {
                liftLeft.setPower(1);
                liftRight.setPower(-1);
            }
            if (gamepad1.b) {
                liftLeft.setPower(-1);
                liftRight.setPower(1);
            }

        }
    }
}
