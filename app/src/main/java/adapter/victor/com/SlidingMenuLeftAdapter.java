package adapter.victor.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import flowfunny.victor.com.R;
import view.victor.com.SelectableRoundedImageView;

/**
 * Created by victor on 2016/1/9.
 */
public class SlidingMenuLeftAdapter extends BaseAdapter{
    private String TAG = "SlidingMenuLeftAdapter";
    private Context mContext;
    private String[] leftMenus;
    private int[] menuIcons;

    public void setMenuIcons(int[] menuIcons) {

        this.menuIcons = menuIcons;
    }

    public int[] getMenuIcons() {
        return menuIcons;
    }

    public void setLeftMenus(String[] leftMenus) {
        this.leftMenus = leftMenus;
    }

    public String[] getLeftMenus() {
        return leftMenus;
    }

    public SlidingMenuLeftAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return leftMenus.length;
    }

    @Override
    public Object getItem(int position) {
        return leftMenus[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder  = new viewHolder();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.lv_sliding_menu_left_item,null);
        holder.mSrvLeftMenuIcon = (SelectableRoundedImageView) convertView.findViewById(R.id.srv_left_menu_icon);
        holder.mTvLeftMenu = (TextView) convertView.findViewById(R.id.tv_menu);
        convertView.setTag(position);
        int iconId = menuIcons[position];
        String menu = leftMenus[position];
        holder.mSrvLeftMenuIcon.setImageResource(iconId);
        holder.mTvLeftMenu.setText(menu);
        return convertView;
    }

    private class viewHolder {
        SelectableRoundedImageView mSrvLeftMenuIcon;
        TextView mTvLeftMenu;
    }
}
