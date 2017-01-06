package model.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public abstract class BaseTest {
    public void ask() {
        System.out.println("国内最牛的微博之一是？" + answer());
    }

    public abstract String answer();
}
