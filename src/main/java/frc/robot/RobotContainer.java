/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.led.SetColorCommand;
import frc.robot.commands.swerve.SwerveDrive;
import frc.robot.commands.swerve.SwerveZero;
import frc.robot.commands.vision.DriveWithoutZCommand;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.mechanisms.JHookSubsystem;

import java.util.HashMap;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final XboxController driverController = new XboxController(0);
    private final XboxController mechanismController = new XboxController(1);
    private final LEDSubsystem ledSubsystem = LEDSubsystem.getInstance();
    private final VisionSubsystem visionSubsystem = VisionSubsystem.getInstance();
    private final JHookSubsystem jHookSubsystem = JHookSubsystem.getInstance();


    public SetColorCommand colorCommand = new SetColorCommand(ledSubsystem, SetColorCommand.Color.BLUE);

    private final SwerveDrive autonomousCommand = new SwerveDrive(swerveSubsystem, new HashMap<>());


    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
        //Drive command I think, I saw it in a tutorial so hopefully it works
        swerveSubsystem.setDefaultCommand(new RunCommand(() -> swerveSubsystem.Drive(driverController.getY(),
                driverController.getX(), driverController.getTriggerAxis(GenericHID.Hand.kLeft), false)));
        ledSubsystem.setDefaultCommand(colorCommand);
        jHookSubsystem.setDefaultCommand(new RunCommand(() ->
                jHookSubsystem.setMotorSpeed(mechanismController.getX(GenericHID.Hand.kRight))));
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton JoystickButton}.
     */
    private void configureButtonBindings()
    {
        JoystickButton r2d2 = new JoystickButton(driverController, XboxController.Button.kStickLeft.value);
        //Implements the following camera code
        r2d2.whenPressed(new DriveWithoutZCommand(visionSubsystem, swerveSubsystem, driverController));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return autonomousCommand;
    }
}
