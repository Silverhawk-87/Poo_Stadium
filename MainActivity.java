package com.example.nomad.poo_stadium;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtv_p1name, txtv_p2name;
    ImageView imv_p1, imv_p2, imv_slash, imv_potion;

    float x_dwn = 0, x_up = 0, y_dwn = 0, y_up = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtv_p1name = findViewById(R.id.txtv_p1name);
        txtv_p2name = findViewById(R.id.txtv_p2name);


        imv_p1 = findViewById(R.id.imv_p1);
        imv_p2 = findViewById(R.id.imv_p2);
        imv_slash = findViewById(R.id.imv_slash);
        imv_potion = findViewById(R.id.imv_potion);

        MainCharacter character1 = new MainCharacter();

        //Log.i("animtag", "¯\\_(ツ)_/¯");

        Log.i("mytag", "Name " + character1.name);
        Log.i("mytag", "Life points " + character1.life);

        character1.life = character1.life + 100;

        Log.i("mytag", "Life points " + character1.life);

        character1.drink_lppotion();

        Log.i("mytag", "Life points " + character1.life);

        character1.drink_lppotion(500);

        Log.i("mytag", "Life points " + character1.life);

        MainCharacter character2 = new MainCharacter("sir_hitsalot", 5000,1,50,60,60,0);
        Log.i("mytag", "Name " + character2.name);
        Log.i("mytag", "Life points " + character2.life);

        character1.attack(character2);
        Log.i("mytag", character2.name + " was attacked");
        Log.i("mytag", "armor points " + character2.armor);
        Log.i("mytag", "life points " + character2.life);



    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_dwn = event.getX();
                y_dwn = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x_up = event.getX();
                y_up = event.getY();
                float dx = Math.abs(x_up - x_dwn);
                float dy = Math.abs(y_up - y_dwn);
                if (dx>150)
                    if(x_up > x_dwn){
                        imv_slash.setRotationY(0);
                        fade_animation(imv_slash,250);
                        ouch_animation(imv_p2,100,20);
                    }
                    else {
                        imv_slash.setRotationY(180);
                        fade_animation(imv_slash,250);
                        ouch_animation(imv_p1,100,-20);
                    }
                if (dy>150){
                    if(y_up > y_dwn) {
                        potion_animation(imv_p2);//
                        Log.i("fingering tag", "downers");
                    }
                    else {
                        potion_animation(imv_p1);//
                        Log.i("fingering tag", "uppers");
                    }
                    // TODO
                }
                break;
        }
        return true;
    }

    public void fade_animation(final ImageView imView, int duration_ms){

        Animation my_anim = new AlphaAnimation(1,0);
        my_anim.setDuration(duration_ms);

        my_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO
            }
        });

        imView.startAnimation(my_anim);

    }

    public void ouch_animation(final ImageView imView, int duration_ms, float bck){
        ObjectAnimator objanim_bck = ObjectAnimator.ofFloat(imView,"translationX",bck);
        ObjectAnimator objanim_fwd = ObjectAnimator.ofFloat(imView,"translationX", 0);
        objanim_bck.setDuration(duration_ms);
        objanim_fwd.setDuration(duration_ms);

        AnimatorSet my_animset = new AnimatorSet();
        my_animset.playSequentially(objanim_bck,objanim_fwd);
        my_animset.start();
    }

    public void potion_animation(final ImageView imView){
        imv_potion.setX(imView.getX());
        imv_potion.setVisibility(View.VISIBLE);

        ObjectAnimator objanim_alpha = ObjectAnimator.ofFloat(imv_potion,View.ALPHA,1,0);
        ObjectAnimator objanim_up = ObjectAnimator.ofFloat(imv_potion,"translationY", -50);
        objanim_alpha.setDuration(1000);
        objanim_up.setDuration(1000);

        AnimatorSet my_animset = new AnimatorSet();
        my_animset.playSequentially(objanim_alpha,objanim_up);

        my_animset.start();
    }
    /*

        public void potion_animation(boolean plyr){
        final float pos_x;
        if(plyr)
            pos_x = imv_p1.getX();
        else
            pos_x = imv_p2.getX() + 150;

        Animation my_anim = new AlphaAnimation(1,0);
        my_anim.setDuration(1000);

        my_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imv_potion.setX(pos_x);
                imv_potion.setTranslationY(-20);
                //imv_potion.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imv_potion.setVisibility(View.INVISIBLE);
                imv_potion.setTranslationY(+20);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO
            }
        });

        imv_potion.startAnimation(my_anim);




        package com.example.david.mysocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textResponse;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextPort = (EditText)findViewById(R.id.port);
        buttonConnect = (Button)findViewById(R.id.connect);
        buttonClear = (Button)findViewById(R.id.clear);
        textResponse = (TextView)findViewById(R.id.response);

        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                textResponse.setText("");
            }});
    }

    OnClickListener buttonConnectOnClickListener =
            new OnClickListener(){

                @Override
                public void onClick(View arg0) {

      * You have to verify editTextAddress and
      * editTextPort are input as correct format.


    MyClientTask myClientTask = new MyClientTask(
            editTextAddress.getText().toString(),
            Integer.parseInt(editTextPort.getText().toString()));
                    myClientTask.execute();
}};

public class MyClientTask extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response;

    MyClientTask(String addr, int port){
        dstAddress = addr;
        dstPort = port;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        try {
            Socket socket = new Socket(dstAddress, dstPort);
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            socket.close();
            response = byteArrayOutputStream.toString("UTF-8");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

}

}



        }*/
}