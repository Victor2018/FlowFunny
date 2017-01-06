package mode.victor.com;

import model.victor.com.BaseTest;
import model.victor.com.SinaTest;
import model.victor.com.TencentTest;

/**
 * 模版方法模式
 * Created by victor on 2016/3/29 0029.
 * 定义一个操作中算法的框架，而将一些步骤延迟到子类中，
 * 使得子类可以不改变算法的结构即可重定义该算法中的某些特定步骤。
 */
public class TemplateMethodMode {
    public static void main(String[] args) {
        test();
    }

    public static void test () {
        //多态
        BaseTest sina = new SinaTest();
        BaseTest tencent = new TencentTest();
        sina.ask();
        tencent.ask();
    }
}
