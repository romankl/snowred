package eu.roklapps.snowred.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import eu.roklapps.snowred.app.api.reddit.model.Subreddit;

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
    private static final String TAG = "Database";
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

    public void insertSubscribedSubs(JsonObject object) {
        SQLiteDatabase database = this.getWritableDatabase();
        Log.d(TAG, object.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUB_VISUAL_NAME, object.get("display_name").getAsString());
        contentValues.put(SUB_RELATIVE_URL, object.get("url").getAsString());

        database.insert(SUBS_USER_SUBREDDITS_TABLE, null, contentValues);

        database.close();
    }

    public List<Subreddit> getSubscribedSubreddits() {
        List<Subreddit> subreddits = new ArrayList<Subreddit>();
        SQLiteDatabase database = this.getReadableDatabase();
        Subreddit subreddit;
        Cursor cursor = database.query(SUBS_USER_SUBREDDITS_TABLE, new String[]{SUB_RELATIVE_URL, SUB_VISUAL_NAME}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                subreddit = new Subreddit();
                subreddit.setUrl(cursor.getString(0));
                subreddit.setDisplay_name(cursor.getString(1));
                subreddits.add(subreddit);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return subreddits;
    }
}
