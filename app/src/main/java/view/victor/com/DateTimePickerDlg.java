package view.victor.com;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import flowfunny.victor.com.R;

/**
 * Created by victor on 2016/1/12.
 */
public class DateTimePickerDlg extends Dialog implements View.OnClickListener,
        DatePicker.OnDateChangedListener,TimePicker.OnTimeChangedListener{
    private Context mContext;
    private int mX = 0, mY = 0;

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private Button mBtnOk,mBtnCancel;
    private String dateTime;
    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat currentFomat;
    private OnDateTimeChangeListener mOnDateTimeChangeListener;

    public interface OnDateTimeChangeListener{
        void onDateTimeChanged(String dateTime);
    }

    public DateTimePickerDlg(Context context,SimpleDateFormat sdf) {
        super(context,android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        mContext = context;
        currentFomat = sdf;

        windowDeploy();
        initialize();
        initData();
    }

    private void initialize(){
        setContentView(R.layout.dlg_datetime);
        mDatePicker = (DatePicker) findViewById(R.id.datepicker);
        mTimePicker = (TimePicker) findViewById(R.id.timepicker);
        mBtnOk = (Button) findViewById(R.id.btn_date_time_dlg_ok);
        mBtnCancel = (Button) findViewById(R.id.btn_date_time_dlg_cancel);
        if(currentFomat == sdf2){
            mTimePicker.setVisibility(View.GONE);
        }
        mBtnOk.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(this);

//		mDatePicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);//禁止通过keyboard输入
//		mTimePicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);//禁止通过keyboard输入

    }

    public void setDateTime(OnDateTimeChangeListener listener){
        mOnDateTimeChangeListener = listener;
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.btn_date_time_dlg_ok:
                if(mOnDateTimeChangeListener != null){
                    mDatePicker.clearFocus();
                    mTimePicker.clearFocus();
                    if(!TextUtils.isEmpty(dateTime)){
                        mOnDateTimeChangeListener.onDateTimeChanged(dateTime);
                    }else{
                        mOnDateTimeChangeListener.onDateTimeChanged(dateTime = currentFomat.format(new Date()));
                    }
                }
                dismiss();
                break;
            case R.id.btn_date_time_dlg_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        // TODO Auto-generated method stub
        onDateChanged(null, 0, 0, 0);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // TODO Auto-generated method stub
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();


        if(currentFomat != null){
            if(currentFomat == sdf1){
                calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(),
                        mDatePicker.getDayOfMonth(), mTimePicker.getCurrentHour(),
                        mTimePicker.getCurrentMinute());
            }else if(currentFomat == sdf2){
                calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(),
                        mDatePicker.getDayOfMonth());
            }
        }

        dateTime = currentFomat.format(calendar.getTime());
//		setTitle(dateTime);
    }

    private void initData(){
        Calendar calendar = Calendar.getInstance();
        mDatePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        mTimePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        mTimePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    private void windowDeploy () {
        Window window = getWindow();

        // 设置背景
        window.setBackgroundDrawableResource(android.R.color.transparent); // 设置对话框背景

        window.setDimAmount(0.7f); // 后面窗口变暗值
        setCanceledOnTouchOutside(true); // 触摸其它地方消失

        WindowManager.LayoutParams wl = window.getAttributes();

        // 设置位置
        wl.gravity = Gravity.CENTER; // 重心: 将改变坐标原点的位置, 例: 重点在中心, 则原点在中心
        wl.x = 0;
        wl.y = 0;
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        wl.width = (int) (width * 0.9);
        wl.height = (int) (height * 0.7);

        // 设置特效
        wl.alpha = 1.0f; // 透明度

        // 设置出入动画, 也可用window.setWindowAnimations();设置
        wl.windowAnimations = R.style.dialogWindowAnim;

        // wl.layoutAnimationParameters = new AnimationParameters();

        window.setAttributes(wl);
    }
}
