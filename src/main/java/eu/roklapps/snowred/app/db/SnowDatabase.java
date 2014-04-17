package eu.roklapps.snowred.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SnowDatabase extends SQLiteOpenHelper {
    public static final String SUBS_USER_SUBREDDITS_TABLE = "userSubreddits";
    public static final String USER_TABLE = "user";
    public static final String USER_USERNAME = "username";
    public static final String USER_HASH = "hash";
    public static final String USER_SALT = "salt";
    public static final String USER_COOKIE = "cookie";
    public static final String SUB_VISUAL_NAME = "visualName";
    public static final String SUB_RELATIVE_URL = "relativeUrl";
    public static final String SUB_TYPE_OF_SUBSCRIPTION = "typeOfSubscription";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "dbstorage.db";
    private Context mContext;

    public SnowDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + USER_TABLE + "(_id integer primary key autoincrement," +
                USER_USERNAME + " text text not null collate nocase," +
                USER_HASH + " text not null," +
                USER_SALT + " text not null," +
                USER_COOKIE + " text not null)");

        sqLiteDatabase.execSQL("create table " + SUBS_USER_SUBREDDITS_TABLE + "(_id integer primary key autoincrement," +
                SUB_VISUAL_NAME + " text not null," +
                SUB_RELATIVE_URL + " text not null," +
                SUB_TYPE_OF_SUBSCRIPTION + " integer DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
