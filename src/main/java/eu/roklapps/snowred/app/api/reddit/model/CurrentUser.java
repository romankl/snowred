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
    private String mCookie;
    private String mModhash;

    public static CurrentUser getInstance() {
        if (sUser == null)
            sUser = new CurrentUser();
        return sUser;
    }

    public String getModhash() {
        return mModhash;
    }

    public void setModhash(String modhash) {
        this.mModhash = modhash;
    }

    public String getCookie() {
        return mCookie;
    }

    public void setCookie(String mCookie) {
        this.mCookie = mCookie;
    }

    public Credentials getCredentials() {
        return mCredentials;
    }

    public CurrentUser setCredentials(Credentials credentials) {
        this.mCredentials = credentials;

        return this;
    }

    public void login(final Context context) {
        Result loginResult = new Result() {
            @Override
            public void result(JsonObject jsonObject) {
                JsonObject object = jsonObject.get("json").getAsJsonObject().get("data").getAsJsonObject();
                mCredentials.setModhash(object.get("modhash").toString());
                mCredentials.setCookie(object.get("cookie").toString());
            }
        };

        new Connection("http://www.reddit.com/api/login", context)
                .setParams(mCredentials.convertPasswordAndUser())
                .setCallback(loginResult)
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
}
