package util.victor.com;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.Buffer;

/**
 * Created by zhanghaifeng on 2016/4/5 0005.
 */
public class DataUtil {
    public void writeByFile(String content) {
        File file = new File("/sdcard/base_knowledge.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file,true);
            PrintWriter out = new PrintWriter(fos);
            out.println(content);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String readByFile () {
        StringBuffer content = new StringBuffer();
        File file = new File("/sdcard/base_knowledge.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int size = fis.read(buffer);
            while (size != -1) {
                String str = new String(buffer);
                content.append(str);
                size = fis.read(buffer);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content.toString();
    }
    public String readByFile2 () {
        StringBuffer content = new StringBuffer();
        File file = new File("/sdcard/base_knowledge.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String m = reader.readLine();
            while (m != null){
                content.append(m);
                m = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content.toString();
    }

    public void writeByXml(Context context,String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("madata", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value);
        ed.commit();
    }
    public String readByXml(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("madata", Context.MODE_WORLD_READABLE);
        String value = sp.getString(key, "default");
        return value;
    }
}
