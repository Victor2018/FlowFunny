package model.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class ColleagueA2 extends AbstractColleague2 {

    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.AaffectB();
    }
}
