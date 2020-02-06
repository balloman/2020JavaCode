package frc.robot.commands.led;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LEDSubsystem;

import java.awt.*;
import java.nio.ByteBuffer;


public class SetColorCommand extends CommandBase {
    private final LEDSubsystem lEDSubsystem;
    private final Constants.Colors color;

    public SetColorCommand(LEDSubsystem lEDSubsystem, Constants.Colors color) {
        this.lEDSubsystem = lEDSubsystem;
        this.color = color;
        addRequirements(lEDSubsystem);
    }

    @Override
    public void initialize() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        byte[] bytes = {0};
        switch (color){
            case Red:bytes[1] = 1;
            case Blue:bytes[1] = 2;
            case Green:bytes[1] = 3;
        }
        buffer.put(bytes);
        lEDSubsystem.sendMessage(buffer, 1);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
