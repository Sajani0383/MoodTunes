package moodtunes.models;

import java.util.*;

public class Playlist {
    private String id;
    private String name;
    private List<String> songIds;

    public Playlist() {}

    public Playlist(String id, String name) {
        this.id = id;
        this.name = name;
        this.songIds = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getSongIds() { return songIds; }

    public void addSong(String songId) { songIds.add(songId); }
    public void removeSong(String songId) { songIds.remove(songId); }
}

