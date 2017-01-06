package model.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class ColleagueB extends AbstractColleague{
    public void setNumber(int number, AbstractColleague coll) {
        this.number = number;
        coll.setNumber(number / 100);
    }
}
