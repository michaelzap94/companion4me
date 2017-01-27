package com.example.mikey.database.UserProfile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikey.database.Database.DatabaseHandler;
import com.example.mikey.database.Database.DatabaseUsernameId;
import com.example.mikey.database.Database.JSONParser;
import com.example.mikey.database.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class registerUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHandler dbHandler;
    Session session = null;
    // Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;

    private EditText editFirstName;
    private EditText editLastName;
    private TextView birthday;
    private Calendar calendar;
    private int year, month, day;
    private Spinner nationalityS;
    private EditText password1;
    private EditText password2;
    private Button signUpBtn;
    private TextView errorMessage;
    private Spinner spinnerSecretQuestions;
    Spinner eduSpinner, genSpinner;
    Spinner spinCity, spinCountry;
    List<String> cityItems;
    ArrayAdapter<String> cityAdp;


    final String[] EDUCATION = {"Not stated", "Further Education", "Higher Education"};
    final String[] GENDER = {"Not stated", "Male", "Female"};
    private String[] spinnerQuestion={"What was your first pet's name?",
            "What was your first car?",("What was your first love's name?"),("What was the city you were born?")};

    public String[] getSpinnerQuestion() { return spinnerQuestion; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String country,city;

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String education,gender;

    private EditText emailBox;
    private String password;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL ="http://www.companion4me.x10host.com/webservice/registerCode.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    public void setPassword(String password) {
        this.password = computeMD5Hash(password);
    }

    public String getPassword() {
        return password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String useremail) {
        this.verificationCode = computeMD5Hash(useremail).substring(0, Math.min(useremail.length(),8));
    }

    EditText answerEt;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private String question;


    DatabaseUsernameId dbHandlerId;


    private String verificationCode;

    // First Layout
    private RelativeLayout firstReg;
    private Button btnFirstRegNext, btnFirstRegBack;

    // Second Layout
    private RelativeLayout secondReg;
    private Button btnSecondRegBack, btnSecondRegNext;

    // Third Layout
    private RelativeLayout thirdReg;
    private Button btnThirdRegBack, btnThirdRegNext;

    // Fourth Layout
    private RelativeLayout fourthReg;
    private Button btnFourthRegBack, btnFourthSignUp;

    HashMap<String, String> hash;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        firstReg = (RelativeLayout)findViewById(R.id.firstRegLayout);
        secondReg = (RelativeLayout)findViewById(R.id.secondRegLayout);
        // thirdReg = (RelativeLayout)findViewById(R.id.thirdRegLayout);
        fourthReg = (RelativeLayout)findViewById(R.id.fourthRegLayout);
        firstReg.setVisibility(View.VISIBLE);
        secondReg.setVisibility(View.INVISIBLE);
        //  thirdReg.setVisibility(View.INVISIBLE);
        fourthReg.setVisibility(View.INVISIBLE);

        btnFirstRegBack = (Button) findViewById(R.id.btn1Back);
        btnFirstRegNext = (Button)findViewById(R.id.btn1Next);
        btnSecondRegBack = (Button)findViewById(R.id.btn2Back);
        btnSecondRegNext = (Button)findViewById(R.id.btn2Next);
        //  btnThirdRegBack = (Button)findViewById(R.id.btn3Back);
        //  btnThirdRegNext = (Button)findViewById(R.id.btn3Next);
        btnFourthRegBack = (Button)findViewById(R.id.btn4Back);

        btnFirstRegNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail();
                checkNames();
                validateAge();

                if(validateEmail() == true && checkNames() == true&&validateAge()==true) {
                    firstReg.setVisibility(View.INVISIBLE);
                    secondReg.setVisibility(View.VISIBLE);
                }
            }
        });
        btnFirstRegBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
                finish();
            }
        });
        btnSecondRegNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secondReg.setVisibility(View.INVISIBLE);
                fourthReg.setVisibility(View.VISIBLE);
            }
        });

        btnSecondRegBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondReg.setVisibility(View.INVISIBLE);
                firstReg.setVisibility(View.VISIBLE);
            }
        });

        btnFourthRegBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthReg.setVisibility(View.INVISIBLE);
                secondReg.setVisibility(View.VISIBLE);
            }
        });

