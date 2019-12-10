package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MediaPlayerDemo extends AppCompatActivity {
   Button b1,b2,b3,b4;
    MediaPlayer mediaPlayer;
    ImageView iv;

    double startTime = 0;
    double finalTime = 0;

    private Handler myHandler = new Handler();
    int forwardTime = 5000;
    int backwardTime = 5000;
    public static int oneTimeOnly = 0;

    TextView t1,t2,t3;

    SeekBar seekBar;
    String url = "https://cdn.unimedliving.com/production/audios/462/saying-I-love-you-what-does-that-mean-RU1805-6-010809v1n.mp3";
    Uri myUri = Uri.parse(url);
    boolean playPlus;
    boolean initialStage =true;

   Button playVideo;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_demo);

       /* playVideo = findViewById(R.id.playvideo);

       playVideo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                videoView = findViewById(R.id.videoview);
               Uri uri = Uri.parse("https://www.youtube.com/embed/avwlXWvGGWY?rel=0&amp;showinfo=0");
               *//*Intent intent = new Intent(Intent.ACTION_VIEW, uri);
               intent.setDataAndType(uri, "video/*");
                startActivity(intent);
               *//*
               videoView.setVideoURI(uri);
               videoView.setMediaController(new MediaController(MediaPlayerDemo.this));
                videoView.requestFocus();
               videoView.start();
           }
       });*/

        b1 = findViewById(R.id.mpbutton);
        b2 = findViewById(R.id.mpbutton2);
        b3 = findViewById(R.id.mpbutton3);
        b4 = findViewById(R.id.mpbutton4);

        iv = findViewById(R.id.mpimageView);

        t1 = findViewById(R.id.mptextView2);
        t2 = findViewById(R.id.mptextView3);
        t3 = findViewById(R.id.mptextView4);
        t3.setText("Song.mp3");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

       // mediaPlayer = (MediaPlayer) MediaPlayer.create(this,myUri);

        seekBar = findViewById(R.id.mpseekBar);
        seekBar.setClickable(false);

        b2.setEnabled(false);

        b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if(!playPlus){
                    if(initialStage){
                        new Player().execute(url);

                        Toast.makeText(MediaPlayerDemo.this, "Playing Sound", Toast.LENGTH_SHORT).show();


                        b2.setEnabled(true);

                    } else {
                        if(!mediaPlayer.isPlaying()){
                            mediaPlayer.start();

                        }
                        playPlus = true;
                    }
                } else {
                    if(mediaPlayer != null){
                        if (mediaPlayer.isPlaying())
                            mediaPlayer.pause();
                        playPlus = false;

                        mediaPlayer.start();

                        finalTime = mediaPlayer.getDuration();
                        startTime = mediaPlayer.getCurrentPosition();

                        if (oneTimeOnly == 0) {
                            seekBar.setMax((int) finalTime);
                            oneTimeOnly = 1;
                        }

                        t2.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                                finalTime)))
                        );

                        t1.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                                startTime)))
                        );

                        seekBar.setProgress((int) startTime);
                        myHandler.postDelayed(UpdateSongTime,100);
                        b2.setEnabled(true);
                        b3.setEnabled(false);
                    }

                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Toast.makeText(MediaPlayerDemo.this, "Pausing sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //updarte the progress depending on the seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            if(mediaPlayer != null){
                startTime = mediaPlayer.getCurrentPosition();
                t1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes((long) startTime)))
                );
                seekBar.setProgress((int)startTime);
                myHandler.postDelayed(this, 100);
            }

        }
    };


    class Player extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progress;

        @Override
        protected Boolean doInBackground(String... params) {

            Boolean prepared;
            try {

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(params[0]);

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        initialStage = true;
                        playPlus=false;

                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
                prepared = false;
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            super.onPostExecute(result);
            if (progress.isShowing()) {
                progress.cancel();
            }
            Log.d("Prepared", "//" + result);
            mediaPlayer.start();

            initialStage = false;
        }

        public Player() {
            progress = new ProgressDialog(MediaPlayerDemo.this);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            this.progress.setMessage("Buffering...");
            this.progress.show();

        }
    }

    @Override
    protected void onPause() {

        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }




   /* public void gone(View v){
         videoView.setZOrderOnTop(true);
        View placeholder = (View) findViewById(R.id.placeholder);

        placeholder.setVisibility(View.GONE);
        videoView.start();
    }*/


}
