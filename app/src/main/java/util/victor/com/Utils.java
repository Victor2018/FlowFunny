package util.victor.com;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by victor on 2016/1/14.
 */
public class Utils {
private static String TAG = "Utils";
    /**
     * 获取IP
     * @return
     */
    public static String localipget() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {}
        return null;
    }

    /**
     * 根据apk路径获取包名
     *
     * @param context
     * @param strFile apk路径
     * @return apk包名
     */
    public static String getPackageName(Context context, String strFile) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(strFile, PackageManager.GET_ACTIVITIES);
        String mPackageName = null;
        if (packageInfo != null) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            mPackageName = applicationInfo.packageName;
        }
        return mPackageName;
    }

    /**
     * 根据包名获取apk名称
     *
     * @param context
     * @param packageName 包名
     * @return apk名称
     */
    public static String getApkName(Context context, String packageName) {
        String apkName = null;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                apkName = pm.getApplicationLabel(applicationInfo).toString();
                // int lable = applicationInfo.labelRes;
                // apkName = context.getResources().getString(lable);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return apkName;
    }

    /**
     * 根据apk文件获取apk应用名称
     * @param context
     * @param apkFilePath  apk文件路径
     * @return
     */
    public static String getAppNameByApkFile(Context context, String apkFilePath) {
        String apkName = null;
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES);
        if (packageInfo != null) {
            apkName = pm.getApplicationLabel(packageInfo.applicationInfo).toString();
        }
        return apkName;
    }

    public static int getScreenWidth (Activity activity) {

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        float density = metrics.density;//屏幕密度（0.75/1.0/1.5）
        int densityDpi = metrics.densityDpi;//屏幕密度DPI(120/160/240)

        return width;
    }
    public static int getScreenHeight (Activity activity) {

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        float density = metrics.density;//屏幕密度（0.75/1.0/1.5）
        int densityDpi = metrics.densityDpi;//屏幕密度DPI(120/160/240)

        return height;
    }

    /**
     * 是否是横屏
     * @param activity
     * @return
     */
    public static boolean isLandScape (Activity activity){
        boolean isLandScape = false;
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        if (width > height) {
            isLandScape = true;
        }
        return isLandScape;
    }
}
