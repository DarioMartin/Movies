package com.fasttrack.android.movies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasttrack.android.movies.R;
import com.fasttrack.android.movies.models.MovieVideos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 19/4/17.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {

    private ArrayList<MovieVideos.TMDBVideo> videos;

    public VideosAdapter() {
        this.videos = new ArrayList<>();
    }

    public void clearContent() {
        videos.clear();
    }


    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, final int position) {
        holder.setItem(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void addVideos(List<MovieVideos.TMDBVideo> videos) {
        this.videos.addAll(videos);
    }


    class VideoViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        VideoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.video_item_name);
        }

        void setItem(MovieVideos.TMDBVideo video) {
            name.setText(video.getName());
        }
    }
}
