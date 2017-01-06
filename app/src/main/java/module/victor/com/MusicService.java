package module.victor.com;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

public class MusicService extends Service implements OnCompletionListener{
	private String TAG = "MusicService";
	private MediaPlayer mp;
	private Cursor cursor;
	private int totalNum;
	private int current;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		mp = new MediaPlayer();
		mp.setOnCompletionListener(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		    // We want this service to continue running until it is explicitly    // stopped, so return sticky.    return START_STICKY;}
		if (intent == null || intent.getAction() == null) {
			return START_NOT_STICKY;
		}
		int playAction = intent.getIntExtra("PLAY_ACTION",-1);
		if (playAction == 1) {
			play();
		} else if (playAction == 2) {
			stop();
		} else if (playAction == 3) {
			next();
		} else if (playAction == 4) {
			prev();
		}
		return START_STICKY;
	}

	private void next(){
		current++;
		play();
	}

	private void prev(){
		current--;
		if (current == -1) {
			current = totalNum - 1;
		}
		play();
	}
	private void stop(){
		mp.stop();
	}


	private void play(){
		try {
			mp.reset();
			mp.setDataSource("http://yinyueshiting.baidu.com/data2/music/251608245/251608245.mp3?xcode=925e26ac11d2c4cce977ca24e3730051");
			mp.prepare();
			mp.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void onCompletion(MediaPlayer mp) {
		next();
	}
}
