package eu.roklapps.snowred.app.api.reddit.access;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.NameValuePair;

import eu.roklapps.snowred.app.connection.NetworkConnection;

public class Connection {
    private NameValuePair[] mParams;
    private Context mContext;
    private String mUrl;
    private FutureCallback<JsonObject> mResult;

    public Connection(String urlEnd, Context context) {
        this.mUrl = urlEnd;
        this.mContext = context;
    }

    public Connection setCallback(FutureCallback<JsonObject> mResult) {
        this.mResult = mResult;

        return this;
    }

    public Connection setContext(Context mContext) {
        this.mContext = mContext;

        return this;
    }

    public Connection setParams(NameValuePair... pair) {
        this.mParams = pair;

        return this;
    }

    public void performOperation() {
        if (mParams == null) {
            mParams = new NameValuePair[0];
        }

        if (NetworkConnection.checkConnectionWithWarning(mContext)) {
            Ion.with(mContext, mUrl)
                    .setHeader(mParams)
                    .asJsonObject()
                    .setCallback(mResult);
        }
    }
}
