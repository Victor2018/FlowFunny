package model.victor.com;

import java.util.HashMap;

import interfaces.victor.com.FlyWeight;
import model.victor.com.CreateFlyWeight;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class FlyWeightFactory {
    private HashMap<Character,FlyWeight> flyMap = new HashMap<Character,FlyWeight>();

    public FlyWeight factory (Character state) {
        //先从缓存中查找对象
        FlyWeight fw = flyMap.get(state);
        if (fw == null) {
            //如果对象不存在则创建一个新的FlyWeight对象
            fw = new CreateFlyWeight(state);
            //把这个心的FlyWeight对象添加到缓存中
            flyMap.put(state,fw);
        }
        return fw;
    }
}
