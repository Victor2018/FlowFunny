package model.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class Mediator extends AbstractMediator {
    public Mediator(AbstractColleague2 a, AbstractColleague2 b) {
        super(a, b);
    }

    // 处理A对B的影响
    public void AaffectB() {
        int number = A.getNumber();
        B.setNumber(number * 100);
    }

    // 处理B对A的影响
    public void BaffectA() {
        int number = B.getNumber();
        A.setNumber(number / 100);
    }
}
