package model.victor.com;

/**
 * 命令接收者，命令正在执行者
 * Created by victor on 2016/3/29 0029.
 */
public class CmdReceiver {
    public void action () {
        System.out.println("执行命令......");
    }

    public void unAction () {
        System.out.println("撤销命令......");
    }

}
