package com.example.mikey.database.UserProfile.Profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikey.database.Database.DatabaseHandlerContacts;
import com.example.mikey.database.Database.DatabaseUsernameId;
import com.example.mikey.database.Database.JSONParser;
import com.example.mikey.database.R;
import com.example.mikey.database.UserProfile.Home;
import com.example.mikey.database.UserProfile.Messaging.MessagingActivity;
import com.example.mikey.database.UserProfile.UserInterests;
import com.example.mikey.database.UserProfile.VoiceCall.AudioPlayer;
import com.example.mikey.database.UserProfile.VoiceCall.PlaceCallTest;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallListener;
import com.sinch.android.rtc.internal.natives.jni.Messaging;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactProfile extends AppCompatActivity {
    private static final String APP_KEY = "ef1889da-3241-4700-a675-200b6b15fecd";
    private static final String APP_SECRET = "fsNJI+ziSkG1d+rDsW84wA==";
    private static final String ENVIRONMENT = "sandbox.sinch.com";
    private static final String REGISTER_DATA = "http://www.companion4me.x10host.com/webservice/registerdata.php";
    private static final String INSERT_FRIEND = "http://www.companion4me.x10host.com/webservice/insertfavourites.php";
    private static final String BLOCK_FRIEND = "http://www.companion4me.x10host.com/webservice/insertblocked.php";

    private static final String DELETE_FRIEND = "http://www.companion4me.x10host.com/webservice/deletefavourites.php";

    private static final String CHECK_FRIEND = "http://www.companion4me.x10host.com/webservice/checkfavourites.php";

    private static final String TAG_MESSAGE = "message";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERS = "userdata";
    private static final String TAG_AGE = "age";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_NAME = "name";
    private static final String TAG_NATIONALITY = "nationality";

    DatabaseUsernameId dbHandlerId;
    HashMap<String,String> idUserHash;
    TextView nameF;
    TextView ageF;
    TextView nationalityF;
    JSONParser jParser = new JSONParser();
    JSONParser jParser2 = new JSONParser();
    JSONArray ldata = null;
    DatabaseHandlerContacts dbHandler;
    HashMap<String, String> hashC;
    ImageButton callUser, messageUser;

  public static SinchCallListener callListener;
    private String _username;
    private String _name;
    private String _age;
    private String _nationality;
    public static Call call;
    private ProgressDialog pDialog;

    /**
     * Gets education
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * Sets education
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * Gets gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @Override
     * @return success
     */
    public int getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     */
    public void setSuccess(int success) {
        this.success = success;
    }

    private   int success;

    /**
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    private String country, city;

    private String education,gender;
    private String music;
    private String food;
    private String hobbies;
    private String sports;
    private String biography;
    private String movies;
    private TextView countryv,cityv;

    private TextView educationv,genderv;
    private TextView musicv;
    private TextView foodv;
    private TextView hobbiesv;
    private TextView sportsv;
    private TextView biographyv;
    private TextView moviesv;


    /**
     *
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;
    private ImageView avatarv;


    /**
     *
     * @return music
     */
    public String getMusic() {
        return music;
    }

    /**
     *
     * @param music
     */
    public void setMusic(String music) {
        this.music = music;
    }

    /**
     *
     * @return food
     */
    public String getFood() {
        return food;
    }

    /**
     *
     * @param food
     */
    public void setFood(String food) {
        this.food = food;
    }

    /**
     *
     * @return hobbies
     */
    public String getHobbies() {
        return hobbies;
    }

    /**
     *
     * @param hobbies
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    /**
     *
     * @return sports
     */
    public String getSports() {
        return sports;
    }

    /**
     * @param sports
     */
    public void setSports(String sports) {
        this.sports = sports;
    }

    /**
     * @return biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     *
     * @param biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     *
     * @return movies
     */
    public String getMovies() {
        return movies;
    }

    /**
     *
     * @param movies
     */
    public void setMovies(String movies) {
        this.movies = movies;
    }

    /**
     *
     * @return _age
     */
    public String get_age() {
        return _age;
    }

    /**
     *
     * @param _age
     */
    public void set_age(String _age) {
        this._age = _age;
    }

    /**
     *
     * @return _name
     */
    public String get_name() {
        return _name;
    }

    /**
     *
     * @param _name
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     *
     * @return _username
     */
    public String get_username() {
        return _username;
    }

    /**
     *
     * @param _username
     */
    public void set_username(String _username) {
        this._username = _username;
    }

    /**
     *
     * @return _nationality
     */
    public String get_nationality() {
        return _nationality;
    }

    /**
     *
     * @param _nationality
     */
    public void set_nationality(String _nationality) {
        this._nationality = _nationality;
    }
ImageView avatarcall;
    TextView usercallingto;
    TextView actioncalling;

    Button add,delete;
    ImageButton blockUser;
    public static SinchClient sinchClient;

  AudioPlayer ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        add = (Button) findViewById(R.id.btnAddUser);
        delete = (Button) findViewById(R.id.btnDeleteUser);

        ap = new AudioPlayer(this);
        dbHandlerId = new DatabaseUsernameId(this);

        idUserHash = dbHandlerId.getUserDetails();



        callUser = (ImageButton) findViewById(R.id.btnCallUser);

        blockUser = (ImageButton) findViewById(R.id.Block_btn);




        messageUser = (ImageButton) findViewById(R.id.btnMessageUser);


        dbHandler = new DatabaseHandlerContacts(this);
        hashC = dbHandler.getUserContacts();

        new ContactProfile.GetTheProfileData().execute();


        sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
               .userId(idUserHash.get("email"))
               .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost(ENVIRONMENT)
                .build();
        new ContactProfile.CheckFriendship().execute();


        sinchClient.setSupportCalling(true);



        sinchClient.start();
          callListener = new SinchCallListener();


        blockUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new blockingContact().execute();

                Intent prof = new Intent(ContactProfile.this, Home.class);
                startActivity(prof);
                finish();
            }
        });

        callUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                call = sinchClient.getCallClient().callUser(hashC.get("namef"));
                /////// call = sinchClient.getCallClient().callUser(hashC.get("namef"));

                call.addCallListener(callListener);

                Intent h = new Intent(ContactProfile.this, PlaceCallTest.class);
                h.putExtra("callingto", get_name());
                h.putExtra("avatar", getAvatar());
                startActivity(h);


            }
        });


        messageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagingActivity();
            }
        });

    }

    private void openMessagingActivity() {
        Intent messagingActivity = new Intent(this, MessagingActivity.class);
        messagingActivity.putExtra("idf", hashC.get("namef"));
        messagingActivity.putExtra("namef", get_name());
       // FOR EMERGENCY ONLY messagingActivity.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


        startActivity(messagingActivity);
    }

    class SinchCallListener implements CallListener {
        @Override
        public void onCallEnded(Call endedCall) {
            ap.stopProgressTone();


            Toast.makeText(ContactProfile.this,"Call ended by your friend." , Toast.LENGTH_LONG).show();

            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
        }
        @Override
        public void onCallEstablished(Call establishedCall) {
            ap.stopProgressTone();
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            Toast.makeText(ContactProfile.this,"Conected" , Toast.LENGTH_LONG).show();
            ap.stopProgressTone();


        }
        @Override
        public void onCallProgressing(Call progressingCall) {
            //call is ringing
            ap.playProgressTone();
        }
        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
            //don't worry about this right now
        }
    }


    public void getProfData() {


        try {
// namef is the email(username) of the friend
            System.out.println("this is the email db " + hashC.get("email"));
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", hashC.get("namef")));
            System.out.println("username of friend" + hashC.get("namef") );

            Log.d("request!", "starting");

            JSONObject json = jParser.makeHttpRequest(
                    REGISTER_DATA, "POST", params);
            // check your log for json response
            Log.d("Login attempt getdata", json.toString());
            ldata = json.getJSONArray(TAG_USERS);
            // looping through all users according to the json object returned
            for (int i = 0; i < ldata.length(); i++) {
                JSONObject c = ldata.getJSONObject(i);

                //gets the content of each tag
                String username = c.getString(TAG_USERNAME);
                String name = c.getString(TAG_NAME);
                String age = c.getString(TAG_AGE);
                String nationality = c.getString(TAG_NATIONALITY);
                setFood(c.getString("food"));

                setEducation(c.getString("education"));
                setGender(c.getString("gender"));
                setHobbies(c.getString("hobbies"));
                setMovies(c.getString("movies"));
                setMusic(c.getString("music"));
                setBiography(c.getString("description"));
                setSports(c.getString("sports"));
                setAvatar(c.getString("avatar"));

                   setCountry(c.getString("country"));
                    setCity(c.getString("city"));
//

                set_name(name);
                set_age(age);
                set_nationality(nationality);
                set_username(username);


                System.out.println("this is the username php: " + username);
                System.out.println("this is the name php: " + name);

                System.out.println("this is the age php: " + age);
                System.out.println("this is the natio php: " + nationality);

        }


    } catch (JSONException e) {
        e.printStackTrace();
    }





    }


    public class GetTheProfileData extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ContactProfile.this);
            pDialog.setMessage("Loading Contact...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }

        @Override
        protected String doInBackground(String... args) {

            getProfData();

            return null;

        }

        protected void onPostExecute(String file_url) {
            nameF = (TextView) findViewById(R.id.txtUserName);
            ageF = (TextView) findViewById(R.id.txtUserAge);
            nationalityF = (TextView) findViewById(R.id.txtNationFriend);
            educationv= (TextView) findViewById(R.id.txtUserEducationFriend);
            genderv= (TextView) findViewById(R.id.txtUserGender);
            musicv= (TextView) findViewById(R.id.fmusic);
            foodv= (TextView) findViewById(R.id.ffood);
            hobbiesv= (TextView) findViewById(R.id.fhobbies);
            sportsv= (TextView) findViewById(R.id.fsports);
            biographyv= (TextView) findViewById(R.id.fbio);
            moviesv= (TextView) findViewById(R.id.fmovies);
            avatarv= (ImageView) findViewById(R.id.imgUserAvatar);
            countryv= (TextView) findViewById(R.id.txtUserCountryFriend);
            cityv= (TextView) findViewById(R.id.txtCityFriend);




            int imgId = getResources().getIdentifier(getAvatar(), "drawable", getPackageName());
            avatarv.setImageResource(imgId);

            countryv.setText(getCountry());
            cityv.setText(getCity());
            educationv.setText(getEducation());
            genderv.setText(getGender());
            musicv.setText(getMusic());
            foodv.setText(getFood());
            hobbiesv.setText(getHobbies());
            sportsv.setText(getSports());
            biographyv.setText(getBiography());
            moviesv.setText(getMovies());
            nameF.setText(get_name());
            ageF.setText(get_age());
            nationalityF.setText(get_nationality());

            System.out.println("this is the name l: " + get_name());

            System.out.println("this is the age l: " + get_age());
            System.out.println("this is the natio l: " + get_nationality());


          pDialog.dismiss();


            if (file_url != null){
                Toast.makeText(ContactProfile.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }

    public class InsertFriend extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ContactProfile.this);
            pDialog.setMessage("adding Contact...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }

        @Override
        protected String doInBackground(String... args) {

            try {
// namef is the email(username) of the friend
                System.out.println("this is the email db " + hashC.get("email"));
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", idUserHash.get("email")));
                 params.add(new BasicNameValuePair("favourites", hashC.get("namef")));
                params.add(new BasicNameValuePair("selected", "ADD TO CONTACTS"));
                params.add(new BasicNameValuePair("name", get_name()));
                params.add(new BasicNameValuePair("avatar",getAvatar()));
                params.add(new BasicNameValuePair("age", get_age()));


                System.out.println("username of friend" + hashC.get("namef"));
                System.out.println("INSERT DATA USERNAME" + idUserHash.get("email") );

                Log.d("request!", "starting");

                JSONObject json = jParser.makeHttpRequest(
                        INSERT_FRIEND, "POST", params);
                // check your log for json response
                Log.d("Login attempt insert", json.toString());

                }


            catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();



            if (file_url != null){
               // Toast.makeText(ContactProfile.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }
    public class blockingContact extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ContactProfile.this);
            pDialog.setMessage("Blocking Contact...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }

        @Override
        protected String doInBackground(String... args) {

            try {
// namef is the email(username) of the friend
                System.out.println("this is the email db " + hashC.get("email"));
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", idUserHash.get("email")));
                params.add(new BasicNameValuePair("blocked", hashC.get("namef")));
                params.add(new BasicNameValuePair("name", get_name()));
                params.add(new BasicNameValuePair("avatar",getAvatar()));
                params.add(new BasicNameValuePair("age", get_age()));


                System.out.println("username of friend BLOCK" + hashC.get("namef"));
                System.out.println("INSERT DATA USERNAME BLOCK" + idUserHash.get("email") );
                System.out.println("INSERT DATA USERNAME BLOCK" + get_name() );
                System.out.println("INSERT DATA USERNAME BLOCK" + getAvatar() );
                System.out.println("INSERT DATA USERNAME BLOCK" +  get_age());

                Log.d("request!", "starting");

                JSONObject json = jParser.makeHttpRequest(
                        BLOCK_FRIEND, "POST", params);
                // check your log for json response
                Log.d("Login attempt insert", json.toString());

            }


            catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {

            if(getSuccess()==0){
                new ContactProfile.DeleteFriend().execute();
                new ContactProfile.CheckFriendship().execute();

            }



            pDialog.dismiss();



            if (file_url != null){
               // Toast.makeText(ContactProfile.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }


    public class CheckFriendship extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {

              }
    @Override
        protected String doInBackground(String... args) {




            try {
//                System.out.println("this is the email db " + hashC.get("email"));
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", idUserHash.get("email")));
                params.add(new BasicNameValuePair("favourites", hashC.get("namef")));
             //   params.add(new BasicNameValuePair("selected", "ADD TO CONTACTS"));



              //  System.out.println("username of friend" + hashC.get("namef"));
             //   System.out.println("INSERT DATA USERNAME" + idUserHash.get("email"));

                Log.d("request!", "starting");



                JSONObject json = jParser.makeHttpRequest(
                        CHECK_FRIEND, "POST", params);
                // check your log for json response
                Log.d("Login CHECK FRIEND", json.toString());
                // full json response
                // json success element

             int success = json.getInt(TAG_SUCCESS);
                setSuccess(success);

                if (success == 1) {
                    Log.d("User not your friend!", json.toString());

          return json.getString(TAG_MESSAGE);



                } else {

                    Log.d("heis yout firend", json.getString(TAG_MESSAGE));



                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
   protected void onPostExecute(String file_url) {

if(getSuccess()==1){
    setAddUser();

}
else{
    setDeleteUser();

}
            if (file_url != null){
              //  Toast.makeText(ContactProfile.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }

    public void setAddUser(){


         add.setVisibility(View.VISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setVisibility(View.INVISIBLE);
                delete.setVisibility(View.VISIBLE);



                new ContactProfile.InsertFriend().execute();
                new ContactProfile.CheckFriendship().execute();

            }
        });
    }
    public void setDeleteUser(){



        delete.setVisibility(View.VISIBLE);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete.setVisibility(View.INVISIBLE);
                add.setVisibility(View.VISIBLE);

                  new ContactProfile.DeleteFriend().execute();
                new ContactProfile.CheckFriendship().execute();

            }
        });
    }

    public class DeleteFriend extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ContactProfile.this);
            pDialog.setMessage("deleting Contact...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }

        @Override
        protected String doInBackground(String... args) {

            try {
// namef is the email(username) of the friend
              //  System.out.println("this is the email db " + hashC.get("email"));
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", idUserHash.get("email")));
                params.add(new BasicNameValuePair("favourites", hashC.get("namef")));
            /*    params.add(new BasicNameValuePair("selected", "ADD TO CONTACTS"));
                params.add(new BasicNameValuePair("name", get_name()));
                params.add(new BasicNameValuePair("avatar",getAvatar()));
                params.add(new BasicNameValuePair("age", get_age()));*/


                System.out.println("delete of friend" + hashC.get("namef"));
                System.out.println("delete DATA USERNAME" + idUserHash.get("email") );

                Log.d("request!", "starting");

                JSONObject json = jParser2.makeHttpRequest(
                       DELETE_FRIEND, "POST", params);
                // check your log for json response
                Log.d("Login attempt delete", json.toString());

            }


            catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();



            if (file_url != null){
              //  Toast.makeText(ContactProfile.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }




}
