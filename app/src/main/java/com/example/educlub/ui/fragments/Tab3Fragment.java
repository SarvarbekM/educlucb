package com.example.educlub.ui.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.educlub.R;
import com.example.educlub.ui.fragments.base.BaseFragment;
//import com.example.studycenter.R;
//import com.example.studycenter.ui.fragments.base.BaseFragment;

public class Tab3Fragment extends BaseFragment {
    VideoView simpleVideoView;
    MediaController mediaControls;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3_index_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Uri uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        Uri uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4");
        simpleVideoView = (VideoView)view.findViewById(R.id.simpleVideoView);

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(getContext());
            mediaControls.setAnchorView(simpleVideoView);
        }
        // set the media controller for video view
        simpleVideoView.setMediaController(mediaControls);
        // set the uri for the video view
        simpleVideoView.setVideoURI(uri);
        // start a video
        //simpleVideoView.start();

        // implement on completion listener on video view
        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        simpleVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }
}
