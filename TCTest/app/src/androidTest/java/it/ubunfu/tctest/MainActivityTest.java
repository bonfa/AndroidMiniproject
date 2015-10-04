package it.ubunfu.tctest;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Francesco Bonfadelli on 29/09/15.
 *
 * Unit test for the MainActivityClass
 */
public class MainActivityTest extends TestCase {

    /**
     * Test the method getCount with no key
     */
    @SmallTest
    public void testGetMapKeyWithWithNoKey() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getValue = MainActivity.class.getDeclaredMethod("getValue", HashMap.class, String.class);
        getValue.setAccessible(true);

        HashMap<String, Integer> map = new HashMap<>();
        String key = "key";

        MainActivity mainActivity = new MainActivity();
        Object returnValue = getValue.invoke(mainActivity, map, key);
        int resultValue = (int) returnValue;

        assertEquals(0, resultValue);
    }

    /**
     * Test the method getCount with key but value null
     */
    @SmallTest
    public void testGetMapKeyWithWithKeyButNullValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getValue = MainActivity.class.getDeclaredMethod("getValue", HashMap.class, String.class);
        getValue.setAccessible(true);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("key", null);
        String key = "key";

        MainActivity mainActivity = new MainActivity();
        Object returnValue = getValue.invoke(mainActivity, map, key);
        int resultValue = (int) returnValue;

        assertEquals(0, resultValue);
    }

    /**
     * Test the method getCount with key and value not null
     */
    @SmallTest
    public void testGetMapKeyWithWithKeyAndNotNullValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getValue = MainActivity.class.getDeclaredMethod("getValue", HashMap.class, String.class);
        getValue.setAccessible(true);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("key", 5);
        String key = "key";

        MainActivity mainActivity = new MainActivity();
        Object returnValue = getValue.invoke(mainActivity, map, key);
        int resultValue = (int) returnValue;

        assertEquals(5, resultValue);
    }
}