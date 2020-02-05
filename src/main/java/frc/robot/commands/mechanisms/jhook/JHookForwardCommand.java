package frc.robot.commands.mechanisms.jhook;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.mechanisms.JHookSubsystem;


public class JHookForwardCommand extends CommandBase {
    private final JHookSubsystem jHookSubsystem;

    public JHookForwardCommand(JHookSubsystem jHookSubsystem) {
        this.jHookSubsystem = jHookSubsystem;
        addRequirements(jHookSubsystem);
    }

    @Override
    public void initialize() {
        jHookSubsystem.setMotorSpeed(0.75);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        jHookSubsystem.setMotorSpeed(0);
    }
}
