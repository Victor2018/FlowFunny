package model.victor.com;

import interfaces.victor.com.CaiInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class ZhangSan implements CaiInterface {
    @Override
    public void fangyan() {
        System.out.println("ZhangSanfangyan");
    }

    @Override
    public void fangyou() {
        System.out.println("ZhangSanfangyou");
    }

    @Override
    public void jiashui() {
        System.out.println("ZhangSanJiashui");
    }
}
