package frc.robot.subsystems;


import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.nio.ByteBuffer;

public class LEDSubsystem extends SubsystemBase {

    SPI spiBus;

    /**
     * The Singleton instance of this LEDSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static LEDSubsystem INSTANCE = new LEDSubsystem();

    /**
     * Creates a new instance of this LEDSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private LEDSubsystem() {
        spiBus = new SPI(SPI.Port.kMXP);
    }

    /**
     * Returns the Singleton instance of this LEDSubsystem. This static method
     * should be used -- {@code LEDSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static LEDSubsystem getInstance() {
        return INSTANCE;
    }

    public void sendMessage(ByteBuffer data, int size){
        spiBus.write(data, size);
    }

    public void readMessage(ByteBuffer data, int size){
        spiBus.read(false, data, size);
    }

}

