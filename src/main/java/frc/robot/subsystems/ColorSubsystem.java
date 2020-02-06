package frc.robot.subsystems;


import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSubsystem extends SubsystemBase {

    private final ColorMatch colorMatch;
    private ColorSensorV3 sensor;
    private PWMTalonSRX motor;

    /**
     * The Singleton instance of this ColorSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static ColorSubsystem INSTANCE = new ColorSubsystem();

    /**
     * Creates a new instance of this ColorSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private ColorSubsystem() {
        sensor = new ColorSensorV3(I2C.Port.kOnboard);
        motor = new PWMTalonSRX(Constants.PWMPorts.get(Constants.PortMap.ColorMotor));
        colorMatch = new ColorMatch();
        for (var color :
                Constants.ColorMatches.keySet()) {
            colorMatch.addColorMatch(Constants.ColorMatches.get(color));
        }
    }

    /**
     * Returns the Singleton instance of this ColorSubsystem. This static method
     * should be used -- {@code ColorSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static ColorSubsystem getInstance() {
        return INSTANCE;
    }

    public void setMotorSpeed(double speed){
        motor.set(speed);
    }

    /**
     * gets the color
     * @return a wpilib defined color
     */
    public Color getColor(){
        return sensor.getColor();
    }

    /**
     * Looks to see if the color detected is similar to any of our matched colors
     * @return One of the possible color constants defined in Constants.java
     */
    public Constants.Colors getMatch(){
        var match = colorMatch.matchClosestColor(getColor());
        for (var colorKey :
                Constants.ColorMatches.keySet()) {
            if (Constants.ColorMatches.get(colorKey) == match.color){
                return colorKey;
            }
        }
        return Constants.Colors.Unknown;
    }

}

