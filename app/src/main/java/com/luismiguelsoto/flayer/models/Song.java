package com.luismiguelsoto.flayer.models;

import androidx.annotation.Nullable;

import java.io.File;

public class Song {

    private File file;
    private File cover;

    /**
     * Constructors
     * */
    public Song(File file) {
        this(file, null);
    }
    public Song(File file, @Nullable File cover) {
        this.file = file;
        this.cover = cover;
    }

    /**
     * Getters
     * */
    public File getFile() {
        return file;
    }

    @Nullable
    public File getCover() {
        return cover;
    }

    /**
     * Setters
     * */
    public void setFile(File file) {
        this.file = file;
    }
    public void setCover(File cover) {
        this.cover = cover;
    }
}
