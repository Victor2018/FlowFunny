package util.victor.com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 异步加载图片
 * */

public class AsyncImageLoader {

	private HashMap<String, Object> imageCache;
	private List<Thread> threadList;
	private String TAG = "AsyncImageLoader";
	private boolean isInputStream;

	public AsyncImageLoader(boolean isInputStream) {
		imageCache = new HashMap<String, Object>();
		threadList = new ArrayList<Thread>();
		this.isInputStream = isInputStream;
	}

	public void loadDrawable(final String imageUrl,final ImageCallback imageCallback) {
		if (imageCache.containsKey(imageUrl)) {
			Object drawable = imageCache.get(imageUrl);
			if (drawable != null) {
				imageCallback.imageLoaded(drawable, imageUrl);
				return;
			}
		}

		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded(message.obj, imageUrl);
			}
		};

		Thread thread = new Thread() {
			@Override
			public void run() {
				InputStream is = loadImageFromUrl(imageUrl);
				Drawable drawable = inputStream2Drawable(is);
				imageCache.put(imageUrl, drawable);
				if (isInputStream){
					Message message = handler.obtainMessage(0, is);
					message.sendToTarget();
				} else {
					Message message = handler.obtainMessage(0, drawable);
					message.sendToTarget();
				}
			}
		};
		thread.start();
		threadList.add(thread);
	}

	public InputStream loadImageFromUrl2(String imgUrl) {
		InputStream inputStream = null;

		try {
			URL url = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.connect();
			inputStream = conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}

	public InputStream loadImageFromUrl(String url) {
		InputStream inputStream = null;
		//		Drawable drawable = null;
		if (url != null && !url.trim().equals("")) {
			try {
				HttpGet request = new HttpGet(url);
				DefaultHttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, HTTP.UTF_8);
				client.getParams().setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET, HTTP.UTF_8);
				client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
				client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					inputStream = entity.getContent();
				}
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Log.e(TAG,"imgUrl错误！！！！！！！！！！！！！！！！！！");
		}

		return inputStream;
	}

	public Drawable inputStream2Drawable(InputStream is) {
		Drawable drawable = null;

		if (is != null) {
			drawable = Drawable.createFromStream(is, "src");
		} else {
			Log.d(TAG, "loadImageFromUrl-is = null");
		}
		return drawable;
	}

	public interface ImageCallback {
		void imageLoaded(Object image, String imageUrl);
	}

	public void close(){
		if(threadList != null){
			for(Iterator<Thread> it = threadList.iterator(); it.hasNext();){
				Thread thread = it.next();
				if(thread != null){
					thread.interrupt();
				}
			}
		}
	}

}
