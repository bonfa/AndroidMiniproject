package it.ubunfu.tctest;

import android.support.annotation.Nullable;

import it.ubunfu.tctest.network.NetworkCall;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * It downloads a web page and retrieves the 10th characters that occur in the page, thanks to @see FirstTenthCharacterListener.
 *
 */
public class FirstTenthCharacterRunnable implements Runnable {

    private final static int BASE = 10;

    private final FirstTenthCharacterListener listener;
    private NetworkCall networkCall;
    private final String url;

    public FirstTenthCharacterRunnable(FirstTenthCharacterListener listener, final String url) {

        this.listener = listener;
        this.networkCall = new NetworkCall();
        this.url = url;
    }

    @Override
    public void run() {

        try {

            String content = networkCall.downloadUrl(url);

            Character tenthCharacter = getTenthCharacter(content);

            listener.onFirstTenthCharacter(tenthCharacter);
        }
        catch (Exception e) {

            e.printStackTrace();

            listener.onError();
        }
    }

    @Nullable
    private Character getTenthCharacter(String content) {

        Character tenthCharacter;

        if (content.length() < BASE) {

            tenthCharacter = null;
        }
        else {

            tenthCharacter = content.charAt(BASE-1);
        }
        return tenthCharacter;
    }
}
