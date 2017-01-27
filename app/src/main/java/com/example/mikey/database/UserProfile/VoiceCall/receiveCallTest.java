package com.example.mikey.database.UserProfile.VoiceCall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikey.database.Database.DatabaseUsernameId;
import com.example.mikey.database.Database.JSONParser;
import com.example.mikey.database.R;
import com.example.mikey.database.UserProfile.Home;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zapatacajas on 16/03/2016.
 */
public class receiveCallTest extends AppCompatActivity {
  DatabaseUsernameId dbHandlerId;
    HashMap<String,String> idUserHash;
   ImageButton accept, decline;
    AudioPlayer ap;//holds the audio player

ImageView avatarv;
    TextView nametext,fixtext;
    @Override
    public void onBackPressed() {

        Intent setIntent = new Intent(Intent.ACTION_MAIN);

        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receivecalls_layout);
        dbHandlerId = new DatabaseUsernameId(this);
        idUserHash = dbHandlerId.getUserDetails();
        avatarv = (ImageView) findViewById(R.id.incomingavatar);
        nametext = (TextView) findViewById(R.id.incomingname);
        fixtext = (TextView) findViewById(R.id.incomingfixtext);

        Intent intent = getIntent();
        String namef = intent.getStringExtra("callingto");
        String ava = intent.getStringExtra("avatar");
        String age = intent.getStringExtra("age");
    int imgId = getResources().getIdentifier(ava, "drawable", getPackageName());
        avatarv.setImageResource(imgId);

        nametext.setText(namef+" "+age);


        ap = new AudioPlayer(this);

        accept = (ImageButton) findViewById(R.id.answer_call);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Home.call.answer();
                Home.call.addCallListener(new SinchCallListener());

                fixtext.setText("You are in a call with ....");
            }
        });

        decline = (ImageButton) findViewById(R.id.end_incoming_call);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixtext.setText("You ended a call with....");

                Home.call.hangup();
                Home.call.addCallListener(new SinchCallListener() {
                });

                Intent h = new Intent(receiveCallTest.this, Home.class);
                finish();
                startActivity(h);
                finish();
                //Toast.makeText(ContactProfile.this,"Call ended by you." , Toast.LENGTH_LONG).show();


            }
        });



        }
/*
    public class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            //Pick up the call!

         //   ap.playRingtone();
          //  notification();
        //    Toast.makeText(Home.this, "receiving call", Toast.LENGTH_LONG).show();


            Home.callcall = incomingCall;
            call.addCallListener(new SinchCallListener());

        }
    }
  */  class SinchCallListener implements CallListener {
        @Override
        public void onCallEnded(Call endedCall) {
            //  Toast.makeText(ContactProfile.this, "Call ended by your friend." + "SHOULD I PUT USER'S NAME?.", Toast.LENGTH_LONG).show();
            Home.call.hangup();
            ap.stopRingtone();
            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
            Intent h = new Intent(receiveCallTest.this, Home.class);
            finish();
            startActivity(h);
            finish();

        }

        @Override
        public void onCallEstablished(Call establishedCall) {
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            ap.stopRingtone();

            //   Toast.makeText(ContactProfile.this,"Conected" , Toast.LENGTH_LONG).show();

        }
        @Override
        public void onCallProgressing(Call progressingCall) {
            //call is ringing
        }
        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
            //don't worry about this right now
        }
    }














}




