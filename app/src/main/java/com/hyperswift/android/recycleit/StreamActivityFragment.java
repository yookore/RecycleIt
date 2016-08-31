package com.hyperswift.android.recycleit;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;
import com.hyperswift.android.recycleit.adapters.UserStreamAdapter;
import com.hyperswift.android.recycleit.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class StreamActivityFragment extends Fragment implements EndlessRecyclerView.Pager {
    public static final String TAG = StreamActivityFragment.class.getSimpleName();
    ArrayList<User> users;
    SwipeRefreshLayout swipeContainer;
    UserStreamAdapter userStreamAdapter;
    EndlessRecyclerView userStream;
    LinearLayoutManager linearLayoutManager;
    int threshold = 5;
    private boolean loading = false;
    private Handler handler = new Handler();

    public StreamActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFreshData();
            }
        });
        threshold = 5;
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        userStream = (EndlessRecyclerView) rootView.findViewById(R.id.stream_users);
        users = populateArray(0, 20);
        userStreamAdapter = new UserStreamAdapter(getActivity(), users);
        userStream.setAdapter(userStreamAdapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        userStream.setLayoutManager(linearLayoutManager);
        userStream.setThreshold(threshold);
        userStream.setProgressView(R.layout.item_progress);
        userStream.setPager(this);

        return rootView;
    }

    private void getFreshData() {
        Log.d(TAG, "adapter count:" + userStreamAdapter.getItemCount());
        userStreamAdapter.clear();
        Log.d(TAG, "adapter count:" + userStreamAdapter.getItemCount());
        users = populateArray(0, 20);
        userStreamAdapter.addAll(users);
        userStreamAdapter.notifyDataSetChanged();
        Log.d(TAG, "adapter count:" + userStreamAdapter.getItemCount());
        swipeContainer.setRefreshing(false);
    }

    private ArrayList<User> populateArray(int start, int end) {
        ArrayList<User> users = new ArrayList<>();
        String json;
        try {
            InputStream is = getActivity().getAssets().open("userdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            JSONArray usersArray = new JSONArray(json);
            for (int i = start; i < end; i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                User user = new User(userJson.getString("name"),
                        userJson.getString("profileurl"),
                        userJson.getBoolean("online"));
                    user.setType((i%2 == 0 ? "post" : "photo"));
                users.add(user);

            }
            return users;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean shouldLoad() {
        Log.d(TAG, "The last visible item position is: " + linearLayoutManager.findLastVisibleItemPosition());
        Log.d(TAG, String.valueOf(linearLayoutManager.getItemCount()));
        return !loading && linearLayoutManager.findLastVisibleItemPosition() + threshold > linearLayoutManager.getItemCount();
    }

    @Override
    public void loadNextPage() {
        loading = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                userStream.setRefreshing(false);
                loading = false;
                Log.d(TAG, "About to load more items");
                loadMoreItems();

            }
        },0);


        Log.d(TAG, "We have been called to supply data");
    }

    private void loadMoreItems() {
        int currentSize = linearLayoutManager.getItemCount();
        Log.d(TAG, "CURRENT SIZE: "+ currentSize);
        ArrayList<User> moreUsers = populateArray(currentSize, currentSize + 20);
        if (moreUsers != null) {
//            users.addAll(moreUsers);
            userStreamAdapter.addAll(moreUsers);
            userStreamAdapter.notifyDataSetChanged();
        }

    }
}
