package adapter.victor.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import data.victor.com.FunnyContentData;
import util.victor.com.ImageDownLoader;
import view.victor.com.CircleImageView;

import flowfunny.victor.com.R;
/**
 * Created by victor on 2016/1/6.
 */
public class FunnyAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private String TAG = "FunnyAdapter";
    private Context mContext;
    private List<FunnyContentData> funnyConnentDatas;
    private ListView mListView;

    /**
     * Image 下载器
     */
    private ImageDownLoader mImageDownLoader;
    private boolean isFirstEnter = true;//记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
    private int mFirstVisibleItem;//一屏中第一个item的位置
    private int mVisibleItemCount;//一屏中所有item的个数

    public void setFunnyConnentDatas(List<FunnyContentData> funnyConnentDatas) {
        this.funnyConnentDatas = funnyConnentDatas;
    }

    public List<FunnyContentData> getFunnyConnentDatas() {
        return funnyConnentDatas;
    }

    public FunnyAdapter(Context context,ListView listView) {
        mContext = context;
        mListView = listView;
        mImageDownLoader = new ImageDownLoader(context);
        mListView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return funnyConnentDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return funnyConnentDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        if (convertView == null) {
            holder = new viewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.lv_funny_frag_item,null);
            holder.mIvIcon = (CircleImageView) convertView.findViewById(R.id.iv_funny_frag_icon);
            holder.mTvName = (TextView) convertView.findViewById(R.id.tv_funny_frag_name);
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_funny_frag_title);
            holder.mTvText = (TextView) convertView.findViewById(R.id.tv_funny_frag_text);

            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        FunnyContentData data = funnyConnentDatas.get(position);
        holder.mTvName.setText(data.getName());
        holder.mTvTitle.setText(data.getCreateTime());
        holder.mTvText.setText(data.getText());

        String imgUrl = data.getProfileImage();

        Bitmap bitmap = mImageDownLoader.showCacheBitmap(imgUrl.replaceAll("[^\\w]", ""));
        if(bitmap != null){
            holder.mIvIcon.setImageBitmap(bitmap);
        }else{
            mImageDownLoader.downloadImage(imgUrl, new ImageDownLoader.onImageLoaderListener() {
                @Override
                public void onImageLoader(Bitmap bitmap, String url) {
                    if (bitmap != null) {
                        holder.mIvIcon.setImageBitmap(bitmap);
                    }
                }
            });
        }

        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //仅当ListView静止时才去下载图片，ListView滑动时取消所有正在下载的任务
        if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
            showImage(mFirstVisibleItem, mVisibleItemCount);
        }else{
            cancelTask();
        }
    }

    /**
     * ListView滚动的时候调用的方法，刚开始显示ListView也会调用此方法
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        // 因此在这里为首次进入程序开启下载任务。
        if(isFirstEnter && visibleItemCount > 0){
            showImage(mFirstVisibleItem, mVisibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * 显示当前屏幕的图片，先会去查找LruCache，LruCache没有就去sd卡或者手机目录查找，在没有就开启线程去下载
     * @param firstVisibleItem
     * @param visibleItemCount
     */
    private void showImage(int firstVisibleItem, int visibleItemCount){
        Log.e(TAG,"showImage()......");
        for(int i=firstVisibleItem; i<firstVisibleItem + visibleItemCount; i++){
            if (funnyConnentDatas != null && funnyConnentDatas.size() > 0) {
                if (i < funnyConnentDatas.size()) {
                    String mImageUrl = funnyConnentDatas.get(i).getProfileImage();
                    mImageDownLoader.downloadImage(mImageUrl);
                }
            }
        }
    }

    /**
     * 取消下载任务
     */
    public void cancelTask(){
        mImageDownLoader.cancelTask();
    }



    private class viewHolder {
        CircleImageView mIvIcon;
        TextView mTvName;
        TextView mTvTitle;
        TextView mTvText;
    }
}
