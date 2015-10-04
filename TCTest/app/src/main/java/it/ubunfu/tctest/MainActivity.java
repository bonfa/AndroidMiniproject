package it.ubunfu.tctest;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidParameterException;
import java.util.HashMap;

import it.ubunfu.tctest.helper.NetworkHelper;

/**
 * Created by Francesco Bonfadelli on 28/09/15.
 * <p/>
 * It has to perform three tasks simultaneously:
 * the first task is to download a web page and find the 10th characted written in its content,
 * the second task is to download a web page and count all the 10th characters that occur, i.e. 10th, 20th, 30th....
 * the third task is to download a web page, split it using whitespaces (whitespace, tab, newline) and count the name of the occurrences of each word.
 * <p/>
 * The three tasks have to start when the user presses the button.
 */
public class MainActivity extends AppCompatActivity {

    private final static String KEY_FIRST_TASK_STATUS = "key_first_task_status";
    private final static String KEY_SECOND_TASK_STATUS = "key_second_task_status";
    private final static String KEY_THIRD_TASK_STATUS = "key_third_task_status";
    private final static String KEY_FIRST_ANSWER = "key_first_answer";
    private final static String KEY_SECOND_ANSWER = "key_second_answer";
    private final static String KEY_THIRD_ANSWER = "key_third_answer";
    private final static String KEY_RUN_BUTTON_ENABLED = "key_run_button_enabled";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_NOT_STARTED, STATUS_ONGOING, STATUS_FINISHED})
    public @interface Status {
    }

    private final static int STATUS_NOT_STARTED = 0;
    private final static int STATUS_ONGOING = 1;
    private final static int STATUS_FINISHED = 2;

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView firstTenthCharacterAnswerTextView;
    private TextView everyTenthCharacterAnswerTextView;
    private TextView wordCounterAnswerTextView;
    private ProgressBar firstTenthCharacterProgressBar;
    private ProgressBar everyTenthCharacterProgressBar;
    private ProgressBar wordCounterProgressBar;
    private Button runButton;

    private FirstTenthCharacterRunnable firstTenthCharacterRunnable;
    private EveryTenthCharacterRunnable everyTenthCharacterRunnable;

    private WordCounterRunnable wordCounterRunnable;
    private
    @Status
    int firstTenthCharacterTaskStatus;
    private
    @Status
    int everyTenthCharacterTaskStatus;
    private
    @Status
    int wordCounterTaskStatus;

    private FirstTenthCharacterListener firstTenthCharacterListener = new FirstTenthCharacterListener() {
        @Override
        public void onFirstTenthCharacter(final Character character) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (character == null) {
                        firstTenthCharacterAnswerTextView.setText(getString(R.string.request_1_finished_no_param) + " " + getString(R.string.not_enough_characters));
                    } else {

                        firstTenthCharacterAnswerTextView.setText(getString(R.string.request_1_finished, character));
                    }
                    firstTenthCharacterProgressBar.setVisibility(View.GONE);
                    firstTenthCharacterTaskStatus = STATUS_FINISHED;
                }
            });

        }

        @Override
        public void onError() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    firstTenthCharacterAnswerTextView.setText(R.string.request_1_error);
                    firstTenthCharacterProgressBar.setVisibility(View.GONE);
                    firstTenthCharacterTaskStatus = STATUS_FINISHED;
                }
            });

        }
    };
    private EveryTenthCharacterListener everyTenthCharacterListener = new EveryTenthCharacterListener() {

        @Override
        public void onEveryTenthCharacterListener(final char[] array) {

            final String tenthCharacterString = new String(array);

            Log.d(TAG, tenthCharacterString);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    everyTenthCharacterAnswerTextView.setText(getString(R.string.request_2_finished) + " " + tenthCharacterString);
                    everyTenthCharacterProgressBar.setVisibility(View.GONE);
                    everyTenthCharacterTaskStatus = STATUS_FINISHED;
                }
            });

        }

        @Override
        public void onError() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    everyTenthCharacterAnswerTextView.setText(R.string.request_2_error);
                    everyTenthCharacterProgressBar.setVisibility(View.GONE);
                    everyTenthCharacterTaskStatus = STATUS_FINISHED;
                }
            });

        }
    };
    private WordCounterListener wordCounterListener = new WordCounterListener() {

        @Override
        public void onWordsCounted(final HashMap<String, Integer> dictionary) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    int count = getValue(dictionary, getString(R.string.truecaller));

                    wordCounterAnswerTextView.setText(getString(R.string.request_3_finished, count));
                    wordCounterProgressBar.setVisibility(View.GONE);
                    wordCounterTaskStatus = STATUS_FINISHED;
                }
            });
        }

        @Override
        public void onError() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    wordCounterAnswerTextView.setText(R.string.request_3_error);
                    wordCounterProgressBar.setVisibility(View.GONE);
                    wordCounterTaskStatus = STATUS_FINISHED;
                }
            });

        }
    };

    /**
     * Looks for the key in the dictionary and if it is present returns the integer number associated
     * with that key, otherwise it returns the value zero (0)
     *
     * @param dictionary a map that associates each keys with an integer number
     * @param key the key to search in the dictionary
     * @return the value associated with the map if the dictionary contains the key, 0 otherwise
     */
    private int getValue(HashMap<String, Integer> dictionary, String key) {

        int count = 0;
        if (dictionary.containsKey(key) && dictionary.get(key) != null) {

            count = dictionary.get(key);
        }
        return count;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        runButton = (Button) findViewById(R.id.runButton);
        firstTenthCharacterAnswerTextView = (TextView) findViewById(R.id.firstTenthCharacterAnswerTextView);
        everyTenthCharacterAnswerTextView = (TextView) findViewById(R.id.everyTenthCharacterAnswerTextView);
        wordCounterAnswerTextView = (TextView) findViewById(R.id.wordCounterAnswerTextView);

        firstTenthCharacterProgressBar = (ProgressBar) findViewById(R.id.firstTenthCharacterProgressBar);
        everyTenthCharacterProgressBar = (ProgressBar) findViewById(R.id.everyTenthCharacterProgressBar);
        wordCounterProgressBar = (ProgressBar) findViewById(R.id.wordCounterProgressBar);

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startThreeRequests();
            }
        });

        firstTenthCharacterRunnable = new FirstTenthCharacterRunnable(firstTenthCharacterListener, getString(R.string.url));
        everyTenthCharacterRunnable = new EveryTenthCharacterRunnable(everyTenthCharacterListener, getString(R.string.url));
        wordCounterRunnable = new WordCounterRunnable(wordCounterListener, getString(R.string.url));

        if (savedInstanceState == null) {

            initView();
        } else {

            fillView(savedInstanceState);
        }
    }

    /* Initial state. When no thread are started*/
    private void initView() {

        firstTenthCharacterTaskStatus = STATUS_NOT_STARTED;
        firstTenthCharacterProgressBar.setVisibility(View.GONE);

        everyTenthCharacterTaskStatus = STATUS_NOT_STARTED;
        everyTenthCharacterProgressBar.setVisibility(View.GONE);

        wordCounterTaskStatus = STATUS_NOT_STARTED;
        wordCounterProgressBar.setVisibility(View.GONE);
    }

    private void fillView(@NonNull Bundle savedInstanceState) {

        firstTenthCharacterTaskStatus = intToStatus(savedInstanceState.getInt(KEY_FIRST_TASK_STATUS));
        switch (firstTenthCharacterTaskStatus) {

            case STATUS_NOT_STARTED:
                firstTenthCharacterProgressBar.setVisibility(View.GONE);
                firstTenthCharacterAnswerTextView.setText(R.string.request_1_not_started_yet);
                break;

            case STATUS_ONGOING:
                firstTenthCharacterProgressBar.setVisibility(View.VISIBLE);
                firstTenthCharacterAnswerTextView.setText(R.string.request_1_started);
                new Thread(firstTenthCharacterRunnable).start();
                break;

            case STATUS_FINISHED:
                firstTenthCharacterProgressBar.setVisibility(View.GONE);
                firstTenthCharacterAnswerTextView.setText(savedInstanceState.getString(KEY_FIRST_ANSWER));
                break;
        }

        everyTenthCharacterTaskStatus = intToStatus(savedInstanceState.getInt(KEY_SECOND_TASK_STATUS));
        switch (everyTenthCharacterTaskStatus) {

            case STATUS_NOT_STARTED:
                everyTenthCharacterProgressBar.setVisibility(View.GONE);
                everyTenthCharacterAnswerTextView.setText(R.string.request_2_not_started_yet);
                break;

            case STATUS_ONGOING:
                everyTenthCharacterProgressBar.setVisibility(View.VISIBLE);
                everyTenthCharacterAnswerTextView.setText(R.string.request_2_started);
                new Thread(everyTenthCharacterRunnable).start();
                break;

            case STATUS_FINISHED:
                everyTenthCharacterProgressBar.setVisibility(View.GONE);
                everyTenthCharacterAnswerTextView.setText(savedInstanceState.getString(KEY_SECOND_ANSWER));
                break;
        }

        wordCounterTaskStatus = intToStatus(savedInstanceState.getInt(KEY_THIRD_TASK_STATUS));
        switch (wordCounterTaskStatus) {

            case STATUS_NOT_STARTED:
                wordCounterProgressBar.setVisibility(View.GONE);
                wordCounterAnswerTextView.setText(R.string.request_3_not_started_yet);
                break;

            case STATUS_ONGOING:
                wordCounterProgressBar.setVisibility(View.VISIBLE);
                wordCounterAnswerTextView.setText(R.string.request_3_started);
                new Thread(wordCounterRunnable).start();
                break;

            case STATUS_FINISHED:
                wordCounterProgressBar.setVisibility(View.GONE);
                wordCounterAnswerTextView.setText(savedInstanceState.getString(KEY_THIRD_ANSWER));
                break;
        }

        runButton.setEnabled(savedInstanceState.getBoolean(KEY_RUN_BUTTON_ENABLED));
    }

    private void startThreeRequests() {

        if (!NetworkHelper.networkAvailable(MainActivity.this)) {

            Toast.makeText(MainActivity.this, getString(R.string.offline), Toast.LENGTH_SHORT).show();
            return;
        }
        runButton.setEnabled(false);

        firstTenthCharacterAnswerTextView.setText(getString(R.string.request_1_started));
        new Thread(firstTenthCharacterRunnable).start();
        firstTenthCharacterTaskStatus = STATUS_ONGOING;
        firstTenthCharacterProgressBar.setVisibility(View.VISIBLE);

        everyTenthCharacterAnswerTextView.setText(getString(R.string.request_2_started));
        new Thread(everyTenthCharacterRunnable).start();
        everyTenthCharacterTaskStatus = STATUS_ONGOING;
        everyTenthCharacterProgressBar.setVisibility(View.VISIBLE);

        wordCounterAnswerTextView.setText(R.string.request_3_started);
        new Thread(wordCounterRunnable).start();
        wordCounterTaskStatus = STATUS_ONGOING;
        wordCounterProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt(KEY_FIRST_TASK_STATUS, firstTenthCharacterTaskStatus);
        outState.putInt(KEY_SECOND_TASK_STATUS, everyTenthCharacterTaskStatus);
        outState.putInt(KEY_THIRD_TASK_STATUS, wordCounterTaskStatus);
        outState.putBoolean(KEY_RUN_BUTTON_ENABLED, runButton.isEnabled());

        if (firstTenthCharacterTaskStatus == STATUS_FINISHED) {
            outState.putString(KEY_FIRST_ANSWER, firstTenthCharacterAnswerTextView.getText().toString());
        }

        if (everyTenthCharacterTaskStatus == STATUS_FINISHED) {
            outState.putString(KEY_SECOND_ANSWER, everyTenthCharacterAnswerTextView.getText().toString());
        }

        if (wordCounterTaskStatus == STATUS_FINISHED) {
            outState.putString(KEY_THIRD_ANSWER, wordCounterAnswerTextView.getText().toString());
        }
    }

    private static
    @Status
    int intToStatus(final int value) throws InvalidParameterException {

        switch (value) {

            case STATUS_NOT_STARTED:
                return STATUS_NOT_STARTED;

            case STATUS_ONGOING:
                return STATUS_ONGOING;

            case STATUS_FINISHED:
                return STATUS_FINISHED;

            default:
                //should never arrive here
                throw new InvalidParameterException();
        }
    }


}
