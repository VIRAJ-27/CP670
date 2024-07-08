package com.example.andriodassignments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static final int REQUEST_CODE_LIST_ITEMS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Finding the Button and we will set the onclick Listener
        Button buttonStartListItems = findViewById(R.id.button);
        buttonStartListItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here we are starting the ListItemsActivity and are expecting some result from it.
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_LIST_ITEMS);
            }
        });

        // Find the start chat button and using it we will navigate to Chat_window
        Button buttonStartChat = findViewById(R.id.start_button);
        buttonStartChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User clicked Start Chat");
                Intent intent = new Intent(MainActivity.this, Chat_window.class);
                startActivity(intent);
            }
        });

        // Find the Test Toolbar button and set the onClickListener
        Button buttonTestToolbar = findViewById(R.id.button_test_toolbar);
        buttonTestToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User clicked Test Toolbar");
                Intent intent = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(intent);
            }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LIST_ITEMS) {
            Log.i(TAG, "Returned to the MainActivity.onActivityResult");
            if (resultCode == RESULT_OK) {
                // Getting the Response Message from the ListItemsActivity
                String messagePassed = data.getStringExtra("Response");
                if (messagePassed != null) {
                    Toast.makeText(this, getString(R.string.list_items_passed) + messagePassed, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
