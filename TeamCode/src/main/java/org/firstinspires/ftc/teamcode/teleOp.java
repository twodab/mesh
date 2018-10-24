package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.util.Range;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DrawViewSource;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.scoring.RatioScorer;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
//import org.firstinspires.ftc.teamcode.Turning;
//import java.util.Arrays;

@TeleOp(name = "teleOp")
public class teleOp extends T10_Library
{
//    Turning test = new Turning(0);
    boolean turn = false;
    public void init()
    {
        initialize_robot();
    }

    public void loop() {
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;
        //defining the stuff. linear = straight, rotation = turning, side = skating.
        //Linear - rotation will compensate one side to allow the other side to overrotate

        if(gamepad1.right_stick_button){
            mode = mode.getNext();
        }
        if(mode == DRIVING.Fast) {
            omni(linear, rotation, side);
        }
        else if (mode == DRIVING.Slow){
            omni(linear/2f,rotation/2f,side/2f);
        }
        else if (mode == DRIVING.Medium){
            omni(linear/1.25f,rotation/1.25f,side/1.25f);
        }

        telemetry.addData("Gold Aligned?", gold.getAligned());
        telemetry.addData("Driving Mode:",mode);
//        telemetry.addData("left_y",linear);
//        telemetry.addData("right_x",linear);
//        telemetry.addData("right_y",linear);
//        telemetry.addData("Servo's moving?", servosMoving);

        //sending inputs to omni code
//        if(gamepad1.a && !turn){
//            turn = true;
//            test.setDestination(30);
//        }
//        if(turn){
//            boolean isTurning = test.update();
//            if(!isTurning){
//                turn = false;
//            }
//        }

    }

    public void stop() {
        gold.disable();
    }
}