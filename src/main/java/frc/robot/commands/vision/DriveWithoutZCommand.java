package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class DriveWithoutZCommand extends CommandBase {
    private final VisionSubsystem visionSubsystem;
    private final SwerveSubsystem swerveSubsystem;
    private final XboxController xboxController;
    private PIDController pidController;

    /**
     * This command allows the user to drive the robot while the rotation is processed by the vision code
     * @param visionSubsystem the vision subsystem
     * @param swerveSubsystem the swerve subsystem
     * @param xboxController the driver controller object
     */
    public DriveWithoutZCommand(VisionSubsystem visionSubsystem, SwerveSubsystem swerveSubsystem,
                                XboxController xboxController) {
        this.visionSubsystem = visionSubsystem;
        this.swerveSubsystem = swerveSubsystem;
        this.xboxController = xboxController;
        addRequirements(visionSubsystem);
        addRequirements(swerveSubsystem);
        //Creates new PID Controller object with respective P, I, and D
        pidController = new PIDController(0.01, 0, 0);
    }

    @Override
    public void initialize() {
        pidController.reset();
    }

    @Override
    public void execute() {
        double x = xboxController.getX(GenericHID.Hand.kLeft);
        double y = xboxController.getY(GenericHID.Hand.kLeft);
        double z = pidController.calculate(visionSubsystem.gettX(), 0);
        swerveSubsystem.Drive(x, y, z, false);
    }

    @Override
    public boolean isFinished() {
        return xboxController.getStickButtonPressed(GenericHID.Hand.kLeft);
    }

    @Override
    public void end(boolean interrupted) {

    }
}
