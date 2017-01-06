package frag.victor.com;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import adapter.victor.com.FunnyAdapter;
import adapter.victor.com.VoiceAdapter;
import data.victor.com.FunnyContentData;
import data.victor.com.FunnyData;
import data.victor.com.VoiceContentData;
import data.victor.com.VoiceData;
import flowfunny.victor.com.R;
import mode.victor.com.DataObservable;
import module.victor.com.Player;
import util.victor.com.Constant;
import util.victor.com.DateUtil;
import util.victor.com.HttpRequestHelper;
import view.victor.com.CircularProgress;
import view.victor.com.VoicePlayDialog;
import view.victor.com.XListView;

public class VoiceFrag extends Fragment implements Observer,XListView.IXListViewListener,AdapterView.OnItemClickListener{

	private String TAG = "VoiceFrag";
	private CircularProgress mCpProgress;
	private XListView mLvVoice;
	private VoiceAdapter mVoiceAdapter;
	private List<VoiceContentData> voiceContentDatas = new ArrayList<VoiceContentData>();
	private HttpRequestHelper mHttpRequestHelper;
	private  int currentPage = 1;//当前页数
	private int countPages;//总页数
	private VoicePlayDialog mVoicePlayDialog;
	private Player mPlayer;
	private Context mContext;

	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case Constant.Msg.REQUEST_SUCCESS:
					mLvVoice.setPullLoadEnable(true);
					mCpProgress.setVisibility(View.GONE);
					voiceContentDatas.addAll((List<VoiceContentData>) msg.obj);
					mVoiceAdapter.setVoiceContentDatas(voiceContentDatas);
					mVoiceAdapter.notifyDataSetChanged();
					mLvVoice.setRefreshTime(DateUtil.getTodayTime());
					if(currentPage == countPages){
						mLvVoice.setPullLoadEnable(false);
					} else {
						mLvVoice.setPullLoadEnable(true);
					}
					break;
				case Constant.Msg.REQUEST_SUCCESS_NO_DATA:
					mCpProgress.setVisibility(View.GONE);
					if (currentPage == 1) {
						voiceContentDatas.clear();
						mLvVoice.setPullLoadEnable(false);
						mVoiceAdapter.notifyDataSetChanged();
					}
					if (mContext != null) {
						Toast.makeText(mContext, "服务器没有数据！", Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.Msg.REQUEST_FAILED:
					mCpProgress.setVisibility(View.GONE);
					if (currentPage == 1) {
						voiceContentDatas.clear();
						mLvVoice.setPullLoadEnable(false);
						mVoiceAdapter.notifyDataSetChanged();
					}
					if (mContext != null) {
						Toast.makeText(mContext,"访问服务器失败！",Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.Msg.PARSING_EXCEPTION:
					mCpProgress.setVisibility(View.GONE);
					if (currentPage == 1) {
						voiceContentDatas.clear();
						mLvVoice.setPullLoadEnable(false);
						mVoiceAdapter.notifyDataSetChanged();
					}
					if (mContext != null) {
						Toast.makeText(mContext,"数据解析异常！",Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.Msg.NETWORK_ERROR:
					mCpProgress.setVisibility(View.GONE);
					if (currentPage == 1) {
						voiceContentDatas.clear();
						mLvVoice.setPullLoadEnable(false);
						mVoiceAdapter.notifyDataSetChanged();
					}
					if (mContext != null) {
						Toast.makeText(mContext,"网络错误，请检查网络是否连接！",Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.Msg.SOCKET_TIME_OUT:
					mCpProgress.setVisibility(View.GONE);
					if (currentPage == 1) {
						voiceContentDatas.clear();
						mLvVoice.setPullLoadEnable(false);
						mVoiceAdapter.notifyDataSetChanged();
					}
					if (mContext != null) {
						Toast.makeText(mContext,"访问服务器超时，请重试！",Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.Msg.PLAY_VOICE:
					if (mPlayer != null) {
						VoiceContentData data = (VoiceContentData) msg.obj;
						String playUrl = data.getVoiceUri();
						mPlayer.playUrl(playUrl,false);
					}
					break;
			}
		}
	};

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContext = getContext();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_voice,container, false);
		initialize (view);
		DataObservable.getInstance().addObserver(this);
		return view;
	}
	private void initialize (View view) {
		mCpProgress = (CircularProgress) view.findViewById(R.id.cp_progress);
		mLvVoice = (XListView) view.findViewById(R.id.lv_voice_frag);
		mLvVoice.setPullLoadEnable(false);
		mLvVoice.setXListViewListener(this);
		mVoiceAdapter = new VoiceAdapter(getContext(),mLvVoice);
		mVoiceAdapter.setVoiceContentDatas(voiceContentDatas);
		mLvVoice.setAdapter(mVoiceAdapter);
		mLvVoice.setOnItemClickListener(this);
		mHttpRequestHelper = new HttpRequestHelper(getContext());
		mPlayer = new Player(getContext(),null,mHandler,null);
		mVoicePlayDialog = new VoicePlayDialog(getActivity());
		requestVoiceDatas();
	}

	@Override
	public void update(Observable observable, Object data) {
		Log.e(TAG, "update()......");
		Message msg = new Message();
		if (data instanceof VoiceData) {
			VoiceData voiceData = (VoiceData) data;
			int status = voiceData.getStatus();
			Log.e(TAG, "update()......status=" + status);
			switch (status) {
				case Constant.Msg.REQUEST_SUCCESS:
					msg.what = Constant.Msg.REQUEST_SUCCESS;
					msg.obj = voiceData.getVoiceContentDatas();
					countPages = voiceData.getAllPages();
					break;
				case Constant.Msg.REQUEST_SUCCESS_NO_DATA:
					msg.what = Constant.Msg.REQUEST_SUCCESS_NO_DATA;
					break;
				case Constant.Msg.REQUEST_FAILED:
					msg.what = Constant.Msg.REQUEST_FAILED;
					break;
				case Constant.Msg.PARSING_EXCEPTION:
					msg.what = Constant.Msg.PARSING_EXCEPTION;
					break;
				case Constant.Msg.NETWORK_ERROR:
					msg.what = Constant.Msg.NETWORK_ERROR;
					break;
				case Constant.Msg.SOCKET_TIME_OUT:
					msg.what = Constant.Msg.SOCKET_TIME_OUT;
					break;
			}
		} else if (data instanceof VoiceContentData) {
			VoiceContentData voiceContentData = (VoiceContentData) data;
			int action = voiceContentData.getAction();
			if (action == Constant.Action.PLAY_VOICE_ACTION) {
				msg.what = Constant.Msg.PLAY_VOICE;
				msg.obj = voiceContentData;
			}
		}
		mHandler.sendMessage(msg);
	}

	private void onLoad() {
		mLvVoice.stopRefresh();
		mLvVoice.stopLoadMore();
		mLvVoice.setRefreshTime(DateUtil.getTodayTime());
	}

	@Override
	public void onRefresh() {
		voiceContentDatas.clear();
		currentPage --;
		if (currentPage < 1){
			currentPage = 1;
		}
		requestVoiceDatas();
		onLoad();
	}

	@Override
	public void onLoadMore() {
		voiceContentDatas.clear();
		currentPage ++;
		requestVoiceDatas();
		onLoad();
	}

	private void requestVoiceDatas () {
		if (mHttpRequestHelper != null) {
			mCpProgress.setVisibility(View.VISIBLE);
			SimpleDateFormat sdf = new SimpleDateFormat(Constant.TIME_FORMAT);
			String time = sdf.format(new Date());
			String requestUrl = String.format(Constant.BUDEJIE_URL,currentPage,Constant.Action.VOICE_ACTION,Constant.APP_ID,Constant.APP_SECRET,time);
			mHttpRequestHelper.sendRequestWithParms(Constant.Msg.VOICE_REQUEST, requestUrl);
		}
	}

	@Override
	public void onDestroy() {
		if (mHttpRequestHelper != null) {
			mHttpRequestHelper.onDestroy();
		}
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (voiceContentDatas != null && voiceContentDatas.size() > 0){
			if (position < voiceContentDatas.size()){
				String url = voiceContentDatas.get(position).getVoiceUri();
				if (mVoicePlayDialog != null && !TextUtils.isEmpty(url)) {
					mVoicePlayDialog.setVoicePlayUrl(url);
					mVoicePlayDialog.show();
				}
			}
		}
	}
}
