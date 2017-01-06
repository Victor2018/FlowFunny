package db.victor.com;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import org.w3c.dom.Text;

import util.victor.com.Constant;

/**
 * Created by victor on 2015/12/25.
 */
public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, Constant.DbConfig.DB_NAME, null, Constant.DbConfig.DB_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createMovieTb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (isTbExist(Constant.TB.MOVIE)) {
            db.execSQL("drop table " + Constant.TB.MOVIE);
        }
        onCreate(db);
    }

    private void createMovieTb (SQLiteDatabase db) {
        String sql = "create table if not exists " + Constant.TB.MOVIE +
                "(_id integer primary key autoincrement,name text,icon text,img text," +
                "server text,channelid text,language text,area text,year text,type text,time text,director text,memo text," +
                "category_type text,action_type text,current text)";//category_type 0 live 1 vod;action_type 1 history,2 fav

        db.execSQL(sql);
    }

    public boolean isTbExist (String tbName) {
        boolean isExist = false;
        if (TextUtils.isEmpty(tbName)) {
            return false;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            String sql = "select count(1) from "+"sqlite_master "+" where type ='table' and name ='"+tbName.trim()+"' ";
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                int count = cursor.getInt(0);
                if(count > 0){
                    isExist = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return isExist;
    }
}
