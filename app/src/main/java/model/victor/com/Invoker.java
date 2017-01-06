package model.victor.com;

import interfaces.victor.com.CmdInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class Invoker {
    //调用者持有命令对象
    private CmdInterface cmdInterface;

    public CmdInterface getCmdInterface() {
        return cmdInterface;
    }

    public void setCmdInterface(CmdInterface cmdInterface) {

        this.cmdInterface = cmdInterface;
    }

    /**
     * 执行命令
     */
    public void runCmd () {
        cmdInterface.execute();
    }

    /**
     * 撤销命令
     */
    public void unDoCmd () {
        cmdInterface.unDo();
    }
}
