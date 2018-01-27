package com.example.adilbekmailanov.speechanalysis;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.yandex.speechkit.Error;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Recognition;
import ru.yandex.speechkit.Recognizer;
import ru.yandex.speechkit.RecognizerListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecognizerListener {

    boolean isListen = true;
    Button listen_button;
    TextView textview_result;
    Recognizer recognizer;
    RecognizerListener recognizerListener;

    @Override
    public void onRecordingBegin(Recognizer recognizer) {
        Log.d("TAG", "BEGIN");
    }
    @Override
    public void onSpeechDetected(Recognizer recognizer) {
        Log.d("TAG", "onSpeechDetected");
    }
    @Override
    public void onSpeechEnds(Recognizer recognizer) {
        Log.d("TAG", "onSpeechEnds");
    }
    @Override
    public void onRecordingDone(Recognizer recognizer) {
        Log.d("TAG", "onRecordingDone");
    }
    @Override
    public void onSoundDataRecorded(Recognizer recognizer, byte[] bytes) {
        Log.d("TAG", "onSoundDataRecorded");
    }
    @Override
    public void onPowerUpdated(Recognizer recognizer, float v) {
        Log.d("TAG", "onPowerUpdated");
    }
    @Override
    public void onPartialResults(Recognizer recognizer, Recognition recognition, boolean b) {
        Log.d("TAG", "onPartialResults");
    }
    @Override
    public void onRecognitionDone(Recognizer recognizer, Recognition recognition) {
        Log.d("TAG", "onRecognitionDone");
    }
    @Override
    public void onError(Recognizer recognizer, Error error) {
        Log.d("TAG", "onError");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechKit.getInstance().configure(getApplicationContext(), getResources().getString(R.string.api_key));
        recognizerListener = this;
        recognizer = Recognizer.create("ru-RU", Recognizer.Model.NOTES, recognizerListener);
        recognizerListener.onRecordingBegin(recognizer);
        listen_button = (Button) findViewById(R.id.listen_button);
        listen_button.setOnClickListener(this);
        textview_result = (TextView) findViewById(R.id.textview_result);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.listen_button:
                if (isListen) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("TAG", "TEST");
                        recognizer.start();
                        isListen = false;
                        listen_button.setText("MUTE");
                    }
                } else {
                    listen_button.setText("START");
                    recognizer.mute();
                    isListen = true;
                }
        }
    }
}
