package moodtunes.models;

import java.util.*;

public class User {
    private String username;
    private String fullName;
    private String email;
    private Set<String> favorites;
    private List<String> recentPlayed;

    public User() {}

    public User(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.favorites = new HashSet<>();
        this.recentPlayed = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Set<String> getFavorites() { return favorites; }
    public List<String> getRecentPlayed() { return recentPlayed; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }

    public void addFavorite(String songId) { favorites.add(songId); }
    public void removeFavorite(String songId) { favorites.remove(songId); }

    public void addRecent(String songId) {
        recentPlayed.add(songId);
        if (recentPlayed.size() > 10) recentPlayed.remove(0);
    }
}

