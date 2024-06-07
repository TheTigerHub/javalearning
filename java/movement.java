import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class movement {
    public DcMotor LeftDriveMotor;
    public DcMotor RightDriveMotor;
    public DcMotor CameraAttachmentArm;

    HardwareMap hwMap;

    public void init(HardwareMap tigerMap) {
        hwMap = tigerMap;
        LeftDriveMotor = hwMap.get(DcMotor.class, "LeftMotor");
        RightDriveMotor = hwMap.get(DcMotor.class, "RightMotor");
        CameraAttachmentArm = hwMap.get(DcMotor.class, "CameraArm");

        LeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        CameraAttachmentArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        LeftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        RightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        CameraAttachmentArm.setDirection(DcMotor.Direction.REVERSE);

        LeftDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        CameraAttachmentArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftDriveMotor.setPower(0);
        RightDriveMotor.setPower(0);
        CameraAttachmentArm.setPower(0);

    }

    public void armPower(double output){
        CameraAttachmentArm.setPower(output);
    }

    public void power(double output){
        LeftDriveMotor.setPower(-output);
        RightDriveMotor.setPower(output);
    }
    public void powerForward(double output, double addspeed){
        LeftDriveMotor.setPower(-output + addspeed);
        RightDriveMotor.setPower(output + addspeed);
    }
}
