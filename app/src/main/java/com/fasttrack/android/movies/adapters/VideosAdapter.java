package com.fasttrack.android.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.MovieVideos;

import java.util.List;

/**
 * Created by dariomartin on 19/4/17.
 */

public class VideosAdapter extends ArrayAdapter<MovieVideos.TMDBVideo> {

    public VideosAdapter(Context context, List<MovieVideos.TMDBVideo> videos) {
        super(context, 0, videos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieVideos.TMDBVideo video = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.video_item, parent, false);
        }

        TextView videoName = (TextView) convertView.findViewById(R.id.video_item_name);
        videoName.setText(video.getName());
        return convertView;
    }

}
