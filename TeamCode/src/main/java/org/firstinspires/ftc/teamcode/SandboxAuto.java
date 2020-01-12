package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "TESTING", group = "Does this even matter?")
public class SandboxAuto extends LinearOpMode {

    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(this);
        waitForStart();
        // robot.getDrive().moveDist(24,1);
        robot.getDrive().brake();
        robot.getDrive().turn(1,90);
        robot.getDrive().moveDist(24,1,90,true);

    }

}
