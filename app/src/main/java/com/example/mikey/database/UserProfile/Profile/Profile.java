package com.example.mikey.database.UserProfile.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikey.database.Database.DatabaseCity;
import com.example.mikey.database.Database.DatabaseHandler;
import com.example.mikey.database.Database.JSONParser;
import com.example.mikey.database.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Profile extends AppCompatActivity {
    private static final String REGISTER_DATA = "http://www.companion4me.x10host.com/webservice/registerdata.php";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERS = "userdata";
    private static final String TAG_AGE = "age";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_NAME = "name";
    private static final String TAG_NATIONALITY = "nationality";
    TextView nameV;
    TextView ageV;
    TextView nationalityV;
DatabaseCity dbcity;

    JSONParser jParser = new JSONParser();
    JSONArray ldata = null;
    DatabaseHandler dbHandler;
    HashMap<String, String> hash;
    private String _username;
    private String _name;
    private String _age;
    private String _nationality;

    /**
     *
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

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
     *
     * @param sports
     */
    public void setSports(String sports) {
        this.sports = sports;
    }

    /**
     *
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



    private ProgressDialog pDialog;

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

    /**
     * @Override
     */
    public void onBackPressed() {

        Intent setIntent = new Intent(Intent.ACTION_MAIN);

        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }


    /**
     * @Override
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        dbHandler = new DatabaseHandler(this);
        dbcity = new DatabaseCity(this);
        hash = dbHandler.getUserDetails();

        new Profile.GetTheProfileData().execute();



    }

    public class GetTheProfileData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Loading Profile...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }

        @Override
        protected String doInBackground(String... args) {

            try {

                System.out.println("this is the email db " + hash.get("email"));
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", hash.get("email")));

                Log.d("request!", "starting");

                JSONObject json = jParser.makeHttpRequest(
                        REGISTER_DATA, "POST", params);
                // check your log for json response
                Log.d("Login attempt", json.toString());
                ldata = json.getJSONArray(TAG_USERS);
                // looping through all users according to the json object returned
                for (int i = 0; i < ldata.length(); i++) {
                    JSONObject c = ldata.getJSONObject(i);

                    //gets the content of each tag
                    String username = c.getString(TAG_USERNAME);
                    String name = c.getString(TAG_NAME);
                    String age = c.getString(TAG_AGE);
                    String nationality = c.getString(TAG_NATIONALITY);


                    set_name(name);
                    set_age(age);
                    set_nationality(nationality);
                    set_username(username);
                    setEducation(c.getString("education"));
                    setGender(c.getString("gender"));
                    setFood(c.getString("food"));
                    setHobbies(c.getString("hobbies"));
                    setMovies(c.getString("movies"));
                    setMusic(c.getString("music"));
                    setBiography(c.getString("description"));
                    setSports(c.getString("sports"));
                    setAvatar(c.getString("avatar"));
                    setCountry(c.getString("country"));
                    setCity(c.getString("city"));






                    System.out.println("this is the city php: " + c.getString("city"));

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            nameV = (TextView) findViewById(R.id.Name_txt);
            ageV = (TextView) findViewById(R.id.Age_txt);
            nationalityV = (TextView) findViewById(R.id.txtMyNation);
            educationv= (TextView) findViewById(R.id.ueducationid);
            genderv= (TextView) findViewById(R.id.ugenderid);
            musicv= (TextView) findViewById(R.id.umusicid);
            foodv= (TextView) findViewById(R.id.ufoodid);
            hobbiesv= (TextView) findViewById(R.id.uhobbiesid);
            sportsv= (TextView) findViewById(R.id.usportsid);
            biographyv= (TextView) findViewById(R.id.ubiographyid);
            moviesv= (TextView) findViewById(R.id.umoviesid);
            avatarv= (ImageView) findViewById(R.id.Avatar_img);
           countryv= (TextView) findViewById(R.id.Country_profile);
            cityv= (TextView) findViewById(R.id.City_profile);

            dbcity.resetTables();
            dbcity.addCity(null, getCity());

            int imgId = getResources().getIdentifier(getAvatar(), "drawable", getPackageName());
            avatarv.setImageResource(imgId);


            educationv.setText(getEducation());
            genderv.setText(getGender());
            musicv.setText(getMusic());
            foodv.setText(getFood());
            hobbiesv.setText(getHobbies());
            sportsv.setText(getSports());
            biographyv.setText(getBiography());
            moviesv.setText(getMovies());
            countryv.setText(getCountry());
            cityv.setText(getCity());
            nameV.setText(get_name());
            ageV.setText(get_age());
            nationalityV.setText(get_nationality());

            System.out.println("this is the name l: " + get_name());

            System.out.println("this is the age l: " + get_age());
            System.out.println("this is the natio l: " + get_nationality());


            pDialog.dismiss();


            if (file_url != null){
                Toast.makeText(Profile.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }

}

















