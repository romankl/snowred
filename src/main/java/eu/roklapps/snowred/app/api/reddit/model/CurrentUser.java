package eu.roklapps.snowred.app.api.reddit.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.NameValuePair;

import java.util.List;

import eu.roklapps.snowred.app.api.reddit.access.Connection;

public class CurrentUser extends User {
    private static CurrentUser sUser;
    private Credentials mCredentials;
    private List<Subreddit> mSubscribedSubreddits;

    public static CurrentUser getInstance() {
        if (sUser == null)
            sUser = new CurrentUser();
        return sUser;
    }

    public static void setUser(CurrentUser user) {
        sUser = user;
    }

    public static Credentials getCredentials() {
        return sUser.mCredentials;
    }

    public CurrentUser setCredentials(Credentials credentials) {
        sUser.mCredentials = credentials;

        return this;
    }

    public boolean isLoggedIn() {
        return sUser.mCredentials != null;
    }

    public void login(final Context context) {
        FutureCallback<JsonObject> result = new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                JsonObject object = result.get("json").getAsJsonObject().get("data").getAsJsonObject();
                sUser.mCredentials.setModhash(object.get("modhash").toString());
                sUser.mCredentials.setCookie(object.get("cookie").toString());
            }
        };
        login(context, result);
    }

    public void login(final Context context, FutureCallback<JsonObject> result) {
        NameValuePair[] header = mCredentials.convertPasswordAndUser();

        new Connection("http://www.reddit.com/api/login", context)
                .setParams(header)
                .setCallback(result)
                .performWorkaroundOperation(mCredentials.convertPasswordAndUserAsMap());
    }

    public void aboutUser(final Context context) {
        FutureCallback<JsonObject> result = new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                JsonElement element = result.get("data");
                sUser = new Gson().fromJson(element, CurrentUser.class);
            }
        };
        super.aboutUser(mCredentials.getUsername(), context, result);
    }

    public void aboutUser(final Context context, FutureCallback<JsonObject> result) {
        super.aboutUser(mCredentials.getUsername(), context, result);
    }

    public void mySubscribedSubreddits(final Context context, FutureCallback<JsonObject> result) {
        Ion.with(context, "http://www.reddit.com/subreddits/mine/subscriber.json")
                .setHeader("cookie", "reddit_session=" + sUser.mCredentials.getCookie())
                .asJsonObject()
                .setCallback(result);

    }
}
