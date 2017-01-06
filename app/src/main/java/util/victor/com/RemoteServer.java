package util.victor.com;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import net.sunniwell.android.httpserver.HttpServer;

import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by victor on 2016/1/14.
 */
public class RemoteServer {
    public static final int REMOTE_INSTALLATION_START_INSTALL = 1001;
    public static final int REMOTE_INSTALLATION_INSTALL_RESULT = 1002;
    public String mPackName = null;
    private Context mContext;
    private HttpServer server;
    private UploadFileHandler upLoadFileHandler;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String apkName = null;
            String packageName = null;
            switch (msg.what) {
                case REMOTE_INSTALLATION_START_INSTALL:
                    String uri = (String) msg.obj;
                    startInstallAPK(uri);
                    // -----将开始安装的信息显示给用户---------
                    mPackName = Utils.getPackageName(mContext, uri);
                    apkName = Utils.getAppNameByApkFile(mContext, uri);
                    showPromptMessage(REMOTE_INSTALLATION_START_INSTALL, apkName, true);
                    break;
                case REMOTE_INSTALLATION_INSTALL_RESULT:
                    boolean result = false;
                    if (msg.arg1 == 1)
                        result = true;
                    packageName = (String) msg.obj;
                    // --------------------------------------------
                    apkName = Utils.getApkName(mContext, packageName);
                    showPromptMessage(REMOTE_INSTALLATION_INSTALL_RESULT, apkName, result);
                    // --------------------------------------------
                    JSONObject json = new JSONObject();
                    if (result) {
                        //安装成功
                        try {
                            json.put("installation", true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else {
                        //安装失败
                        try {
                            json.put("installation", false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        upLoadFileHandler.writeToHTML(json.toString());
                        UploadFileHandler.isInstall = false;
                    } catch (Exception e) {

                    }
                    break;
            }
        };
    };

    public void setSuccessResult(int result,String packageName) {
        Message msg = mHandler.obtainMessage();
        msg.arg1 = result;
        msg.obj = packageName;
        msg.what = REMOTE_INSTALLATION_INSTALL_RESULT;
        mHandler.sendMessageDelayed(msg, 1000);
    }

    public RemoteServer(Context context) {
        super();
        this.mContext = context;
    }

    /**
     * 开启HttpServer服务
     * @param port 端口	 */
    public void start(int port) {
        try {
            server = new HttpServer(port);
            HttpRequestHandlerRegistry registry = server.getHttpRequestHandlerRegistry();
            upLoadFileHandler = new UploadFileHandler(mHandler,mContext);
            registry.register("/upload.action", upLoadFileHandler);
            registry.register("*", new HttpFileHandler(mContext));
            server.start();
        } catch (Exception e) {
        }
    }

    /**
     * 关闭HttpServer服务
     */
    public void stop() {
        server.stop();
    }

    private void startInstallAPK(final String uri) {
        Thread installThread = new Thread() {
            @Override
            public void run() {
                super.run();
                install(uri);//调用系统的安装程序			}
            }
        };
        installThread.start();
    }
    /**
     * 安装apk开始和结束时给用户提示
     * @param state 开始或结束
     * @param apkName apk名称
     * @param result 安装成功或失败
     */
    private void showPromptMessage(int state, String apkName, boolean result) {
        switch (state) {
            case REMOTE_INSTALLATION_START_INSTALL:
                Toast.makeText(mContext, apkName + "开始安装！", Toast.LENGTH_SHORT).show();
                break;
            case REMOTE_INSTALLATION_INSTALL_RESULT:
                if(result){
                    Toast.makeText(mContext, apkName+"安装成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, apkName+"安装失败！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 调用android原生的安装接口安装apk
     *
     * @param uri 文件路径
     */
    public void install(String uri) {
        File updateFile = new File(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(updateFile),"application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }
}
