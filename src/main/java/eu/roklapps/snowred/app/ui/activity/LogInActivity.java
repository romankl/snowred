package eu.roklapps.snowred.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.roklapps.snowred.app.R;
import eu.roklapps.snowred.app.api.reddit.model.Credentials;
import eu.roklapps.snowred.app.api.reddit.model.CurrentUser;

public class LogInActivity extends Activity implements View.OnClickListener{

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
            CurrentUser.getInstance().setCredentials(credentials).login(this);
        } else {
            Crouton.makeText(this, R.string.username_and_password, Style.ALERT).show();
        }
    }
}
