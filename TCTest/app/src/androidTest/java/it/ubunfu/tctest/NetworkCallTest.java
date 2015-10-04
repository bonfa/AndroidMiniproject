package it.ubunfu.tctest;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import it.ubunfu.tctest.network.NetworkCall;

/**
 * Created by Francesco Bonfadelli on 29/09/15.
 *
 * Unit test for the class NetworkCall
 */
public class NetworkCallTest extends TestCase{

    /**
     *
     * Test the method readInputStream
     */
    @SmallTest
    public void testReadInputStream() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method readInputStream = NetworkCall.class.getDeclaredMethod("readInputStream", InputStream.class);
        readInputStream.setAccessible(true);

        String content = "Nel mezzo del cammin di nostra vita";
        InputStream is = new ByteArrayInputStream(content.getBytes());

        NetworkCall networkCall = new NetworkCall();
        Object returnValue = readInputStream.invoke(networkCall, is);
        String result = (String) returnValue;

        String expected = content;

        assertEquals(expected.length(), result.length());
        assertTrue(expected.equals(result));
    }
}