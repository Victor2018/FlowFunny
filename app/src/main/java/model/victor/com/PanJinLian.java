package model.victor.com;

import interfaces.victor.com.KindWoman;

/**
 * Created by victor on 2016/3/23 0023.
 * 定义一个潘金莲是什么样的人
 */
public class PanJinLian implements KindWoman {
    @Override
    public void happyWithMan() {
        System.out.println("潘金莲和男人在做那个...");

    }

    @Override
    public void makeEyesWithMan() {
        System.out.println("潘金莲抛媚眼...");

    }

}
