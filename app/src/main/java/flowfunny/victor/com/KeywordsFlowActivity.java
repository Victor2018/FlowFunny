package flowfunny.victor.com;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import view.victor.com.ClearEditText;
import view.victor.com.KeywordsFlow;

public class KeywordsFlowActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int FEEDKEY_START = 1;
    private FloatingActionButton mFab;
    private ImageView back_arrow;
    private Animation shakeAnim;
    private ClearEditText searchEdit;
    private KeywordsFlow keywordsFlow;
    private int STATE = 1;

    private static String[] keywords = new String[] { "娘要嫁人", "球爱酒吧", "使徒行者",
            "亮剑", "完美搭档", "致青春", "非常完美", "一生一世", "穿越火线", "天龙八部", "匹诺曹", "让子弹飞",
            "穿越火线", "情定三生", "心术", "马向阳下乡记", "人在囧途", " 高达", " 刀剑神域", "泡芙小姐",
            "尖刀出鞘", "甄嬛传", "兵出潼关", "电锯惊魂3D", "古剑奇谭", "同桌的你" };

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case FEEDKEY_START:
                    keywordsFlow.rubKeywords();
                    feedKeywordsFlow(keywordsFlow, keywords);
                    keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
                    sendEmptyMessageDelayed(FEEDKEY_START, 5000);
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywords_flow);
        initialize();
    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);
        keywordsFlow.setDuration(1000l);
        keywordsFlow.setOnItemClickListener(this);
        back_arrow = (ImageView) findViewById(R.id.back_arrow);
        searchEdit = (ClearEditText) findViewById(R.id.search_view);
        shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_y);
        back_arrow.setAnimation(shakeAnim);
        feedKeywordsFlow(keywordsFlow, keywords);
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
        handler.sendEmptyMessageDelayed(FEEDKEY_START, 5000);

        mFab.setOnClickListener(this);
    }


    private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < KeywordsFlow.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            keywordsFlow.feedKeyword(tmp);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                break;
        }
        if (v instanceof TextView) {
            String keyword = ((TextView) v).getText().toString().trim();
            searchEdit.setText(keyword);
            searchEdit.setSelection(keyword.length());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        back_arrow.clearAnimation();
        handler.removeMessages(FEEDKEY_START);
        STATE = 0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(FEEDKEY_START);
        STATE = 0;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(FEEDKEY_START);
        STATE = 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (STATE == 0) {
            keywordsFlow.rubKeywords();
            handler.sendEmptyMessageDelayed(FEEDKEY_START, 3000);
        }

    }


}
