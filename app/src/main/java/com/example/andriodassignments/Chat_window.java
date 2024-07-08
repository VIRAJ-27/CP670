package com.example.andriodassignments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

public class Chat_window extends AppCompatActivity {

    // Defining the constants and the variables
    private static final String TAG = "ChatWindowActivity";
    private ListView chatlistview;
    private EditText chat_input;
    private Button send_button;
    private ChatAdapter chat_adapter;
    private ChatManager chatManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "inside onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_window);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Enable the up button for navigation
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Initializing the views by finding them in the XML layout
        chatlistview = findViewById(R.id.chat_list_view);
        chat_input = findViewById(R.id.chat_input_text);
        send_button = findViewById(R.id.chat_send_button);

        // Initialize the ChatManager and Adapter
        chatManager = new ChatManager();
        chat_adapter = new ChatAdapter(this, chatManager.getChatMessages());
        chatlistview.setAdapter(chat_adapter);

        // Setting an OnClickListener for the send button
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting the message from the input field
                String message = chat_input.getText().toString();
                // If the message is not empty, add it to the list and update the adapter
                if (!message.isEmpty()) {
                    chatManager.addMessage(message);
                    chat_adapter.notifyDataSetChanged(); // Notify the adapter to refresh the ListView
                    chat_input.setText(""); // Clear the input field
                    Log.i(TAG, "Message sent: " + message);
                }
            }
        });
    }

    // Inner class for custom ArrayAdapter to handle Chat messages
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx, ArrayList<String> messages) {
            super(ctx, 0, messages);
        }

        @Override
        public int getCount() {
            return chatManager.getChatMessages().size(); // Returns the number of messages
        }

        @Override
        public String getItem(int position) {
            return chatManager.getChatMessages().get(position); // Returns the message at the specified position
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Determine the appropriate layout based on the position
            int layoutId = (position % 2 == 0) ? R.layout.chat_row_outgoing : R.layout.chat_row_incoming;
            if (convertView == null || !layoutIdEqualsTag(convertView, layoutId)) {
                convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
                convertView.setTag(layoutId);
            }

            // Get the message at the current position
            String message = getItem(position);
            // Find the TextView in the inflated view and set its text to the message
            TextView messageText = convertView.findViewById(R.id.message_text);
            messageText.setText(message);
            return convertView;
        }

        private boolean layoutIdEqualsTag(View convertView, int layoutId) {
            Object tag = convertView.getTag();
            return tag instanceof Integer && (Integer) tag == layoutId;
        }
    }

    public static class ChatManager {
        private ArrayList<String> chatMessages;

        public ChatManager() {
            chatMessages = new ArrayList<>();
        }

        public void addMessage(String message) {
            if (message != null && !message.trim().isEmpty()) {
                chatMessages.add(message);
            }
        }

        public ArrayList<String> getChatMessages() {
            return chatMessages;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Respond to the action bar's Up/Home button
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
