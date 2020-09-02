package com.luismiguelsoto.flayer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.luismiguelsoto.flayer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongFinderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongFinderFragment extends Fragment {

    private static final String FIRST_ARGUMENT = "param1";
    private static final String SECOND_ARGUMENT = "param2";

    private String param1;
    private String param2;

    public SongFinderFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SongFinderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SongFinderFragment newInstance(String param1, String param2) {
        SongFinderFragment fragment = new SongFinderFragment();
        Bundle args = new Bundle();
        args.putString(FIRST_ARGUMENT, param1);
        args.putString(SECOND_ARGUMENT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getString(FIRST_ARGUMENT);
            param2 = getArguments().getString(SECOND_ARGUMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_finder, container, false);
    }
}