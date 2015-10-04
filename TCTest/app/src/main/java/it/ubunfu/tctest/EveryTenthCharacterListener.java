package it.ubunfu.tctest;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * Interface definition for a callback to be invoked when the operation of counting
 * each tenth character is terminated, either with good or bad result
 */
public interface EveryTenthCharacterListener {

    /**
     * Operation finished with success
     *
     * @param array contains each tenth character in the document, i.e (10th, 20th, 30th, ...)
     */
    void onEveryTenthCharacterListener(char[] array);

    /**
     * Operation completed with error
     */
    void onError();
}
