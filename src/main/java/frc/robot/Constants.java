/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;

import static java.util.Map.entry;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
    public enum Wheel{
        FL,
        FR,
        BL,
        BR
    }

    public static final double WheelbaseConstant = 0.707106;

    public static final double RotationMultiplier = 36864;
    public static final double InverseRotationMultiplier = 0.009765625;

    //IDK what this would be but this would be updated based on some user testing
    public static final double AimConstant = 0.123456;

    //This is a list of all the objects in the code that will have ports
    public enum PortMap {
        ShooterWheel,
        JHook,
        ShooterAim,
        ShooterEncoderA,
        ShooterEncoderB
    }

    //This is a map of all of the objects to their respective ports.
    //This ensures safe port creation that is easily modifiable in one place
    public static final Map<PortMap, Integer> PWMPorts = Map.ofEntries(
            entry(PortMap.ShooterWheel, 1),
            entry(PortMap.JHook, 0),
            entry(PortMap.ShooterAim, 2)
    );

    public static final Map<PortMap, Integer> DIOPorts = Map.ofEntries(
            entry(PortMap.ShooterEncoderA, 0),
            entry(PortMap.ShooterEncoderB, 1)
    );
}