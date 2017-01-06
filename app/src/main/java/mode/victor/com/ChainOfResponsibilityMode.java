package mode.victor.com;

import model.victor.com.CreateHandler;
import model.victor.com.Handler;

/**
 * 责任链模式
 * Created by victor on 2016/3/29 0029.
 * 使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
 */
public class ChainOfResponsibilityMode {
    public static void main(String[] args) {
        test();
    }
    public static void test() {
        // 组装责任链
        Handler handler1 = new CreateHandler();
        Handler handler2 = new CreateHandler();
        handler1.setSuccessor(handler2);
        // 提交请求
        handler1.handleRequest();
    }
}
