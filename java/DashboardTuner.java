import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp(name = "Dashboard Tuner")
public class DashboardTuner extends LinearOpMode {
    double integralSum = 0;
    double Kp = PIDnumbertuning.Kp;
    double Ki = PIDnumbertuning.Ki;
    double Kd = PIDnumbertuning.Kd;

    movement drivetrain = new movement();

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    private BHI260IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        drivetrain.init(hardwareMap);
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();

        //target
        double refrenceAngle = Math.toRadians(-90);
        waitForStart();

        while(opModeIsActive()){
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            telemetry.addData("Target Angle", refrenceAngle*(180/Math.PI));
            telemetry.addData("Current Angle", orientation.getYaw(AngleUnit.DEGREES));
            double power = PIDControl(refrenceAngle, orientation.getYaw(AngleUnit.RADIANS));
            drivetrain.power(power);
            telemetry.update();
        }

    }

    //(target, current)
    public double PIDControl(double refrence, double state) {
        double error = angleWrap(refrence - state);
        telemetry.addData("Error: ", Math.toDegrees(error));
        //i timer + sum
        integralSum += error * timer.seconds();
        //d calc
        double derivative = (error - lastError) / (timer.seconds());
        lastError = error;
        timer.reset();
        //pid calc thing
        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }
    public double angleWrap(double radians){
        while(radians > Math.PI){
            radians -= 2 * Math.PI;
        }
        while(radians < -Math.PI){
            radians += 2 * Math.PI;
        }
        return radians;
    }


}

