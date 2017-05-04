package com.fasttrack.android.movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fasttrack.android.movies.adapters.VideosAdapter;
import com.fasttrack.android.movies.models.MovieVideos;

import java.util.List;

/**
 * Created by dariomartin on 17/4/17.
 */

public class VideosModule extends LinearLayout {

    private final static String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=";
    private static final String YOUTUBE = "YouTube";

    private LinearLayout videoList;
    private OnClickListener onVideoClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            if (!videos.isEmpty() && videos.size() > position) {
                MovieVideos.TMDBVideo video = videos.get(position);
                String baseUrl = null;
                switch (video.getSite()) {
                    case YOUTUBE:
                        baseUrl = YOUTUBE_VIDEO_URL;
                        break;
                }
                if (baseUrl != null) {
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl + videos.get(position).getKey())));
                }
            }
        }
    };

    private List<MovieVideos.TMDBVideo> videos;

    public VideosModule(Context context) {
        super(context);
        init(context);
    }

    public VideosModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.videos_module, this);
        videoList = (LinearLayout) findViewById(R.id.video_linear_layout);
    }

    public void addVideos(List<MovieVideos.TMDBVideo> videos) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        this.videos = videos;

        for (int i = 0; i < videos.size(); i++) {
            View item = inflater.inflate(R.layout.video_item, null, false);
            TextView videoName = (TextView) item.findViewById(R.id.video_item_name);
            videoName.setText(videos.get(i).getName());
            item.setTag(i);
            item.setOnClickListener(onVideoClickListener);
            videoList.addView(item);
        }
    }

}
