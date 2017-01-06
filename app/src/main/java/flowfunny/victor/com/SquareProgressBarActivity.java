package flowfunny.victor.com;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import view.victor.com.SquareProgressBar;

public class SquareProgressBarActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton mFab;
    private SquareProgressBar mSpbImg;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    mSpbImg.setProgress(msg.arg1);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_progress_bar);

        initialize();
    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mSpbImg = (SquareProgressBar) findViewById(R.id.spb_img);
        mFab.setOnClickListener(this);

        mSpbImg.setColor("#00ffff");
        mSpbImg.setImage(R.mipmap.square_progress_bar);
        mSpbImg.setProgress(32);

        new Thread (){
            @Override
            public void run() {
                for (int i=33;i<101;i++){
                    Message msg = new Message();
                    msg.what = 100;
                    msg.arg1 = i;
                    mHandler.sendMessage(msg);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
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
}
