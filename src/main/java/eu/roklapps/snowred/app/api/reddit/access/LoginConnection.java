package eu.roklapps.snowred.app.api.reddit.access;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import eu.roklapps.snowred.app.api.reddit.callbacks.ConnectionResult;
import eu.roklapps.snowred.app.api.reddit.callbacks.Result;
import eu.roklapps.snowred.app.api.reddit.model.Credentials;
import eu.roklapps.snowred.app.api.reddit.model.CurrentUser;

public class LoginConnection {
    private Credentials mCredentials;
    private Result mResult;
    private Context mContext;

    public LoginConnection(Credentials mCredentials, ConnectionResult mResult) {
        this.mCredentials = mCredentials;
        this.mResult = mResult;
    }

    public LoginConnection(Credentials mCredentials, Result mResult, Context context) {
        this.mCredentials = mCredentials;
        this.mResult = mResult;
        this.mContext = context;
    }

    public LoginConnection(Credentials mCredentials) {
        this.mCredentials = mCredentials;
    }

    public LoginConnection setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    public void startLogin(final Result result) {
        Ion.with(mContext, "http://www.reddit.com/api/login")
                .setMultipartParameter("passwd", mCredentials.getPassword())
                .setMultipartParameter("user", mCredentials.getUsername())
                .setMultipartParameter("api_type", "json")
                .setMultipartParameter("rem", "True")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject json) {
                        Log.d("Connection", json.toString());
                        Log.d("Connection", json.get("json").getAsJsonObject().get("data").toString());

                        JsonObject object = json.get("json").getAsJsonObject().get("data").getAsJsonObject();

                        CurrentUser.getInstance().setModhash(object.get("modhash").toString());
                        CurrentUser.getInstance().setCookie(object.get("cookie").toString());
                    }
                });
    }


}
