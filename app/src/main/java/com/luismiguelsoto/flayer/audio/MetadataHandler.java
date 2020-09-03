package com.luismiguelsoto.flayer.audio;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.luismiguelsoto.flayer.models.Song;
import com.luismiguelsoto.flayer.utils.FileUtils;

import java.io.IOException;

public class MetadataHandler {

    /**
     * Class attributes
     * */
    public enum SONG_METADATA {
        TITLE(MediaMetadataRetriever.METADATA_KEY_TITLE),
        ALBUM(MediaMetadataRetriever.METADATA_KEY_ALBUM),
        ARTIST(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        int value;
        SONG_METADATA(int value) {
            this.value = value;
        }
    }
    private static MetadataHandler singleton;

    /**
     * Constructor
     * */
    private MetadataHandler(){}

    /**
     * Other methods
     * */
    @Nullable
    public String get(SONG_METADATA dataType, Song song, Context context) {
        switch (dataType) {
            case TITLE:
                return getTitle(song, context);
            case ALBUM:
                return "None";
            case ARTIST:
                return "Unknown";
            default: return null;
        }
    }
    private String getTitle(Song song, Context context) {
        MediaMetadataRetriever retriever = null;
        String title = "Unknown";

        try {
            // Reset the name to the filename.
            title = song.getFile().getName().replace("." + FileUtils.getExtension(song.getFile()),"");

            // Try and get the song's title via the metadata.
            retriever = new MediaMetadataRetriever();

            retriever.setDataSource(context, Uri.parse(song.getFile().getAbsolutePath()));

            if (FileUtils.isPlayable(song.getFile())) {
                String newTitle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

                if (newTitle != null)
                    title = newTitle;
            }

        } catch (IOException ioException) {
            Log.e("IOException", "occurred while trying to read from the file \"" + song.getFile().getName() + "\". Message: " + ioException.getMessage());
            ioException.printStackTrace();
        } catch (RuntimeException runtimeException) {
            Log.e("RuntimeException", "occurred while reading metadata from a file \"" + song.getFile().getName() + "\". Message: " + runtimeException.getMessage());
        } finally {
            if (retriever != null)
                retriever.release();
        }

        return title;
    }

    /**
     * Returns the unique instance of the handler.
     * @return MetadataHandler
     * */
    public static MetadataHandler getInstanceOf() {
        if (singleton == null)
            singleton = new MetadataHandler();

        return singleton;
    }
}
