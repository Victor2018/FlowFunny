package flowfunny.victor.com;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import util.victor.com.Constant;
import view.victor.com.WaveView;

public class WaveViewActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{
    private FloatingActionButton mFab;
    private SeekBar seekBar;
    private WaveView waveView;

    Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == Constant.Msg.UPDATE_WAVE_VIEW_PROGRESS) {
                waveView.setProgress(msg.arg1);
                seekBar.setProgress(msg.arg1);

            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);

        initialize();
    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        waveView = (WaveView) findViewById(R.id.wave_view);

        mFab.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);

        new Thread(){
            public void run() {
                try {
                    for(int i=0;i<100;i++){
                        Thread.sleep(100);
                        Message msg = new Message();
                        msg.what = Constant.Msg.UPDATE_WAVE_VIEW_PROGRESS;
                        msg.arg1 = i + 1;
                        mHandler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            };
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        waveView.setProgress(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
