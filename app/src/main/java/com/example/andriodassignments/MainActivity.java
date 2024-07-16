package com.example.andriodassignments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static final int REQUEST_CODE_LIST_ITEMS = 10;
    private TextView forecastTextView;
    private TextView progressTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        forecastTextView = findViewById(R.id.forecast_text_view);
        progressTextView = findViewById(R.id.progress_text_view);
        progressBar = findViewById(R.id.progress_bar);

        Button buttonWeather = findViewById(R.id.weather_button);
        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User clicked Weather Button");
                Intent intent = new Intent(MainActivity.this, WeatherForecast.class);
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
                String messagePassed = data.getStringExtra("Response");
                if (messagePassed != null) {
                    Toast.makeText(this, getString(R.string.list_items_passed) + messagePassed, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
