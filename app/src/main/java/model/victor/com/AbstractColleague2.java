package model.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public abstract class AbstractColleague2 {
    protected int number;
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    // 注意这里的参数不再是同事类，而是一个中介者
    public abstract void setNumber(int number, AbstractMediator am);
}
