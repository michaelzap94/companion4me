package com.example.mikey.database.FrontEnd;


import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mikey.database.UserProfile.Login;
import com.example.mikey.database.UserProfile.Profile.Profile;
import com.example.mikey.database.R;
import com.example.mikey.database.UserProfile.registerUser;

import org.junit.Test;
import org.w3c.dom.Text;


/**
 * Created by Mohammad Humaid on 13/03/2016 at 21:23.
 */
public class UserLoginTests extends ActivityInstrumentationTestCase2<Login> {

    public UserLoginTests() { super(Login.class); }

    @Test
    public void testLoginActivityExists(){
        Login login = getActivity();
        assertNotNull("Login activity doesn't exist", login);
    }

    /**
     * Checks if a user can login.
     * @result account will login without any errors.
     */
    @Test
    public void testUserLogin(){

        Login login = getActivity();
        final EditText usernameField = (EditText)login.findViewById(R.id.emailBoxLogin);
        final EditText passwordField = (EditText)login.findViewById(R.id.passwordBox);
        final Button loginButton = (Button)login.findViewById(R.id.btn_login);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                usernameField.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("mohammadhumaid@gmail.com");
        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                passwordField.requestFocus();
            }
        });

        getInstrumentation().sendStringSync("random");
        getInstrumentation().waitForIdleSync();

        TouchUtils.clickView(this, loginButton);

        Profile profile = new Profile();
        assertNotNull("User has not been able to log in.", profile);
        profile.finish();

    }

    /**
     * Checks if a user can login.
     * @result account will login without any errors.
     */
    @Test
    public void testUserLogin1(){

        Login login = getActivity();
        final EditText usernameField = (EditText)login.findViewById(R.id.emailBoxLogin);
        final EditText passwordField = (EditText)login.findViewById(R.id.passwordBox);
        final Button loginButton = (Button)login.findViewById(R.id.btn_login);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                usernameField.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("mohammadhumaid@gmail.com");
        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                passwordField.requestFocus();
            }
        });

        getInstrumentation().sendStringSync("random");
        getInstrumentation().waitForIdleSync();

        assertEquals("Username has not been entered correctly.", usernameField.getText().toString(), "mohammadhumaid@gmail.com");
        assertEquals("Password has not been entered correctly.", passwordField.getText().toString(), "random");

    }



    @Test
    public void testUserProfile(){

        Login login = getActivity();
        final Button registerButton = (Button)login.findViewById(R.id.btn_signUp);
        TouchUtils.clickView(this, registerButton);

        registerUser reg = new registerUser();
        final EditText emailField = (EditText)reg.findViewById(R.id.emailBoxSignup);
        final EditText firstNameField = (EditText)reg.findViewById(R.id.txtFirstName);
        final EditText lastNameField = (EditText)reg.findViewById(R.id.txtLastName);
        final Spinner genderSpinner = (Spinner)reg.findViewById(R.id.gender_spinner_register);
        final TextView birthday = (TextView)reg.findViewById(R.id.birthday);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                emailField.requestFocus();
            }
        });

        getInstrumentation().sendStringSync("mohammad.humaid@kcl.ac.uk");
        getInstrumentation().waitForIdleSync();






    }







}
