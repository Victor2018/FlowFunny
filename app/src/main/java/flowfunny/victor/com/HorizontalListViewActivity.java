package flowfunny.victor.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.ArrayList;
import java.util.List;

import adapter.victor.com.HorizontalListViewAdapter;
import util.victor.com.Constant;
import view.victor.com.HorizontalListview;
import view.victor.com.MovingTextView;
import view.victor.com.TipView;

public class HorizontalListViewActivity extends AppCompatActivity implements View.OnClickListener,BDLocationListener {

    private String TAG = "HorizontalListViewActivity";
    private FloatingActionButton mFab;
    private MovingTextView mMtvLoc;
    private HorizontalListview mHlvTitle;
    private ListView mLvMenus;
    private TipView mTvTip;
    private String[] titles = new String[20];
    private HorizontalListViewAdapter mHlvAdapter;
    private LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_listview);

        initialize();
        initData();
        initLoctionOption();
    }

    private void initialize () {
        locationClient = new LocationClient(this);
        locationClient.registerLocationListener(this);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mLvMenus = (ListView) findViewById(R.id.lv_menu);
        mTvTip = (TipView) findViewById(R.id.tv_tip);
        mMtvLoc = (MovingTextView) findViewById(R.id.mtv_loc);
        mHlvTitle = (HorizontalListview) findViewById(R.id.hlv_title);

        mLvMenus.setOnItemClickListener(new myOnItemClickListener(Constant.Action.MENU_ITEM_CLICK));
        mFab.setOnClickListener(this);
        mHlvTitle.setOnItemClickListener(new myOnItemClickListener(Constant.Action.HLV_ITEM_CLICK));
        mHlvAdapter = new HorizontalListViewAdapter(getApplicationContext());
        mHlvAdapter.setTitles(titles);
        mHlvTitle.setAdapter(mHlvAdapter);

    }

    private void initData () {
        for (int i=0;i<titles.length;i++) {
            titles[i] = "测试标题 " + i;
        }
        mHlvAdapter.setTitles(titles);
        mHlvAdapter.notifyDataSetChanged();

        List<String> tips = new ArrayList<>();
        for (int i = 100; i < 120; i++) {
            tips.add("this is tip No." + i);
        }
        mTvTip.setTipList(tips);

    }

    private void initLoctionOption() {
        LocationClientOption locOption = new LocationClientOption();
        //net loc
        locOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        locOption.setCoorType("gcj02");// 定位结果坐标系
        locOption.setScanSpan(0);//定位请求的时间间隔，定位一次
        locOption.setIsNeedAddress(true);//设置是否需要地址信息
        locOption.setIsNeedLocationDescribe(true);//简单位置描述
        locOption.setIsNeedLocationPoiList(true);
        locOption.setIgnoreKillProcess(true);
        locationClient.setLocOption(locOption);

        locationClient.start();//默认发起1次请求
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        StringBuilder sb=new StringBuilder();

        if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            sb.append("网络定位成功,当前位置：");
            sb.append(bdLocation.getAddrStr() + bdLocation.getSemaAptag());
        }

        List<Poi> list = bdLocation.getPoiList();// 附近信息
        if (list != null) {
            sb.append(",附近信息：");
            for (int i=0;i<list.size();i++) {
                sb.append(String.valueOf(i + 1) + "," +  list.get(i).getName() + ";");
            }
        }
        Log.e("result:", sb.toString());
        mMtvLoc.setText(sb.toString());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
               finish();
                break;
        }
    }


    class myOnItemClickListener implements AdapterView.OnItemClickListener {
        private int action;
        public myOnItemClickListener (int action) {
            this.action = action;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (action) {
                case Constant.Action.HLV_ITEM_CLICK:
                    if (titles != null && titles.length > 0) {
                        if (position < titles.length) {
                            mHlvAdapter.setSelectIndex(position);
                            mHlvAdapter.notifyDataSetChanged();
                            mHlvTitle.setSelection(position);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationClient.stop();
    }
}
