package model.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public abstract class AbstractMediator {
    protected AbstractColleague2 A;
    protected AbstractColleague2 B;
    public AbstractMediator(AbstractColleague2 a, AbstractColleague2 b) {
        A = a;
        B = b;
    }
    public abstract void AaffectB();
    public abstract void BaffectA();

}
