package com.nehsus.placeMVIT.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nehsus.placeMVIT.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    private static final String TAG = RecyclerViewHolders.class.getSimpleName();

    public TextView profileHeader;

    public TextView profileContent;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        profileHeader = (TextView)itemView.findViewById(R.id.heading);
        profileContent = (TextView) itemView.findViewById(R.id.profile_content);
    }
}
