package it.ubunfu.tctest;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Francesco Bonfadelli on 29/09/15.
 *
 * Unit test for the initial state of MainActivity
 * As MainActivity behaviour relies on network calls, that cannot be tested with unit test
 */
public class MainActivityInitialStateTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private TextView firstTenthCharacterAnswerTextView;
    private TextView everyTenthCharacterAnswerTextView;
    private TextView wordCounterAnswerTextView;
    private ProgressBar firstTenthCharacterProgressBar;
    private ProgressBar everyTenthCharacterProgressBar;
    private ProgressBar wordCounterProgressBar;
    private Button runButton;

    public MainActivityInitialStateTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {

        super.setUp();

        setActivityInitialTouchMode(true);

        mainActivity = getActivity();

        runButton = (Button) mainActivity.findViewById(R.id.runButton);
        firstTenthCharacterAnswerTextView = (TextView) mainActivity.findViewById(R.id.firstTenthCharacterAnswerTextView);
        everyTenthCharacterAnswerTextView = (TextView) mainActivity.findViewById(R.id.everyTenthCharacterAnswerTextView);
        wordCounterAnswerTextView = (TextView) mainActivity.findViewById(R.id.wordCounterAnswerTextView);

        firstTenthCharacterProgressBar = (ProgressBar) mainActivity.findViewById(R.id.firstTenthCharacterProgressBar);
        everyTenthCharacterProgressBar = (ProgressBar) mainActivity.findViewById(R.id.everyTenthCharacterProgressBar);
        wordCounterProgressBar = (ProgressBar) mainActivity.findViewById(R.id.wordCounterProgressBar);
    }

    /**
     * Test that all the objects in the view are instantiated properly
     * */
    @SmallTest
    public void testPreconditions() {

        assertNotNull("mainActivity is null", mainActivity);
        assertNotNull("firstTenthCharacterAnswerTextView is null", firstTenthCharacterAnswerTextView);
        assertNotNull("everyTenthCharacterAnswerTextView is null", everyTenthCharacterAnswerTextView);
        assertNotNull("wordCounterAnswerTextView is null", wordCounterAnswerTextView);
        assertNotNull("firstTenthCharacterProgressBar is null", firstTenthCharacterProgressBar);
        assertNotNull("everyTenthCharacterProgressBar is null", everyTenthCharacterProgressBar);
        assertNotNull("wordCounterProgressBar is null", wordCounterProgressBar);
        assertNotNull("runButton is null", runButton);
    }

    //----------------------------- VISIBILITY ON THE SCREEN ---------------------------------------

    /**
     * Test that the run button is on the screen and is visible
     * */
    @SmallTest
    public void testRunButton_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, runButton);
        assertTrue(View.VISIBLE == runButton.getVisibility());
    }

    /**
     * Test that the textview for the first task is on the screen and is visible
     * */
    @SmallTest
    public void testFirstTenthCharacterAnswerTextView_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, firstTenthCharacterAnswerTextView);
        assertTrue(View.VISIBLE == firstTenthCharacterAnswerTextView.getVisibility());
    }

    /**
     * Test that the progressbar for the first task is on the screen and is not visible
     * */
    @SmallTest
    public void testFirstTenthCharacterProgressBar_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, firstTenthCharacterProgressBar);
        assertTrue(View.GONE == firstTenthCharacterProgressBar.getVisibility());
    }

    /**
     * Test that the textview for the second task is on the screen and is visible
     * */
    @SmallTest
    public void testEveryFirstTenthCharacterAnswerTextView_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, everyTenthCharacterAnswerTextView);
        assertTrue(View.VISIBLE == everyTenthCharacterAnswerTextView.getVisibility());
    }

    /**
     * Test that the progressbar for the second task is on the screen and is not visible
     * */
    @SmallTest
    public void testEveryFirstTenthCharacterAnswerProgressBar_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, everyTenthCharacterProgressBar);
        assertTrue(View.GONE == everyTenthCharacterProgressBar.getVisibility());
    }

    /**
     * Test that the textview for the third task is on the screen and is visible
     * */
    @SmallTest
    public void testWordCounterAnswerTextView_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, wordCounterAnswerTextView);
        assertTrue(View.VISIBLE == wordCounterAnswerTextView.getVisibility());
    }

    /**
     * Test that the progressbar for the third task is on the screen and is not visible
     * */
    @SmallTest
    public void testWordCounterProgressBar_layout() {

        final View decorView = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, wordCounterProgressBar);
        assertTrue(View.GONE == wordCounterProgressBar.getVisibility());
    }

    //----------------------------- LABELS AND CONTENT ---------------------------------------------

    /**
     * Test that the initial text for the button
     * */
    @SmallTest
    public void testRunButton_labelText() {

        final String expectedRunButtonText = mainActivity.getString(R.string.run_request);
        final String actualRunButtonText = runButton.getText().toString();
        assertEquals(expectedRunButtonText, actualRunButtonText);
    }

    /**
     * Test that the initial text for the first textview
     * */
    @SmallTest
    public void testFirstTenthCharacterAnswerTextView_labelText() {

        final String expectedFirstTenthCharacterAnswerTextViewText = mainActivity.getString(R.string.request_1_not_started_yet);
        final String actualFirstTenthCharacterAnswerTextViewText = firstTenthCharacterAnswerTextView.getText().toString();
        assertEquals(expectedFirstTenthCharacterAnswerTextViewText, actualFirstTenthCharacterAnswerTextViewText);
    }

    /**
     * Test that the initial text for the second textview
     * */
    @SmallTest
    public void testEveryTenthCharacterAnswerTextView_labelText() {

        final String expectedEveryTenthCharacterAnswerTextViewText = mainActivity.getString(R.string.request_2_not_started_yet);
        final String actualEveryTenthCharacterAnswerTextViewText = everyTenthCharacterAnswerTextView.getText().toString();
        assertEquals(expectedEveryTenthCharacterAnswerTextViewText, actualEveryTenthCharacterAnswerTextViewText);
    }

    /**
     * Test that the initial text for the third textview
     * */
    @SmallTest
    public void testWordCounterAnswerTextView_labelText() {

        final String expectedWordCounterAnswerTextViewText = mainActivity.getString(R.string.request_3_not_started_yet);
        final String actualWordCounterAnswerTextViewText = wordCounterAnswerTextView.getText().toString();
        assertEquals(expectedWordCounterAnswerTextViewText, actualWordCounterAnswerTextViewText);
    }


    //--------------------------------- ENABLED ----------------------------------------------------


    /**
     * Test that the button is enabled
     * */
    @SmallTest
    public void testRunButton_enableStatus() {

        assertTrue(runButton.isEnabled());
    }

}
