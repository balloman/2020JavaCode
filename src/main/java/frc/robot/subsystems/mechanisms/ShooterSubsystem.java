package frc.robot.subsystems.mechanisms;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.*;

public class ShooterSubsystem extends SubsystemBase {

    private PWMTalonSRX shooterWheelMotor;
    private PWMTalonSRX shooterAimMotor;
    private Encoder shooterEncoder;

    /**
     * The Singleton instance of this ShooterSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();

    /**
     * Creates a new instance of this ShooterSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private ShooterSubsystem() {
        shooterWheelMotor = new PWMTalonSRX(PWMPorts.get(PortMap.ShooterWheel));
        shooterAimMotor = new PWMTalonSRX(PWMPorts.get(PortMap.ShooterAim));
        shooterEncoder = new Encoder(DIOPorts.get(PortMap.ShooterEncoderA), DIOPorts.get(PortMap.ShooterEncoderB));
    }

    public double getEncoder(){
        return shooterEncoder.get();
    }

    public void setAim(double speed){
        shooterAimMotor.set(speed);
    }

    public void setWheelSpin(double speed){
        shooterWheelMotor.set(speed);
    }

    /**
     * Returns the Singleton instance of this ShooterSubsystem. This static method
     * should be used -- {@code ShooterSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }

}

