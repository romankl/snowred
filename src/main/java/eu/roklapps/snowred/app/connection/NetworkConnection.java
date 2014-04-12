package eu.roklapps.snowred.app.connection;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.roklapps.snowred.app.R;

public class NetworkConnection {
    public static boolean checkConnection(Context withContext) {
        ConnectivityManager connMgr = (ConnectivityManager) withContext.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean checkConnectionWithWarning(Context withContext) {
        if (!checkConnection(withContext)) {
            Crouton.makeText((android.app.Activity) withContext, R.string.no_connection, Style.ALERT).show();
            return false;
        }
        return true;
    }
}
