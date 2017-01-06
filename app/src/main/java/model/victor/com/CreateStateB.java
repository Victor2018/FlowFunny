package model.victor.com;

import interfaces.victor.com.StateInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class CreateStateB implements StateInterface {
    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateB handle ：" + sampleParameter);
    }
}
