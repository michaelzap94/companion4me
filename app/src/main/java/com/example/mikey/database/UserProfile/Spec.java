package com.example.mikey.database.UserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mikey.database.Database.DatabaseHandlerContacts;
import com.example.mikey.database.Database.DatabaseInterests;
import com.example.mikey.database.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Spec extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button search_btn,clear_btn;
    private EditText min_age,max_age, etEmail, etName;
    Spinner eduSpinner, genSpinner, musSpinner, movSpinner, spoSpinner, fooSpinner, hobSpinner;
    private Spinner nationalityS;

    final String[] EDUCATION = {"","Not stated", "Further Education", "Higher Education"};
    final String[] GENDER = {"","Not stated", "Male", "Female"};
    final String[] MUSIC = {"None","Indie", "Blues", "R&B", "Reggae", "Soul", "Hip Hop", "Jazz", "Metal", "Classical"};

    final String[] MOVIES = {"None","Action", "Animation", "Comedy", "Drama", "Fantasy", "Musical", "Mystery", "Sci-Fi", "Horror"};

    final String[] SPORTS = {"None","Golf", "Boxing", "Tennis", "Football", "Cricket", "Squash", "Hockey", "Rugby", "Weightlifting"};

    final String[] FOOD = {"None","African", "American", "Carribean", "British", "French", "Greek", "Mexican", "Nordic", "Turkish"};

    final String[] HOBBIES = {"None","Reading", "Running", "Yoga", "Cooking", "Puzzles", "Chess", "Fishing", "Hiking", "Photography"};







    public String getUserEt() {
        return userEt;
    }

    public void setUserEt(String userEt) {
        this.userEt = userEt;
    }

    public String getNameEt() {
        return nameEt;
    }

    public void setNameEt(String nameEt) {
        this.nameEt = nameEt;
    }

    private String userEt;
    private String nameEt;
    private int age;

    private String fcountry,fcity;
    private Spinner sfcountry,sfcity;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    private String gender, education;
    private String music;

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    private String movies;
    private String food;
    private String sports;
    private String hobbies;


    public String getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(String minimumAge) {
        this.minimumAge = minimumAge;
    }

    public String getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(String maximumAge) {
        this.maximumAge = maximumAge;
    }

    private String minimumAge;
    private String maximumAge;

    public String getNationalityEt() {
        return nationalityEt;
    }

    public void setNationalityEt(String nationality) {
        this.nationalityEt = nationality;
    }

    private String nationalityEt;





    DatabaseHandlerContacts dbHandler;
    HashMap<String, String> hash;
    DatabaseInterests dbInte;
    HashMap<String, String> hashInte;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
        dbHandler = new DatabaseHandlerContacts(this);
        hash = dbHandler.getUserContacts();
        dbInte = new DatabaseInterests(this);
        hashInte = dbInte.getUserInterests();


        //Methods

        min_age = (EditText) findViewById(R.id.minage);
        max_age = (EditText) findViewById(R.id.maxage);
     //   etEmail = (EditText) findViewById(R.id.email_search);
        etName = (EditText) findViewById(R.id.name_search_info);
        spinnerCountries();//creates nationality spinner
        spinner_methodGender();
        spinner_methodEdu();
        spinner_methodMusic();
        spinner_methodMovies();
        spinner_methodSports();
        spinner_methodFood();
        spinner_methodHobbies();



        search_btn = (Button) findViewById(R.id.btn_search);
        clear_btn = (Button) findViewById(R.id.btn_clear);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                clearForm((ViewGroup) findViewById(R.id.testClear));


            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNameEt(etName.getText().toString());
            //    setUserEt(etEmail.getText().toString());
                setMaximumAge(max_age.getText().toString());
                setMinimumAge(min_age.getText().toString());



                               dbHandler.resetTablesContacts();
                dbHandler.addUserContacts(getNameEt(), null, getNationalityEt(), null, getMaximumAge(),getMinimumAge(), null, getEducation(),getGender(),null,null);
                dbInte.resetTables();
               dbInte.addUser(getMusic(), getMovies(), getSports(), null, getFood(),getHobbies());




                Intent displaycontact = new Intent(getApplicationContext(), DisplayContact.class);
                startActivity(displaycontact);


            }
        });


    }



    private void clearForm(ViewGroup group)
    {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if (view instanceof Spinner) {
                ((Spinner) view).setSelection(0);
            }
            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }






    public void spinner_methodMusic(){
        musSpinner = (Spinner) findViewById(R.id.music_search);
        musSpinner.setOnItemSelectedListener(this);

        List<String> mSpinnerA = new ArrayList<String>();



        for (String countryCode : MUSIC) {


            mSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        musSpinner.setAdapter(adapter);



    }

    public void spinner_methodMovies(){
        movSpinner = (Spinner) findViewById(R.id.movies_search);
        movSpinner.setOnItemSelectedListener(this);

        List<String> moSpinnerA = new ArrayList<String>();



        for (String countryCode : MOVIES) {


            moSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        movSpinner.setAdapter(adapter);



    }
    public void spinner_methodSports(){
        spoSpinner = (Spinner) findViewById(R.id.sports_search);
        spoSpinner.setOnItemSelectedListener(this);

        List<String> spSpinnerA = new ArrayList<String>();



        for (String countryCode : SPORTS) {


            spSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spoSpinner.setAdapter(adapter);



    }
    public void spinner_methodFood(){
        fooSpinner = (Spinner) findViewById(R.id.food_search);
        fooSpinner.setOnItemSelectedListener(this);

        List<String> fooSpinnerA = new ArrayList<String>();



        for (String countryCode : FOOD) {


            fooSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fooSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        fooSpinner.setAdapter(adapter);



    }
    public void spinner_methodHobbies(){
        hobSpinner = (Spinner) findViewById(R.id.hobbies_search);
        hobSpinner.setOnItemSelectedListener(this);

        List<String> hobSpinnerA = new ArrayList<String>();



        for (String countryCode : HOBBIES) {


            hobSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hobSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        hobSpinner.setAdapter(adapter);



    }














    public void spinner_methodGender(){
        genSpinner = (Spinner) findViewById(R.id.gender_spinner);
        genSpinner.setOnItemSelectedListener(this);

        List<String> genderSpinnerA = new ArrayList<String>();



        for (String countryCode : GENDER) {


            genderSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        genSpinner.setAdapter(adapter);






    }


    // this needs to be added to the database php
    public void spinner_methodEdu(){
        eduSpinner = (Spinner) findViewById(R.id.edu_spinner);
        eduSpinner.setOnItemSelectedListener(this);

        List<String> eduSpinnerA = new ArrayList<String>();



        for (String countryCode : EDUCATION) {


            eduSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eduSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        eduSpinner.setAdapter(adapter);



    }

    //TODO create algorithms to search for other users. Assign data into each object.
// gets nationality to search it - ---------------------------------

    public void spinnerCountries() {

        nationalityS = (Spinner) findViewById(R.id.nationalitySearch);
        nationalityS.setOnItemSelectedListener(this);

        List<String> countriesSpinner = new ArrayList<String>();

        String[] locales = Locale.getISOCountries();
        countriesSpinner.add("No selected");
        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);


            countriesSpinner.add(obj.getDisplayCountry());
        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countriesSpinner);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        nationalityS.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item





            Spinner spinner = (Spinner) parent;
            if(spinner.getId() == R.id.nationalitySearch)
            {
                String natio = parent.getItemAtPosition(position).toString();
                setNationalityEt(natio);
            }
            else if(spinner.getId() == R.id.edu_spinner)
            {

                String edu = parent.getItemAtPosition(position).toString();
                setEducation(edu);
            }
            else if(spinner.getId() == R.id.gender_spinner)
            {

                String gender = parent.getItemAtPosition(position).toString();
                setGender(gender);
            }
            else if(spinner.getId() == R.id.music_search)
            {

                String music = parent.getItemAtPosition(position).toString();
                setMusic(music);
            }
            else if(spinner.getId() == R.id.movies_search)
            {

                String gender = parent.getItemAtPosition(position).toString();
                setMovies(gender);
            }
            else if(spinner.getId() == R.id.sports_search)
            {

                String gender = parent.getItemAtPosition(position).toString();
                setSports(gender);
            }
            else if(spinner.getId() == R.id.food_search)
            {

                String gender = parent.getItemAtPosition(position).toString();
                setFood(gender);
            }
            else if(spinner.getId() == R.id.hobbies_search)
            {

                String gender = parent.getItemAtPosition(position).toString();
                setHobbies(gender);
            }



        // Showing selected spinner item

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
//---------------------------------------------------------------------------------------------

}
