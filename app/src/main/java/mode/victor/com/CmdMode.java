package mode.victor.com;

import interfaces.victor.com.CmdInterface;
import model.victor.com.CmdReceiver;
import model.victor.com.CreateCmd;
import model.victor.com.Invoker;

/**
 * 命令模式
 * Created by victor on 2016/3/29 0029.
 * 意图：将一个请求封装为一个对象，从而可用不同的请求对客户进行参数化；
 * 对请求排队或记录日志，以及支持可撤销的操作动机：
 * 将”发出请求的对象”和”接收与执行这些请求的对象”分隔开来。
 */
public class CmdMode {
    public static void main(String[] args) {
        test();
    }
    public static void test () {
        //创建接受者
        CmdReceiver cmdReceiver = new CmdReceiver();
        // 创建命令对象，并设置它的接收者
        CmdInterface cmdInterface = new CreateCmd(cmdReceiver);

        //创建调用者，将命令对象设置进去
        Invoker invoker = new Invoker();
        invoker.setCmdInterface(cmdInterface);

        //测试
        invoker.runCmd();
        invoker.unDoCmd();
    }
}
