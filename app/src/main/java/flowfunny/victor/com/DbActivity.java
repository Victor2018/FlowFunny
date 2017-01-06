package flowfunny.victor.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.victor.com.MovieAdapter;
import adapter.victor.com.MenuAdapter;
import dao.victor.com.DbDao;
import data.victor.com.Move;
import interfaces.victor.com.DbInterface;
import util.victor.com.Constant;

public class DbActivity extends AppCompatActivity implements View.OnClickListener,DbInterface {
    private String TAG = "HorizontalListViewActivity";
    private FloatingActionButton mFab;
    private DrawerLayout mDlMenu;
    private LinearLayout mLayoutRitht;
    private ListView mLvChannels,mLvMenus;
    private DbDao dbDao;
    private MovieAdapter mChannelAdapter;
    private List<Move> channelList = new ArrayList<Move>();
    private MenuAdapter mMenuAdapter;
    private String[] menus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        initialize();
        initData();
    }

    private void initialize () {
        dbDao = new DbDao(this);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mDlMenu = (DrawerLayout) findViewById(R.id.dl_menu);
        mLayoutRitht = (LinearLayout) findViewById(R.id.l_right);
        mLvMenus = (ListView) findViewById(R.id.lv_menu);
        mLvChannels = (ListView) findViewById(R.id.lv_channels);

        mLvMenus.setOnItemClickListener(new myOnItemClickListener(Constant.Action.MENU_ITEM_CLICK));
        mFab.setOnClickListener(this);
        mChannelAdapter = new MovieAdapter(getApplicationContext());
        mChannelAdapter.setChannels(channelList);
        mLvChannels.setAdapter(mChannelAdapter);
        mMenuAdapter = new MenuAdapter(getApplicationContext());
        menus = getResources().getStringArray(R.array.db_menu_array);
        mMenuAdapter.setMenus(menus);
        mLvMenus.setAdapter(mMenuAdapter);

    }

    private void initData () {
        channelList = query(Constant.Action.LIVE_ACTION, Constant.Action.LIVE_ACTION);
        if (channelList.size() == 0){
            List<Move> channels = new ArrayList<Move>();
            for(int i=0;i<5;i++){
                Move channel = new Move();
                channel.setName("驯龙记" + i);
                channel.setIcon(i + "xlj.png");
                channel.setImg(i + "xlj1.png");
                channel.setServer("192.168.1." + i);
                channel.setChannelId("ADFLASJDIJASDASDFDSFS" + i);
                channel.setLang("English");
                channel.setArea("American");
                channel.setYear("2015");
                channel.setTime("1:30");
                channel.setType("喜剧");
                channel.setDirector("张艺谋");
                channel.setMemo("影片简介：" + i);
                channels.add(channel);
            }
            insert(channels);
        }
        mChannelAdapter.setChannels(channelList);
        mChannelAdapter.notifyDataSetChanged();


    }

    @Override
    public void insert(Object data) {
        List<Move> channels = (List<Move>) data;
        dbDao.insert(channels);
    }

    @Override
    public void delete(int categoryType, int actionType) {
        dbDao.delete(categoryType, actionType);
    }

    @Override
    public void update(Move channel) {
        dbDao.update(channel);
    }

    @Override
    public List<Move> query(int categoryType, int actionType) {
        List<Move> channels = dbDao.query(categoryType, actionType);
        Log.e(TAG, "channels.size()=" + channels.size());
        return channels;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                if (mDlMenu.isDrawerOpen(mLayoutRitht)) {
                    mDlMenu.closeDrawer(mLayoutRitht);
                } else {
                    mDlMenu.openDrawer(mLayoutRitht);
                }
                break;
        }
    }

    private void onMenuItemClickAction (int position,View view) {
        Intent intent;
        switch (position) {
            case 0://Insert
                List<Move> channels = new ArrayList<Move>();
                for(int i=0;i<2;i++){
                    Move channel = new Move();
                    channel.setName("驯龙记" + i);
                    channel.setIcon(i + "xlj.png");
                    channel.setImg(i + "xlj1.png");
                    channel.setServer("192.168.1." + i);
                    channel.setChannelId("ADFLASJDIJASDASDFDSFS" + i);
                    channel.setLang("English");
                    channel.setArea("American");
                    channel.setYear("2015");
                    channel.setTime("1:30");
                    channel.setType("喜剧");
                    channel.setDirector("张艺谋");
                    channel.setMemo("影片简介：" + i);
                    channels.add(channel);
                }
                insert(channels);

                channelList = query(Constant.Action.LIVE_ACTION, Constant.Action.LIVE_ACTION);
                mChannelAdapter.setChannels(channelList);
                mChannelAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "数据添加成功！", Toast.LENGTH_SHORT).show();
                break;
            case 1://Delete
                delete(Constant.Action.LIVE_ACTION,Constant.Action.LIVE_ACTION);

                channelList = query(Constant.Action.LIVE_ACTION, Constant.Action.LIVE_ACTION);
                mChannelAdapter.setChannels(channelList);
                mChannelAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),"数据删除成功！",Toast.LENGTH_SHORT).show();
                break;
            case 2://Update
                Move channel = new Move();
                channel.setName("捉妖记" + 0);
                channel.setIcon("zyj.png");
                channel.setImg("zyj1.png");
                channel.setServer("128.0.0.1");
                channel.setChannelId("ADFLASJDIJASDASDFDSFS0");
                channel.setLang("中文");
                channel.setArea("中国");
                channel.setYear("2015");
                channel.setTime("2:30");
                channel.setType("喜剧");
                channel.setDirector("张艺谋");
                channel.setMemo("影片简介：胡巴胡巴！！！");
                update(channel);

                channelList = query(Constant.Action.LIVE_ACTION, Constant.Action.LIVE_ACTION);
                mChannelAdapter.setChannels(channelList);
                mChannelAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),"数据更新成功！", Toast.LENGTH_SHORT).show();
                break;
            case 3://Query
                channelList = query(Constant.Action.LIVE_ACTION, Constant.Action.LIVE_ACTION);
                mChannelAdapter.setChannels(channelList);
                mChannelAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),"数据查询完毕！",Toast.LENGTH_SHORT).show();
                break;
        }
        mDlMenu.closeDrawer(mLayoutRitht);
    }

    class myOnItemClickListener implements AdapterView.OnItemClickListener {
        private int action;
        public myOnItemClickListener (int action) {
            this.action = action;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (action) {
                case Constant.Action.MENU_ITEM_CLICK:
                    onMenuItemClickAction(position,view);
                    break;
            }
        }
    }

}
