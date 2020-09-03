package com.luismiguelsoto.flayer.audio;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.luismiguelsoto.flayer.models.Song;

import java.io.IOException;

public class PlayerService {

    private static PlayerService player;
    private static MediaPlayer playerService;
    private static MediaMetadataRetriever metadataProcessor;
    private Song playingSong;

    /**
     * PlayerService private constructor
     * */
    private PlayerService() {
        playingSong = null;

        playerService = new MediaPlayer();
        playerService.setVolume(0.5f, 0.5f);
        playerService.setLooping(false);
        playerService.setOnCompletionListener(mediaPlayer -> Log.i("MediaPlayer: ", "song completed"));
        playerService.setOnPreparedListener(MediaPlayer::start);

        metadataProcessor = new MediaMetadataRetriever();
    }

    /**
     * Getters
     * */
    public boolean isPlaying() {
        return playerService.isPlaying();
    }

    public static MediaPlayer getPlayerService() {
        return playerService;
    }

    /**
     * Other methods
     * */
    public static PlayerService getInstanceOf() {
        if (player == null)
            player = new PlayerService();

        return player;
    }

    private void play(Song song, Context context) {
        try {

            String filePath = "file://" + song.getFile().getAbsolutePath();
            metadataProcessor.setDataSource(context, Uri.parse(filePath));

            Log.i("PlayerService: ", "playing new song (" +  song.getFile().getName() + ")");

            playerService.setDataSource(filePath);
            playerService.prepareAsync();

            this.playingSong = song;

        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void playFile(Song song, Context context){
        try {

            if (playerService.isPlaying()) {
                // Currently playing stuff

                if (playingSong.getFile().getAbsolutePath().equals(song.getFile().getAbsolutePath())) {
                    // Same file requested, refusing new song request.
                    Log.i("PlayerService: ", "request refused, already playing song.");
                    return;
                }

                // Stop current song
                playerService.stop();
                playerService.reset();
                Log.i("PlayerService: ", "stopped playing current song (" +  this.playingSong.getFile().getName() + ").");
                this.playingSong = null;

            }
            // Play new song
            this.play(song, context);

        } catch (IllegalStateException ise) {
            Log.e("IllegalStateException occurred: ", "while trying to play a new file: " + ise.getMessage());
            ise.printStackTrace();
        }
    }
}
