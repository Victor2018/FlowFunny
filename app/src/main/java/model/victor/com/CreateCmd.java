package model.victor.com;

import interfaces.victor.com.CmdInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class CreateCmd implements CmdInterface {
    private CmdReceiver cmdReceiver;
    public CreateCmd ( CmdReceiver cmdReceiver) {
        this.cmdReceiver = cmdReceiver;
    }

    @Override
    public void execute() {
        cmdReceiver.action();
    }

    @Override
    public void unDo() {
        cmdReceiver.unAction();
    }
}
