package it.ubunfu.tctest;

import it.ubunfu.tctest.network.NetworkCall;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * It downloads a web page, counts all the 10th characters that occur in the page (i.e. 10th, 20th, 30th) and,
 * when it is done, it notifies the caller thanks to @see EveryTenthCharacterListener.
 *
 */
public class EveryTenthCharacterRunnable implements Runnable {

    private final static int BASE = 10;
    private static final String TAG = EveryTenthCharacterRunnable.class.getSimpleName();

    private final EveryTenthCharacterListener listener;
    private NetworkCall networkCall;
    private final String url;

    public EveryTenthCharacterRunnable(EveryTenthCharacterListener listener, final String url) {

        this.listener = listener;
        this.networkCall = new NetworkCall();
        this.url = url;
    }

    @Override
    public void run() {

        try {

            String content = networkCall.downloadUrl(url);

//            Log.d(TAG, content);

            char[] array = getEveryTenthCharacterArray(content);

            listener.onEveryTenthCharacterListener(array);
        }
        catch (Exception e) {

            e.printStackTrace();

            listener.onError();
        }
    }

    /**
     * It returns an array containing every tenth character of the string
     * (i.e. 10th, 20th, 30th, ...)
     *
     * @param content the string to be processed
     * @return the array with evert tenth character of the input message
     */
    private char[] getEveryTenthCharacterArray(String content) {

        int length = content.length() / BASE;

        char[] array = new char[length];

        for(int i=0; i<length; i++) {

            int indexInString = ((i+1) * BASE) - 1;

            array[i] = content.charAt(indexInString);
        }
        return array;
    }

}
