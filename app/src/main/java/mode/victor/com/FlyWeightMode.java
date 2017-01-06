package mode.victor.com;

import interfaces.victor.com.FlyWeight;
import model.victor.com.FlyWeightFactory;

/**
 * 享元模式
 * Created by victor on 2016/3/29 0029.
 * 复用我们内存中已存在的对象，降低系统创建对象实例的性能消耗。
 * Flyweight在拳击比赛中指最轻量级，即“蝇量级”或“雨量级”，这里选择使用“享元模式”的意译，
 * 是因为这样更能反映模式的用意。享元模式是对象的结构模式。
 * 享元模式以共享的方式高效地支持大量的细粒度对象。
 */
public class FlyWeightMode {
    public static void main(String[] args) {
        test();
    }

    public static void test () {
        FlyWeightFactory fwf = new FlyWeightFactory();
        FlyWeight fw = fwf.factory(new Character('a'));
        fw.operAction("First Call!!!");

        fw = fwf.factory(new Character('b'));
        fw.operAction("Second Call!!!");

        fw = fwf.factory(new Character('a'));
        fw.operAction("Third Call!!!");
    }

}
