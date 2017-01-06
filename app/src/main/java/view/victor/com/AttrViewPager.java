package view.victor.com;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by victor on 2016/1/20.
 */
public class AttrViewPager extends ViewPager {
    private String TAG = "AttrViewPager";
    float xLast,yLast;
    private boolean isSlide = true;
    public AttrViewPager(Context context) {
        super(context);
    }

    public AttrViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(isSlide);
        float xDistance,yDistance;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = 0f;
                yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float curX = ev.getX();
                float curY = ev.getY();
                xDistance = curX - xLast;
                yDistance = curY - yLast;
                float distance = Math.abs(xDistance) - Math.abs(yDistance);
                if(xDistance > 0 && distance > 60){//从左向右滑动并且x方向比y方向滑动距离>60
                    if(getChildCount()>0 && getCurrentItem() == 0){
                        isSlide = false;
                    }
                } else {//从右向左滑动
                    if(getChildCount()>0 && getCurrentItem() == getChildCount() - 1){
                        isSlide = false;
                    }
                    isSlide = true;
                }
                if (SlidingMenu.isOpen) {
                    isSlide = false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        getParent().requestDisallowInterceptTouchEvent(isSlide);
        return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        getParent().requestDisallowInterceptTouchEvent(isSlide);
        return super.onTouchEvent(arg0);
    }

}
