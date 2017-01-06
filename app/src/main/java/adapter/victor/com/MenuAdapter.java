package adapter.victor.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import flowfunny.victor.com.R;
import view.victor.com.MovingTextView;

/**
 * Created by victor on 2016/1/7.
 */
public class MenuAdapter extends BaseAdapter{
    private String TAG = "MenuAdapter";
    private Context mContext;
    private String[] menus;

    public void setMenus(String[] menus) {
        this.menus = menus;
    }

    public String[] getMenus() {
        return menus;
    }

    public MenuAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return menus.length;
    }

    @Override
    public Object getItem(int position) {
        return menus[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView == null) {
            holder = new viewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.lv_menu_item,null);
            holder.mTvMenu = (MovingTextView) convertView.findViewById(R.id.tv_menu);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        String menu = menus[position];
        holder.mTvMenu.setText(menu);
        return convertView;
    }

    private class viewHolder {
        MovingTextView mTvMenu;
    }
}
