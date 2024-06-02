import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class movement {
    public DcMotor LeftDriveMotor;
    public DcMotor RightDriveMotor;

    HardwareMap hwMap;

    public void init(HardwareMap tigerMap) {
        hwMap = tigerMap;
        LeftDriveMotor = hwMap.get(DcMotor.class, "LeftMotor");
        RightDriveMotor = hwMap.get(DcMotor.class, "RightMotor");

        LeftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        LeftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        RightDriveMotor.setDirection(DcMotor.Direction.FORWARD);

        LeftDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftDriveMotor.setPower(0);
        RightDriveMotor.setPower(0);

    }

    public void power(double output){
        LeftDriveMotor.setPower(-output);
        RightDriveMotor.setPower(output);
    }
}
