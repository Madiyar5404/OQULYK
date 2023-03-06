package com.example.oqulyk;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;

    private static final String OQYRMAN_TABLE_NAME="OQYRMANDAR_TABLE";
    public static final String OQYRMAN_ID= "_ID";
    public static final String OQYRMAN_NAME="NAME";
    public static final String OQYRMAN_EMAIL="EMAIL";
    public static final String OQYRMAN_PASSWORD="PASSWORD";

    private static final String CREATE_OQYRMAN_TABLE=
            "CREATE TABLE "+ OQYRMAN_TABLE_NAME + "( "+
                    OQYRMAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    OQYRMAN_NAME+" TEXT NOT NULL, "+
                    OQYRMAN_EMAIL+" TEXT NOT NULL, "+
                    OQYRMAN_PASSWORD+" TEXT NOT NULL, "+
                    "UNIQUE (" + OQYRMAN_NAME +","+OQYRMAN_EMAIL+","+OQYRMAN_PASSWORD+")"+
                    ");";

    private static final String DROP_OQYRMAN_TABLE= "DROP TABLE IF EXISTS "+OQYRMAN_TABLE_NAME;
    private static final String SELECT_OQYRMAN_TABLE = "SELECT * FROM "+OQYRMAN_TABLE_NAME;



    //GENRE table
    private static final String GENRE_TABLE_NAME = "GENRE_TABLE";
    public static final String GENRE_ID = "_GENREID";
    public static final String GENRE_NAME_KEY = "GENRE_NAME";
    public static final String AUTHOR_NAME_KEY = "AUTHOR_NAME";

    private static final String CREATE_GENRE_TABLE =
            "CREATE TABLE " + GENRE_TABLE_NAME + "( " +
                    GENRE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    GENRE_NAME_KEY + " TEXT NOT NULL, " +
                    AUTHOR_NAME_KEY + " TEXT NOT NULL, " +
                    "UNIQUE (" + GENRE_NAME_KEY + "," + AUTHOR_NAME_KEY + ")" +
                    ");";

    private static final String DROP_GENRE_TABLE = "DROP TABLE IF EXISTS " + GENRE_TABLE_NAME;
    private static final String SELECT_GENRE_TABLE = "SELECT * FROM " + GENRE_TABLE_NAME;


    //KITAP table

    private static final String KITAP_TABLE_NAME = "KITAP_TABLE";
    public static final String KITAP_ID = "_KITAPID";
    public static final String KITAP_NAME_KEY = "KITAP_NAME";
    public static final String KITAP_SANY_KEY = "SANY";

    private static final String CREATE_KITAP_TABLE =
            "CREATE TABLE " + KITAP_TABLE_NAME +
                    "( " +
                    KITAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    GENRE_ID + " INTEGER NOT NULL, " +
                    KITAP_NAME_KEY + " TEXT NOT NULL, " +
                    KITAP_SANY_KEY + " INTEGER, " +
                    " FOREIGN KEY ( " + GENRE_ID + ") REFERENCES " + GENRE_TABLE_NAME + "(" + GENRE_ID + ")" +
                    ")";

    private static final String DROP_KITAP_TABLE = "DROP TABLE IF EXISTS " + KITAP_TABLE_NAME;
    private static final String SELECT_KITAP_TABLE = "SELECT * FROM " + KITAP_TABLE_NAME;

    //STATUS TABLE

    private static final String STATUS_TABLE_NAME = "STATUS_TABLE";
    public static final String STATUS_ID = "_STATUS_ID";
    public static final String DATE_KEY = "STATUS_DATE";
    public static final String STATUS_KEY = "STATUS";

    private static final String CREATE_STATUS_TABLE =
            "CREATE TABLE " + STATUS_TABLE_NAME +
                    "(" +
                    STATUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    KITAP_ID + " INTEGER NOT NULL, " +
                    GENRE_ID + " INTEGER NOT NULL, " +
                    DATE_KEY + " DATE NOT NULL, " +
                    STATUS_KEY + " TEXT NOT NULL, " +
                    " UNIQUE (" + KITAP_ID + "," + DATE_KEY + ")," +
                    " FOREIGN KEY (" + KITAP_ID + ") REFERENCES " + KITAP_TABLE_NAME + " (" + KITAP_ID + ")," +
                    " FOREIGN KEY (" + GENRE_ID + ") REFERENCES " + GENRE_TABLE_NAME + " (" + GENRE_ID + ")" +
                    ");";

    private static final String DROP_STATUS_TABLE = " DROP TABLE IF EXISTS " + STATUS_TABLE_NAME;
    private static final String SELECT_STATUS_TABLE = " SELECT * FROM " + STATUS_TABLE_NAME;


    public DBHelper(@Nullable Context context) {
        super(context, "Attendance.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_OQYRMAN_TABLE);
        db.execSQL(CREATE_GENRE_TABLE);
        db.execSQL(CREATE_KITAP_TABLE);
        db.execSQL(CREATE_STATUS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_OQYRMAN_TABLE);
            db.execSQL(DROP_GENRE_TABLE);
            db.execSQL(DROP_KITAP_TABLE);
            db.execSQL(DROP_STATUS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //registration
    public boolean add_oqyrman(String name, String email, String password){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(OQYRMAN_NAME,name);
        values.put(OQYRMAN_EMAIL,email);
        values.put(OQYRMAN_PASSWORD,password);
        long result = database.insert(OQYRMAN_TABLE_NAME, null,values);
        if(result == -1)
            return false;
        else
            return true;
    }

    //login
    public Cursor login_oqyrman(String email, String password){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor res=database.rawQuery("SELECT  * FROM "+ OQYRMAN_TABLE_NAME + " where EMAIL='"+email+"'AND PASSWORD='"+password+"'",null);
        return res;
    }

    public boolean checkUsername(String name){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+OQYRMAN_TABLE_NAME+ " where NAME=?", new String[]{name});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    long updatePassword(String name,String password){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(OQYRMAN_PASSWORD,password);
        return database.update(OQYRMAN_TABLE_NAME,values,"name =?",new String[]{name});
    }

    public long addGenre(String genreName, String authorName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GENRE_NAME_KEY, genreName);
        values.put(AUTHOR_NAME_KEY, authorName);

        return database.insert(GENRE_TABLE_NAME, null, values);
    }

    public Cursor getGenreTable() {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery(SELECT_GENRE_TABLE, null);
    }


   public int deleteClass(long gid) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.delete(GENRE_TABLE_NAME, GENRE_ID + "=?", new String[]{String.valueOf(gid)});
    }

   public long updateClass(long gid, String genreName, String authorName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GENRE_NAME_KEY, genreName);
        values.put(AUTHOR_NAME_KEY, authorName);

        return database.update(GENRE_TABLE_NAME, values, GENRE_ID + "=?", new String[]{String.valueOf(gid)});
    }

    //kitap
    public long addKitap(long gid, int sany, String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GENRE_ID, gid);
        values.put(KITAP_SANY_KEY, sany);
        values.put(KITAP_NAME_KEY, name);
        return database.insert(KITAP_TABLE_NAME, null, values);
    }

    public Cursor getKitapTable(long gid) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(KITAP_TABLE_NAME, null, GENRE_ID + "=?", new String[]{String.valueOf(gid)}, null, null, KITAP_SANY_KEY);
    }

    public int deleteKitap(long kid) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.delete(KITAP_TABLE_NAME, KITAP_ID + "=?", new String[]{String.valueOf(kid)});
    }

    public long updateKitap(long kid, String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KITAP_NAME_KEY, name);
        return database.update(KITAP_TABLE_NAME, values, KITAP_ID + "=?", new String[]{String.valueOf(kid)});
    }


    //status

    public long addStatus(long kid, long gid, String date, String status) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KITAP_ID, kid);
        values.put(GENRE_ID, gid);
        values.put(DATE_KEY, date);
        values.put(STATUS_KEY, status);
        return database.insert(STATUS_TABLE_NAME, null, values);
    }

    public long updateStatus(long kid, String date, String status) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS_KEY, status);
        String whereClause = DATE_KEY + "='" + date + "' AND " + KITAP_ID + "=" + kid;
        return database.update(STATUS_TABLE_NAME, values, whereClause, null);
    }

    @SuppressLint("Range")
    public String getStatus(long kid, String date) {
        String status = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = DATE_KEY + "='" + date + "' AND " + KITAP_ID + "=" + kid;
        Cursor cursor = database.query(STATUS_TABLE_NAME, null, whereClause, null, null, null, null);
        if (cursor.moveToFirst())
            status = cursor.getString(cursor.getColumnIndex(STATUS_KEY));
        return status;
    }

    public Cursor getDistinctMonth(long gid) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(STATUS_TABLE_NAME, new String[]{DATE_KEY}, GENRE_ID + "=" + gid, null, "substr(" + DATE_KEY + ",4,7)", null, null); //01.04.2020
    }

}
