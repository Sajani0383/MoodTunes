package moodtunes.models;

public class Song {
    private String id;
    private String title;
    private String artist;
    private String mood;
    private int durationSeconds;

    public Song() {}

    public Song(String id, String title, String artist, String mood, int durationSeconds) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.mood = mood;
        this.durationSeconds = durationSeconds;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getMood() { return mood; }
    public int getDurationSeconds() { return durationSeconds; }

    @Override
    public String toString() {
        String dur = (durationSeconds > 0)
                ? String.format(" (%d:%02d)", durationSeconds / 60, durationSeconds % 60)
                : "";
        // ✅ Fix: Prevent out-of-bounds error
        String shortId = (id != null && id.length() >= 6) ? id.substring(0, 6) : id;
        if (shortId == null) shortId = "------";
        return title + " — " + artist + " [" + mood + "]" + dur + " (id: " + shortId + ")";
    }
}


