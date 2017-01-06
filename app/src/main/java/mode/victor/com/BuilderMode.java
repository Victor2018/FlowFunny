package mode.victor.com;

import interfaces.victor.com.CaiInterface;
import model.victor.com.Director;
import model.victor.com.ZhangSan;

/**
 * 建造者模式
 * Created by victor on 2016/3/29 0029.
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 */
public class BuilderMode {

    public static void main(String[] args) {
        test();
    }
    public static void test () {
        CaiInterface caiInterface = new ZhangSan();// 张三做菜
        Director d = new Director(caiInterface); // 指挥者
        d.zuocai();// 我要指挥者弄菜，其实指挥者叫张三弄菜去了。
    }
}
