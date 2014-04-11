package eu.roklapps.snowred.app.api.reddit.access;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.roklapps.snowred.app.api.reddit.callbacks.Result;

public class Connection {
    private Map<String, List<String>> mParams;
    private Context mContext;
    private String mUrl;
    private Result mResult;

    public Connection(String urlEnd, Context context) {
        this.mUrl = urlEnd;
        this.mContext = context;
    }

    public Connection setCallback(Result mResult) {
        this.mResult = mResult;

        return this;
    }

    public Connection setContext(Context mContext) {
        this.mContext = mContext;

        return this;
    }

    public Connection setParams(Map<String, List<String>> mParams) {
        this.mParams = mParams;

        addApiType();

        return this;
    }

    private void addApiType() {
        List<String> api = new ArrayList<String>();
        api.add("json");
        this.mParams.put("api_type", api);
    }

    public void performOperation() {
        Ion.with(mContext, mUrl)
                .setMultipartParameters(mParams)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject json) {
                        if (mResult != null) {
                            mResult.result(json);
                        }
                    }
                });
    }
}
