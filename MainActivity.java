package com.example.saadqamer.rex2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private RexModel model;
    private ToneGenerator tone;
    private ScoreModel score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new RexModel();
        int streamType = AudioManager.STREAM_MUSIC;
        int volume = 70;
        tone = new ToneGenerator(streamType, volume);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        score = new ScoreModel();
        newRegex();
    }

    public void onSensorChanged(SensorEvent event)
    {
        double x = Math.sqrt((event.values[0] * event.values[0])+(event.values[1] * event.values[1])+(event.values[2] * event.values[2]));
        if(x>14){
            ((EditText)findViewById(R.id.stringEdit)).setText("");
        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1){}

    public void checkButtonClicked(View v)
    {
        String x = ((EditText)findViewById(R.id.stringEdit)).getText().toString();
        boolean match = model.doesMatch(x);
        String logString = ((TextView)findViewById(R.id.log)).getText().toString();
        String str = logString+"\n"+"regex = "+model.getRex()+", string = "+x+"---->"+model.doesMatch(x);
        ((TextView)findViewById(R.id.log)).setText(str);
        if(model.doesMatch(x)){newRegex();}
        else
        {
            int toneType = ToneGenerator.TONE_DTMF_1;
            int duration = 200;
            tone.startTone(toneType, duration);
        }
    }

    private void newRegex()
    {

    }

}
