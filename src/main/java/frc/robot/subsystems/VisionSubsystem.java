package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class VisionSubsystem extends SubsystemBase {

    private double tX;
    private double tY;
    private double tA;
    private NetworkTable nt;

    /**
     * The Singleton instance of this VisionSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static VisionSubsystem INSTANCE = new VisionSubsystem();

    /**
     * Creates a new instance of this VisionSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private VisionSubsystem() {
        nt = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /**
     * Returns the Singleton instance of this VisionSubsystem. This static method
     * should be used -- {@code VisionSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static VisionSubsystem getInstance() {
        return INSTANCE;
    }

    @Override
    public void periodic() {
        tX = nt.getEntry("tx").getDouble(0);
        tY = nt.getEntry("ty").getDouble(0);
        tA = nt.getEntry("ta").getDouble(0);
    }

    public double gettX() {
        return tX;
    }

    public double gettY() {
        return tY;
    }

    public double gettA() {
        return tA;
    }

    /**
     * Sets the led state
     * @param mode 0 for pipeline, 1 for force off, 3 for force on
     */
    public void setLedMode(int mode){
        int[] validModes = {0, 1, 2, 3};
        for (int valid : validModes) {
            if (mode == valid){
                nt.getEntry("ledMode").setNumber(mode);
            }
        }
    }
}

