package moodtunes.services;

import moodtunes.models.Song;
import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;

public class MusicLibrary {
    private final Map<String, Song> songsById = new LinkedHashMap<>();
    private final String dataFilePath;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public MusicLibrary(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        load();
        if (songsById.isEmpty()) seedDefaultSongs();
    }

    private void load() {
        try (Reader reader = Files.newBufferedReader(Paths.get(dataFilePath))) {
            Type listType = new TypeToken<List<Song>>() {}.getType();
            List<Song> list = gson.fromJson(reader, listType);
            songsById.clear();
            if (list != null) for (Song s : list) songsById.put(s.getId(), s);
        } catch (IOException e) {
            System.out.println("(ℹ️) songs.json not found, will create one.");
        }
    }

    public void save() {
        try (Writer writer = Files.newBufferedWriter(Paths.get(dataFilePath))) {
            List<Song> list = new ArrayList<>(songsById.values());
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void addSong(Song s) {
        songsById.put(s.getId(), s);
        save();
    }

    public boolean removeSong(String idPrefix) {
        String fullId = findFullId(idPrefix);
        if (fullId == null) return false;
        songsById.remove(fullId);
        save();
        return true;
    }

    public List<Song> songsByMood(String mood) {
        return songsById.values().stream()
                .filter(s -> s.getMood().equalsIgnoreCase(mood))
                .collect(Collectors.toList());
    }

    public List<Song> allSongs() {
        return new ArrayList<>(songsById.values());
    }

    public Song getSongByPrefix(String prefix) {
        String id = findFullId(prefix);
        return id != null ? songsById.get(id) : null;
    }

    private String findFullId(String prefix) {
        for (String id : songsById.keySet()) {
            if (id.startsWith(prefix)) return id;
        }
        return null;
    }

    private void seedDefaultSongs() {
        addSong(new Song(UUID.randomUUID().toString(), "Happy", "Pharrell Williams", "happy", 233));
        addSong(new Song(UUID.randomUUID().toString(), "Fix You", "Coldplay", "sad", 295));
        addSong(new Song(UUID.randomUUID().toString(), "Believer", "Imagine Dragons", "energetic", 204));
        addSong(new Song(UUID.randomUUID().toString(), "Let It Be", "The Beatles", "relaxed", 243));
        addSong(new Song(UUID.randomUUID().toString(), "Perfect", "Ed Sheeran", "romantic", 263));
        System.out.println("(ℹ️) Default songs added.");
        save();
    }
}


