package dao.victor.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import data.victor.com.Move;
import interfaces.victor.com.DbInterface;
import db.victor.com.DataBase;
import util.victor.com.Constant;

/**
 * Created by victor on 2015/12/25.
 */
public class DbDao implements DbInterface{
    private String TAG = "DbDao";
    private Context mContext;
    private DataBase db;

    public DbDao (Context context){
        mContext = context;
        db = new DataBase(mContext, null, null, 0);
    }

    @Override
    public void insert(Object data) {
        Log.e(TAG, "insert()......");
        if (!db.isTbExist(Constant.TB.MOVIE)) {
            return;
        }
        long startTime = System.currentTimeMillis();
        List<Move> channelList = (List<Move>) data;
        if(channelList != null && channelList.size() > 0){
            SQLiteDatabase sdb = db.getWritableDatabase();
            sdb.beginTransaction();
            try {
                for(Move channel:channelList){
                    ContentValues values = new ContentValues();
                    values.put("name", channel.getName());
                    values.put("icon", channel.getIcon());
                    values.put("img", channel.getImg());
                    values.put("channelid", channel.getChannelId());
                    values.put("language", channel.getLang());
                    values.put("area", channel.getArea());
                    values.put("year", channel.getYear());
                    values.put("type", channel.getType());
                    values.put("time", channel.getTime());
                    values.put("director", channel.getDirector());
                    values.put("memo", channel.getMemo());

                    values.put("category_type", String.valueOf(Constant.Action.LIVE_ACTION));
                    values.put("action_type",String.valueOf(Constant.Action.LIVE_ACTION));
                    values.put("current", String.valueOf(0));

                    sdb.insert(Constant.TB.MOVIE, null, values);
                }
                sdb.setTransactionSuccessful();
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                if (sdb != null) {
                    sdb.endTransaction();
                    sdb.close();
                }
            }
        }
        Log.e(TAG, "insert time=" + (System.currentTimeMillis() - startTime));

    }

    @Override
    public void delete(int categoryType, int actionType) {
        Log.e(TAG, "delete()......");
        if (!db.isTbExist(Constant.TB.MOVIE)) {
            return;
        }
        SQLiteDatabase sdb = db.getReadableDatabase();
        try {
            sdb.delete(Constant.TB.MOVIE, "category_type = ? and action_type=?", new String[]{String.valueOf(categoryType),String.valueOf(actionType)});
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            if (sdb != null) {
                sdb.close();
            }
        }
    }

    @Override
    public void update(Move channel) {
        Log.e(TAG, "update()......");
        if (!db.isTbExist(Constant.TB.MOVIE)) {
            return;
        }
        if(channel != null){
            SQLiteDatabase sdb = db.getReadableDatabase();
            try {
                String channelId = channel.getChannelId();

                ContentValues values = new ContentValues();
                values.put("name", channel.getName());
                values.put("icon", channel.getIcon());
                values.put("img", channel.getImg());
                values.put("channelid", channel.getChannelId());
                values.put("language", channel.getLang());
                values.put("area", channel.getArea());
                values.put("year", channel.getYear());
                values.put("type", channel.getType());
                values.put("time", channel.getTime());
                values.put("director", channel.getDirector());
                values.put("memo", channel.getMemo());

                values.put("category_type", String.valueOf(Constant.Action.LIVE_ACTION));
                values.put("action_type",String.valueOf(Constant.Action.LIVE_ACTION));
                values.put("current", String.valueOf(channel.getProgress()));

                sdb.update(Constant.TB.MOVIE, values, "channelid = ?", new String[]{channelId});

            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                if (sdb != null) {
                    sdb.close();
                }
            }
        }

    }

    @Override
    public List<Move> query(int categoryType, int actionType) {
        Log.e(TAG, "query()......");
        List<Move> favList = new ArrayList<Move>();
        if (!db.isTbExist(Constant.TB.MOVIE)) {
            return favList;
        }
        Uri uri = Uri.parse(Constant.DbConfig.URI_PATH + Constant.TB.MOVIE);
        Cursor cursor = mContext.getContentResolver().query(uri, new String[]{"name","icon","img","server",
                        "channelid","language","area","year","type","time","director","memo","current"},
                "category_type = ? and action_type = ?" ,
                new String[]{String.valueOf(categoryType),String.valueOf(actionType)}, null);
        try {
            int row = cursor.getCount();
            if (row > 0) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);

                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String icon = cursor.getString(cursor.getColumnIndex("icon"));
                    String img = cursor.getString(cursor.getColumnIndex("img"));
                    String server = cursor.getString(cursor.getColumnIndex("server"));
                    String channelId = cursor.getString(cursor.getColumnIndex("channelid"));
                    String language = cursor.getString(cursor.getColumnIndex("language"));
                    String area = cursor.getString(cursor.getColumnIndex("area"));
                    String year = cursor.getString(cursor.getColumnIndex("year"));
                    String type = cursor.getString(cursor.getColumnIndex("type"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    String director = cursor.getString(cursor.getColumnIndex("director"));
                    String memo = cursor.getString(cursor.getColumnIndex("memo"));
                    int current = cursor.getInt(cursor.getColumnIndex("current"));

                    Move channel = new Move();
                    channel.setName(name);
                    channel.setIcon(icon);
                    channel.setImg(img);
                    channel.setChannelId(channelId);
                    channel.setLang(language);
                    channel.setArea(area);
                    channel.setYear(year);
                    channel.setType(type);
                    channel.setTime(time);
                    channel.setDirector(director);
                    channel.setMemo(memo);
                    channel.setProgress(current);

                    favList.add(channel);
                }
            }
        } catch (Exception e) {

        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return favList;
    }
}
