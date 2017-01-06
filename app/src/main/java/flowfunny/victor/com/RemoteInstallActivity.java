package flowfunny.victor.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import util.victor.com.RemoteServer;
import util.victor.com.Utils;

public class RemoteInstallActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "RemoteInstallActivity";
    private FloatingActionButton mFab;
    private TextView mTvRemoteUrl;
    private int port = 10101;
    private RemoteServer server;
    private BroadcastReceiver mReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String packageName = intent.getData().getSchemeSpecificPart();
            String mPackName = server.mPackName;
            Log.d(TAG, "packageName=" + packageName + "\t mPackName=" + mPackName);
            if(packageName.equals(mPackName)) {
                server.setSuccessResult(1, packageName);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_install);

        initialize();
        initData();
    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTvRemoteUrl = (TextView) findViewById(R.id.tv_remote_url);
        mFab.setOnClickListener(this);
    }

    private void registerPackageReceiver(){
        //注册广播
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        mFilter.addDataScheme("package");
        registerReceiver(mReceiver, mFilter);
    }


    private void initData(){
        String str = getResources().getString(R.string.remote_installation_help_info);
        String ip = Utils.localipget();
        String helpInfo = str + "\thttp://" + ip + ":" + port;
        mTvRemoteUrl.setText(helpInfo);
        server = new RemoteServer(getApplicationContext());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "打开httpserver服务");
        server.start(port);
        registerPackageReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "关闭httpserver服务");
        server.stop();
        unregisterReceiver(mReceiver);
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
