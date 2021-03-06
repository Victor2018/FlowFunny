package module.victor.com;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import flowfunny.victor.com.R;

public class VoicePlayer implements OnBufferingUpdateListener, OnCompletionListener,
		OnPreparedListener {

	public MediaPlayer mediaPlayer; // 媒体播放器
	private SeekBar seekBar; // 拖动条
	private TextView tv_durationView;
	private Button ib_play;
	private Timer mTimer = new Timer(); // 计时器
	public boolean isplay=false;

	// 初始化播放器
	public VoicePlayer(SeekBar seekBar,TextView tv_durationView,Button ib_play) {
		super();
		this.seekBar = seekBar;
		this.tv_durationView=tv_durationView;
		this.ib_play=ib_play;
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 每一秒触发一次
		mTimer.schedule(timerTask, 0, 1000);
	}

	// 计时器
	TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
				handler.sendEmptyMessage(0); // 发送消息
			}
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();
			if (duration > 0) {
				long pos = seekBar.getMax() * position / duration;
				String timeStr = getMusicTime(position+1)+ "/" + getMusicTime(duration);
				tv_durationView.setText(timeStr);
				seekBar.setProgress((int) pos+1);
			}
		};
	};

	public void play() {
		mediaPlayer.start();
		isplay=true;
		ib_play.setBackgroundResource(R.drawable.btn_pause_selector);
	}

	/**
	 *
	 * @param url
	 *            url地址
	 */
	public void playUrl(String url) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url); // 设置数据源
			mediaPlayer.prepare(); // prepare自动播放
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 暂停
	public void pause() {
		mediaPlayer.pause();
		isplay=false;
		ib_play.setBackgroundResource(R.drawable.btn_play_selector);
	}

	// 停止
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
		ib_play.setBackgroundResource(R.drawable.btn_pause_selector);
		Log.e("mediaPlayer", "onPrepared");
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.e("mediaPlayer", "onCompletion");
	}

	/**
	 * 缓冲更新
	 */
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		seekBar.setSecondaryProgress(percent);
		int currentProgress = seekBar.getMax()
				* mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
		Log.e(currentProgress + "% play", percent + " buffer");
	}

	public static String getMusicTime(long duration) {
		return new SimpleDateFormat("mm:ss", Locale.CHINA).format(new Date(
				duration));
	}

}
