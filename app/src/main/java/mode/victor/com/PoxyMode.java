package mode.victor.com;

import model.victor.com.WangPo;

/**
 * 代理模式
 * Created by victor on 2016/3/23 0023.
 * 为其他对象提供一种代理以控制对这个对象的访问。
 *
 * 水浒传是这样写的：西门庆被潘金莲用竹竿敲了一下，西门庆看痴迷了，被王婆看到了，就开始撮合两人好事，王婆作为潘金莲的代理人收了不少好处费，那我们假设一下：
 * 如果没有王婆在中间牵线，这两个不要脸的能成事吗？难说得很！
 */
public class PoxyMode {
    public static void main(String[] args) {
        test();
    }

    public static void test () {
        WangPo wangPo;
        //把王婆叫出来
        wangPo = new WangPo();
        //然后西门庆说，我要和潘金莲Happy,然后王婆就安排了西门庆丢筷子哪出戏：
        wangPo.makeEyesWithMan();
        //看到没有表面是王婆在做，其实爽的是潘金莲
        wangPo.happyWithMan();
    }
}
