package db.victor.com;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import util.victor.com.Constant;

/**
 * Created by victor on 2015/12/25.
 */
public class DataBaseContent extends ContentProvider {

    DataBase db;
    private static final int TB_MOVIE                        = 1;
    private static final int TB_MOVIE_ID                     = 8;
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        sURIMatcher.addURI(Constant.DbConfig.AUTHORITY, Constant.TB.MOVIE, TB_MOVIE);
        sURIMatcher.addURI(Constant.DbConfig.AUTHORITY, Constant.TB.MOVIE + "/#", TB_MOVIE_ID);
    }
    @Override
    public boolean onCreate() {
        db = new DataBase(this.getContext(), null, null, 0);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int type = sURIMatcher.match(uri);
        System.out.println("type:" + type);
        String id = "";
        if(type == 8 ){
            if (uri.getPathSegments().size() > 1) {
                id = uri.getPathSegments().get(1);
            }
        }
        if(id != null && !id.equals("")){
            if(selection == null){
                selection = "_id" + id;
                System.out.println("selection:" + selection);
            }else{
                selection = selection + "and _id" + id;
            }
        }
        SQLiteDatabase sdb = db.getReadableDatabase();
        SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
        sqb.setTables(getTbName(uri));
        Cursor cursor = sqb.query(sdb, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sdb = db.getWritableDatabase();
        String insertUri = Constant.DbConfig.URI_PATH + getTbName(uri);
        long id = sdb.insert(insertUri, null, values);
        uri = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(uri, null);

        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sdb = db.getWritableDatabase();
        String rowId = uri.getPathSegments().get(1);

        return sdb.delete(getTbName(uri), "_id=" + rowId, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase sdb = db.getWritableDatabase();
        String rowId = uri.getPathSegments().get(1);

        return sdb.update(getTbName(uri), values, "_id = " + rowId, null);
    }

    private String getTbName (Uri uri) {
        String tbName = "";
        int type = sURIMatcher.match(uri);
        switch (type) {
            case TB_MOVIE:
                tbName = Constant.TB.MOVIE;
                break;
            case TB_MOVIE_ID:
                tbName = Constant.TB.MOVIE;
                break;
        }
        return tbName;
    }
}