/*
        btnThirdRegNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdReg.setVisibility(View.INVISIBLE);
                fourthReg.setVisibility(View.VISIBLE);
            }
        });

*/

        signUpBtn = (Button) findViewById(R.id.btnSaveChanges);
        spinnerQuestion();
        spinnerCountries();
        spinner_methodGender();
        spinner_methodEdu();
       // answerEt = (EditText) findViewById(R.id.answer_atregister);

        birthday = (TextView) findViewById(R.id.birthday);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        getTheAge(year, month, day);


        emailBox = (EditText) findViewById(R.id.emailBoxLogin);


        dbHandler = new DatabaseHandler(this);
        hash = dbHandler.getUserDetails();

        // dbHandlerId = new DatabaseUsernameId(this);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPasswords();
                validateAnswer();
                if (checkPasswords() == true&&validateAnswer()==true) {



                    String username = emailBox.getText().toString();
                    setVerificationCode(username);
                    String password3 = getPassword();
                    System.out.println("username: " +username);
                    System.out.println("code short onc: " + getVerificationCode());
                    System.out.println("code: long onc" + computeMD5Hash(username).substring(0, Math.min(username.length(), 8)));



                    new CreateUserCode().execute(username, getVerificationCode());
                    System.out.println("for real spinner getquestion " + getQuestion());
                    dbHandler.resetTables();
                    dbHandler.addUser(getName(), Integer.toString(getAge()), getNationality(), getEmail(), password3, getAnswer(), getQuestion(), getEducation(), getGender(), getCountry(), getCity());
                    sendingEmail(username);


                }
            }


        });
    }

    public void sendingEmail(String username){
        rec = username;

        subject = "Companion4me Verification Code.";
        textMessage = "<html><body><head><b>Thank you very much for joining Companion4me.</b></head><br><br>Your verification code is: <b>"
                +getVerificationCode()+
                "</b><br><br>Please enter the verification code in the app.<br><br>We are looking forward of you meeting new people with Companion4me.<br><br><br><br><p><b>Terms and Conditions:</b></p><ol><li>We(Companion4me Ltd.) securely stored your details in our database and will not give out to the outsider.</li><li>It is your own responsibility to not pass on your details to stranger as it may put you in danger of scamming.</li></ol></body></html>";


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("companion4meapp@gmail.com", "Companion4");
            }
        });

        //  pdialog = ProgressDialog.show(registerUser.this, "", "Sending Mail...", true);


        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();    }




    class RetreiveFeedTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

        }


        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("companion4meapp@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }


    class CreateUserCode extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(registerUser.this);
            pDialog.setMessage("Sending email...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {

            // Check for success tag
            int success;
            String username = args[0];
            String verificationcode = args[1];

            System.out.println("args0 in php" + username);
            System.out.println("args1 in php" + verificationcode);


            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("verificationcode", verificationcode));

                System.out.println("username: in php" + username);
                System.out.println("code in php" + verificationcode);
                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();


            Intent i = new Intent(registerUser.this, EmailVerification.class);
            //  finish();
            startActivity(i);

            //  finish();

            // finish();
            if (file_url != null){
                Toast.makeText(registerUser.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

    }

    //-------------------------------------------------------------------------------------------------------------

    /** UTILITY METHODS */

    public boolean validateAnswer() {
        boolean valid = true;

        answerEt = (EditText) findViewById(R.id.answer_atregister);
        String answer = answerEt.getText().toString();
        setAnswer(answer);


        if (answer.isEmpty()) {
            answerEt.setError("Answer cannot be empty.");

            valid = false;

        }
        else{
            answerEt.setError(null);
        }


        return valid;


    }









    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            showDate(arg1, arg2 + 1, arg3);
            getTheAge(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        birthday.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }

    // gets the age of the user,
    private int getTheAge(int year, int month, int day)
    {
        LocalDate birthday = new LocalDate (year, month, day);          //Birth date
        LocalDate now = new LocalDate();                    //Today's date
        Period period = new Period(birthday, now, PeriodType.yearMonthDay());

        System.out.println("days are"+period.getDays());
        System.out.println("months are"+period.getMonths());
        System.out.println("years are" + period.getYears());
        age = period.getYears();
        setAge(age);
        return age;
    }
    //validates the age of the user
    public boolean validateAge(){

        boolean valid = true;
        if( age<18){
            birthday.setError("You must be at least 18 years old to Sign Up");

            valid = false;
        }
        else{
            birthday.setError(null);
        }
        return valid;
    }





    public void spinnerQuestion() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerQuestion);
        //   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSecretQuestions = (Spinner)findViewById(R.id.spinnerSecretQuestion);
        spinnerSecretQuestions.setAdapter(dataAdapter);
        spinnerSecretQuestions.setOnItemSelectedListener(this);


    }


    public void spinner_methodGender(){
        genSpinner = (Spinner) findViewById(R.id.gender_spinner_register);
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
        eduSpinner = (Spinner) findViewById(R.id.edu_spinner_register);
        eduSpinner.setOnItemSelectedListener(this);
        List<String> eduSpinnerA = new ArrayList<String>();



        for (String countryCode : EDUCATION) {


            eduSpinnerA.add(countryCode);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eduSpinnerA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        eduSpinner.setAdapter(adapter);



    }

    public void spinnerCountries() {

        nationalityS = (Spinner) findViewById(R.id.Nationality);
        nationalityS.setOnItemSelectedListener(this);

        spinCountry = (Spinner) findViewById(R.id.country_register);
        spinCity = (Spinner) findViewById(R.id.city_register);
        spinCountry.setOnItemSelectedListener(this);
        spinCity.setOnItemSelectedListener(this);


        List<String> countriesSpinner = new ArrayList<String>();

        String[] locales = Locale.getISOCountries();

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
        spinCountry.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item




        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.Nationality)
        {
            String item = parent.getItemAtPosition(position).toString();
            setNationality(item);

        }
        else if(spinner.getId() == R.id.spinnerSecretQuestion)
        {
            String itemQ = parent.getItemAtPosition(position).toString();
            setQuestion(itemQ);

        }
        else if(spinner.getId() == R.id.edu_spinner_register)
        {

            String edu = parent.getItemAtPosition(position).toString();
            setEducation(edu);
        }
        else if(spinner.getId() == R.id.gender_spinner_register)
        {

            String gender = parent.getItemAtPosition(position).toString();
            setGender(gender);
        }
        else if (spinner.getId() == R.id.city_register)
        {
            String city = parent.getItemAtPosition(position).toString();
            setCity(city);

        }
        else if (spinner.getId() == R.id.country_register)
        {
            String country = parent.getItemAtPosition(position).toString();
            setCountry(country);

            findCity(spinCountry.getSelectedItem().toString());

        }



        // Showing selected spinner item
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public void findCity(String country) {
        cityItems = new ArrayList<>();
        if (country.equals(spinCountry.getSelectedItem().toString()) ) {
            try {

                JSONObject cities = new JSONObject(loadJSONFromAsset());
                JSONArray cityArray = cities.getJSONArray(country);

                for (int i = 0; i < cityArray.length(); i++) {
                    cityItems.add(cityArray.getString(i));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Collections.sort(cityItems);
        cityAdp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityItems);
        cityAdp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCity.setAdapter(cityAdp);

    }



    public boolean checkNames() {

        editFirstName = (EditText) findViewById(R.id.txtFirstName);
        editLastName = (EditText) findViewById(R.id.txtLastName);
        boolean valid = true;

        String firstNameText = editFirstName.getText().toString();
        String lastNameText = editLastName.getText().toString();

        setName(firstNameText+" "+lastNameText);


        if (validateNames(firstNameText) == false) {
            editFirstName.setError("Name must be between 1 and 15 characters, using letters only.");


        }
        else{
            editFirstName.setError(null);
        }

        if (validateNames(lastNameText) == false) {

            editLastName.setError("Last name must be between 1 and 15 characters, using letters only.");

        }
        else{
            editLastName.setError(null);
        }

        return valid;

    }

    public boolean validateNames(String namesLength) {
        boolean valid = true;

        if (namesLength.isEmpty() || namesLength.length() < 1 || namesLength.length() > 16 || !namesLength.matches("[a-zA-Z]+")) {

            valid = false;
        }
        return valid;
    }

    public boolean checkPasswords() {

        boolean valid = false;

        password1 = (EditText) findViewById(R.id.passwordBoxSignup1);
        password2 = (EditText) findViewById(R.id.passwordBoxSignup2);
        signUpBtn = (Button) findViewById(R.id.btnSaveChanges);
        errorMessage = (TextView) findViewById(R.id.error_message);


        String password1Text = password1.getText().toString();
        String password2Text = password2.getText().toString();


        if (password1Text.equals(password2Text) && validatePassword(password1Text) == true) {


            setPassword(password1.getText().toString());


            valid = true;

        }

        else if (validatePassword(password1Text) == false) {
            password1.setError("Password must be between 4 and 20 alphanumeric characters");


        }

        else if (!password1Text.equals(password2Text) && validatePassword(password1Text) == false) {

            errorMessage.setText("Passwords do not match, Please check and try again.");
            password1.setError("Password must be between 4 and 20 characters, including numbers and letters.");

        }

        else {
            errorMessage.setText("Passwords do not match, Please check and try again.");

        }

        return valid;

    }


    public boolean validatePassword(String passwordLength) {
        boolean valid = true;

        if (passwordLength.isEmpty() || passwordLength.length() < 4 || passwordLength.length() > 20) {

            valid = false;
        }



        return valid;
    }

    public boolean validateEmail() {
        boolean valid = true;

        emailBox = (EditText) findViewById(R.id.emailBoxSignup);
        String email = emailBox.getText().toString();
        setEmail(email);


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailBox.setError("Enter a valid email address");

            valid = false;

        }
        else{
            emailBox.setError(null);
        }


        return valid;


    }

    public String computeMD5Hash(String password) {

        StringBuffer MD5Hash = new StringBuffer();
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }

            Log.d("The hash is: ", String.valueOf(MD5Hash));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return MD5Hash.toString();
    }
}

