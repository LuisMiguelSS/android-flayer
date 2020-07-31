package com.luismiguelsoto.flayer.utils;

import com.luismiguelsoto.flayer.models.Song;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
    public static final String[] PLAYABLE_FORMATS = { "mp3", "avi"};

    /**
     * Gets the file extension for the given file. (Shortcut method)
     * 
     * @param file The file to get the extension from.
     * @return The extension without the dot (.).
     * @throws IOException When the provided file is a directory.
     * */
    public static String getExtension(File file) throws IOException {
        if (file.isFile())
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);

        throw new IOException("The provided file is a directory.");
    }

    /**
     * Checks whether a given file could be played by the {@link android.media.MediaPlayer}.
     *
     * @param file The file to check.
     * @return True if it is playable, false if not.
     * @throws IOException Rethrown from {@link FileUtils#getExtension(File)}.
     * */
    public static boolean isPlayable(File file) throws IOException {
        String fileExtension = getExtension(file);

        for (String format : PLAYABLE_FORMATS) {
            if (fileExtension.equalsIgnoreCase(format))
                return true;
        }

        return false;
    }

    /**
     * Gets the files from the given directory which comply with {@link FileUtils#isPlayable(File)}.
     *
     * @param directory A file indicating the folder to search from.
     * @return Song[] An array of songs contained in the given folder and sub-folders.
     * @throws IOException If the given file is not a directory.
     * */
    public static Song[] getSongsFromDir(File directory) throws IOException {

        if (!directory.isDirectory())
            throw new IOException("The provided file is not a directory.");

        File[] listAllFiles = directory.listFiles();
        ArrayList<Song> result = new ArrayList<>();

        if (listAllFiles != null && listAllFiles.length > 0) {
            for (File currentFile : listAllFiles) {

                if (currentFile.exists()) {
                    if (currentFile.isDirectory())
                        getSongsFromDir(currentFile);
                    else if (isPlayable(currentFile))
                        result.add(new Song(currentFile));
                }

            }
        }

        Song[] resultSongs = new Song[result.size()];
        resultSongs = result.toArray(resultSongs);

        return resultSongs;
    }

}
