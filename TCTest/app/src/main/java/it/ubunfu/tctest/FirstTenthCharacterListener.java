package it.ubunfu.tctest;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 *
 * Interface definition for a callback to be invoked when the operation
 * of counting the tenth character is terminated, either with good or bad result
 *
 */
public interface FirstTenthCharacterListener {

    /**
     * Operation completed with success
     *
     * @param character the tenth character
     */
    void onFirstTenthCharacter(final Character character);

    /**
     * Operation completed with error
     */
    void onError();
}
