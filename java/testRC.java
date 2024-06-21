import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp (name = "testRC")

public class testRC extends OpMode {

    public DcMotor leftmotor, rightmotor, arm;
    public Servo servo;
    public ColorSensor colorSensor;
    public TouchSensor touchSensor;
    public float servoPos;
    public float lspeed = 0;
    public float rspeed = 0;
    public float speedmod = 1;

    @Override
    public void init() {
        telemetry.addData("Initialization:", "Completed");
        telemetry.update();
        leftmotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        rightmotor = hardwareMap.get(DcMotor.class, "RightMotor");
        rightmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        arm = hardwareMap.get(DcMotor.class, "CameraArm");
        servo = hardwareMap.get(Servo.class, "servo");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    @Override
    public void loop() {
        telemetry.addData("left m pos", leftmotor.getCurrentPosition());
        telemetry.addData("right m pos", rightmotor.getCurrentPosition());
        telemetry.addData("arm m pos", arm.getCurrentPosition());
        telemetry.addData("servo pos", servo.getPosition());
        telemetry.addData("speed mod", speedmod);
        telemetry.addData("red value", colorSensor.red());
        telemetry.addData("green value", colorSensor.green());
        telemetry.addData("blue value", colorSensor.blue());
        //---------------------------------------------------

        if (gamepad1.dpad_up) {
            speedmod += 0.005;
        }
        if (gamepad1.dpad_down) {
            speedmod -= 0.005;
        }
        if(gamepad1.a && !(servoPos == 1)) {
            servoPos += 0.005;
        }
        if (gamepad1.b && !(servoPos == 0)) {
            servoPos -= 0.005;
        }
        //---------------------------------------------------
        lspeed = gamepad1.left_stick_y - gamepad1.left_stick_x;
        rspeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        servo.setPosition(servoPos);
        leftmotor.setPower(lspeed*speedmod);
        rightmotor.setPower(rspeed*speedmod);
        telemetry.update();
    }
}
