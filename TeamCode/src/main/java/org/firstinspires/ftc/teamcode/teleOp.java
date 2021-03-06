package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "teleOp")
public class teleOp extends T10_Library
{

    boolean bPressed, descending, intake  = false;
    Turning test;
    imuData imu;
    double pos = 0.0;
    double time_millis = 0.0;
    ElapsedTime t = new ElapsedTime();
    int i = 1;

    public void init() {
        initialize_robot();
        imu = new imuData(hardwareMap);
        test = new Turning();
    }

    public void loop() {

        telemetry.addData("Imu pitch: ", imu.getPitch());
        telemetry.addData("Imu roll: ", imu.getRoll());
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;

//        if(gamepad1.right_bumper){
//            armMotorLeft.setPower(.9);
//        } else if (gamepad1.left_bumper) {
//            armMotorLeft.setPower(-.9);
//        } else {
//            armMotorLeft.setPower(0);
//        }
        //defining the stuff. linear = straight, rotation = turning, side = skating.
        //Linear - rotation will compensate one side to allow the other side to overrotate

        if(gamepad1.right_stick_button){
            mode = mode.getNext();
        }

        if(mode == DRIVING.Slow){
            omni(linear/2, rotation/2, side/2);} // slow driving
        if(mode == DRIVING.Medium) {
            omni(linear/1.5f, rotation/1.5f, side/1.5f);} // medium driving
        if(mode == DRIVING.Fast) {
            omni(linear, rotation, side);} // fast driving

        if(gamepad1.left_bumper){
            latchMotor.setPower(-1f);
        }
        else if (gamepad1.right_bumper){
            latchMotor.setPower(1f);
        }

        else{
            latchMotor.setPower(0f);
        }

        if(gamepad1.y){
            markServo.setPosition(i%2);
            i++;
        }

        if(gamepad1.left_trigger > 0){
            scoreMotor.setPower(-gamepad1.left_trigger);
        }
        else if (gamepad1.right_trigger > 0){
            scoreMotor.setPower(gamepad1.right_trigger);
        }
        else{
            scoreMotor.setPower(0f);
        }

        if(gamepad1.a){
            intake = true;
            if(intake) {
                intake(1);
            }
        }
        else {
            intake(0);
        }

        intake = false;

        if(gamepad1.x){
            gate.setPosition(1);
        }
        else if(gamepad1.b){
            gate.setPosition(0);
        }
        else{
            gate.setPosition(.5);
        }


        telemetry.addData("Current Angle?", imu.getAngle());
        telemetry.addData("Gold Aligned?", gold.getAligned());
        telemetry.addData("Driving Mode:",mode);
        telemetry.addData("Gate position?", gate.getPosition());
        telemetry.addData("Time taken to de-latch: ", t.milliseconds());

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
