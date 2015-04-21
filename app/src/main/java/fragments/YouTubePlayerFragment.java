package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.zari.matan.testapk.R;

import Configuration.Config;

/**
 * Created by Matan on 4/20/2015.
 */
public class YouTubePlayerFragment extends Fragment implements YouTubePlayer.OnInitializedListener {


    private YouTubePlayer YPlayer;
    private static final String YoutubeDeveloperKey = Config.YOU_TUBE_KEY;
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.youtube_player_layout, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        youTubePlayerFragment.initialize(YoutubeDeveloperKey, this);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();


        return rootView;

    }
    @Override
    public void onInitializationSuccess (YouTubePlayer.Provider provider, YouTubePlayer
            youTubePlayer,boolean b){
        if (!b) {
            youTubePlayer.setFullscreen(false);
            youTubePlayer.loadVideo("2zNSgSzhBfM");
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure (YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult){

    }
}
