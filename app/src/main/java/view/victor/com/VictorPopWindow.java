package view.victor.com;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import flowfunny.victor.com.R;

/**
 * Created by victor on 2016/1/6.
 */
public class VictorPopWindow extends PopupWindow {
    private String TAG = "VictorPopWindow";
    private Context mContext;

    public VictorPopWindow (Context context) {
        mContext = context;
        windowDeploy();
        initialize();
    }

    private void initialize (){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.victor_popwindow,null);
        setContentView(view);
    }

    private void windowDeploy () {
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        //设置popwindow弹出窗体的宽
        setWidth((int) (width / 2.5));
        //设置popwindow弹出窗体的高
        setHeight(height / 3);
        //设置popwindow弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        //刷新状态
        update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable cd = new ColorDrawable(0000000000);
        //点击BACK键和其他地方使其消失，设置了这个才能触发OnDismissListener，设置其他控件变化等操作
        setBackgroundDrawable(cd);
        setAnimationStyle(R.style.VictorPopWindowStyle);
    }

    public void showPopWindow (View view) {
        Log.e(TAG,"showPopWindow()......");
        if (!isShowing()) {
            showAsDropDown(view, view.getLayoutParams().width / 2, 2);
        } else {
            dismiss();
        }
    }

    /**
     * 隐藏系统软键盘
     */
    private void hideKeyBoard (EditText et) {
        if (Build.VERSION.SDK_INT <= 10) {
            et.setInputType(InputType.TYPE_NULL);
        } else {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Class<EditText> cls = EditText.class;
            try {
                Method setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus",boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(et,false);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
