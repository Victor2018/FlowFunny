package adapter.victor.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import data.victor.com.Channel;
import data.victor.com.Move;
import flowfunny.victor.com.R;
import view.victor.com.CircleImageView;
import view.victor.com.MovingTextView;

/**
 * Created by victor on 2016/1/6.
 */
public class ChannelAdapter extends BaseAdapter{
    private String TAG = "ChannelAdapter";
    private Context mContext;
    private List<Channel> channels;

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public ChannelAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.lv_channel_item,null);
            holder.mIndex = (TextView) convertView.findViewById(R.id.tv_channel_index);
            holder.mMTvName = (MovingTextView) convertView.findViewById(R.id.mtv_channel_name);

            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        Channel channel = channels.get(position);
        holder.mIndex.setText(String.valueOf(position + 1));
        holder.mMTvName.setText(channel.getName());

        return convertView;
    }

    private class viewHolder {
        TextView mIndex;
        MovingTextView mMTvName;
    }
}
