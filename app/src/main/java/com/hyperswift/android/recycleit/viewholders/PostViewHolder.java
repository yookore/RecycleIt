package com.hyperswift.android.recycleit.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyperswift.android.recycleit.R;

/**
 * Created by jome on 2016/08/31.
 */
public  class PostViewHolder extends RecyclerView.ViewHolder {
    public ImageView userimage;
    public TextView username;
    public TextView postType;
    public Button userStatus;

    public PostViewHolder(View itemView) {
        super(itemView);
        postType = (TextView) itemView.findViewById(R.id.txt_type);
        userimage = (ImageView) itemView.findViewById(R.id.user_image);
        username = (TextView) itemView.findViewById(R.id.user_name);
        userStatus = (Button) itemView.findViewById(R.id.user_status);

    }
}
