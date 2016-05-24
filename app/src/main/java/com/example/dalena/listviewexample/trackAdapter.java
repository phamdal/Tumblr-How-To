package com.example.dalena.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Dalena on 5/23/2016.
 */
public class trackAdapter extends BaseAdapter{
    private Context mContext;
    private track[] mTracks;

    public trackAdapter(Context context, track[] tracks) {
        mContext = context;
        mTracks = tracks;
    }

    @Override
    public int getCount() {
        return mTracks.length;
    }

    @Override
    public Object getItem(int position) {
        return mTracks[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.track_item, null);
            holder = new ViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.trackName);
            holder.mCount = (TextView) convertView.findViewById(R.id.noteCount);
            holder.mPlays = (TextView) convertView.findViewById(R.id.postCount);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        track currentTrack = mTracks[position];
        holder.mTitle.setText(currentTrack.getTrackName());
        holder.mCount.setText(currentTrack.getNoteCount());
        holder.mPlays.setText(currentTrack.getPlayNumber());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTitle;
        TextView mCount;
        TextView mPlays;
    }
}
