package com.hyperswift.android.recycleit.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyperswift.android.recycleit.R;
import com.hyperswift.android.recycleit.models.User;
import com.hyperswift.android.recycleit.viewholders.UserViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jome on 2016/08/30.
 */
public class UserStreamAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = UserStreamAdapter.class.getSimpleName();
    private List<User> usersList;
    private Context mContext;

    public UserStreamAdapter(Context context, List<User> users) {
        usersList = users;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.user_card, parent, false);
        ViewHolder viewHolder = new UserViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = usersList.get(position);

        UserViewHolder userViewHolder = (UserViewHolder) holder;
        configureUserViewHolder(user, userViewHolder);
    }

    private void configureUserViewHolder(User user, UserViewHolder userViewHolder) {
        TextView usernameTextView = userViewHolder.username;
        ImageView userImage = userViewHolder.userimage;
        String imageUrl = user.getProfileurl();
        Log.i(TAG, imageUrl);
        Picasso.with(mContext).load(user.getProfileurl()).placeholder(R.drawable.ic_user_placeholder).fit().into(userImage);
        Button userStatus = userViewHolder.userStatus;
        usernameTextView.setText(user.getName());
        if(user.isOnline()){
            userStatus.setText("ONLINE");
        }else{
            userStatus.setText("OFFLINE");
        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void clear(){
        usersList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<User> users){
        usersList.addAll(users);
        notifyDataSetChanged();
    }
}
