package model.victor.com;

import interfaces.victor.com.CaiInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class Director {
    private CaiInterface caiInterface;

    public Director(CaiInterface caiInterface) {
        this.caiInterface = caiInterface;
    }

    public void zuocai() {
        caiInterface.fangyan();
        caiInterface.fangyou();
        caiInterface.jiashui();
    }
}
