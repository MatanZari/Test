package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zari.matan.testapk.R;

import java.util.ArrayList;

/**
 * Created by Matan on 4/19/2015.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private Activity activity;
    private CallActivity callActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_layout, container, false);
        activity = getActivity();
        if (activity instanceof CallActivity){
           callActivity = (CallActivity) activity;
           callActivity.getLayout(v);
        }
        return v;
    }


    public interface CallActivity{
        public void getLayout(View v);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.feed_list);
    }
}