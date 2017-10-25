package com.example.athreyaanand.insider;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TwoFragment extends Fragment{

    MediaPlayer mediaPlayer;
    public static StoreItem [] itemList = {new StoreItem(R.drawable.ic_launcher, "Horror Pack", "This is a description! WOah woah woah wooooo"), new StoreItem(R.drawable.ic_launcher, "School Pack", "This is a description! WOah woah woah wooooo"), new StoreItem(R.drawable.ic_launcher, "Halloween Pack", "This is a description! WOah woah woah wooooo"), new StoreItem(R.drawable.ic_launcher, "Christmas Pack", "This is a description! WOah woah woah wooooo"), new StoreItem(R.drawable.ic_launcher, "Superhero Pack", "This is a description! WOah woah woah wooooo"), new StoreItem(R.drawable.ic_launcher, "Cool Kids Pack", "This is a description! WOah woah woah wooooo")};

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ListView lv = getView().findViewById(R.id.lv);
        lv.setAdapter(new StoreAdapter(getActivity(), itemList));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.store_music);
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store , container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // Make sure that we are currently visible
        if (this.isVisible()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!isVisibleToUser) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mediaPlayer.pause();
        }
    }

}

