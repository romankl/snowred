package eu.roklapps.snowred.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.roklapps.snowred.app.R;
import eu.roklapps.snowred.app.api.reddit.callbacks.Result;
import eu.roklapps.snowred.app.api.reddit.model.Credentials;
import eu.roklapps.snowred.app.api.reddit.model.CurrentUser;

public class LogInActivity extends Activity implements View.OnClickListener, Result {

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
        Credentials credentials = new Credentials(mPassword.getText().toString(), mUsername.getText().toString());

        if (credentials.verifyCredentials()) {
            CurrentUser.getInstance().setCredentials(credentials).login(this, new Result() {
                @Override
                public void result(JsonObject jsonObject) {
                    if (checkForErrorsInResponse(jsonObject)) {
                        setupError(jsonObject);
                    } else {
                        prepareCurrentUser(jsonObject);
                        CurrentUser.getInstance().aboutUser(LogInActivity.this, LogInActivity.this);
                    }
                }
            });
        } else {
            Crouton.makeText(this, R.string.username_and_password, Style.ALERT).show();
        }
    }

    private boolean checkForErrorsInResponse(JsonObject jsonObject) {
        return jsonObject.getAsJsonObject("json").getAsJsonArray("errors").size() > 0;
    }

    @Override
    public void result(JsonObject jsonObject) {
        JsonElement element = jsonObject.get("data");
        CurrentUser.setUser(new Gson().fromJson(element, CurrentUser.class));

        finish();
    }

    private void setupError(JsonObject jsonObject) {
        JsonElement element = jsonObject.getAsJsonObject("json").getAsJsonArray("errors").get(0);
        String reason = element.getAsJsonArray().get(1).toString();

        Crouton.makeText(this, reason, Style.ALERT).show();
    }

    private void prepareCurrentUser(JsonObject jsonObject) {
        JsonObject object = jsonObject.get("json").getAsJsonObject().get("data").getAsJsonObject();
        CurrentUser.getCredentials().setModhash(object.get("modhash").toString());
        CurrentUser.getCredentials().setCookie(object.get("cookie").toString());
    }
}
