/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.utils.SwerveMath;

import java.util.Map;

/**
 * An example command that uses an example subsystem.
 */
public class SwerveDrive extends CommandBase
{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField", "FieldCanBeLocal"})
    private final SwerveSubsystem subsystem;
    private SwerveMath swerveMath;
    private Map<String, Double> inputs;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public SwerveDrive(SwerveSubsystem subsystem, Map<String, Double> inputs)
    {
        this.subsystem = subsystem;
        addRequirements(subsystem);
        this.inputs = inputs;
    }

    @Override
    public void execute() {

    }




}
