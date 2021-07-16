package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class playerActivity extends AppCompatActivity {

    Button btnPlay, btnNext , btnPrevious , btnFastForward, btnFastBackward;
    TextView txtSongName, txtSongStart, txtSongEnd;
    SeekBar seekMusicBar;

    ImageView imageView;

    String songName;

    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer mediaPlayer;

    int position;
    ArrayList<File> mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnPlay = findViewById(R.id.btnPlay);
        btnFastForward = findViewById(R.id.btnFastForward);
        btnFastBackward = findViewById(R.id.btnFastBackward);

        txtSongName = findViewById(R.id.txtSong);
        txtSongStart = findViewById(R.id.txtSongStart);
        txtSongEnd = findViewById(R.id.txtSongEnd);
        seekMusicBar = findViewById(R.id.seekBar);

        imageView = findViewById(R.id.imgView);

        if(mediaPlayer != null){
            mediaPlayer.start();
            mediaPlayer.release();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mySongs = (ArrayList)bundle.getParcelableArrayList("songs");
        String sName = intent.getStringExtra("songname");
        position = bundle.getInt("pos", 0 );
        txtSongName.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(position).toString());
        songName = mySongs.get(position).getName();
        txtSongName.setText(songName);

        mediaPlayer = MediaPlayer.create(getApplicationContext() ,uri);
        mediaPlayer.start();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    btnPlay.setBackgroundResource(R.drawable.ic_play);
                    mediaPlayer.pause();
                }
                else{
                    btnPlay.setBackgroundResource(R.drawable.ic_pause);
                    mediaPlayer.start();
                    TranslateAnimation moveAnim = new TranslateAnimation(-25, 25,-25,25);
                    moveAnim.setInterpolator(new AccelerateInterpolator());
                    moveAnim.setDuration(600);
                    moveAnim.setFillEnabled(true);
                    moveAnim.setFillAfter(true);
                    moveAnim.setRepeatMode(Animation.REVERSE);
                    moveAnim.setRepeatCount(1);
                    imageView.startAnimation(moveAnim);
                }
            }
        });
        
    }
}