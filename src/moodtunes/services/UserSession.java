package moodtunes.services;

import moodtunes.models.*;
import java.util.*;

public class UserSession {
    private final User user;
    private final MusicLibrary library;
    private final Map<String, Playlist> playlists = new LinkedHashMap<>();

    public UserSession(User user, MusicLibrary library) {
        this.user = user;
        this.library = library;
    }

    public void updateDetails(String fullName, String email) {
        if (!fullName.isBlank()) user.setFullName(fullName);
        if (!email.isBlank()) user.setEmail(email);
        System.out.println("✅ Personal details updated.");
    }

    public void recommend(String mood) {
        List<Song> list = library.songsByMood(mood);
        if (list.isEmpty()) {
            System.out.println("No songs found for mood: " + mood);
            return;
        }
        System.out.println("\n🎧 Recommended songs for mood (" + mood + "):");
        list.forEach(System.out::println);
    }

    public void playSong(String idPrefix) {
        Song s = library.getSongByPrefix(idPrefix);
        if (s == null) {
            System.out.println("❌ Song not found!");
            return;
        }
        System.out.println("▶ Now playing: " + s);
        user.addRecent(s.getId());
    }

    public void addFavorite(String idPrefix) {
        Song s = library.getSongByPrefix(idPrefix);
        if (s == null) {
            System.out.println("❌ Song not found!");
            return;
        }
        user.addFavorite(s.getId());
        System.out.println("❤️ Added to favorites: " + s.getTitle());
    }

    public void showFavorites() {
        if (user.getFavorites().isEmpty()) {
            System.out.println("No favorites yet.");
            return;
        }
        System.out.println("\n⭐ Your Favorites:");
        for (String id : user.getFavorites()) {
            Song s = library.getSongByPrefix(id);
            if (s != null) System.out.println(s);
        }
    }

    public void createPlaylist(String name) {
        Playlist p = new Playlist(UUID.randomUUID().toString(), name);
        playlists.put(p.getId(), p);
        System.out.println("🎶 Created playlist: " + name);
    }

    public void addSongToPlaylist(String playlistIdPrefix, String songIdPrefix) {
        Playlist p = findPlaylistByPrefix(playlistIdPrefix);
        Song s = library.getSongByPrefix(songIdPrefix);
        if (p == null || s == null) {
            System.out.println("❌ Playlist or song not found!");
            return;
        }
        p.addSong(s.getId());
        System.out.println("✅ Added " + s.getTitle() + " to playlist " + p.getName());
    }

    public void showPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists created.");
            return;
        }
        System.out.println("\n📂 Your Playlists:");
        for (Playlist p : playlists.values()) {
            System.out.println("- " + p.getName() + " (" + p.getSongIds().size() + " songs)");
        }
    }

    private Playlist findPlaylistByPrefix(String prefix) {
        for (Playlist p : playlists.values()) {
            if (p.getId().startsWith(prefix)) return p;
        }
        return null;
    }
}


