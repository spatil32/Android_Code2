package com.spatil32.a20367073_homework2;

import android.app.Application;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data members from GUI
        final SeekBar tempSeekBar = (SeekBar) findViewById(R.id.seekBarTemp);
        final TextView fahrenheitTemp = (TextView) findViewById(R.id.fahrenheitTemp);
        final TextView celciusTemp = (TextView)findViewById(R.id.celciusTemp);
        final TextView tempText = (TextView)findViewById(R.id.temperatureText);
        final ImageView temperatureImage = (ImageView)findViewById(R.id.tempImage);

        //seekbar onChangeListener functions decides the operation to perform when event is occured.
        tempSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //to manage decimal values in seekbar, max value of seekbar is taken as 2000 and is divided by 10.0f
                float seekbarValue = ((float)progress/10.0f);

                //formatting of output temperature upto 2 decimals
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                // fahrenheit value on seekbar
                Float fahrenheit = Float.valueOf(String.valueOf(seekbarValue));
                //converted celcius value
                Float celcius = Float.valueOf((5.0f/9.0f) * (fahrenheit - 32.0f));

                //set text according to temperature calculations
                fahrenheitTemp.setText(String.valueOf(fahrenheit));
                celciusTemp.setText(String.valueOf(numberFormat.format(celcius)));

                //change images according to progress and temperature ranges
                if ((progress/10) <= 40.0)
                {
                    tempText.setText("Too Cold!!");
                    tempText.setBackgroundColor(Color.BLUE);
                    tempText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    temperatureImage.setImageResource(R.drawable.toocold);
                }
                else if ((progress/10) > 40.0 && (progress/10) < 90.0)
                {
                    tempText.setText("Just Right!!");
                    tempText.setBackgroundColor(Color.GREEN);
                    tempText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    temperatureImage.setImageResource(R.drawable.normal);
                }
                else if ((progress/10) >= 90.0)
                {
                    tempText.setText("Too Hot!!");
                    tempText.setBackgroundColor(Color.RED);
                    tempText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    temperatureImage.setImageResource(R.drawable.toohot);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking Seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Stopped tracking Seekbar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
