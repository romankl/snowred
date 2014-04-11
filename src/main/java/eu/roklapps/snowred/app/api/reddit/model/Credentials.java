package eu.roklapps.snowred.app.api.reddit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Credentials {
    private String mUsername;
    private String mPassword;
    private String mModhash;
    private String mCookie;

    public Credentials(String mPassword, String mUsername) {
        this.mPassword = mPassword;
        this.mUsername = mUsername;
    }

    public Map<String, List<String>> convertPasswordAndUser() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> param = new ArrayList<String>();
        param.add(mPassword);
        map.put("passwd", param);

        param = new ArrayList<String>();

        param.add(mUsername);
        map.put("user", param);

        return map;
    }

    public Map<String, List<String>> getCookieAndModhash() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> param = new ArrayList<String>();
        param.add(mCookie);
        map.put("reddit_session", param);

        param = new ArrayList<String>();

        param.add(mModhash);
        map.put("modhash", param);

        return map;
    }

    public String getUsername() {

        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public boolean verifyCredentials() {
        return (mPassword.length() > 0 && !mPassword.isEmpty()) &&
                ((mUsername.length() > 0) && !mUsername.isEmpty());
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmodhash() {
        return mModhash;
    }

    public void setModhash(String mModhash) {
        this.mModhash = mModhash;
    }

    public String getCookie() {
        return mCookie;
    }

    public void setCookie(String mCookie) {
        this.mCookie = mCookie;
    }
}
