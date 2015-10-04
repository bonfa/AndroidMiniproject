package it.ubunfu.tctest;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Francesco Bonfadelli on 29/09/15.
 *
 * Unit test for the class EveryTenthCharacterRunnable
 */
public class EveryTenthCharacterRunnableTest extends TestCase{

    /**
     *
     * Test the method getEveryTenthCharacterArray with less than 10 characters
     */
    @SmallTest
    public void tesGetEveryTenthCharacterArrayWithLessThanTenCharacter() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getEveryTenthCharacterArray = EveryTenthCharacterRunnable.class.getDeclaredMethod("getEveryTenthCharacterArray", String.class);
        getEveryTenthCharacterArray.setAccessible(true);

        String content = "012345678";
        char[] expected = {};

        EveryTenthCharacterRunnable everyTenthCharacterRunnable = new EveryTenthCharacterRunnable(null, "");
        Object returnValue = getEveryTenthCharacterArray.invoke(everyTenthCharacterRunnable, content);
        char[] result = (char []) returnValue;

        assertEquals(expected, result);
    }

    /**
     *
     * Test the method getEveryTenthCharacterArray with 10 characters
     */
    @SmallTest
    public void tesGetEveryTenthCharacterArrayWithTenCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getEveryTenthCharacterArray = EveryTenthCharacterRunnable.class.getDeclaredMethod("getEveryTenthCharacterArray", String.class);
        getEveryTenthCharacterArray.setAccessible(true);

        String content = "0123456789";
        char[] expected = {'9'};

        EveryTenthCharacterRunnable everyTenthCharacterRunnable = new EveryTenthCharacterRunnable(null, "");
        Object returnValue = getEveryTenthCharacterArray.invoke(everyTenthCharacterRunnable, content);
        char[] result = (char []) returnValue;

        assertEquals(expected, result);
    }

    /**
     *
     * Test the method getEveryTenthCharacterArray with more than 10 characters
     */
    @SmallTest
    public void tesGetEveryTenthCharacterArrayWithMoreThanTenCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getEveryTenthCharacterArray = EveryTenthCharacterRunnable.class.getDeclaredMethod("getEveryTenthCharacterArray", String.class);
        getEveryTenthCharacterArray.setAccessible(true);

        String content = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ1234";
        char[] expected = {'9','J','T','4'};

        EveryTenthCharacterRunnable everyTenthCharacterRunnable = new EveryTenthCharacterRunnable(null, "");
        Object returnValue = getEveryTenthCharacterArray.invoke(everyTenthCharacterRunnable, content);
        char[] result = (char []) returnValue;

        assertEquals(expected, result);
    }
}