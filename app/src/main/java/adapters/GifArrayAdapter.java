package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zari.matan.testapk.R;

import java.util.ArrayList;
import java.util.List;

import images.ShowGifView;

/**
 * Created by Matan on 4/21/2015.
 */
public class GifArrayAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> data;
    public GifArrayAdapter(Context context, int resource, ArrayList<String> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gif_list_layout,null,false);
            ShowGifView showGifView = (ShowGifView) convertView.findViewById(R.id.gif);
            viewHolder = new ViewHolder();
            viewHolder.showGifView = showGifView;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.showGifView = new ShowGifView(context,data.get(position));
        return convertView;
    }

    public class ViewHolder{
        ShowGifView showGifView;
    }
}
