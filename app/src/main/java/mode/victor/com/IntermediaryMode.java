package mode.victor.com;

import model.victor.com.AbstractColleague;
import model.victor.com.AbstractColleague2;
import model.victor.com.AbstractMediator;
import model.victor.com.ColleagueA;
import model.victor.com.ColleagueA2;
import model.victor.com.ColleagueB;
import model.victor.com.ColleagueB2;
import model.victor.com.Mediator;

/**
 * 中介者模式
 * Created by victor on 2016/3/29 0029.
 * 用一个中介者对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，
 * 从而使耦合松散，而且可以独立地改变它们之间的交互。
 * 中介者模式的优点
 * 适当地使用中介者模式可以避免同事类之间的过度耦合，使得各同事类之间可以相对独立地使用。
 * 使用中介者模式可以将对象间一对多的关联转变为一对一的关联，使对象间的关系易于理解和维护。
 * 使用中介者模式可以将对象的行为和协作进行抽象，能够比较灵活的处理对象间的相互作用。
 */
public class IntermediaryMode {
    public static void main(String[] args) {
        test();
    }
    public static void test () {
        System.out.println("************** testA *****************");
        testA();
        System.out.println("************** testB *****************");
        testB();
    }

    private static void testA() {
        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        System.out.println("==========设置A影响B==========");
        collA.setNumber(1288, collB);
        System.out.println("collA的number值：" + collA.getNumber());
        System.out.println("collB的number值：" + collB.getNumber());

        System.out.println("==========设置B影响A==========");
        collB.setNumber(87635, collA);
        System.out.println("collB的number值：" + collB.getNumber());
        System.out.println("collA的number值：" + collA.getNumber());
    }

    private static void testB () {
        AbstractColleague2 collA = new ColleagueA2();
        AbstractColleague2 collB = new ColleagueB2();

        AbstractMediator am = new Mediator(collA, collB);

        System.out.println("==========通过设置A影响B==========");
        collA.setNumber(1000, am);
        System.out.println("collA的number值为：" + collA.getNumber());
        System.out.println("collB的number值为A的10倍：" + collB.getNumber());

        System.out.println("==========通过设置B影响A==========");
        collB.setNumber(1000, am);
        System.out.println("collB的number值为：" + collB.getNumber());
        System.out.println("collA的number值为B的0.1倍：" + collA.getNumber());
    }
}
