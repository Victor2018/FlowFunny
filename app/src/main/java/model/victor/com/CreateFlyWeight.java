package model.victor.com;

import android.util.Log;
import android.widget.Toast;

import interfaces.victor.com.FlyWeight;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class CreateFlyWeight implements FlyWeight {
    private Character intrinsicState;

    //构造函数，内蕴状态作为参数传入
    public CreateFlyWeight (Character state) {
        intrinsicState = state;
    }

    //外蕴状态作为参数传入方法中，改变方法的行为，但是并不改变对象的内蕴状态
    @Override
    public void operAction(String state) {
        System.out.println("intrinsicState = " + intrinsicState);
        System.out.println("state = " + state);
    }
}
