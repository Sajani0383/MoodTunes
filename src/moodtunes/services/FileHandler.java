package moodtunes.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import moodtunes.models.Song;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveSongs(String filePath, List<Song> songs) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            gson.toJson(songs, writer);
        } catch (IOException e) {
            System.out.println("Error saving songs: " + e.getMessage());
        }
    }

    public static List<Song> loadSongs(String filePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Type listType = new TypeToken<List<Song>>() {}.getType();
            List<Song> songs = gson.fromJson(reader, listType);
            return songs != null ? songs : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

