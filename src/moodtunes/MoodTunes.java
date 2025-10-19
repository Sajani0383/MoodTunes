package moodtunes;
import moodtunes.models.*;
import moodtunes.services.*;
import java.util.*;

public class MoodTunes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dataFile = "data/songs.json";
        MusicLibrary library = new MusicLibrary(dataFile);

        System.out.println("🎵 Welcome to Mood Tunes 🎵");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Full name: ");
        String fullName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        User user = new User(username, fullName, email);
        UserSession session = new UserSession(user, library);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Update Personal Details");
            System.out.println("2. Recommend Songs by Mood");
            System.out.println("3. Play Song");
            System.out.println("4. Favorites (Add/View)");
            System.out.println("5. Playlists (Create/Add/View)");
            System.out.println("6. Admin: Add/Remove Songs");
            System.out.println("7. List All Songs");
            System.out.println("8. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("New full name (Enter to skip): ");
                    String fn = sc.nextLine();
                    System.out.print("New email (Enter to skip): ");
                    String em = sc.nextLine();
                    session.updateDetails(fn, em);
                }
                case "2" -> {
                    System.out.print("Enter mood (happy/sad/energetic/relaxed/romantic): ");
                    session.recommend(sc.nextLine());
                }
                case "3" -> {
                    System.out.print("Enter song ID prefix to play: ");
                    session.playSong(sc.nextLine());
                }
                case "4" -> {
                    System.out.println("a) Add Favorite  b) View Favorites");
                    String opt = sc.nextLine();
                    if (opt.equalsIgnoreCase("a")) {
                        System.out.print("Enter song ID prefix: ");
                        session.addFavorite(sc.nextLine());
                    } else {
                        session.showFavorites();
                    }
                }
                case "5" -> {
                    System.out.println("a) Create Playlist  b) Add Song  c) View Playlists");
                    String opt = sc.nextLine();
                    if (opt.equalsIgnoreCase("a")) {
                        System.out.print("Playlist name: ");
                        session.createPlaylist(sc.nextLine());
                    } else if (opt.equalsIgnoreCase("b")) {
                        System.out.print("Playlist ID prefix: ");
                        String pid = sc.nextLine();
                        System.out.print("Song ID prefix: ");
                        String sid = sc.nextLine();
                        session.addSongToPlaylist(pid, sid);
                    } else {
                        session.showPlaylists();
                    }
                }
                case "6" -> {
                    System.out.println("a) Add Song  b) Remove Song");
                    String opt = sc.nextLine();
                    if (opt.equalsIgnoreCase("a")) {
                        System.out.print("Title: ");
                        String t = sc.nextLine();
                        System.out.print("Artist: ");
                        String a = sc.nextLine();
                        System.out.print("Mood: ");
                        String m = sc.nextLine();
                        System.out.print("Duration (seconds): ");
                        int d;
                        try { d = Integer.parseInt(sc.nextLine()); } catch (Exception e) { d = 0; }
                        library.addSong(new Song(UUID.randomUUID().toString(), t, a, m, d));
                        System.out.println("✅ Song added.");
                    } else {
                        System.out.print("Enter song ID prefix to remove: ");
                        if (library.removeSong(sc.nextLine())) System.out.println("Removed.");
                        else System.out.println("No match found.");
                    }
                }
                case "7" -> library.allSongs().forEach(System.out::println);
                case "8" -> {
                    library.save();
                    System.out.println("💾 Data saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

