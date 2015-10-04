package it.ubunfu.tctest;

import java.util.HashMap;

import it.ubunfu.tctest.network.NetworkCall;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * It downloads a web page, counts how many times each word is repeated in the page and,
 * when it is done, it notifies the caller thanks to @see WordCounterListener.
 *
 */
public class WordCounterRunnable implements Runnable {

    private static final String TAG = WordCounterRunnable.class.getSimpleName();

    private WordCounterListener listener;
    private NetworkCall networkCall;
    private final String url;

    public WordCounterRunnable(WordCounterListener listener, final String url) {
        this.listener = listener;
        this.url = url;
        this.networkCall = new NetworkCall();
    }

    @Override
    public void run() {

        try {

            String content = networkCall.downloadUrl(url);

            HashMap<String, Integer> dictionary = countWords(content);

            listener.onWordsCounted(dictionary);
        }
        catch (Exception e) {

            e.printStackTrace();

            listener.onError();
        }
    }

    private HashMap<String, Integer> countWords(String content) {

        HashMap<String, Integer> dictionary = new HashMap<>();

        content = content.toLowerCase();

        String[] words = content.split("\\s+");

//        Log.d(TAG, "length: " + words.length);

        for (String word : words) {

                Integer count = dictionary.get(word);
                if (count==null) {

                    count = 1;
                }
                else {
                    count++;
                }

                dictionary.put(word, count);
        }

        return dictionary;
    }
}
