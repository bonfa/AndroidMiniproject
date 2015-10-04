package it.ubunfu.tctest;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Francesco Bonfadelli on 29/09/15.
 *
 * Unit test for the class FirstTenthCharacterRunnable
 */
public class WordCounterRunnableTest extends TestCase{

    /**
     * Test the method getTenthCharacter with no words
     */
    @SmallTest
    public void testWordCounterWithNoWords() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method countWords = WordCounterRunnable.class.getDeclaredMethod("countWords", String.class);
        countWords.setAccessible(true);

        String content = "";
        HashMap<String, Integer> expected = new HashMap<>();

        WordCounterRunnable everyTenthCharacterRunnable = new WordCounterRunnable(null, "");
        Object returnValue = countWords.invoke(everyTenthCharacterRunnable, content);
        HashMap<String, Integer> result = (HashMap<String, Integer>) returnValue;

        assertEquals(expected.size(), result.size());
        assertTrue(expected.equals(result));
    }

    /**
     * Test the method getTenthCharacter with one words
     */
    @SmallTest
    public void testWordCounterWithOneWord() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method countWords = WordCounterRunnable.class.getDeclaredMethod("countWords", String.class);
        countWords.setAccessible(true);

        String content = "Nel";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("nel", 1);

        WordCounterRunnable everyTenthCharacterRunnable = new WordCounterRunnable(null, "");
        Object returnValue = countWords.invoke(everyTenthCharacterRunnable, content);
        HashMap<String, Integer> result = (HashMap<String, Integer>) returnValue;

        assertEquals(expected.size(), result.size());
        assertTrue(expected.equals(result));
    }

    /**
     * Test the method getTenthCharacter with more than a word but no one of them occurs more than once in the sentence
     */
    @SmallTest
    public void testWordCounterWithNoRepeatedWords() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method countWords = WordCounterRunnable.class.getDeclaredMethod("countWords", String.class);
        countWords.setAccessible(true);

        String content = "Nel mezzo\tdel cammin\ndi nostra vita";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("nel", 1);
        expected.put("mezzo", 1);
        expected.put("del", 1);
        expected.put("cammin", 1);
        expected.put("di", 1);
        expected.put("nostra", 1);
        expected.put("vita", 1);

        WordCounterRunnable everyTenthCharacterRunnable = new WordCounterRunnable(null, "");
        Object returnValue = countWords.invoke(everyTenthCharacterRunnable, content);
        HashMap<String, Integer> result = (HashMap<String, Integer>) returnValue;

        assertEquals(expected.size(), result.size());
        assertTrue(expected.equals(result));
    }

    /**
     * Test the method getTenthCharacter with more than a word and some of them occur more than once in the sentence
     */
    @SmallTest
    public void testWordCounterWithMoreThanOneWord() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method countWords = WordCounterRunnable.class.getDeclaredMethod("countWords", String.class);
        countWords.setAccessible(true);

        String content = "Nel mezzo\tdel cammin\ndi nostra vita mezzo mezzo";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("nel", 1);
        expected.put("mezzo", 3);
        expected.put("del", 1);
        expected.put("cammin", 1);
        expected.put("di", 1);
        expected.put("nostra", 1);
        expected.put("vita", 1);

        WordCounterRunnable everyTenthCharacterRunnable = new WordCounterRunnable(null, "");
        Object returnValue = countWords.invoke(everyTenthCharacterRunnable, content);
        HashMap<String, Integer> result = (HashMap<String, Integer>) returnValue;

        assertEquals(expected.size(), result.size());
        assertTrue(expected.equals(result));
    }
}