/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.SwerveMath;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frc.robot.Constants;

import static frc.robot.Constants.Wheel;
import static frc.robot.Constants.Wheel.*;

public class SwerveSubsystem extends SubsystemBase
{
//    private final TalonFX backLeftDrive = new TalonFX(0);
//    private final TalonFX frontLeftDrive = new TalonFX(1);
//    private final TalonFX frontRightDrive = new TalonFX(2);
//    private final TalonFX backRightDrive = new TalonFX(3);
//    private final TalonFX backLeftRotation = new TalonFX(4);
//    private final TalonFX frontLeftRotation = new TalonFX(5);
//    private final TalonFX backRightRotation = new TalonFX(6);
//    private final TalonFX frontRightRotation = new TalonFX(7);
    private Map<Wheel, TalonFX> driveMotors = new HashMap<>();
    private Map<Wheel, TalonFX> rotationMotors = new HashMap<>();


    /**
     * Creates a new SwerveSubsystem
     */
    public SwerveSubsystem()
    {
        driveMotors.put(BL, new TalonFX(0));
        driveMotors.put(FL, new TalonFX(1));
        driveMotors.put(FR, new TalonFX(2));
        driveMotors.put(BR, new TalonFX(3));
        rotationMotors.put(BL, new TalonFX(4));
        rotationMotors.put(FL, new TalonFX(5));
        rotationMotors.put(BR, new TalonFX(6));
        rotationMotors.put(FR, new TalonFX(7));
        for (TalonFX talon:driveMotors.values()
             ) {
            talon.config_kP(0, 0.01);
        }
    }
    
    /**
     * Will be called periodically whenever the CommandScheduler runs.
     */
    @Override
    public void periodic()
    {

    }

    /*
    Returns encoder position to go to and speed to drive at
     */
    public double[] wheelCalc(Wheel wheel, SwerveMath math){
        var encoder = 0.0;
        var angle = 0.0;
        var speed = 0.0;
        //Modify our angle and encoder values based on wheel
        switch (wheel){
            case BL:{
                speed = math.speeds.get(BL);
                angle = math.angles.get(BL);
                encoder = getRotationEncoder(wheel);
                }
            case BR:{
                speed = math.speeds.get(BR);
                angle = math.angles.get(BR);
                encoder = rotationMotors.get(wheel).getSensorCollection().getIntegratedSensorPosition();
            }
            case FL:{
                speed = math.speeds.get(FL);
                angle = math.angles.get(FL);
                encoder = rotationMotors.get(wheel).getSensorCollection().getIntegratedSensorPosition();
            }
            case FR:{
                speed = math.speeds.get(FL);
                angle = math.angles.get(FL);
                encoder = rotationMotors.get(wheel).getSensorCollection().getIntegratedSensorPosition();
            }
        }
        //Essentially same math that is in the LabVIEW version of the code
        var currentAngle = SwerveMath.wrapAngle(
                (encoder % Constants.RotationMultiplier) * Constants.InverseRotationMultiplier,
                SwerveMath.AngleRange.NegPi_Pi, SwerveMath.AngleUnits.DegIn_DegOut);
        //Decide whether to turn the other way and invert wheel speed
        var angleDist = SwerveMath.angleDistance(currentAngle, angle);
        var altAngleDist = SwerveMath.angleDistance(currentAngle+180, angle);
        if (angleDist > 90) {
            return new double[]{(angleDist * 102.4) + encoder, speed};
        }
        return new double[]{(altAngleDist* 102.4) + encoder, -speed};
    }

    /*
    Sets the Drive Motors with Percent Output
     */
    public void setDriveMotors(Wheel refnum, double speed){
        driveMotors.get(refnum).set(TalonFXControlMode.PercentOutput, speed);
    }

    public void setRotationMotorsByPosition(Wheel refnum, double position){
        rotationMotors.get(refnum).set(TalonFXControlMode.Position, position);
    }

    public double getRotationEncoder(Wheel refnum){
        return rotationMotors.get(refnum).getSensorCollection().getIntegratedSensorPosition();
    }

    public void Drive(double x, double y, double z){
        SwerveMath swerveMath = new SwerveMath(x, y, z, false);
        //Math
        swerveMath.configABCD();
        //Math
        swerveMath.calcAngles();
        //Math
        swerveMath.calcSpeeds();
        //Iterate over the wheels and apply math accordingly
        for (Wheel wheel :
                rotationMotors.keySet()) {
            var position = wheelCalc(wheel, swerveMath)[0];
            var speed = wheelCalc(wheel, swerveMath)[1];
            if (!swerveMath.deadBandActive){
                setRotationMotorsByPosition(wheel, position);
                setDriveMotors(wheel, speed);
            }
        }
    }
}
