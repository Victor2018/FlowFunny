package adapter.victor.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import data.victor.com.Move;
import view.victor.com.CircleImageView;
import view.victor.com.MovingTextView;
import flowfunny.victor.com.R;

/**
 * Created by victor on 2016/1/6.
 */
public class MovieAdapter extends BaseAdapter{
    private String TAG = "MovieAdapter";
    private Context mContext;
    private List<Move> channels;

    public void setChannels(List<Move> channels) {
        this.channels = channels;
    }

    public List<Move> getChannels() {
        return channels;
    }

    public MovieAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
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
            convertView = inflater.inflate(R.layout.lv_move_item,null);
            holder.mIvIcon = (CircleImageView) convertView.findViewById(R.id.iv_icon);
            holder.mTvName = (TextView) convertView.findViewById(R.id.tv_channel_name);
            holder.mTvLang = (TextView) convertView.findViewById(R.id.tv_channel_lang);
            holder.mTvTime = (TextView) convertView.findViewById(R.id.tv_channel_time);
            holder.mTvYear = (TextView) convertView.findViewById(R.id.tv_channel_year);
            holder.mTvType = (TextView) convertView.findViewById(R.id.tv_channel_type);
            holder.mTvDirector = (TextView) convertView.findViewById(R.id.tv_channel_director);
            holder.mTvArea = (TextView) convertView.findViewById(R.id.tv_channel_area);
            holder.mMtvMemo = (MovingTextView) convertView.findViewById(R.id.mtv_channel_memo);

            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        Move channel = channels.get(position);
        holder.mTvName.setText(channel.getName());
        holder.mTvLang.setText(channel.getLang());
        holder.mTvTime.setText(channel.getTime());
        holder.mTvYear.setText(channel.getYear());
        holder.mTvType.setText(channel.getType());
        holder.mTvDirector.setText(channel.getDirector());
        holder.mTvArea.setText(channel.getArea());
//        holder.mTvMemo.setText(channel.getMemo());

        return convertView;
    }

    private class viewHolder {
        CircleImageView mIvIcon;
        TextView mTvName;
        TextView mTvLang;
        TextView mTvTime;
        TextView mTvYear;
        TextView mTvType;
        TextView mTvDirector;
        TextView mTvArea;
        MovingTextView mMtvMemo;
    }
}
