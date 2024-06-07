import com.acmerobotics.dashboard.config.Config;

@Config
public class PIDnumbertuning {
    public static double Kp = 1.8;  // Proportional gain
    public static double Ki = 0.003;   // Integral gain ( KEEP THIS 0 )
    public static double Kd = 0.02;   // Derivative gain
}
