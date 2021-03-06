package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CommandTransformation implements CommandVisitorInterface {
    private List<DriverCommand> commands;

    public CommandTransformation(){
        commands = new ArrayList<>();
    }

    public ICompoundCommand getTransformedCommand() {
        return new CompoundCommand(commands);
    }

    @Override
    public void visit(OperateToCommand driver) {
        DriverCommand transformedCommand = new OperateToCommand(getTransformedX(driver.getPosX(), driver.getPosY()), getTransformedY(driver.getPosX(), driver.getPosY()));
        commands.add(transformedCommand);
    }

    @Override
    public void visit(SetPositionCommand driver) {
        SetPositionCommand transformedCommand = new SetPositionCommand(getTransformedX(driver.getPosX(), driver.getPosY()), getTransformedY(driver.getPosX(), driver.getPosY()));
        commands.add(transformedCommand);
    }

    @Override
    public void visit(ICompoundCommand driver) {
        for (Iterator<DriverCommand> i = driver.iterator(); i.hasNext(); ) {
            i.next().accept(this);
        }
    }

    abstract int getTransformedX(int x, int y);
    abstract int getTransformedY(int x, int y);
}
