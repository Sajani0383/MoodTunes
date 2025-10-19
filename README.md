# 🎵 Mood Tunes — Java Music Recommendation System

**Mood Tunes** is a Java-based desktop application that recommends songs according to a user’s **mood**.  
It also lets users manage **playlists**, mark **favorites**, and update **personal details**.  
This project demonstrates strong use of **Object-Oriented Programming (OOP)** principles, **JSON file handling**, and a clean modular design.

---
## 👤 Author
Developed by: **Sajani G**

## ✨ Key Features

- 🧠 **Mood-based suggestions** – Happy, Sad, Energetic, Relaxed, Romantic  
- 👤 **User personalization** – username, full name, and email stored in session  
- 🎶 **Playlist management** – create, add, and view playlists  
- ❤️ **Favorites and recently played lists**  
- 💾 **Data persistence** with JSON using the Gson library  
- 🧱 **Modular code** in `models` and `services` packages  
- 🪶 **Simple console interface** – easy to use and understand  

---

## 🧩 Project Structure

MoodTunes/
├─ src/
│ └─ moodtunes/
│ ├─ MoodTunes.java ← Main program
│ ├─ models/
│ │ ├─ Song.java
│ │ ├─ User.java
│ │ └─ Playlist.java
│ └─ services/
│ ├─ MusicLibrary.java
│ ├─ UserSession.java
│ └─ FileHandler.java
├─ data/
│ └─ songs.json
├─ lib/
│ └─ gson-2.10.1.jar
├─ .gitignore
└─ README.md
