package eu.roklapps.snowred.app.api.reddit.callbacks;

public interface FutureResult<T> {
    public void withResponse(FutureResult<T> result);
}
