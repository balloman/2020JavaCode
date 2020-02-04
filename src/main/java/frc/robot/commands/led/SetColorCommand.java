package frc.robot.commands.led;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

import java.awt.*;
import java.nio.ByteBuffer;


public class SetColorCommand extends CommandBase {
    private final LEDSubsystem lEDSubsystem;
    private final Color color;

    public enum Color{
        RED,
        BLUE,
        GREEN
    }

    public SetColorCommand(LEDSubsystem lEDSubsystem, Color color) {
        this.lEDSubsystem = lEDSubsystem;
        this.color = color;
        addRequirements(lEDSubsystem);
    }

    @Override
    public void initialize() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        byte[] bytes = {0};
        switch (color){
            case RED:bytes[1] = 1;
            case BLUE:bytes[1] = 2;
            case GREEN:bytes[1] = 3;
        }
        buffer.put(bytes);
        lEDSubsystem.sendMessage(buffer, 1);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
