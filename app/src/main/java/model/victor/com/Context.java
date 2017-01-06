package model.victor.com;

import interfaces.victor.com.StateInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class Context {
    // 持有一个State类型的对象实例
    private StateInterface stateInterface;

    public void setState(StateInterface stateInterface) {
        this.stateInterface = stateInterface;
    }
    /**
     * 用户感兴趣的接口方法
     */
    public void request(String sampleParameter) {
        // 转调state来处理
        stateInterface.handle(sampleParameter);
    }
}
