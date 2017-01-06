package model.victor.com;

import interfaces.victor.com.StateInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class CreateStateA implements StateInterface {
    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateA handle ：" + sampleParameter);
    }
}
