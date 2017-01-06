package interfaces.victor.com;

import android.support.v4.view.ViewPager;

import mode.victor.com.DataObservable;
import util.victor.com.Constant;

/**
 * Created by victor on 2016/1/20.
 */
public class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener{

    private String TAG = "ViewPagerOnPageChangeListener";
    private int currentFrag = Constant.Action.FIRST_FRAG;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                currentFrag = Constant.Action.FIRST_FRAG;
                break;
            case 1:
                currentFrag = Constant.Action.SECOND_FRAG;
                break;
            case 2:
                currentFrag = Constant.Action.THIRD_FRAG;
                break;
            default:
                currentFrag = Constant.Action.FIRST_FRAG;
                break;
        }
        DataObservable.getInstance().setData(currentFrag);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
