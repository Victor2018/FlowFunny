package mode.victor.com;

import model.victor.com.BlackHorse;
import model.victor.com.Horse;
import model.victor.com.HorseRide;
import model.victor.com.WhiteHorse;

/**
 * 访问中模式
 * Created by victor on 2016/3/29 0029.
 * 封装某些作用于某种数据结构中各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
 * 访问者模式是对象的行为模式。访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。
 * 一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。
 */
public class VisitorMode {
    public static void main(String[] args) {

    }

    public static void test () {
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        HorseRide hr = new HorseRide();
        hr.ride(wh);
        hr.ride(bh);
    }
}
