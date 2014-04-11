package eu.roklapps.snowred.app.api.reddit.callbacks;

import com.google.gson.JsonObject;

public interface Result<T> {
    public void result(JsonObject jsonObject);
}
