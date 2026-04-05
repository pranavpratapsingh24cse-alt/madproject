package com.example.q2_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        Uri uri = Uri.parse("https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(mp -> {
            mp.setVolume(1.0f, 1.0f);
        });

        Button openFileBtn = findViewById(R.id.openFileBtn);
        Button playBtn = findViewById(R.id.playBtn);
        Button pauseBtn = findViewById(R.id.pauseBtn);
        Button stopBtn = findViewById(R.id.stopBtn);
        Button restartBtn = findViewById(R.id.restartBtn);



        openFileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("video/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 1);
        });

        playBtn.setOnClickListener(v -> videoView.start());

        pauseBtn.setOnClickListener(v -> videoView.pause());

        stopBtn.setOnClickListener(v -> videoView.stopPlayback());

        restartBtn.setOnClickListener(v -> {
            if (videoUri != null) {
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            videoUri = data.getData();
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }
}