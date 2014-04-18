package eu.roklapps.snowred.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.roklapps.snowred.app.R;
import eu.roklapps.snowred.app.api.reddit.model.Credentials;
import eu.roklapps.snowred.app.api.reddit.model.CurrentUser;
import eu.roklapps.snowred.app.asyncs.SubscribedRedditInsert;
import eu.roklapps.snowred.app.asyncs.callback.AsyncResult;

public class LogInActivity extends Activity implements View.OnClickListener, AsyncResult {

    public static final int LOGIN_REQUEST = 100;
    private static final String TAG = "LoginActivity";
    private Button mLoginButton;
    private EditText mPassword;
    private EditText mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mPassword = (EditText) findViewById(R.id.password);
        mUsername = (EditText) findViewById(R.id.username);

        mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final FutureCallback<JsonObject> followUp = new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                JsonElement element = result.get("data");
                Credentials credentials = CurrentUser.getCredentials();
                CurrentUser.setUser(new Gson().fromJson(element, CurrentUser.class));
                CurrentUser.getInstance().setCredentials(credentials);

                CurrentUser.getInstance().
                        mySubscribedSubreddits(LogInActivity.this, new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                new SubscribedRedditInsert(LogInActivity.this)
                                        .setCallback(LogInActivity.this)
                                        .execute(result);
                            }
                        });
            }
        };

        Credentials credentials = new Credentials(mPassword.getText().toString(), mUsername.getText().toString());

        if (credentials.verify()) {
            CurrentUser.getInstance()
                    .setCredentials(credentials)
                    .login(this, new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (!checkForErrorsInResponse(result)) {
                                prepareCurrentUser(result);
                                CurrentUser.getInstance().aboutUser(LogInActivity.this, followUp);
                            }
                        }
                    });
        } else {
            Crouton.makeText(this, R.string.username_and_password, Style.ALERT).show();
        }
    }

    /**
     * Checks if the json object has the error array
     *
     * @param jsonObject
     * @return true if the error array is filled, false if not
     */
    private boolean checkForErrorsInResponse(JsonObject jsonObject) {
        if (jsonObject.getAsJsonObject("json").getAsJsonArray("errors").size() > 0) {
            setupError(jsonObject);
            return true;
        }

        return false;
    }

    /**
     * Extracts the error message out of the json and displays it in a Toast
     *
     * @param jsonObject
     */
    private void setupError(JsonObject jsonObject) {
        JsonElement element = jsonObject.getAsJsonObject("json").getAsJsonArray("errors").get(0);
        String reason = element.getAsJsonArray().get(1).toString();

        Crouton.makeText(this, reason, Style.ALERT).show();
    }

    /**
     * Prepares the CurrentUser with the Modhash and cookie returned by the API.
     *
     * @param jsonObject
     */
    private void prepareCurrentUser(JsonObject jsonObject) {
        JsonObject object = jsonObject.get("json").getAsJsonObject().get("data").getAsJsonObject();
        String temp = object.get("modhash").getAsString();
        CurrentUser.getCredentials().setModhash(temp);
        temp = object.get("cookie").getAsString();
        CurrentUser.getCredentials().setCookie(temp);
    }

    @Override
    public void onAsyncFinished() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
