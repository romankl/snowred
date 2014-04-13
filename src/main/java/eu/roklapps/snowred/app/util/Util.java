package eu.roklapps.snowred.app.util;

import android.os.Build;
import android.os.StrictMode;

import eu.roklapps.snowred.app.MainActivity;
import eu.roklapps.snowred.app.ui.fragments.LinkFragment;

public class Util {
    private Util() {
    }

    public static void enableStrictMode() {
        StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog();

        StrictMode.VmPolicy.Builder vmPolicyBuilder =
                new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog();

        threadPolicyBuilder.penaltyFlashScreen();
        vmPolicyBuilder
                .setClassInstanceLimit(MainActivity.class, 1)
                .setClassInstanceLimit(LinkFragment.class, 1);


        StrictMode.setThreadPolicy(threadPolicyBuilder.build());
        StrictMode.setVmPolicy(vmPolicyBuilder.build());
    }


    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasJB() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasKk() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
