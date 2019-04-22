package dgsw.hs.kr.no_smoke_guide.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dgsw.hs.kr.no_smoke_guide.Model.Board;
import dgsw.hs.kr.no_smoke_guide.Model.Diary;
import dgsw.hs.kr.no_smoke_guide.Model.User;

public class DBHelper extends SQLiteOpenHelper {

    private ArrayList<Board> boards;

    private final String USER_SQL = "create table user (username TEXT primary key, password TEXT, email TEXT, date INTEGER)";
    private final String DIARY_SQL = "create table diary (date TEXT primary key, feeling TEXT)";
    private final String BOARD_SQL = "create table board (idx INTEGER primary key AUTOINCREMENT, username TEXT, title TEXT, content TEXT, date INTEGER)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DIARY_SQL);
        db.execSQL(USER_SQL);
        db.execSQL(BOARD_SQL);


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

    public User login(User user) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{user.getUsername(), user.getPassword()});
        if (cursor.moveToFirst()) {
            User u = new User();
            u.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            u.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setDate(cursor.getLong(cursor.getColumnIndex("date")));
            return u;
        }
        return null;
    }

    public Diary getDiary(String date) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from diary where date=?";
        Cursor cursor = db.rawQuery(sql, new String[]{date});
        if (cursor.moveToFirst()) {
            Diary diary = new Diary();
            diary.setDate(cursor.getString(cursor.getColumnIndex("date")));
            diary.setFelling(cursor.getString(cursor.getColumnIndex("feeling")));
            return diary;
        }
        return null;
    }

    public long setDiary(Diary diary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("date", diary.getDate());
        value.put("feeling", diary.getFelling());
        return db.insert("diary", null, value);
    }

    public User getInfo(String username) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from user where username=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst()) {
            User u = new User();
            u.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            u.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setDate(cursor.getLong(cursor.getColumnIndex("date")));
            return u;
        }
        return null;
    }

    public ArrayList<Board> getBoard(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("board", null, null, null, null, null, null);
        boards = new ArrayList<>();
        while(cursor.moveToNext()){
            Board board = new Board();
            board.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
            board.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            board.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            board.setContent(cursor.getString(cursor.getColumnIndex("content")));
            board.setDate(cursor.getLong(cursor.getColumnIndex("date")));
            boards.add(board);
        }
        return boards;
    }

    public long post(Board board){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("username", board.getUsername());
        value.put("title", board.getTitle());
        value.put("content", board.getContent());
        value.put("date", board.getDate());
        return db.insert("board", null, value);
    }

    public Board getBoard(int idx){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from board where idx=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(idx)});
        if (cursor.moveToFirst()) {
            Board board = new Board();
            board.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
            board.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            board.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            board.setContent(cursor.getString(cursor.getColumnIndex("content")));
            board.setDate(cursor.getLong(cursor.getColumnIndex("date")));
            return board;
        }
        return null;
    }
}
