package flowfunny.victor.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import view.victor.com.GifView;

public class GifViewActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener,GifView.OnGifPlayingListener{

    FloatingActionButton mFab;
    private GifView mGf;
    private SeekBar mSbTime;
    private Button mBtnStart;

    private boolean startDrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_view);

        initialize();

    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mGf = (GifView) findViewById(R.id.gif);
        mSbTime = (SeekBar) findViewById(R.id.sb_time);
        mBtnStart = (Button) findViewById(R.id.btn_start);

        mFab.setOnClickListener(this);
        mBtnStart.setOnClickListener(this);
        mSbTime.setOnSeekBarChangeListener(this);
        mGf.setOnGifPlayingListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                break;
            case R.id.btn_start:
                if (mGf.isPaused()) {
                    mGf.resume();
                } else {
                    mGf.pause();
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }
        mGf.seekTo(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        startDrag = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        startDrag = false;
    }

    @Override
    public void onProgress(int time) {
        // 正在拖动时不更新seekbar进度
        if (!startDrag) {
            mSbTime.setProgress(time);
        }
    }
}
