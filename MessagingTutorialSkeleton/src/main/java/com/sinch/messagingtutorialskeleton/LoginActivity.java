package com.sinch.messagingtutorialskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messagingtutorialskeleton.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends Activity {

    Button loginButton;
    Button signUpButton;
    EditText usernameField;
    EditText passwordField;
    String username;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Nkz5LmkPNqzHulLStWpWBPqKadCgsA3wfnwobds5", "NRnkGhDWaxv6t3zJi6XezdBiJNNx5LF78PSA3ylv");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        foo();
    }

    public void foo(){

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            final Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
            final Intent serviceIntent = new Intent(getApplicationContext(), MessageService.class);
            startActivity(intent);
            startService(serviceIntent);
        }

        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);
        usernameField = (EditText) findViewById(R.id.loginUsername);
        passwordField = (EditText) findViewById(R.id.loginPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            final Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
                            final Intent serviceIntent = new Intent(getApplicationContext(), MessageService.class);
                            startActivity(intent);
                            startService(serviceIntent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error logging in.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            //start next activity
                            //start sinch service
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error signing up."
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
