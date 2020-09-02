package com.luismiguelsoto.flayer.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.luismiguelsoto.flayer.R;
import com.luismiguelsoto.flayer.audio.MetadataHandler;
import com.luismiguelsoto.flayer.audio.PlayerService;
import com.luismiguelsoto.flayer.models.Song;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.luismiguelsoto.flayer.utils.FileUtils.getSongsFromDir;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongListFragment extends Fragment {

    /**
     * Class attributes
     * */

    private static final String FIRST_ARGUMENT = "param1";
    private static final String SECOND_ARGUMENT = "param2";

    private String param1;
    private String param2;

    private ListView songsListView;
    private List<Song> songList;

    /**
     * Needed constructor
     * */
    public SongListFragment() {
    }

    /**
     * Fragment methods
     * */

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SongListFragment.
     */
    public static SongListFragment newInstance(String param1, String param2) {
        SongListFragment fragment = new SongListFragment();
        Bundle args = new Bundle();
        args.putString(FIRST_ARGUMENT, param1);
        args.putString(SECOND_ARGUMENT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ArrayAdapter<String> getMusicListAdapter() {
        ArrayList<String> filenames = new ArrayList<>();

        try {
            File downloadDir = new File("/storage/emulated/0/Download/");

            Song[] songs = getSongsFromDir(downloadDir);
            this.songList = Arrays.asList(songs);

            for (Song song : songs)
                filenames.add(MetadataHandler.getInstanceOf().get(MetadataHandler.SONG_METADATA.TITLE, song, getContext()));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new ArrayAdapter<>(
                Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_list_item_1, filenames);
    }

    /**
     * Fragment Listeners
     * */
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

        View view = inflater.inflate(R.layout.fragment_song_list, container, false);

        songsListView = view.findViewById(R.id.current_songList);
        //currentSongList.setHasFixedSize(true);


        songsListView.setAdapter(getMusicListAdapter());
        songsListView.setOnItemClickListener((adapterView, viewArg, clickedItemIndex, arg3) -> {
            if (songsListView.getCount() > 0 && songList.size() > 0) {
                PlayerService.getInstanceOf().playFile(songList.get(clickedItemIndex), getContext());

                Log.e("Playable files in total: ", songList.size() + "");
                Log.e("Clicked item in list: ", songList.get(clickedItemIndex).getFile().getName());
            } else {
                Log.e("Song List Error: ", "The list has no files to play.");
            }
        });

        return view;
    }
}