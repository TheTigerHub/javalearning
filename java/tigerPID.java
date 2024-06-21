import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp(name = "T PID")
public class tigerPID extends LinearOpMode {
    newdrivetrain drivetrain = new newdrivetrain();
    double integralSum = 0;
    double kp = 0.1;
    double ki = 0.00000005;
    double kd = 0.003;
    private BHI260IMU imu;

    ElapsedTime timer = new ElapsedTime();
    double lastError = 0;
    
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("init complete", "");
        telemetry.update();
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();
        drivetrain.init(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            double power = PIDcontrol(90, orientation.getYaw(AngleUnit.DEGREES));
            drivetrain.power(power*0.5);
        }
    }
    public double PIDcontrol (double target, double current) {
        double error = angleWrap(target - current);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;
        timer.reset();
        double output = (error * kp) + (derivative * kd) * (integralSum * ki);
        return output;
    }

    public double angleWrap(double degrees){
        while(degrees > 180){
            degrees -= 360;
        }
        while(degrees < -180){
            degrees += 360;
        }
        return degrees;
    }
}
