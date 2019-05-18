package com.inducesmile.androidpayexample.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.inducesmile.androidpayexample.OnBackPressed;
import com.inducesmile.androidpayexample.PlayerConfig;
import com.inducesmile.androidpayexample.R;
import com.inducesmile.androidpayexample.ShoppingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements OnBackPressed {


    YouTubePlayerView youTubePlayerView;
    Button button;


    YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list_item);

        /* button=(Button) getView().findViewById(R.id.button);

        youTubePlayerView=(YouTubePlayerView) getView().findViewById(R.id.youtube_player_view);*/

        onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("LHeSaTQXiBk");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }


        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);
            }
        });


    }



    private void setContentView(int video_list_item) {
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }
}
