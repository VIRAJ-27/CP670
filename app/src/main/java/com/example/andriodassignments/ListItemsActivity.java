package com.example.andriodassignments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class ListItemsActivity extends AppCompatActivity {

    // Defining the Constants and Variables that will be used
    private static final String TAG = "ListItemsActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        // Here we are Finding the Image button on our UI and will set a onclick listener activity
        imageButton = findViewById(R.id.imageButton);
        // Below will check the permission for the Camera
        imageButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                openCamera();
            }
        });

        // Here we are finding the Switch button and are setting the on checked Listener
        Switch switchButton = findViewById(R.id.switch1);
        // Setting the Toast on whether the Switch is used or Not
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;

                if (isChecked) {
                    text=getString(R.string.switch_on);
                    duration = Toast.LENGTH_SHORT;
                } else {
                    text = getString(R.string.switch_off);
                    duration = Toast.LENGTH_LONG;
                }

                Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                toast.show();
            }
        });

        // Similarly we are doing this for Check Box Too
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // A dialog box will be Shown whe the activity is finished i.e when the checkbox is checked a dialog box will appear
                if (isChecked) {
                    showFinishDialog();
                }
            }
        });



        Log.i(TAG, "inside onCreate");
    }

    // This is a method to Open Camera
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isCameraAvailable()) {
            Log.i(TAG, "Camera activity resolved");
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Log.e(TAG, "No activity found to handle the camera intent");
        }
    }

    // Method foe checking the camera is available or not
    private boolean isCameraAvailable() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> availableActivities = getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
        return !availableActivities.isEmpty();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Log.e(TAG, "Camera permission denied");
            }
        }
    }

    // Below part is use to set the image that is clicked by the camera on the image button
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                /// Get the thumbnail of the image and set it to the ImageButton
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageButton.setImageBitmap(imageBitmap);
                print("Image captured and set on ImageButton");
            }
        }

    }

    // Below method is used to show the Toast message
    private void print(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(ListItemsActivity.this, message, duration);
        toast.show();
    }

    // Below part is used to to show dialog box o/p To user
    private void showFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If the user clicks OK button, then the activity is finished
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("Response", getString(R.string.response_message));
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .show();
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
}
