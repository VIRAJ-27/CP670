package com.example.andriodassignments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivityLogs";
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String EMAIL_KEY = "DefaultEmail";

    protected EditText emailedittext;
    protected EditText passwordedittext;
    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Finding the Text and the Login button as well as password
        emailedittext = findViewById(R.id.editTextText);
        passwordedittext = findViewById(R.id.editTextText2);
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Button loginbutton = findViewById(R.id.button3);

        // we will be using the shared preferences and will store the email
        String storedEmail = sharedPreferences.getString(EMAIL_KEY, "email@domain.com");
        emailedittext.setText(storedEmail);// The stored email will be reflected on the edittext(email text) i.e where the email is asked

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndProceed(); // Validate email and password before proceeding
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.i(TAG, "inside onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "inside onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "inside onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "inside onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "inside onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "inside onDestroy");
    }

    void validateAndProceed() {
        // Get the text entered by the user and convert it to a string
        String email = emailedittext.getText().toString();
        String password = passwordedittext.getText().toString();

        // Validate the email format and check if the password is empty
        if (!isValidEmail(email)) {
            emailedittext.setError(getString(R.string.invalid_email));
        } else if (password.isEmpty()) {
            passwordedittext.setError(getString(R.string.empty_password));
        } else {
            saveEmailAndProceed(email);
        }
    }

    protected boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    protected void saveEmailAndProceed(String email) {
        // Saving the email to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.apply(); // Applying the changes

        // Creating a new Intent to Start the Main Activity after the Login Activity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
