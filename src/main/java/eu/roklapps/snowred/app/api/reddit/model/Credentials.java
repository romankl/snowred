package eu.roklapps.snowred.app.api.reddit.model;

public class Credentials {
    private String mUsername;
    private String mPassword;
    private String mModhash;
    private String mCookie;

    public Credentials(String mPassword, String mUsername) {
        this.mPassword = mPassword;
        this.mUsername = mUsername;
    }

    public String getUsername() {

        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
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
