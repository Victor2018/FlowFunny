package view.victor.com;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

public class BWSeekBarColorPicker
        extends SeekBar
        implements SeekBar.OnSeekBarChangeListener {

    private int color;
    private OnSeekBarChangeListener listener;

    public BWSeekBarColorPicker(Context context) {
        super(context);
        init();
    }

    public BWSeekBarColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BWSeekBarColorPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setMax(255);
        setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * Sets a listener to receiver notifications about color changes to the Seekbar's progress level
     *
     * @param listener The seek bar notification listener
     */
    public void setOnSeekBarColorChangedListener(OnSeekBarChangeListener listener) {
        this.listener = listener;
    }

    public int getCurrentColor() {
        return color;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            color = changeColor(progress);
            if (listener != null) {
//                listener.onProgressColorChanged(color);
                listener.onProgressChanged(null,color,false);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        color = changeColor(seekBar.getProgress());
        if (listener != null) {
//            listener.onStartColorChanged(color);
            listener.onStartTrackingTouch(null);
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        color = changeColor(seekBar.getProgress());
        if (listener != null) {
//            listener.onStopColorChanged(color);
            listener.onStopTrackingTouch(null);
        }
    }

    private int changeColor(int progress) {

        int r = 0;
        int g = 0;
        int b = 0;

        Log.d("progress", "progress: " + progress);
        if (progress < 256) {
        	b=progress % 256;
            r = progress % 256 ;//
            g=progress % 256;
        } 
        return Color.argb(255, r, g, b);

    }
}
