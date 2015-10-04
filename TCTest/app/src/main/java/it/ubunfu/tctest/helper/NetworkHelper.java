package it.ubunfu.tctest.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * Contains common methods related to the network
 *
 */
public class NetworkHelper {

    /**
     * Check if the device is connected to an internet connection and such connection is transmitting
     *
     *  @param context the current context
     *
     */
    public static boolean networkAvailable(final Context context) {

        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            return (activeNetworkInfo != null) && activeNetworkInfo.isConnected();

        } catch (final Exception e) {

            return false;
        }
    }
}
