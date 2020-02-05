package frc.robot.subsystems.mechanisms;


import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JHookSubsystem extends SubsystemBase {

    private final PWMTalonSRX motor;

    /**
     * The Singleton instance of this JHookSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static JHookSubsystem INSTANCE = new JHookSubsystem();

    /**
     * Creates a new instance of this JHookSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private JHookSubsystem() {
        motor = new PWMTalonSRX(0);
    }

    /**
     * Returns the Singleton instance of this JHookSubsystem. This static method
     * should be used -- {@code JHookSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static JHookSubsystem getInstance() {
        return INSTANCE;
    }

    public void setMotorSpeed(double speed){
        motor.set(speed);
    }

}

