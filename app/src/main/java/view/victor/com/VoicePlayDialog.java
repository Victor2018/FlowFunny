package view.victor.com;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import flowfunny.victor.com.R;
import module.victor.com.VoicePlayer;

/**
 * Created by victor on 2016/1/6.
 */
public class VoicePlayDialog extends Dialog implements View.OnClickListener {
    private String TAG = "VictorDialog";
    private Context mContext;
    private TextView mTvDuration,mTvTitle;
    private Button mBtnPlay,mBtnClose;
    private SeekBar mSbProgress;

    private VoicePlayer mVoicePlayer;

    private String mPlayUrl;

    public VoicePlayDialog(Context context) {
        super(context,android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        mContext = context;
        windowDeploy();
        initialize();
    }

    public VoicePlayDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void initialize () {
        setContentView(R.layout.voice_play);
        mTvDuration = (TextView) findViewById(R.id.tv_voice_play_dlg_duration);
        mTvTitle = (TextView) findViewById(R.id.tv_voice_play_dlg_title);
        mBtnPlay = (Button) findViewById(R.id.btn_voice_play_dlg_play);
        mBtnClose = (Button) findViewById(R.id.btn_voice_play_dlg_close);
        mSbProgress = (SeekBar) findViewById(R.id.sb_play_dlg_progress);

        mBtnPlay.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);

        mVoicePlayer = new VoicePlayer(mSbProgress,mTvDuration,mBtnPlay);

    }

    public void setVoicePlayUrl (String playUrl) {
        mPlayUrl = playUrl;
    }

    @Override
    public void dismiss() {
        if (mVoicePlayer != null) {
            mVoicePlayer.stop();
            mVoicePlayer = null;
        }
        super.dismiss();
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
        wl.width = (int) (width * 0.8);
//        wl.height = (int) (height * 0.3);
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置特效
        wl.alpha = 1.0f; // 透明度

        // 设置出入动画, 也可用window.setWindowAnimations();设置
        wl.windowAnimations = R.style.dialogWindowAnim;

        // wl.layoutAnimationParameters = new AnimationParameters();

        window.setAttributes(wl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_voice_play_dlg_play:
                if (!TextUtils.isEmpty(mPlayUrl)) {
                    if (mVoicePlayer != null) {
                        if (mVoicePlayer.mediaPlayer.isPlaying()) {
                            mVoicePlayer.pause();
                            mBtnPlay.setBackgroundResource(R.drawable.btn_play_selector);
                        } else {
                            mVoicePlayer.play();
                            mBtnPlay.setBackgroundResource(R.drawable.btn_pause_selector);
                        }
                        mVoicePlayer.playUrl(mPlayUrl);
                    } else {
                        mVoicePlayer = new VoicePlayer(mSbProgress,mTvDuration,mBtnPlay);
                        if (mVoicePlayer.mediaPlayer.isPlaying()) {
                            mVoicePlayer.pause();
                            mBtnPlay.setBackgroundResource(R.drawable.btn_play_selector);
                        } else {
                            mVoicePlayer.play();
                            mBtnPlay.setBackgroundResource(R.drawable.btn_pause_selector);
                        }
                        mVoicePlayer.playUrl(mPlayUrl);
                    }
                }
                break;
            case R.id.btn_voice_play_dlg_close:
                if (mVoicePlayer != null) {
                    mVoicePlayer.stop();
                }
                dismiss();
                break;
        }
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

}
