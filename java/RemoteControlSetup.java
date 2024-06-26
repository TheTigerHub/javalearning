import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp (name = "RCSetup")

public class RemoteControlSetup extends OpMode{

    public DcMotor leftmotor, rightmotor, arm;
    public Servo servo;
    int servopos;
    public void init() {
        servopos = 0;
        leftmotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        rightmotor = hardwareMap.get(DcMotor.class, "RightMotor");
        arm = hardwareMap.get(DcMotor.class, "CameraArm");
        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void loop() {
        double speedMod = -((gamepad1.right_trigger*0.8)-1);
        double leftpower = gamepad1.left_stick_y - gamepad1.right_stick_x;
        double rightpower = gamepad1.left_stick_y + gamepad1.right_stick_x;
        double armpower = gamepad1.left_trigger*0.2;
        if (gamepad1.a) {
            armpower = -armpower;
        }
        if (gamepad1.right_bumper) {
            servopos += 1;
        }
        if (gamepad1.left_bumper) {
            servopos -= 1;
        }
        servo.setPosition(servopos);
        leftmotor.setPower(Range.clip(leftpower*speedMod, -1.0, 1.0));
        rightmotor.setPower(-Range.clip(rightpower*speedMod, -1.0, 1.0));
        arm.setPower(armpower);

        telemetry.addData("left motor position", leftmotor.getCurrentPosition());
        telemetry.addData("right motor position", rightmotor.getCurrentPosition());
        telemetry.addData("speedmod", speedMod);
        telemetry.addData("armpower", armpower);
        telemetry.addData("servo pos", servopos);
        telemetry.update();
    }

}
