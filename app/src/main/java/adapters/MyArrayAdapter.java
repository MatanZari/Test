package adapters;

import android.content.Context;
import android.os.Handler;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.zari.matan.testapk.Item;
import com.zari.matan.testapk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 4/20/2015.
 */
public class MyArrayAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> data;
    private int resource;
    private static boolean isOnPause = false;
    private static int currentTime = 0;
    private Handler mHandler = new Handler();

    public MyArrayAdapter(Context context, int resource, ArrayList<Item> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        VideoView videoView;
        final ImageView imageView;
        final ViewHolder viewHolder;
        if (convertView ==null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resource, null, false);
            videoView = (VideoView) convertView.findViewById(R.id.video);
            imageView = (ImageView) convertView.findViewById(R.id.image);
            ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.imageButton2);
            viewHolder = new ViewHolder();
            viewHolder.videoView = videoView;
            viewHolder.imageView = imageView;
            viewHolder.imageButton = imageButton;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.get(position).thumbnail).into(viewHolder.imageView);

        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewHolder.videoView.isPlaying()) {
                    v.setVisibility(View.GONE);
                    viewHolder.imageView.setVisibility(View.GONE);
                    viewHolder.videoView.setVisibility(View.VISIBLE);
                    viewHolder.videoView.setVideoPath(data.get(position).videoUrl);
                    if (isOnPause)
                        viewHolder.videoView.seekTo(currentTime);
                    viewHolder.videoView.start();
                }else{

                }

            }
        });
        viewHolder.videoView.setOnClickListener(new VideoView.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTime = ((VideoView)v).getCurrentPosition();
                isOnPause = true;
                viewHolder.imageButton.setVisibility(View.VISIBLE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
                ((VideoView)v).setVisibility(View.GONE);
                ((VideoView)v).pause();

            }
        });
        viewHolder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isOnPause) {
                    isOnPause = true;
                    if ( viewHolder.videoView.isPlaying()) {
                        viewHolder.imageButton.setVisibility(View.VISIBLE);
                        viewHolder.imageView.setVisibility(View.VISIBLE);
                        ((VideoView)v).setVisibility(View.GONE);
                        viewHolder.videoView.pause();
                        currentTime = ((VideoView)v).getCurrentPosition();
                    } else {
                        ((VideoView)v).seekTo(currentTime);
                        ((VideoView)v).start();
                        isOnPause = false;

                    }
                    mHandler.postDelayed(new Runnable() {
                       public void run() {
                            isOnPause = false;
                        }
                    }, 100);
                }
                return true;
            }
        });
        return convertView;
    }

    public class ViewHolder{
        VideoView videoView;
        ImageView imageView;
        ImageButton imageButton;
    }


}
