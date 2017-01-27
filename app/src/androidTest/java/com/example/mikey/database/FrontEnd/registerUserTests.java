package com.example.mikey.database.FrontEnd;

import android.media.effect.Effect;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mikey.database.R;
import com.example.mikey.database.UserProfile.registerUser;

import junit.framework.TestResult;

import org.junit.Test;

/**
 * Created by Mohammad Humaid on 24/03/2016 at 17:50.
 */
public class registerUserTests extends ActivityInstrumentationTestCase2<registerUser> {
    public registerUserTests() { super(registerUser.class); }

    @Test
    public void testActivity() {
        registerUser reg = getActivity();
        assertNotNull("Activity is not running.", reg);
    }

    @Test
    public void testRegistration() {
        registerUser reg = getActivity();

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

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                firstNameField.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("Mohammad");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                lastNameField.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("Humaid");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                genderSpinner.requestFocus();
                genderSpinner.setSelection(1);
            }
        });

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                birthday.requestFocus();
                birthday.setText("19/07/1995");
            }
        });
        reg.setAge(20);
        getInstrumentation().waitForIdleSync();

        final Button btn1Next = (Button)reg.findViewById(R.id.btn1Next);
        TouchUtils.clickView(this, btn1Next);

        final Spinner nationalitySpinner = (Spinner)reg.findViewById(R.id.Nationality);
        final Spinner countrySpinner = (Spinner)reg.findViewById(R.id.country_register);
        final Spinner citySpinner = (Spinner)reg.findViewById(R.id.city_register);
        final Spinner educationSpinner = (Spinner)reg.findViewById(R.id.edu_spinner_register);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nationalitySpinner.requestFocus();
                nationalitySpinner.setSelection(10);
            }
        });

        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                countrySpinner.requestFocus();
                countrySpinner.setSelection(10);
            }
        });

        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                citySpinner.requestFocus();
                citySpinner.setSelection(10);
            }
        });

        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                educationSpinner.requestFocus();
                educationSpinner.setSelection(2);
            }
        });

        getInstrumentation().waitForIdleSync();

        final Button btn2Next = (Button)reg.findViewById(R.id.btn2Next);
        TouchUtils.clickView(this, btn2Next);

        final EditText password1 = (EditText)reg.findViewById(R.id.passwordBoxSignup1);
        final EditText password2 = (EditText)reg.findViewById(R.id.passwordBoxSignup2);
        final Spinner secretSpinner = (Spinner)reg.findViewById(R.id.spinnerSecretQuestion);
        final EditText answerInput = (EditText)reg.findViewById(R.id.passwordBoxSignup1);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                password1.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("random");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                password2.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("random");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                secretSpinner.requestFocus();
                secretSpinner.setSelection(1);
            }
        });

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                answerInput.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("Rabbit");





    }
}
