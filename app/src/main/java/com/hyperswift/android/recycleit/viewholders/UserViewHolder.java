package com.hyperswift.android.recycleit.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyperswift.android.recycleit.R;

public  class UserViewHolder extends RecyclerView.ViewHolder {
    public ImageView userimage;
    public TextView username;
    public Button userStatus;

    public UserViewHolder(View itemView) {
        super(itemView);

        userimage = (ImageView) itemView.findViewById(R.id.user_image);
        username = (TextView) itemView.findViewById(R.id.user_name);
        userStatus = (Button) itemView.findViewById(R.id.user_status);
    }
}
