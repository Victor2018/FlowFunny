package mode.victor.com;


import interfaces.victor.com.StateInterface;
import model.victor.com.Context;
import model.victor.com.CreateStateB;

/**
 * 状态模式
 * Created by victor on 2016/3/29 0029.
 * 当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。
 * 状态模式允许一个对象在其内部状态改变的时候改变其行为。这个对象看上去就像是改变了它的类一样。
 */
public class StateMode {
    public static void main(String[] args) {
       test();
    }

    public static void test () {
        //创建状态
        StateInterface stateInterface = new CreateStateB();
        //创建环境
        Context context = new Context();
        //将状态设置到环境中
        context.setState(stateInterface);
        //请求
        context.request("test");
    }
}
