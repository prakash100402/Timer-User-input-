package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tim;
    boolean counter = false;
    Button btn;
    ImageView img;
    CountDownTimer count;
    MediaPlayer md;
    EditText txt;

    public void begin(View view)
    {
         txt =findViewById(R.id.editTextTime);
        String value= txt.getText().toString();
        int finalValue=Integer.parseInt(value);
        timeleft(finalValue);
        btnclc(finalValue);
    }

    public void resettim(){
        txt =findViewById(R.id.editTextTime);
        tim.setText("00:00");
        count.cancel();
        btn.setText("GO!");
        counter=false;
    }

    public void btnclc(int i)
    {
        img = findViewById(R.id.imageView);
        btn =findViewById(R.id.button);


        if (counter)
        {
            resettim();
        }
        else {

            counter = true;
            btn.setText("Stop!");

            count = new CountDownTimer( i* 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    timeleft((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    md = MediaPlayer.create(getApplicationContext(), R.raw.iphone_alarm);
                    md.start();
                    resettim();


                }
            }.start();
        }
    }
    public void timeleft(int i)
    {
        tim = findViewById(R.id.textView);
        int hour = i / 3600;
        int  min = (i % 3600) / 60;
        int  sec = i % 60;
        /*int day=(i%604800)/86400;
        int hour=((i%604800)%86400)/3600;
        int min=(((i%604800)%86400)/3600)/60;
        int sec=(((i%604800)%86400)/3600)%60;*/

      /*  int min = i/60;
        int sec= i-(min*60);*/
        String second=Integer.toString(sec);

        if (sec<=9)
        {
            second="0"+second;
        }

        tim.setText(/*Integer.toString(day)+":"+*/Integer.toString(hour)+"h:"+Integer.toString(min)+ "m:" +second );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.editTextTime);
        btn=findViewById(R.id.button);
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String g = txt.getText().toString().trim();
                btn.setEnabled(!g.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}