package eu.roklapps.snowred.app.api.reddit.model;

import android.content.Context;

import eu.roklapps.snowred.app.api.reddit.access.LoginConnection;
import eu.roklapps.snowred.app.api.reddit.callbacks.ConnectionResult;

public class CurrentUser extends User {
    private static CurrentUser sUser;
    private Credentials mCredentials;
    private String mCookie;

    public static CurrentUser getInstance() {
        if (sUser == null) {
            sUser = new CurrentUser();
        }
        return sUser;
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

    public LoginConnection connectWithReddit() {
        return new LoginConnection(mCredentials);
    }

    public LoginConnection connectWithReddit(ConnectionResult results, Context context) {
        return new LoginConnection(mCredentials, results, context);
    }

    public LoginConnection connectWithReddit(ConnectionResult results) {
        return new LoginConnection(mCredentials, results);
    }
}
