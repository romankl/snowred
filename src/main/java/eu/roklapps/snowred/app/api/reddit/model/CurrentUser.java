package eu.roklapps.snowred.app.api.reddit.model;

import android.content.Context;

import eu.roklapps.snowred.app.api.reddit.access.Connection;

public class CurrentUser extends User{
    private static CurrentUser sUser;
    private Credentials mCredentials;
    private String mCookie;
    private String mModhash;

    public static CurrentUser getInstance() {
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

    public CurrentUser setCredentials(Credentials credentials) {
        this.mCredentials = credentials;

        return this;
    }

    public Connection login(Context context) {
        return new Connection("http://www.reddit.com/api/login", context)
                .setParams(mCredentials.convertPasswordAndUser());
    }
}
