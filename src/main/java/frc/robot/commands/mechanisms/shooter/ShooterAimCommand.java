package frc.robot.commands.mechanisms.shooter;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.mechanisms.ShooterSubsystem;

/**
 * This command ideally would auto set the shooter aim/speed? based on the distance from the target
 */
public class ShooterAimCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final VisionSubsystem visionSubsystem;
    private final XboxController controller;
    private PIDController pid;

    public ShooterAimCommand(ShooterSubsystem shooterSubsystem, VisionSubsystem visionSubsystem,
                             XboxController controller) {
        this.shooterSubsystem = shooterSubsystem;
        this.visionSubsystem = visionSubsystem;
        this.controller = controller;
        addRequirements(shooterSubsystem);
        addRequirements(visionSubsystem);
        pid = new PIDController(1, 0.01, 0);
    }

    @Override
    public void initialize() {
        pid.reset();
    }

    @Override
    public void execute() {
        double setPoint = visionSubsystem.gettA() * Constants.AimConstant;
        shooterSubsystem.setAim(pid.calculate(shooterSubsystem.getEncoder(), setPoint));
    }

    @Override
    public boolean isFinished() {
       return controller.getBumperPressed(GenericHID.Hand.kRight);
    }

    @Override
    public void end(boolean interrupted) {

    }
}
