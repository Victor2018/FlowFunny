package adapter.victor.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import flowfunny.victor.com.R;

/**
 * Created by victor on 2016/1/7.
 */
public class HorizontalListViewAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private int selectIndex = -1;
    private String[] titles;
    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {

        this.titles = titles;
    }

    public HorizontalListViewAdapter(Context context){
        this.mContext = context;
        this.titles = titles;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return titles.length;
    }
    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.hlv_item, null);
            holder.mTitle=(TextView)convertView.findViewById(R.id.tv_hlv_title);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }

        holder.mTitle.setText(titles[position]);

        return convertView;
    }

    private static class ViewHolder {
        private TextView mTitle ;
    }
    public void setSelectIndex(int i){
        selectIndex = i;
    }
}
