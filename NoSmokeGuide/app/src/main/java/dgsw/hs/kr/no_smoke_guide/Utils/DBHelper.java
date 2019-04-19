package dgsw.hs.kr.no_smoke_guide.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dgsw.hs.kr.no_smoke_guide.Model.User;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user (username TEXT primary key, password TEXT, email TEXT, date INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long register(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("username", user.getUsername());
        value.put("password", user.getPassword());
        value.put("email", user.getEmail());
        value.put("date", user.getDate());
        return db.insert("user", null, value);
    }

    public boolean login(User user){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{user.getUsername(), user.getPassword()});
        if(cursor.moveToFirst()){
           return true;
        }
        return false;
    }
}
