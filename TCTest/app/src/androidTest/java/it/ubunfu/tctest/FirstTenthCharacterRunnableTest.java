package it.ubunfu.tctest;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Francesco Bonfadelli on 29/09/15.
 *
 * Unit test for the class FirstTenthCharacterRunnable
 */
public class FirstTenthCharacterRunnableTest extends TestCase{

    /**
     *
     * Test the method getTenthCharacter with less than 10 characters
     */
    @SmallTest
    public void testGetTenthCharacterArraywithlessThanTenCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getTenthCharacter = FirstTenthCharacterRunnable.class.getDeclaredMethod("getTenthCharacter", String.class);
        getTenthCharacter.setAccessible(true);

        String content = "012345678";

        FirstTenthCharacterRunnable everyTenthCharacterRunnable = new FirstTenthCharacterRunnable(null, "");
        Object returnValue = getTenthCharacter.invoke(everyTenthCharacterRunnable, content);
        Character result = (Character) returnValue;

        assertEquals(null, result);
    }

    /**
     *
     * Test the method getTenthCharacter with 10 characters
     */
    @SmallTest
    public void testGetTenthCharacterArrayWithTenCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getTenthCharacter = FirstTenthCharacterRunnable.class.getDeclaredMethod("getTenthCharacter", String.class);
        getTenthCharacter.setAccessible(true);

        String content = "0123456789";
        char expected = '9';

        FirstTenthCharacterRunnable everyTenthCharacterRunnable = new FirstTenthCharacterRunnable(null, "");
        Object returnValue = getTenthCharacter.invoke(everyTenthCharacterRunnable, content);
        char result = (char) returnValue;

        assertEquals(expected, result);
    }

    /**
     *
     * Test the method getTenthCharacter with more than 10 characters
     */
    @SmallTest
    public void testGetTenthCharacterArrayWithMoreThanTenCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getTenthCharacter = FirstTenthCharacterRunnable.class.getDeclaredMethod("getTenthCharacter", String.class);
        getTenthCharacter.setAccessible(true);

        String content = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ1234";
        char expected = '9';

        FirstTenthCharacterRunnable everyTenthCharacterRunnable = new FirstTenthCharacterRunnable(null, "");
        Object returnValue = getTenthCharacter.invoke(everyTenthCharacterRunnable, content);
        char result = (char) returnValue;

        assertEquals(expected, result);
    }
}