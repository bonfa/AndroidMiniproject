package it.ubunfu.tctest;

import java.util.HashMap;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * Interface definition for a callback to be invoked when the operation
 * of counting the occurences of each word in a web page is terminated,
 * either with good or bad result
 * 
 */
public interface WordCounterListener {

    /**
     * Operation finished with success
     *
     * @param dictionary contains all the words in the document, each one with its occurence
     */
    void onWordsCounted(HashMap<String, Integer> dictionary);

    /**
     * Operation finished with error
     */
    void onError();
}
