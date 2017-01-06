package model.victor.com;

import java.net.SocketTimeoutException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.content.Context;

import mode.victor.com.DataObservable;
import util.victor.com.Constant;
import util.victor.com.HttpUtil;


public class BaseKnowledgeTask {
	private String TAG = "BaseKnowledgeTask";
	private int requestCount;
	private android.content.Context mContext;

	public BaseKnowledgeTask (Context context) {
		mContext = context;
	}

	public void requestBaseData() {
		requestCount++;
		Log.e(TAG, "requestCount=" + requestCount);
		if(requestCount > 5){
			return;
		}
		new BaseTask().execute(Constant.BASE_URL);
	}
	
	class BaseTask extends AsyncTask<String, Integer, Bundle>{

		@Override
		protected Bundle doInBackground(String... params) {
			// TODO Auto-generated method stub
			int status = 0;
			Bundle responseData = new Bundle();
			if (HttpUtil.isNetEnable(mContext)){
				String result = null;
				try {
					result = HttpUtil.HttpGetRequest(params[0]);
					if (!TextUtils.isEmpty(result)) {
						status = Constant.Msg.REQUEST_SUCCESS;
						responseData.putString(Constant.BASE_DATA_KEY,result);
					} else {
						status = Constant.Msg.REQUEST_FAILED;
					}
				} catch (SocketTimeoutException e) {
					e.printStackTrace();
					status = Constant.Msg.SOCKET_TIME_OUT;
				}
			} else {
				status = Constant.Msg.NETWORK_ERROR;
			}
			responseData.putInt(Constant.STATUS_KEY, status);
			responseData.putInt(Constant.REQUEST_MSG_KEY, Constant.Msg.BASE_REQUEST);

			return responseData;
		}
		
		protected void onPostExecute(Bundle result) {
			if(result != null){
				DataObservable.getInstance().setData(result);
			}else{
				requestBaseData();
			}
		}
	}
	
}