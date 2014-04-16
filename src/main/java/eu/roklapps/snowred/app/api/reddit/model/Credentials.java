package eu.roklapps.snowred.app.api.reddit.model;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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

    public NameValuePair[] convertPasswordAndUser() {
        NameValuePair[] header = new NameValuePair[4];

        header[0] = new BasicNameValuePair("user", mUsername);
        header[1] = new BasicNameValuePair("passwd", mPassword);
        header[2] = new BasicNameValuePair("api_type", "json");
        header[3] = new BasicNameValuePair("rem", "true");

        return header;
    }

    public NameValuePair[] getCookieAndModHash() {
        NameValuePair[] header = new NameValuePair[4];
        header[0] = new BasicNameValuePair("User-Agent", "SnowRed");
        header[1] = new BasicNameValuePair("cookie", "reddit_session" + mCookie);
        header[2] = new BasicNameValuePair("uh", mModhash);
        header[3] = new BasicNameValuePair("api_type", "json");

        return header;
    }

    public Map<String, List<String>> convertPasswordAndUserAsMap() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> param = new ArrayList<String>();
        param.add(mPassword);
        map.put("passwd", param);

        param = new ArrayList<String>();

        param.add(mUsername);
        map.put("user", param);

        param = new ArrayList<String>();

        param.add("json");
        map.put("api_type", param);

        return map;
    }


    public String getUsername() {

        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public boolean verify() {
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
