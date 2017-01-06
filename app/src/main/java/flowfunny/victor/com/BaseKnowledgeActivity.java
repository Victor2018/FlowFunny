package flowfunny.victor.com;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import mode.victor.com.DataObservable;
import model.victor.com.BaseKnowledgeTask;
import util.victor.com.Constant;
import util.victor.com.HttpRequestHelper;
import view.victor.com.CircularProgress;

public class BaseKnowledgeActivity extends AppCompatActivity implements View.OnClickListener,Observer{
    private FloatingActionButton mFab;
    private TextView mTvContent;
    private CircularProgress mCpProgress;

    private BaseKnowledgeTask mBaseKnowledgeTask;
//    private HttpRequestHelper mHttpRequestHelper;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.Msg.REQUEST_SUCCESS:
                    mCpProgress.setVisibility(View.GONE);
                    mTvContent.setText(msg.obj.toString());
                    break;
                case Constant.Msg.REQUEST_FAILED:
                    mCpProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"访问服务器失败！",Toast.LENGTH_SHORT).show();
                    break;
                case Constant.Msg.NETWORK_ERROR:
                    mCpProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"网络错误，请检查网络是否连接！",Toast.LENGTH_SHORT).show();
                    break;
                case Constant.Msg.SOCKET_TIME_OUT:
                    mCpProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"访问服务器超时，请重试！",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_knowledge);
        initialize();
        initData();
    }

    private void initialize () {
        DataObservable.getInstance().addObserver(this);
//        mHttpRequestHelper = new HttpRequestHelper(this);
        mBaseKnowledgeTask = new BaseKnowledgeTask(this);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mCpProgress = (CircularProgress) findViewById(R.id.cp_progress);
        mFab.setOnClickListener(this);
    }

    private void initData () {
//        if (mHttpRequestHelper != null) {
//            mHttpRequestHelper.requestBaseDatas(Constant.Msg.BASE_REQUEST);
//        }
        mBaseKnowledgeTask.requestBaseData();
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
    public void update(Observable observable, Object data) {
        if (data instanceof Bundle) {
            Bundle result = (Bundle) data;
            int status = result.getInt(Constant.STATUS_KEY);
            int requstMst = result.getInt(Constant.REQUEST_MSG_KEY);
            Message msg = new Message();
            msg.arg1 = requstMst;
            switch (status) {
                case Constant.Msg.REQUEST_SUCCESS:
                    msg.what = Constant.Msg.REQUEST_SUCCESS;
                    msg.obj = result.getString(Constant.BASE_DATA_KEY);
                    break;
                case Constant.Msg.REQUEST_FAILED:
                    msg.what = Constant.Msg.REQUEST_FAILED;
                    break;
                case Constant.Msg.NETWORK_ERROR:
                    msg.what = Constant.Msg.NETWORK_ERROR;
                    break;
                case Constant.Msg.SOCKET_TIME_OUT:
                    msg.what = Constant.Msg.SOCKET_TIME_OUT;
                    break;
            }
            if (msg.arg1 == Constant.Msg.BASE_REQUEST) {
                mHandler.sendMessage(msg);
            }
        }
    }

    @Override
    protected void onDestroy() {
//        if (mHttpRequestHelper != null) {
//            mHttpRequestHelper.onDestroy();
//        }
        DataObservable.getInstance().deleteObserver(this);
        super.onDestroy();
    }
}
