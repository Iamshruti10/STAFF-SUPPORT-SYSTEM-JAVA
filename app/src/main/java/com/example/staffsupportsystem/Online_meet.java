package com.example.staffsupportsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//import org.jitsi.meet.sdk.JitsiMeetActivity;
//import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
//
//import java.net.MalformedURLException;
//import java.net.URL;

public class Online_meet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_meet);
//        try {
//            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
//                    .setServerURL(new URL(""))
//                    .setWelcomePageEnabled(false)
//                    .build();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onButtonClick(View v) {
//        EditText editText = findViewById(R.id.conferenceName);
//        String text = editText.getText().toString();
//        if (text.length() > 0) {
//            JitsiMeetConferenceOptions options
//                    = new JitsiMeetConferenceOptions.Builder()
//                    .setRoom(text)
//                    .build();
//            JitsiMeetActivity.launch(this, options);
//        }
    }

}