package eu.roklapps.snowred.app.api.reddit.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import eu.roklapps.snowred.app.api.reddit.access.Connection;
import eu.roklapps.snowred.app.api.reddit.callbacks.Result;

public class CurrentUser extends User {
    private static CurrentUser sUser;
    private Credentials mCredentials;

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

    public void login(final Context context) {
        Result loginResult = new Result() {
            @Override
            public void result(JsonObject jsonObject) {
                JsonObject object = jsonObject.get("json").getAsJsonObject().get("data").getAsJsonObject();
                sUser.mCredentials.setModhash(object.get("modhash").toString());
                sUser.mCredentials.setCookie(object.get("cookie").toString());
            }
        };
        login(context, loginResult);
    }

    public void login(final Context context, Result result) {
        new Connection("http://www.reddit.com/api/login", context)
                .setParams(mCredentials.convertPasswordAndUser())
                .setCallback(result)
                .performPostOperation();
    }

    public void aboutUser(final Context context) {
        Result result = new Result() {
            @Override
            public void result(JsonObject jsonObject) {
                JsonElement element = jsonObject.get("data");
                sUser = new Gson().fromJson(element, CurrentUser.class);
            }
        };
        super.aboutUser(mCredentials.getUsername(), context, result);
    }

    public void aboutUser(final Context context, Result result) {
        super.aboutUser(mCredentials.getUsername(), context, result);
    }
}
