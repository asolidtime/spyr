package spyr.configStorage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import spyr.App;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConfigManager {
    ArrayList<Song> songJsonList;
    Type songJsonListType = new TypeToken<ArrayList<Song>>(){}.getType();
    public static String createDefaultConfig() {
        return "{'isDarkMode':false}";
    }
    public static String createTestSongsConfig() {
        return "[]";
    }
    public ConfigManager() {
        Gson gson = new Gson();
        try {
            songJsonList = gson.fromJson(new JsonReader(new FileReader(App.songsJson)), songJsonListType);
            System.out.println(songJsonList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addSongToJson(String songName, String songId) {
        if (!songJsonList.isEmpty()) {
            boolean foundSong = false;
            for (int songIndex = 0; songIndex < songJsonList.size(); songIndex++) {
                if (songJsonList.get(songIndex).youtubeId.equals(songId)) {
                    songJsonList.get(songIndex).timesListenedTo++;
                    foundSong = true;
                }
            }
            // could this be replaced with a break statement or something?
            if (!foundSong){
                songJsonList.add(new Song(
                        songName,
                        songId,
                        1
                ));
            }
        } else {
            songJsonList.add(new Song(
                    songName,
                    songId,
                    1
            ));
        }

        Gson gsonTest = new Gson();
        System.out.println("Current song JSON: " + gsonTest.toJson(songJsonList));
    }
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(songJsonList);
    }
    public String getTitle(int index) {
        return songJsonList.get(index).name;
    }
    public int getTimesListenedTo(int index) {
        return songJsonList.get(index).timesListenedTo;
    }
    public String getYoutubeId(int index) {
        return songJsonList.get(index).youtubeId;
    }
    public int getNumSongs() {
        return songJsonList.size();
    }
}
