package eu.roklapps.snowred.app.api.reddit.access;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

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

        // TODO: Workaround to fix the npe mentioned in #201
        if (NetworkConnection.checkConnectionWithWarning(mContext)) {
            Builders.Any.B ion = Ion.with(mContext, mUrl);
            for (NameValuePair p : mParams) {
                ion.setHeader(p.getName(), p.getValue());
            }

            ion.asJsonObject()
                    .setCallback(mResult);
        }
    }

    @Deprecated
    public void performWorkaroundOperation(Map<String, List<String>> header) {
        if (NetworkConnection.checkConnectionWithWarning(mContext)) {
            Ion.with(mContext, mUrl)
                    .setHeader("User-Agent", "SnowRed")
                    .setMultipartParameters(header)
                    .asJsonObject()
                    .setCallback(mResult);

        }
    }
}
