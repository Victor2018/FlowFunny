package view.victor.com;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import flowfunny.victor.com.R;
import util.victor.com.Constant;

/**
 * Created by victor on 2016/1/6.
 */
public class VictorDialog extends Dialog implements View.OnClickListener {
    private String TAG = "VictorDialog";
    private Context mContext;

    private Button mBtnOk,mBtnClear,mBtnCancel;
    private Bitmap mSignBitmap;
    private ImageView mIvSign;
    private PaintView mPaintView;
    private BWSeekBarColorPicker mBWSeekBarColorPicker;
    private SeekBarColorPicker mSeekBarColorPicker;
    private SeekBar mSbStrokeWidth;
    private String signPath;

    private int paintW,paintH;

    private OnWriteDoneListener mOnWriteDoneListener;

    public interface OnWriteDoneListener {
        void writeDone(Object result);
    }

    public void setOnWriteDoneListener (OnWriteDoneListener listener) {
        mOnWriteDoneListener = listener;
    }

    public VictorDialog(Context context) {
        super(context,android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        mContext = context;
        windowDeploy();
        initialize();
    }

    public VictorDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void initialize () {
        setContentView(R.layout.victor_dlg);
        mSeekBarColorPicker = (SeekBarColorPicker) findViewById(R.id.sbc_picker);
        mBWSeekBarColorPicker = (BWSeekBarColorPicker) findViewById(R.id.bw_sbc_picker);
        mSbStrokeWidth = (SeekBar) findViewById(R.id.sb_stroke_width);
        setGradients(mSeekBarColorPicker, Color.RED);
        setGradients2(mBWSeekBarColorPicker, Color.RED);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mBtnClear = (Button) findViewById(R.id.btn_clear);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);

        mBtnOk.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        mPaintView = (PaintView) findViewById(R.id.tablet_view);
        mPaintView.requestFocus();
        mIvSign =(ImageView)findViewById(R.id.iv_sign);

        mSignBitmap = mPaintView.getCachebBitmap();
        signPath = createFile();
        mIvSign.setImageBitmap(mSignBitmap);

        mSeekBarColorPicker.setOnSeekBarColorChangedListener(new OnColorChangeListener(Constant.Action.CHANGE_COLOR_ACTION));
        mBWSeekBarColorPicker.setOnSeekBarColorChangedListener(new OnColorChangeListener(Constant.Action.CHANGE_COLOR_ACTION));
        mSbStrokeWidth.setOnSeekBarChangeListener(new OnColorChangeListener(Constant.Action.CHANGE_STROKE_WIDTH_ACTION));
    }

    public void setGradients(SeekBar bar,int color){
        int[] mcolor=new int[]
//    			{0xFF000000, 0xFF0000FF, 0xFF00FF00, 0xFF00FFFF, 0xFFFF0000, 0xFFFF00FF, 0xFFFFFF00, 0xFFFFFFFF};
                {0xFFFF0000,0xFFFFFF00,0xFF00FF00, 0xFF00FFFF, 0xFF0000FF, 0xFFFF00FF,0xFFFF0000};
        //调色跳的颜色梯度
        GradientDrawable m=new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,mcolor);
        m.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        m.setCornerRadius(15);//进度条圆角
        bar.setProgressDrawable(m);
    }
    public void setGradients2(SeekBar bar,int color){
        int[] mcolor=new int[]{0xFF000000,0xFFFFFFFF};
        //黑，白
        GradientDrawable m=new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,mcolor);
        m.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        m.setCornerRadius(15);
        bar.setProgressDrawable(m);
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
        wl.width = (int) (width * 0.99);
        wl.height = (int) (height * 0.9);

        // 设置特效
        wl.alpha = 1.0f; // 透明度

        // 设置出入动画, 也可用window.setWindowAnimations();设置
        wl.windowAnimations = R.style.dialogWindowAnim;

        // wl.layoutAnimationParameters = new AnimationParameters();

        window.setAttributes(wl);
        paintW = wl.width;
        paintH = wl.height;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                Toast.makeText(mContext,"确定",Toast.LENGTH_SHORT).show();
                if (mOnWriteDoneListener != null) {
                    mOnWriteDoneListener.writeDone(mPaintView.getCachebBitmap());
                }
                mSignBitmap = mPaintView.getCachebBitmap();
                signPath = createFile();
                mIvSign.setImageBitmap(mSignBitmap);
                break;
            case R.id.btn_clear:
                Toast.makeText(mContext,"清除",Toast.LENGTH_SHORT).show();
                mPaintView.clear();
                break;
            case R.id.btn_cancel:
                Toast.makeText(mContext,"取消",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }

    /**
     * 创建手写签名文件
     *
     * @return
     */
    private String createFile() {
        ByteArrayOutputStream baos = null;
        String _path = null;
        try {
            String sign_dir = Environment.getExternalStorageDirectory() + File.separator;
            _path = sign_dir + System.currentTimeMillis() + ".jpg";
            baos = new ByteArrayOutputStream();
            mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] photoBytes = baos.toByteArray();
            if (photoBytes != null) {
                new FileOutputStream(new File(_path)).write(photoBytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return _path;
    }

    @Override
    public void show() {
        if (mPaintView != null) {
            mPaintView.clear();
        }
        super.show();
    }

    /**
     * 隐藏系统软键盘
     */
    private void hideKeyBoard (EditText et) {
        if (Build.VERSION.SDK_INT <= 10) {
            et.setInputType(InputType.TYPE_NULL);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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

    class OnColorChangeListener implements SeekBar.OnSeekBarChangeListener {
        private int action;

        public OnColorChangeListener (int actionType) {
            action = actionType;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (action == Constant.Action.CHANGE_COLOR_ACTION) {
                if (mPaintView != null) {
                    mPaintView.setPaintColor(progress);
                }
            } else if (action == Constant.Action.CHANGE_STROKE_WIDTH_ACTION) {
                if (mPaintView != null) {
                    mPaintView.setStrokeWidth(progress);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

}
