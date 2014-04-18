package eu.roklapps.snowred.app.asyncs;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eu.roklapps.snowred.app.asyncs.callback.AsyncResult;
import eu.roklapps.snowred.app.db.SnowDatabase;

public class SubscribedRedditInsert extends AsyncTask<JsonObject, Void, Void> {
    private static final String TAG = "SubscribedRedditInsert";
    private AsyncResult mCallback;
    private Context mContext;

    public SubscribedRedditInsert(Context mContext) {
        this.mContext = mContext;
    }

    public SubscribedRedditInsert setCallback(AsyncResult mCallback) {
        this.mCallback = mCallback;

        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (mCallback != null) mCallback.onAsyncFinished();
    }

    @Override
    protected Void doInBackground(JsonObject... jsonObjects) {
        SnowDatabase db = new SnowDatabase(mContext);

        JsonArray children = jsonObjects[0].getAsJsonObject("data").getAsJsonArray("children");

        for (int i = 0; i < children.size(); i++) {
            db.insertSubscribedSubs(children.get(i).getAsJsonObject().get("data").getAsJsonObject());
        }
        db.close();
        return null;
    }
}
